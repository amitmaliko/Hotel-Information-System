package gui;
import javax.swing.*;

import utils.Specialization;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;

import exceptions.AlreadyExistsException;
import exceptions.EmptyFieldsException;
import exceptions.IllegalInputException;
import main.Main;
import model.Department;
import model.Hotel;
/**
 * Internal Jframe to add new Department 
 * @author Amit Malik 207850074
 *
 */
public class AddDepartmentFrame extends JInternalFrame {

    private JLabel depIDLabel, specLabel;
    private JTextField depIDField;
    private JButton submitButton;
    private JLabel lblHeadLine;
    private  JComboBox <Specialization[]> specComboBox;

    public AddDepartmentFrame() {
    	
    	getContentPane().setBackground(new Color(144, 238, 144));
        setTitle("Add New Department");
        setSize(419, 473);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                
        /*
         * -Submit Button Actions-
         * This is what happens when Add Department is being pressed:
         */
                                        submitButton = new JButton("Add Department");
                                        submitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
                                        submitButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                // Get data from fields
                                                String departmentId = depIDField.getText();
                                                Specialization specialization = (Specialization) specComboBox.getSelectedItem();

                                                try {
                                                    // Check if null
                                                    if (departmentId.isEmpty() || specialization == null) {
                                                        throw new EmptyFieldsException("All fields must be filled!");
                                                    }
                                                    //Decided to give free typed Identifier of 5 chars long
                                                    if (departmentId.length() > 5) {
                                                        throw new IllegalInputException("Department ID must not exceed 5 characters");
                                                    }
                                                    //Check if Identifier already exists:
                                                    if (Main.hotel.getAllDepartments().containsKey(departmentId)) {
                                                        throw new AlreadyExistsException("Same ID Already Exist!\n Please choose another PK to create a new department.");
                                                    }

                                                } catch (EmptyFieldsException ex) {
                                                    JOptionPane.showMessageDialog(AddDepartmentFrame.this, ex.getMessage(), "Incomplete Information", JOptionPane.ERROR_MESSAGE);
                                                    return;
                                                } 
                                            	catch (IllegalInputException ex) {
                                                JOptionPane.showMessageDialog(AddDepartmentFrame.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                                return;
                                            	} catch (AlreadyExistsException ex) {
                                                    JOptionPane.showMessageDialog(AddDepartmentFrame.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                                    return;
                                                }

                                                // All conditions passed, call the Department constructor
                                                Department newDepartment = new Department(departmentId, specialization);

                                                // Save Department to hotel database:
                                                Main.hotel.addDepartment(newDepartment);

                                                // Clear the fields after successful submission:
                                                clearForm();
                                                JOptionPane.showMessageDialog(null, "Department added!");
                                            }
                                        });
                                        
                                        JButton btnExit = new JButton("");
                                        btnExit.setBorder(null);
                                        btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
                                        btnExit.setFont(new Font("Tahoma", Font.BOLD, 9));
                                        btnExit.addActionListener(new ActionListener() {
                                			public void actionPerformed(ActionEvent e) {
                                				dispose();	
                                			}
                                		});
                                                
        
        lblHeadLine = new JLabel("To Add a new Department Please Fill up the Form:");
        lblHeadLine.setFont(new Font("Tahoma", Font.BOLD, 13));
                                        
                                                depIDLabel = new JLabel("Department ID:");
                                        depIDField = new JTextField();
                                        specLabel = new JLabel("Specialization:");
                                        
                                        Specialization[] spec = {Specialization.Finance,Specialization.Advertisement,Specialization.Logistics,Specialization.Administration,Specialization.costumerService};
                                        specComboBox = new JComboBox(spec);
                                        Integer[] population = {1,2};
                                        GroupLayout groupLayout = new GroupLayout(getContentPane());
                                        groupLayout.setHorizontalGroup(
                                        	groupLayout.createParallelGroup(Alignment.TRAILING)
                                        		.addGroup(groupLayout.createSequentialGroup()
                                        			.addGap(25)
                                        			.addComponent(lblHeadLine, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                                        			.addGap(51))
                                        		.addGroup(groupLayout.createSequentialGroup()
                                        			.addGap(45)
                                        			.addComponent(depIDLabel, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                        			.addGap(50)
                                        			.addComponent(depIDField, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                        			.addGap(26))
                                        		.addGroup(groupLayout.createSequentialGroup()
                                        			.addGap(49)
                                        			.addComponent(specLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        			.addGap(54)
                                        			.addComponent(specComboBox, 0, 212, Short.MAX_VALUE)
                                        			.addGap(26))
                                        		.addGroup(groupLayout.createSequentialGroup()
                                        			.addContainerGap(378, Short.MAX_VALUE)
                                        			.addComponent(btnExit)
                                        			.addContainerGap())
                                        		.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                        			.addGap(127)
                                        			.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
                                        			.addContainerGap(130, Short.MAX_VALUE))
                                        );
                                        groupLayout.setVerticalGroup(
                                        	groupLayout.createParallelGroup(Alignment.LEADING)
                                        		.addGroup(groupLayout.createSequentialGroup()
                                        			.addContainerGap()
                                        			.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                        			.addGap(24)
                                        			.addComponent(lblHeadLine, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                                        			.addGap(35)
                                        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        				.addComponent(depIDLabel, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                        				.addComponent(depIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        			.addGap(5)
                                        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        				.addComponent(specLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        				.addComponent(specComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        			.addGap(129)
                                        			.addComponent(submitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        			.addGap(144))
                                        );
                                        getContentPane().setLayout(groupLayout);
        
   
                                        

    }
    
    /**
     * Clears the form fields
     */
    public void clearForm() {
    	 depIDField.setText("");
         specComboBox.setSelectedIndex(0);
    }


}




