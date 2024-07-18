package gui;
import javax.swing.*;

import model.Hotel;
import exceptions.*;
import model.StandardRoom;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.MatteBorder;

import exceptions.IllegalInputException;
import main.Main;
/**
 * Internal Jframe to add new Standard Room 
 * @author Amit Malik 207850074
 *
 */
public class AddStandardRoomFrame extends JInternalFrame {

    private JLabel dailyPriceLabel, floorLabel, avgDailyCostLabel, gradeLabel, maxPopulationLabel, sizeLabel, viewLabel;
    private JTextField dailyPriceField, avgDailyCostField, gradeField, sizeField;
    private JCheckBox viewCheckbox;
    private JButton submitButton;
    private JComboBox<Integer> comboBox;
    private JLabel lblHeadLine;
    private JComboBox<Integer[]> floorBox;
    

    public AddStandardRoomFrame() {
    	/*
    	 * Set InternalFrame :
    	 */
    	getContentPane().setBackground(new Color(245, 255, 250));
    	
        setTitle("Add New Standard Room");
        setSize(435, 473);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{171, 94, 0};
        gridBagLayout.rowHeights = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
                                
        
        /*
         * -Submit Button Actions-
         * This is what happens when Add Standard Room is being pressed:
         */
        submitButton = new JButton("Add Standard Room");
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the data from the fields
            double dailyPrice = 0.0;
            int floor = 0;
            double avgDailyCost = 0.0;
            double roomGrade = 0.0;
            int maxPopulationCapacity = 0;
            int size = 0;
            boolean hasView = viewCheckbox.isSelected();

            //Ensure all numeric fields are not empty and contain valid data
            try {
                dailyPrice = Double.parseDouble(dailyPriceField.getText());
                floor = (int) floorBox.getSelectedItem();
                avgDailyCost = Double.parseDouble(avgDailyCostField.getText());
                roomGrade = Double.parseDouble(gradeField.getText());
                maxPopulationCapacity = (int) comboBox.getSelectedItem();
                size = Integer.parseInt(sizeField.getText());
                
                // Additional validation checks: Ensure all data is positive
                if (dailyPrice <= 0 || floor <= 0 || avgDailyCost <= 0 || roomGrade <= 0 || maxPopulationCapacity <= 0 || size <= 0) {
                    throw new IllegalInputException("Please enter valid positive numeric values for all room data.");
                }
                //ensure that room grade is not larger than 10
                if (roomGrade>10) {
                	throw new IllegalInputException("Please Rate room in scale between 1-10");	
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numeric values for the room data.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return; // Stop the submission since parsing numeric values failed.
            } catch (IllegalInputException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return; // Stop the submission since some data is not positive or grade is not in range
            }

            //creates new StandardRoom object:
            StandardRoom newRoom = new StandardRoom(dailyPrice, floor, avgDailyCost, roomGrade, maxPopulationCapacity, size, hasView);
            //adds room to hotel:
            try {
                Main.hotel.addStandardRoom(newRoom);
            } catch (MaxPopulationCapacityException e1) {
            	JOptionPane.showMessageDialog(AddStandardRoomFrame.this, e1.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }

            clearForm();
            JOptionPane.showMessageDialog(null, "Room added!");
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
	  GridBagConstraints gbc_btnExit = new GridBagConstraints();
	  gbc_btnExit.insets = new Insets(0, 0, 5, 0);
	  gbc_btnExit.anchor = GridBagConstraints.EAST;
	  gbc_btnExit.gridx = 2;
	  gbc_btnExit.gridy = 0;
	  getContentPane().add(btnExit, gbc_btnExit);
                                                
        /*
         * HeadLine:
         */
        lblHeadLine = new JLabel("To Add a new Standard Room Please Fill up the Form:");
        lblHeadLine.setFont(new Font("Tahoma", Font.BOLD, 13));
        GridBagConstraints gbc_lblHeadLine = new GridBagConstraints();
        gbc_lblHeadLine.gridwidth = 2;
        gbc_lblHeadLine.insets = new Insets(0, 0, 5, 5);
        gbc_lblHeadLine.fill = GridBagConstraints.VERTICAL;
        gbc_lblHeadLine.gridx = 0;
        gbc_lblHeadLine.gridy = 2;
        getContentPane().add(lblHeadLine, gbc_lblHeadLine);
        
        //All InternalFrame components:
                                        
                                        dailyPriceLabel = new JLabel("Daily Price:");
                                        GridBagConstraints gbc_dailyPriceLabel = new GridBagConstraints();
                                        gbc_dailyPriceLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_dailyPriceLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_dailyPriceLabel.gridx = 0;
                                        gbc_dailyPriceLabel.gridy = 4;
                                        getContentPane().add(dailyPriceLabel, gbc_dailyPriceLabel);
                                        dailyPriceField = new JTextField();
                                        GridBagConstraints gbc_dailyPriceField = new GridBagConstraints();
                                        gbc_dailyPriceField.insets = new Insets(0, 0, 5, 5);
                                        gbc_dailyPriceField.fill = GridBagConstraints.BOTH;
                                        gbc_dailyPriceField.gridx = 1;
                                        gbc_dailyPriceField.gridy = 4;
                                        getContentPane().add(dailyPriceField, gbc_dailyPriceField);
                                        floorLabel = new JLabel("Floor:");
                                        GridBagConstraints gbc_floorLabel = new GridBagConstraints();
                                        gbc_floorLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_floorLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_floorLabel.gridx = 0;
                                        gbc_floorLabel.gridy = 5;
                                        getContentPane().add(floorLabel, gbc_floorLabel);
                                        
                                        Integer [] floors = {1,2,3,4,5,6,7};
                                        floorBox = new JComboBox(floors);
                                        GridBagConstraints gbc_floorBox = new GridBagConstraints();
                                        gbc_floorBox.insets = new Insets(0, 0, 5, 5);
                                        gbc_floorBox.fill = GridBagConstraints.HORIZONTAL;
                                        gbc_floorBox.gridx = 1;
                                        gbc_floorBox.gridy = 5;
                                        getContentPane().add(floorBox, gbc_floorBox);
                                        avgDailyCostLabel = new JLabel("Average Daily Cost:");
                                        GridBagConstraints gbc_avgDailyCostLabel = new GridBagConstraints();
                                        gbc_avgDailyCostLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_avgDailyCostLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_avgDailyCostLabel.gridx = 0;
                                        gbc_avgDailyCostLabel.gridy = 6;
                                        getContentPane().add(avgDailyCostLabel, gbc_avgDailyCostLabel);
                                        avgDailyCostField = new JTextField();
                                        GridBagConstraints gbc_avgDailyCostField = new GridBagConstraints();
                                        gbc_avgDailyCostField.insets = new Insets(0, 0, 5, 5);
                                        gbc_avgDailyCostField.fill = GridBagConstraints.BOTH;
                                        gbc_avgDailyCostField.gridx = 1;
                                        gbc_avgDailyCostField.gridy = 6;
                                        getContentPane().add(avgDailyCostField, gbc_avgDailyCostField);
                                        gradeLabel = new JLabel("Grade:");
                                        GridBagConstraints gbc_gradeLabel = new GridBagConstraints();
                                        gbc_gradeLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_gradeLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_gradeLabel.gridx = 0;
                                        gbc_gradeLabel.gridy = 7;
                                        getContentPane().add(gradeLabel, gbc_gradeLabel);
                                        gradeField = new JTextField();
                                        GridBagConstraints gbc_gradeField = new GridBagConstraints();
                                        gbc_gradeField.insets = new Insets(0, 0, 5, 5);
                                        gbc_gradeField.fill = GridBagConstraints.BOTH;
                                        gbc_gradeField.gridx = 1;
                                        gbc_gradeField.gridy = 7;
                                        getContentPane().add(gradeField, gbc_gradeField);
                                        maxPopulationLabel = new JLabel("Max Population Capacity:");
                                        GridBagConstraints gbc_maxPopulationLabel = new GridBagConstraints();
                                        gbc_maxPopulationLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_maxPopulationLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_maxPopulationLabel.gridx = 0;
                                        gbc_maxPopulationLabel.gridy = 8;
                                        getContentPane().add(maxPopulationLabel, gbc_maxPopulationLabel);
                                        Integer[] population = {1,2}; 
                                        comboBox = new JComboBox(population);
                                        GridBagConstraints gbc_comboBox = new GridBagConstraints();
                                        gbc_comboBox.insets = new Insets(0, 0, 5, 5);
                                        gbc_comboBox.fill = GridBagConstraints.BOTH;
                                        gbc_comboBox.gridx = 1;
                                        gbc_comboBox.gridy = 8;
                                        getContentPane().add(comboBox, gbc_comboBox);
                                        sizeLabel = new JLabel("Size:");
                                        GridBagConstraints gbc_sizeLabel = new GridBagConstraints();
                                        gbc_sizeLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_sizeLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_sizeLabel.gridx = 0;
                                        gbc_sizeLabel.gridy = 9;
                                        getContentPane().add(sizeLabel, gbc_sizeLabel);
                                        sizeField = new JTextField();
                                        GridBagConstraints gbc_sizeField = new GridBagConstraints();
                                        gbc_sizeField.insets = new Insets(0, 0, 5, 5);
                                        gbc_sizeField.fill = GridBagConstraints.BOTH;
                                        gbc_sizeField.gridx = 1;
                                        gbc_sizeField.gridy = 9;
                                        getContentPane().add(sizeField, gbc_sizeField);
                                        viewLabel = new JLabel("Has View");
                                        GridBagConstraints gbc_viewLabel = new GridBagConstraints();
                                        gbc_viewLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_viewLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_viewLabel.gridx = 0;
                                        gbc_viewLabel.gridy = 10;
                                        getContentPane().add(viewLabel, gbc_viewLabel);
                                        viewCheckbox = new JCheckBox();
                                        GridBagConstraints gbc_viewCheckbox = new GridBagConstraints();
                                        gbc_viewCheckbox.anchor = GridBagConstraints.WEST;
                                        gbc_viewCheckbox.insets = new Insets(0, 0, 5, 5);
                                        gbc_viewCheckbox.fill = GridBagConstraints.VERTICAL;
                                        gbc_viewCheckbox.gridx = 1;
                                        gbc_viewCheckbox.gridy = 10;
                                        getContentPane().add(viewCheckbox, gbc_viewCheckbox);
                                        GridBagConstraints gbc_submitButton = new GridBagConstraints();
                                        gbc_submitButton.gridwidth = 2;
                                        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
                                        gbc_submitButton.fill = GridBagConstraints.VERTICAL;
                                        gbc_submitButton.gridx = 0;
                                        gbc_submitButton.gridy = 14;
                                        getContentPane().add(submitButton, gbc_submitButton);
   
    }
    /*
     * Functions:                                    
     */
       
    /**
     * Clears the form fields
     */
    private void clearForm() {
        dailyPriceField.setText("");
        floorBox.setSelectedIndex(0);
        avgDailyCostField.setText("");
        gradeField.setText("");
        sizeField.setText("");
        viewCheckbox.setSelected(false);
        comboBox.setSelectedIndex(0); // Set to the default option in the combo box
    }


}




