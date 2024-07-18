package gui;
import javax.swing.*;


import model.Customer;
import model.Hotel;
import exceptions.*;
import model.VIPCustomer;
import utils.Area;
import utils.Gender;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import exceptions.AlreadyExistsException;
import exceptions.DateFormatException;
import exceptions.EmptyFieldsException;
import exceptions.IllegalInputException;
import main.Main;
/**
 * Internal Jframe to add new Customer 
 * @author Amit Malik 207850074
 *
 */
public class AddCustomerFrame extends JInternalFrame {

    private JLabel fnameLabel, lnameLabel, phoneLabel, areaLabel, genderLabel, birthLabel;
    private JButton submitButton;
    private JComboBox<Area[]> areaComboBox;
    private JLabel lblHeadLine;
    private JTextField birthField;
    private JLabel dateOfJoinLabel;
    private JTextField dateOfJoinField;
    private JLabel discountLabel;
    private JLabel lblPercent;
    private JTextField discountField;
    private JTextField phoneField;
    private JTextField lnameField;
    private JTextField fnameField;
    private JTextField idField;
    private JComboBox <Gender[]> genderComboBox;
    private JLabel isVipLabel;
    private JCheckBox isVipCheckBox;

    public AddCustomerFrame() {
    	/*
    	 * Set InternalFrame :
    	 */	
    	getContentPane().setBackground(new Color(250, 250, 210));
        setTitle("Add New Customer");
        setSize(419, 536);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        /*
         * -Submit Button Actions-
         * This is what happens when Add customer/VIP customer is being pressed:
         */
        submitButton = new JButton("Add Customer");
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the values from the GUI components
                String id = idField.getText();
                String firstName = fnameField.getText();
                String lastName = lnameField.getText();
                String phoneNumber = phoneField.getText();
/**
 * Testing if form filled up correctly, if not, throws relevant exception:
 */             
                try {
               //Ensure all fields are not empty	
                	if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
                    throw new EmptyFieldsException("All contact fields must be filled!");
                	}
                
              //firstName and lastName must contain chars only and be no longer than 15.
                	if (!firstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+") || firstName.length() > 15 || lastName.length() > 15) {
                    throw new IllegalInputException ("First name and last name must contain letters only\n and be no longer than 15 characters.");                    
                	}

               //id must be 9 digits.
                	if (!id.matches("\\d{9}")) {
                		throw new IllegalInputException("ID must be 9 digits.");
                	}
                	if (Main.hotel.getAllCustomers().containsKey(id)) {
                        throw new AlreadyExistsException("Same ID Already Exist!\n Please choose another PK to create a new customer.");
                    }

                //phoneNumber must be 10 digits.
                	if (!phoneNumber.matches("\\d{10}")) {
                    throw new IllegalInputException( "Phone number must be 10 digits.");
                	}
                
               }                
                catch (EmptyFieldsException ex) {
               	 JOptionPane.showMessageDialog(AddCustomerFrame.this, ex.getMessage(), "Incomplete Information", JOptionPane.ERROR_MESSAGE);
               	 return;
               }
                catch (IllegalInputException ex) {
                  JOptionPane.showMessageDialog(AddCustomerFrame.this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                  return;
                } 
                catch (AlreadyExistsException ex) {
                    JOptionPane.showMessageDialog(AddCustomerFrame.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                
                Area area = (Area) areaComboBox.getSelectedItem();
                Gender gender = (Gender) genderComboBox.getSelectedItem();
                int yearOfBirth = 0;
                try {
                    yearOfBirth = Integer.parseInt(birthField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid year of birth.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return; // Stop the submission since parseInt() failed.
                }
                // Additional validation check: yearOfBirth- Restricts age to be between 18-93 years old
                try {
                if (yearOfBirth > 2006 || yearOfBirth < 1930) {
                    throw new IllegalInputException( "Invalid Year Of Birth!\nAge Can't be Under 18 - maximum year is 2016\n And minimum year is 1930");
                	}
                }
                catch (IllegalInputException ex) {
                	JOptionPane.showMessageDialog(AddCustomerFrame.this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                	return;
                }

                //Date Of joining must be after birth and in past or present:
                Date dateOfJoining = parseDate(dateOfJoinField.getText());
                try {
                if (dateOfJoining == null) {
                	throw new DateFormatException(); 
                	}
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
                    throw new IllegalInputException("Customer must be at least 18 years old at the time of joining.");
                	}
                }
                catch (DateFormatException ex)    {
       	    	 JOptionPane.showMessageDialog(AddCustomerFrame.this, ex.getMessage(), "Invalid Date", JOptionPane.ERROR_MESSAGE);
       	    	 return;        	    	 
                }
                catch (IllegalInputException ex)    {
          	    	 JOptionPane.showMessageDialog(AddCustomerFrame.this, ex.getMessage(), "Invalid Date", JOptionPane.ERROR_MESSAGE);
           	    	 return;   
                    }
               
    /**
     * Vip Customer Handling:           
     */
                if (isVipCheckBox.isSelected()) {
                    double discountPercentage = 0.0;
                    try {
                        discountPercentage = Double.parseDouble(discountField.getText());
                        if(discountPercentage>100||discountPercentage<0) {
                        	throw new IllegalInputException("Please enter Valid discount\n 0-100%");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid discount percentage.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return; // Stop the submission since parsing discountPercentage failed.
                    }
                    catch (IllegalInputException ex) {
                    JOptionPane.showMessageDialog(AddCustomerFrame.this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                    }
                    VIPCustomer newCustomer = new VIPCustomer(id, firstName, lastName, phoneNumber, area, gender, yearOfBirth, dateOfJoining, discountPercentage);
                    try {
                        Main.hotel.addVIPCustomer(newCustomer);
                    } catch (PersonAlreadyExistException e1) {                       
                    	JOptionPane.showMessageDialog(AddCustomerFrame.this, e1.getMessage(), "Duplicate Object", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Customer newCustomer = new Customer(id, firstName, lastName, phoneNumber, area, gender, yearOfBirth, dateOfJoining);
                    try {
                        Main.hotel.addCustomer(newCustomer);
                    } catch (PersonAlreadyExistException e1) {   
                        JOptionPane.showMessageDialog(AddCustomerFrame.this, e1.getMessage(), "Duplicate Object", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                clearForm();
                JOptionPane.showMessageDialog(null, "Customer added!");
            }
        });
        
        /*
         * Exit Button:
         */ 
        JButton btnExit = new JButton("");
        btnExit.setBorder(null);
        btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 9));
        btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
        
		/*
		* HeadLine:
		*/                                      
        lblHeadLine = new JLabel("To Add a new Customer Please Fill up the Form:");
        lblHeadLine.setFont(new Font("Tahoma", Font.BOLD, 13));
      
        
      //All InternalFrame components:   
               isVipLabel = new JLabel("Vip Customer:");
                                                
               isVipCheckBox = new JCheckBox("");
                                                
               isVipCheckBox.addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent e) {
                                                      
               boolean selected = e.getStateChange() == ItemEvent.SELECTED;
               discountLabel.setVisible(selected);
               discountField.setVisible(selected);
               lblPercent.setVisible(selected);
                 }
               });
                                        
                                        idField = new JTextField(10);
                                        idField.setColumns(10);
                                        fnameLabel = new JLabel("First Name:");
                                        
                                        fnameField = new JTextField();
                                        fnameField.setColumns(10);
                                        lnameLabel = new JLabel("Last Name:");
                                        
                                        lnameField = new JTextField();
                                        lnameField.setColumns(10);
                                        phoneLabel = new JLabel("Phone Number:");
                                        
                                        phoneField = new JTextField();
                                        phoneField.setColumns(10);
                                        areaLabel = new JLabel("Living Area:");
                                        Area[] area = {Area.Jerusalem,Area.Northern,Area.Haifa,Area.Central,Area.TelAviv,Area.Southern,Area.JudeaAndSamaria};
                                        areaComboBox = new JComboBox(area);
                                        genderLabel = new JLabel("Gender:");
                                        
                                        Gender[] gender = {Gender.M,Gender.F}; 
                                        genderComboBox = new JComboBox(gender);
                                        birthLabel = new JLabel("Year Of Birth:");
                                        
                                        birthField = new JTextField();
                                        birthField.setColumns(10);
                                        
                                        dateOfJoinLabel = new JLabel("Date Of Joining:");
                                        
                                        dateOfJoinField = new JTextField();
                                        dateOfJoinField.setColumns(10);
                                        
                                        discountLabel = new JLabel("VIP Discount Percentage:");
                                        discountLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
                                        
                                        lblPercent = new JLabel("%");
                                        lblPercent.setFont(new Font("Tahoma", Font.BOLD, 11));
                                        
                                        discountField = new JTextField();
                                        discountField.setColumns(10);
                                        
                                        discountLabel.setVisible(false);
                                        discountField.setVisible(false);
                                        lblPercent.setVisible(false);
                                       
                                       JLabel idLabel = new JLabel("ID:");
                                       
                                      
                                       GroupLayout groupLayout = new GroupLayout(getContentPane());
                                       groupLayout.setHorizontalGroup(
                                       	groupLayout.createParallelGroup(Alignment.TRAILING)
                                       		.addGroup(groupLayout.createSequentialGroup()
                                       			.addGap(54)
                                       			.addComponent(areaLabel, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                       			.addGap(298))
                                       		.addGroup(groupLayout.createSequentialGroup()
                                       			.addGap(63)
                                       			.addComponent(genderLabel, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                       			.addGap(307))
                                       		.addGroup(groupLayout.createSequentialGroup()
                                       			.addGap(150)
                                       			.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE)
                                       			.addGap(150))
                                       		.addGroup(groupLayout.createSequentialGroup()
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                       				.addGroup(groupLayout.createSequentialGroup()
                                       					.addGap(46)
                                       					.addComponent(lblHeadLine, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
                                       				.addGroup(groupLayout.createSequentialGroup()
                                       					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       						.addGroup(groupLayout.createSequentialGroup()
                                       							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       								.addGroup(groupLayout.createSequentialGroup()
                                       									.addGap(56)
                                       									.addComponent(lnameLabel, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                                       								.addGroup(groupLayout.createSequentialGroup()
                                       									.addGap(55)
                                       									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       										.addGroup(groupLayout.createSequentialGroup()
                                       											.addComponent(idLabel, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                       											.addPreferredGap(ComponentPlacement.RELATED))
                                       										.addComponent(fnameLabel, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))))
                                       							.addGap(45))
                                       						.addGroup(groupLayout.createSequentialGroup()
                                       							.addGap(45)
                                       							.addComponent(phoneLabel, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
                                       					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       						.addGroup(groupLayout.createSequentialGroup()
                                       							.addGap(34)
                                       							.addComponent(areaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                       							.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE))
                                       						.addGroup(groupLayout.createSequentialGroup()
                                       							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       								.addComponent(lnameField, 167, 167, 167)
                                       								.addComponent(fnameField, 167, 167, 167)
                                       								.addComponent(idField, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                       								.addComponent(phoneField, 167, 167, 167))
                                       							.addGap(54))
                                       						.addGroup(groupLayout.createSequentialGroup()
                                       							.addGap(44)
                                       							.addComponent(genderComboBox, 0, 39, Short.MAX_VALUE)
                                       							.addGap(138))))
                                       				.addGroup(groupLayout.createSequentialGroup()
                                       					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                       						.addGroup(groupLayout.createSequentialGroup()
                                       							.addGap(44)
                                       							.addComponent(dateOfJoinLabel, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                       							.addGap(33)
                                       							.addComponent(dateOfJoinField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                                       						.addGroup(groupLayout.createSequentialGroup()
                                       							.addGap(50)
                                       							.addComponent(birthLabel, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                       							.addGap(39)
                                       							.addComponent(birthField, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
                                       					.addGap(54)))
                                       			.addGap(30))
                                       		.addGroup(groupLayout.createSequentialGroup()
                                       			.addGap(49)
                                       			.addComponent(isVipLabel, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                       			.addGap(70)
                                       			.addComponent(isVipCheckBox)
                                       			.addGap(202))
                                       		.addGroup(groupLayout.createSequentialGroup()
                                       			.addGap(13)
                                       			.addComponent(discountLabel, GroupLayout.PREFERRED_SIZE, 147, Short.MAX_VALUE)
                                       			.addPreferredGap(ComponentPlacement.UNRELATED)
                                       			.addComponent(discountField, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                       			.addPreferredGap(ComponentPlacement.RELATED)
                                       			.addComponent(lblPercent, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                       			.addGap(164))
                                       		.addGroup(groupLayout.createSequentialGroup()
                                       			.addContainerGap(369, Short.MAX_VALUE)
                                       			.addComponent(btnExit)
                                       			.addGap(21))
                                       );
                                       groupLayout.setVerticalGroup(
                                       	groupLayout.createParallelGroup(Alignment.LEADING)
                                       		.addGroup(groupLayout.createSequentialGroup()
                                       			.addContainerGap()
                                       			.addComponent(btnExit)
                                       			.addPreferredGap(ComponentPlacement.RELATED)
                                       			.addComponent(lblHeadLine, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                                       			.addGap(7)
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                       				.addGroup(groupLayout.createSequentialGroup()
                                       					.addComponent(isVipLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                       					.addGap(38))
                                       				.addGroup(groupLayout.createSequentialGroup()
                                       					.addComponent(isVipCheckBox)
                                       					.addPreferredGap(ComponentPlacement.RELATED)))
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       				.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                       				.addComponent(idLabel))
                                       			.addGap(5)
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       				.addComponent(fnameLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                       				.addComponent(fnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                       			.addGap(5)
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       				.addComponent(lnameLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                       				.addComponent(lnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                       			.addGap(5)
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       				.addComponent(phoneLabel, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                       				.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                       			.addGap(5)
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       				.addComponent(areaLabel, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                       				.addComponent(areaComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                       			.addGap(5)
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       				.addComponent(genderLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                       				.addComponent(genderComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                       			.addGap(5)
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       				.addComponent(birthLabel, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                       				.addComponent(birthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                       			.addGap(8)
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       				.addComponent(dateOfJoinLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                       				.addComponent(dateOfJoinField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                       			.addGap(41)
                                       			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                       				.addComponent(discountLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                       				.addComponent(discountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                       				.addComponent(lblPercent))
                                       			.addGap(38)
                                       			.addComponent(submitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                       			.addGap(88))
                                       );
                                       getContentPane().setLayout(groupLayout);
        
                               
                                       
       
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
     * Clears the form fields
     */
    private void clearForm() {
        idField.setText("");
        fnameField.setText("");
        lnameField.setText("");
        phoneField.setText("");
        areaComboBox.setSelectedIndex(0); // Set the default selection for the combo box
        genderComboBox.setSelectedIndex(0); // Set the default selection for the combo box
        birthField.setText("");
        dateOfJoinField.setText("");
        discountField.setText("");
        isVipCheckBox.setSelected(false);
    }
}




