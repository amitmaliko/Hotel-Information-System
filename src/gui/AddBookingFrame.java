package gui;
import javax.swing.*;
import exceptions.DateFormatException;
import exceptions.EmptyFieldsException;
import main.Main;
import model.Booking;
import model.Customer;
import model.Hotel;
import model.Room;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Internal Jframe to add new booking 
 * @author Amit Malik 207850074
 *
 */


public class AddBookingFrame extends JInternalFrame {

    private JLabel roomLabel, customerLabel, dateLabel, daysLabel;
    private JTextField dateField;
    private JButton submitButton;
    private JLabel lblHeadLine;
    private JComboBox roomsComboBox;
    private JComboBox customersComboBox;
    private JTextField daysField;

    public AddBookingFrame() {    	
    	/*
    	 * Set InternalFrame :
    	 */
    	getContentPane().setBackground(new Color(238, 130, 238));
        setTitle("Create New Booking");
        setSize(386, 473);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{171, 94, 0};
        gridBagLayout.rowHeights = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        /**
         * Initializing ComboBoxes:
         */
        //Rooms combobox is filled with allRooms HasMap keys (Room Number)
        HashMap<String, Room> roomsHashmap = Main.hotel.getAllRooms();
        roomsComboBox = new JComboBox();
        for (String key : roomsHashmap.keySet()) {
			roomsComboBox.addItem(key);
        }
        GridBagConstraints gbc_roomsComboBox = new GridBagConstraints();
        gbc_roomsComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_roomsComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_roomsComboBox.gridx = 1;
        gbc_roomsComboBox.gridy = 5;
        getContentPane().add(roomsComboBox, gbc_roomsComboBox);
        
        //Customers combobox is filled with allCustomers HasMap keys (Customer ID)
        HashMap<String, Customer> CustomersHashmap = Main.hotel.getAllCustomers();
        customersComboBox = new JComboBox();
        for (String key : CustomersHashmap.keySet()) {
			customersComboBox.addItem(key);
        }
        GridBagConstraints gbc_customersComboBox = new GridBagConstraints();
        gbc_customersComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_customersComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_customersComboBox.gridx = 1;
        gbc_customersComboBox.gridy = 6;
        getContentPane().add(customersComboBox, gbc_customersComboBox);
                                
        /*
         * -Submit Button Actions-
         * This is what happens when Create Booking is being pressed:
         */
       submitButton = new JButton("Create Booking");
       submitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
       submitButton.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
    	 // Get the values from the GUI components
    	   
    	    String roomNumber = (String) roomsComboBox.getSelectedItem();
    	    String customerName = (String) customersComboBox.getSelectedItem();
    	 //Ensure roomNumber and customerName are not null
    	    try {
    	    if (roomNumber == null || customerName == null) {       
    	        throw new EmptyFieldsException("Please select Room and Customer For Booking\n Or Add new to the System!");
    	       } 
    	    }
    	    catch (EmptyFieldsException ex) {
                JOptionPane.showMessageDialog(AddBookingFrame.this, ex.getMessage(), "Incomplete Information", JOptionPane.ERROR_MESSAGE);
                return;
            }
    	    
    	  //Checking Correct Formatted Date , using parseDate() function. 
    	    Date checkInDate = parseDate(dateField.getText());
    	    try {
    	    if (checkInDate == null) {
    	    	throw new DateFormatException();         
            	}
    	    }
    	    catch (DateFormatException ex)    {
    	    	 JOptionPane.showMessageDialog(AddBookingFrame.this, ex.getMessage(), "Invalid Date", JOptionPane.ERROR_MESSAGE);
    	    	 return;  // Stop the submission since date parsing failed. 
    	    }
    	    
 

    	    // validation check: Ensure numberOfDays is not empty and is a positive integer
    	    Integer numberOfDays = 0;
    	    try {
    	        numberOfDays = Integer.parseInt(daysField.getText());
    	        if (numberOfDays <= 0) {
    	            JOptionPane.showMessageDialog(null, "Please enter a valid number of days (positive integer).", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	            return; // Stop the submission since the numberOfDays is not valid.
    	        }
    	    } catch (NumberFormatException ex) {
    	        JOptionPane.showMessageDialog(null, "Please enter a valid number of days (positive integer).", "Invalid Input", JOptionPane.ERROR_MESSAGE);
    	        return; // Stop the submission since parsing numberOfDays failed.
    	    }

    	    // validation check: Ensure customerName is not empty
    	    try {
    	    if (customerName.isEmpty()) {
    	        throw new EmptyFieldsException("Please select a customer.");   	       
    	    	}
    	    }catch (EmptyFieldsException ex){
    	    	JOptionPane.showMessageDialog(AddBookingFrame.this, ex.getMessage(), "Incomplete Information", JOptionPane.ERROR_MESSAGE);
                return;
    	    }

    	    Customer customer = Main.hotel.getRealCustomer(customerName);

    	    // Call the Booking Constructor and add it to Hotel allBookings:
    	    Booking newBooking = new Booking(roomNumber, customer, checkInDate, numberOfDays);
    	    Main.hotel.addBooking(newBooking);

    	    clearForm();
    	    JOptionPane.showMessageDialog(null, "Booking added!");
    	    
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
        
        lblHeadLine = new JLabel("To Add a new Booking Please Fill up the Form:");
        lblHeadLine.setFont(new Font("Tahoma", Font.BOLD, 13));
        GridBagConstraints gbc_lblHeadLine = new GridBagConstraints();
        gbc_lblHeadLine.gridwidth = 2;
        gbc_lblHeadLine.insets = new Insets(0, 0, 5, 5);
        gbc_lblHeadLine.fill = GridBagConstraints.VERTICAL;
        gbc_lblHeadLine.gridx = 0;
        gbc_lblHeadLine.gridy = 2;
        getContentPane().add(lblHeadLine, gbc_lblHeadLine);
                                        roomLabel = new JLabel("Room Number:");
                                        GridBagConstraints gbc_roomLabel = new GridBagConstraints();
                                        gbc_roomLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_roomLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_roomLabel.gridx = 0;
                                        gbc_roomLabel.gridy = 5;
                                        getContentPane().add(roomLabel, gbc_roomLabel);
                                        

                                        customerLabel = new JLabel("Customer:");
                                        GridBagConstraints gbc_customerLabel = new GridBagConstraints();
                                        gbc_customerLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_customerLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_customerLabel.gridx = 0;
                                        gbc_customerLabel.gridy = 6;
                                        getContentPane().add(customerLabel, gbc_customerLabel);
                                        
                                        
                                        dateLabel = new JLabel("Check-in Date:");
                                        GridBagConstraints gbc_dateLabel = new GridBagConstraints();
                                        gbc_dateLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_dateLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_dateLabel.gridx = 0;
                                        gbc_dateLabel.gridy = 7;
                                        getContentPane().add(dateLabel, gbc_dateLabel);
                                        dateField = new JTextField();
                                        dateField.setFont(new Font("Tahoma", Font.PLAIN, 11));
                                        GridBagConstraints gbc_dateField = new GridBagConstraints();
                                        gbc_dateField.insets = new Insets(0, 0, 5, 5);
                                        gbc_dateField.fill = GridBagConstraints.BOTH;
                                        gbc_dateField.gridx = 1;
                                        gbc_dateField.gridy = 7;
                                        getContentPane().add(dateField, gbc_dateField);
                                        daysLabel = new JLabel("Days to stay:");
                                        GridBagConstraints gbc_daysLabel = new GridBagConstraints();
                                        gbc_daysLabel.insets = new Insets(0, 0, 5, 5);
                                        gbc_daysLabel.fill = GridBagConstraints.VERTICAL;
                                        gbc_daysLabel.gridx = 0;
                                        gbc_daysLabel.gridy = 8;
                                        getContentPane().add(daysLabel, gbc_daysLabel);
                                        Integer[] population = {1,2};
                                        
                                        daysField = new JTextField();
                                        GridBagConstraints gbc_daysField = new GridBagConstraints();
                                        gbc_daysField.insets = new Insets(0, 0, 5, 5);
                                        gbc_daysField.fill = GridBagConstraints.HORIZONTAL;
                                        gbc_daysField.gridx = 1;
                                        gbc_daysField.gridy = 8;
                                        getContentPane().add(daysField, gbc_daysField);
                                        daysField.setColumns(10);
                                        GridBagConstraints gbc_submitButton = new GridBagConstraints();
                                        gbc_submitButton.gridwidth = 2;
                                        gbc_submitButton.insets = new Insets(0, 0, 5, 5);
                                        gbc_submitButton.fill = GridBagConstraints.VERTICAL;
                                        gbc_submitButton.gridx = 0;
                                        gbc_submitButton.gridy = 14;
                                        getContentPane().add(submitButton, gbc_submitButton);
                                           
                                        
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
        dateField.setText("");
        roomsComboBox.setSelectedIndex(0);
        customersComboBox.setSelectedIndex(0);
        daysField.setText("");
    }
}




