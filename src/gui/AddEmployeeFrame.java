package gui;
import javax.swing.*;

import utils.Area;
import utils.Gender;
import utils.Specialization;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import exceptions.AlreadyExistsException;
import exceptions.DateFormatException;
import exceptions.EmptyFieldsException;
import exceptions.IllegalInputException;
import main.Main;
import model.Department;
import model.DepartmentManager;
import model.Employee;
import model.Hotel;
import exceptions.*;
import model.Room;
/**
 * Internal Jframe to add new Employee 
 * @author Amit Malik 207850074
 *
 */
public class AddEmployeeFrame extends JInternalFrame {

    private JLabel idLabel, fnameLabel, lnameLabel, phoneLabel, areaLabel, genderLabel, birthLabel;
    private JButton submitButton;
    private JComboBox<Area[]> areaComboBox;
    private JLabel lblHeadLine;
    private JTextField birthField;
    private JLabel dateOfJoinLabel;
    private JTextField dateOfJoinField;
    private JTextField phoneField;
    private JTextField lnameField;
    private JTextField fnameField;
    private JTextField idField;
    private JComboBox <Gender[]>genderComboBox;
    private JLabel salaryLabel;
    private JTextField salaryField;
    private JLabel depLabel;
    private JComboBox depComboBox;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JButton btnManagerSubmitButton;
    private JLabel bonusLabel;
    private JTextField bonusField;
    private JLabel isDepLabel;
    private JCheckBox isDepManagerCheckBox;
    private JLabel pictureLabel;
    private DepartmentManager newDepartmentManager;
    private Employee newEmployee;
    private String picturePath;

    public AddEmployeeFrame() {
    	
    	
    	getContentPane().setBackground(new Color(95, 158, 160));
        setTitle("Add New Employee");
        setSize(491, 593);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Needs Map of all existing departments to create employee:
        HashMap<String, Department> departmentsHashMap = Main.hotel.getAllDepartments();
        
        
        
        /*
         * -Submit Button Actions-
         * This is what happens when Add Employee is being pressed:
         */
        submitButton = new JButton("Add Employee");
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        
        btnManagerSubmitButton = new JButton("Add Department Manager");
        btnManagerSubmitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
       
        ActionListener submitActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get data from fields
                String id = idField.getText();
                String firstName = fnameField.getText();
                String lastName = lnameField.getText();
                String phoneNumber = phoneField.getText();
                Area area = (Area) areaComboBox.getSelectedItem();
                Gender gender = (Gender) genderComboBox.getSelectedItem();
                int yearOfBirth = 0;
                double salary = 0.0;
                double bonus = 0.0;
                Date dateOfJoining = parseDate(dateOfJoinField.getText());
                String password = passwordField.getText();
                String departmentID = (String) depComboBox.getSelectedItem();
                Department department=departmentsHashMap.get(departmentID);

                // Check year is integer
               try {  
                    yearOfBirth = Integer.parseInt(birthField.getText());
                	} catch (NumberFormatException ex) {
                		JOptionPane.showMessageDialog(null, "Please enter a valid year of birth.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    	return; 
                		}
               // Checks that all fields are filled
               try {
                	if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() ||
                        password.isEmpty()) {
                	throw new EmptyFieldsException("All fields must be filled!");
                		}
                	//Restricts age to be between 18-93 years old
                	if (yearOfBirth < 1930 || yearOfBirth > 2006) {
                	throw new IllegalInputException( "Invalid Year Of Birth!\nAge Can't be Under 18 - maximum year is 2016\n And minimum year is 1930");
                		}
                	//checks that phone number is 10 digits (only)
                	if (!phoneNumber.matches("\\d{10}")) {
                        throw new IllegalInputException( "Phone number must be 10 digits.");
                    	}
                	//checks that Name contains only Letters
                	if (!firstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+") || firstName.length() > 15 || lastName.length() > 15) {
                        throw new IllegalInputException ("First name and last name must contain letters only\n and be no longer than 15 characters.");                    
                    	}
                	//checks that ID is 9 digits (only)
                	if (!id.matches("\\d{9}")) {
                		throw new IllegalInputException("ID must be 9 digits.");
                	}
                	//checks for duplicate IDs
                	if (Main.hotel.getAllEmployees().containsKey(id)) {
                        throw new AlreadyExistsException("Same ID Already Exist!\n Please choose another PK to create a new Employee.");
                    }
                	//if Parse Date is failed 
                	if (dateOfJoining==null) {
                		throw new DateFormatException(); 
                	}
                	//Checks that salary and bonus are double
                	try {
                        salary = Double.parseDouble(salaryField.getText());
                        if (isDepManagerCheckBox.isSelected()) {
                            bonus = Double.parseDouble(bonusField.getText());
                        }
                    } catch (NumberFormatException ex) {
                        // Handle the case when the salary or bonus fields contain invalid numbers
                        JOptionPane.showMessageDialog(AddEmployeeFrame.this, "Salary and bonus must contain valid numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                	//Gets today's data and ensure that Joining date is not in future
                	Date today = new Date();
                    if (dateOfJoining.after(today)) {
                        throw new IllegalInputException("Date of joining cannot be in the future.");
                     	}
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateOfJoining);
                    int joiningYear = calendar.get(Calendar.YEAR);  //Get Year of joining from joining Date
                    int ageDifference = joiningYear - yearOfBirth;  //We want to check if the customer joining year is before birthday or not after 18 years.
                    if (joiningYear < yearOfBirth) {
                        throw new IllegalInputException("Date of joining year cannot be before the year of birth.");
                    	} 
                    if (ageDifference < 18) {
                        throw new IllegalInputException("Employee must be at least 18 years old at the time of joining.");
                        
                    	}
                
                } catch (EmptyFieldsException ex) {
                	JOptionPane.showMessageDialog(AddEmployeeFrame.this, ex.getMessage(), "Incomplete Information", JOptionPane.ERROR_MESSAGE);
                  	 return;
                } catch (IllegalInputException ex)    {
          	    	 JOptionPane.showMessageDialog(AddEmployeeFrame.this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
           	    	 return;   
                } catch (DateFormatException ex)    {
          	    	 JOptionPane.showMessageDialog(AddEmployeeFrame.this, ex.getMessage(), "Invalid Date", JOptionPane.ERROR_MESSAGE);
           	    	 return;        	    	 
                } catch (AlreadyExistsException ex) {
                   JOptionPane.showMessageDialog(AddEmployeeFrame.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }
                
                
                	String dateOfJoin = dateOfJoinField.getText();   // we need String Date for Employees constructors
                	
                // All conditions passed, now create the object
                	//If Department Manager is chosen create new Department manager:
                if (isDepManagerCheckBox.isSelected()) {
                		try {
                			//ensures Department has'nt already have a manager
                			if(department.getDepManager()!=null)
                			throw new IllegalInputException("Chosen Department Has already have Manager!");
                			
                			} catch (IllegalInputException ex)    {
                			  JOptionPane.showMessageDialog(AddEmployeeFrame.this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                			  return;
                			}
                		//Creates New Department Manager Object and sets his profile picture
                     newDepartmentManager = new DepartmentManager(id, firstName, lastName, phoneNumber, area, gender,
                            yearOfBirth, dateOfJoin, salary, department, password, bonus);
                     if (picturePath != null) {
                    	 newDepartmentManager.setPicturePath(picturePath);
                     }
                     // trying to add new manager to hotel:
                    try {
                        Main.hotel.addDepartmentManager(newDepartmentManager);
                    } catch (PersonAlreadyExistException e1) {
                    	JOptionPane.showMessageDialog(AddEmployeeFrame.this, e1.getMessage(), "Duplicate Object", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    clearForm();
                    JOptionPane.showMessageDialog(null, "Department Manager Added To Hotel!");
                    
                  //If Department Manager is NOT chosen create new Employee (same as earlier):
                } else {
                     newEmployee = new Employee(id, firstName, lastName, phoneNumber, area, gender,
                            yearOfBirth, dateOfJoin, salary, department, password);
                     if (picturePath != null) {
                         newEmployee.setPicturePath(picturePath);
                     }
                    try {
                        Main.hotel.addEmployee(newEmployee);
                    } catch (PersonAlreadyExistException e1) {
                    	JOptionPane.showMessageDialog(AddEmployeeFrame.this, e1.getMessage(), "Duplicate Object", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    clearForm();
                    JOptionPane.showMessageDialog(null, "Worker Added To Hotel!");
                }}};
        
        
        //There are 2 "Submit" buttons which appears according to selection (manager or not) that triggers the same action listener:
                submitButton.addActionListener(submitActionListener);
                btnManagerSubmitButton.addActionListener(submitActionListener);
        
        
        
        JButton btnExit = new JButton("");
        btnExit.setBorder(null);
        btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 9));
        btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
        
        JButton uploadPictureButton = new JButton("Upload Picture");
        uploadPictureButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        uploadPictureButton.setForeground(Color.BLUE);
        
             
        uploadPictureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(AddEmployeeFrame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    picturePath = selectedFile.getAbsolutePath();

                    // Load the original image from the file
                    ImageIcon originalIcon = new ImageIcon(picturePath);
                    Image originalImage = originalIcon.getImage();

                    // Create a scaled version of the image with the constant size
                    Image scaledImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

                    // Set the scaled image as the icon for the label
                    pictureLabel.setIcon(new ImageIcon(scaledImage));
                }
            }
        });

                                                
        
        lblHeadLine = new JLabel("To Add a new Employee Please Fill up the Form:");
        lblHeadLine.setFont(new Font("Tahoma", Font.BOLD, 13));
                                        
                                        idLabel = new JLabel("ID:");
                                        
                                        idField = new JTextField();
                                        idField.setColumns(10);
                                        fnameLabel = new JLabel("First Name:");
                                        
                                        fnameField = new JTextField();
                                        fnameField.setColumns(10);
                                        lnameLabel = new JLabel("Last Name:");
                                        
                                        lnameField = new JTextField();
                                        lnameField.setColumns(10);
                                        phoneLabel = new JLabel("Phone Number:");
                                        Area[] area = {Area.Jerusalem,Area.Northern,Area.Haifa,Area.Central,Area.TelAviv,Area.Southern,Area.JudeaAndSamaria};
                                        
                                        phoneField = new JTextField();
                                        phoneField.setColumns(10);
                                        areaLabel = new JLabel("Living Area:");
                                        
                                        Gender[] gender = {Gender.M,Gender.F}; 
                                        areaComboBox = new JComboBox(area);
                                        genderLabel = new JLabel("Gender:");
                                        genderComboBox = new JComboBox(gender);
                                        birthLabel = new JLabel("Year Of Birth (yyyy):");
                                        
                                        birthField = new JTextField();
                                        birthField.setColumns(10);
                                        
                                        dateOfJoinLabel = new JLabel("Date Of Joining (dd/mm/yyyy):");
                                        
                                        dateOfJoinField = new JTextField();
                                        dateOfJoinField.setColumns(10);
                                        
                                        depLabel = new JLabel("Department ID:");
                                        depComboBox = new JComboBox();
                                        for (String key : departmentsHashMap.keySet()) {
                                        	depComboBox.addItem(key);
                                        }
                                        
                                        
                                        isDepLabel = new JLabel("Department Manager");
                                        isDepLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
                                        
                                        isDepManagerCheckBox = new JCheckBox("");
                                        
                                        
                                        isDepManagerCheckBox.addItemListener(new ItemListener() {                                            
                                            public void itemStateChanged(ItemEvent e) {
                                                boolean selected = e.getStateChange() == ItemEvent.SELECTED;
                                                bonusLabel.setVisible(selected);
                                                bonusField.setVisible(selected);
                                                btnManagerSubmitButton.setVisible(selected);
                                                submitButton.setVisible(!selected);
                                            }
                                        });
                                        
                                        
                                        salaryLabel = new JLabel("Salary:");
                                                        

                                                                        
                                                                        salaryField = new JTextField();
                                                                        salaryField.setText("");
                                                                        salaryField.setColumns(10);
                                                                        
                                                                        bonusLabel = new JLabel("Bonus:");
                                                                        bonusLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
                                                                        bonusLabel.setForeground(new Color(0, 0, 0));
                                                                        
                                                                        bonusField = new JTextField();
                                                                        bonusField.setColumns(10);
                                                                        
                                                                        passwordLabel = new JLabel("Login Passoword:");
                                                                        
                                                                        passwordField = new JTextField();
                                                                        passwordField.setColumns(10);
                                                                        
                                                                        pictureLabel = new JLabel("Employee Picture");
                                                                        pictureLabel.setFont(new Font("MS PGothic", Font.BOLD, 11));
                                                                        
                                                                        
                                                                        
                                                                     
                                                                        GroupLayout groupLayout = new GroupLayout(getContentPane());
                                                                        groupLayout.setHorizontalGroup(
                                                                        	groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGap(75)
                                                                        			.addComponent(idLabel)
                                                                        			.addGap(133)
                                                                        			.addComponent(idField)
                                                                        			.addGap(163))
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGap(55)
                                                                        			.addComponent(fnameLabel, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                                                        			.addGap(113)
                                                                        			.addComponent(fnameField)
                                                                        			.addGap(163))
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGap(56)
                                                                        			.addComponent(lnameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        			.addGap(113)
                                                                        			.addComponent(lnameField, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                                                        			.addGap(163))
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGap(45)
                                                                        			.addComponent(phoneLabel, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                                                        			.addGap(102)
                                                                        			.addComponent(phoneField)
                                                                        			.addGap(163))
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGap(54)
                                                                        			.addComponent(areaLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        			.addGap(99)
                                                                        			.addComponent(areaComboBox, 0, 112, Short.MAX_VALUE)
                                                                        			.addGap(160))
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGap(63)
                                                                        			.addComponent(genderLabel, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                                                        			.addGap(134)
                                                                        			.addComponent(genderComboBox, 0, 48, Short.MAX_VALUE)
                                                                        			.addGap(186))
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGap(31)
                                                                        			.addComponent(birthLabel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                                                        			.addGap(75)
                                                                        			.addComponent(birthField)
                                                                        			.addGap(163))
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGap(18)
                                                                        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                        				.addGroup(groupLayout.createSequentialGroup()
                                                                        					.addComponent(dateOfJoinLabel)
                                                                        					.addGap(56)
                                                                        					.addComponent(dateOfJoinField)
                                                                        					.addGap(163))
                                                                        				.addGroup(groupLayout.createSequentialGroup()
                                                                        					.addGap(9)
                                                                        					.addComponent(isDepLabel, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                                                        					.addGap(75)
                                                                        					.addComponent(isDepManagerCheckBox)
                                                                        					.addGap(212))
                                                                        				.addGroup(groupLayout.createSequentialGroup()
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                        						.addGroup(groupLayout.createSequentialGroup()
                                                                        							.addComponent(lblHeadLine, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                                                        							.addGap(103)
                                                                        							.addComponent(btnExit))
                                                                        						.addGroup(groupLayout.createSequentialGroup()
                                                                        							.addGap(168)
                                                                        							.addComponent(uploadPictureButton))
                                                                        						.addComponent(pictureLabel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                                                        					.addGap(23))))
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGap(52)
                                                                        			.addComponent(depLabel, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                                                        			.addGap(101)
                                                                        			.addComponent(depComboBox, 0, 89, Short.MAX_VALUE)
                                                                        			.addGap(164))
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGap(52)
                                                                        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        				.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
                                                                        				.addComponent(btnManagerSubmitButton, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
                                                                        				.addGroup(groupLayout.createSequentialGroup()
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addComponent(bonusLabel, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                                                        						.addComponent(passwordLabel, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                                                        						.addComponent(salaryLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
                                                                        					.addGap(18)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addComponent(salaryField, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                                        						.addComponent(bonusField, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                                        						.addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                                                        					.addGap(171)))
                                                                        			.addContainerGap())
                                                                        );
                                                                        groupLayout.setVerticalGroup(
                                                                        	groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        		.addGroup(groupLayout.createSequentialGroup()
                                                                        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                        				.addGroup(groupLayout.createSequentialGroup()
                                                                        					.addGap(20)
                                                                        					.addComponent(lblHeadLine, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                                                                        					.addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                        						.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                        					.addGap(5)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addComponent(fnameLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                        						.addComponent(fnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                        					.addGap(5)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addComponent(lnameLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                        						.addComponent(lnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                        					.addGap(5)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addComponent(phoneLabel, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                                                        						.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                        					.addGap(5)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addComponent(areaLabel, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                                                        						.addComponent(areaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                        					.addGap(5)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addComponent(genderLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                        						.addComponent(genderComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                        					.addGap(5)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                                        						.addComponent(birthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        						.addComponent(birthLabel, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                                                                        					.addPreferredGap(ComponentPlacement.RELATED)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addComponent(dateOfJoinField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        						.addComponent(dateOfJoinLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
                                                                        					.addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addGroup(groupLayout.createSequentialGroup()
                                                                        							.addGap(3)
                                                                        							.addComponent(depLabel, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                                                        							.addGap(3))
                                                                        						.addComponent(depComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                        					.addGap(18)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        						.addComponent(isDepLabel, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                                                                        						.addComponent(isDepManagerCheckBox))
                                                                        					.addPreferredGap(ComponentPlacement.RELATED)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        						.addComponent(salaryField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        						.addComponent(salaryLabel))
                                                                        					.addPreferredGap(ComponentPlacement.RELATED)
                                                                        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        						.addComponent(bonusField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                        						.addComponent(bonusLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        						.addComponent(pictureLabel, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)))
                                                                        				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                                                        					.addContainerGap()
                                                                        					.addComponent(btnExit)))
                                                                        			.addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        				.addComponent(passwordLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        				.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        				.addComponent(uploadPictureButton))
                                                                        			.addGap(59)
                                                                        			.addComponent(submitButton)
                                                                        			.addPreferredGap(ComponentPlacement.RELATED)
                                                                        			.addComponent(btnManagerSubmitButton)
                                                                        			.addGap(34))
                                                                        );
                                                                        getContentPane().setLayout(groupLayout);
                                                                        btnManagerSubmitButton.setVisible(false);
        
                                                                        
                                                                        bonusLabel.setVisible(false);
                                                                        bonusField.setVisible(false);
                                                                        
                                                                        
                                                                        
                                                                    
                                                                     
                                                                        
       
    }
    
    /**
    * Functions:
    */
           /**
            * 
            * @param dateString the input date.
            * @return parsed Date<br>
            * If failed to parse (due to invalid format for example) returns null
            */  
    private Date parseDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        dateFormat.setLenient(false);
		try {
			Date parsedDate = dateFormat.parse(dateString);
			return parsedDate;
		} catch (ParseException e) {
		}
        return null;
    }

    /**
     * 
     * @param string from form
     * Checks if a valid number
     */
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
        	SoundFile.uhoh();
            return false;
        }
    }
    
    /**
     * Clears the form fields
     */
    public void clearForm() {   
         idField.setText("");
         fnameField.setText("");
         lnameField.setText("");
         phoneField.setText("");
         areaComboBox.setSelectedIndex(0);
         genderComboBox.setSelectedIndex(0);
         birthField.setText("");
         dateOfJoinField.setText("");
         salaryField.setText("");
         depComboBox.setSelectedIndex(0);
         passwordField.setText("");
         bonusField.setText("");
         isDepManagerCheckBox.setSelected(false);
         pictureLabel.setIcon(null);
    
    }
}




