package gui;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Main;
import model.Booking;
import model.Hotel;
import exceptions.*;
import model.StandardRoom;
import model.SuperiorRoom;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
/**
 * JInternalFrame To Display All existing Bookings <br>
 * Using bookingsHashmap from hotel and displays its toString() to textPane<br>
 * see below the FILTER bonus.
 * 
 * @author Amit Malik 207850074
 */

public class ShowAllBookings extends JInternalFrame {
	HashMap<String, Booking> bookingsHashmap = Main.hotel.getAllBookings();
	JTextPane textPane = new JTextPane();
	JComboBox roomBox = new JComboBox();
	JComboBox customerIdBox = new JComboBox();
	JComboBox daysBox = new JComboBox();
	JComboBox bookingsBox = new JComboBox();
	
	    public ShowAllBookings() {
	        setTitle("All Bookings toString()");
	        setSize(676, 513);
	        
	        JPanel panel = new JPanel();
	        getContentPane().add(panel, BorderLayout.CENTER);
	        
	        JLabel lblHeadLabel = new JLabel("All Bookings:");
	        lblHeadLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	        
	        JButton btnExit = new JButton("");
	        btnExit.setBorder(null);
	        btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
	        btnExit.setFont(new Font("Tahoma", Font.BOLD, 9));
		    btnExit.addActionListener(e -> {
			   dispose();
			});
	        
	        JScrollPane scrollPane = new JScrollPane();
	        JLabel lblFilter = new JLabel("Choose Filter : ");
	        lblFilter.setFont(new Font("Tahoma", Font.BOLD, 11));   
	        JLabel lblRoom = new JLabel("Room Number");     
	        JLabel lblCustomer = new JLabel("Customer ID");    
	        JLabel lblDays = new JLabel("Number Of Days");  	       
	        
	        JLabel lbGetBooking = new JLabel("or Get Booking by ID:");
	        lbGetBooking.setFont(new Font("Tahoma", Font.BOLD, 11));
	        
	        
	        GroupLayout gl_panel = new GroupLayout(panel);
	        gl_panel.setHorizontalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
	        			.addContainerGap())
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addGap(32)
	        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
	        				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        					.addComponent(lblHeadLabel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
	        					.addGroup(gl_panel.createSequentialGroup()
	        						.addGap(10)
	        						.addComponent(lblFilter, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
	        				.addComponent(lbGetBooking, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
	        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        						.addComponent(bookingsBox, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        								.addComponent(roomBox, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
	        								.addComponent(lblRoom))
	        							.addPreferredGap(ComponentPlacement.UNRELATED)
	        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
	        								.addComponent(lblCustomer, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        								.addComponent(customerIdBox, Alignment.TRAILING, 0, 103, Short.MAX_VALUE))
	        							.addGap(18)
	        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        								.addComponent(daysBox, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
	        								.addComponent(lblDays))))
	        					.addGap(28))
	        				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
	        					.addPreferredGap(ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
	        					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
	        					.addContainerGap())))
	        );
	        gl_panel.setVerticalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addGap(20)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
	        						.addComponent(lblHeadLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
	        						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        							.addComponent(lblRoom)
	        							.addComponent(lblCustomer)
	        							.addComponent(lblDays)))
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        						.addComponent(lblFilter)
	        						.addComponent(roomBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(customerIdBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(daysBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        					.addGap(14)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        						.addComponent(bookingsBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(lbGetBooking)))
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addContainerGap()
	        					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
	        			.addGap(18)
	        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap())
	        );
	        
	        
	        scrollPane.setViewportView(textPane);
	        panel.setLayout(gl_panel);
	        
			
			textPane.setText(bookingsHashmap.values().toString());
			
	        /**
	         * Filter Bonus:
	         */


			// Initialize the roomBox with unique room numbers from bookingsHashmap
			HashSet<String> roomNumbersSet = new HashSet<>();
			for (Booking booking : bookingsHashmap.values()) {
			    roomNumbersSet.add(booking.getRoomNumber());
			}
			roomBox.addItem("Any");
			for (String roomNumber : roomNumbersSet) {
			    roomBox.addItem(roomNumber);
			}

			// Initialize the customerIdBox with unique customer IDs from bookingsHashmap
			HashSet<String> customerIdsSet = new HashSet<>();
			for (Booking booking : bookingsHashmap.values()) {
			    customerIdsSet.add(booking.getCustomer().getId());
			}
			customerIdBox.addItem("Any");
			for (String customerId : customerIdsSet) {
			    customerIdBox.addItem(customerId);
			}

			// Initialize the daysBox with unique number of days from bookingsHashmap
			HashSet<Integer> daysSet = new HashSet<>();
			for (Booking booking : bookingsHashmap.values()) {
			    daysSet.add(booking.getNumberOfDays());
			}
			daysBox.addItem("Any");
			for (Integer days : daysSet) {
			    daysBox.addItem(days);
			}
			
			// Initialize the bookingsBox with unique booking IDs from bookingsHashmap
			bookingsBox.addItem("Any");
			for (String bookingId : bookingsHashmap.keySet()) {
			    bookingsBox.addItem(bookingId);
			}

			// Add action listeners to the combo boxes to apply filters
			roomBox.addActionListener(e -> updateFilteredBookings());
			customerIdBox.addActionListener(e -> updateFilteredBookings());
			daysBox.addActionListener(e -> updateFilteredBookings());
			bookingsBox.addActionListener(e -> updateFilteredBookings());


			

			
	       
	    }
	    
	    private void updateFilteredBookings() {
	        StringBuilder filteredBookings = new StringBuilder();

	        for (Booking booking : bookingsHashmap.values()) {
	            boolean isRoomFiltered = true;
	            boolean isCustomerFiltered = true;
	            boolean isDaysFiltered = true;
	            boolean isBookingIdFiltered = true;

	            // Apply filter based on room number selection
	            if (!roomBox.getSelectedItem().equals("Any")) {
	                String selectedRoomNumber = (String) roomBox.getSelectedItem();
	                if (!booking.getRoomNumber().equals(selectedRoomNumber)) {
	                    isRoomFiltered = false;
	                }
	            }

	            // Apply filter based on customer ID selection
	            if (!customerIdBox.getSelectedItem().equals("Any")) {
	                String selectedCustomerId = (String) customerIdBox.getSelectedItem();
	                if (!booking.getCustomer().getId().equals(selectedCustomerId)) {
	                    isCustomerFiltered = false;
	                }
	            }

	            // Apply filter based on number of days selection
	            if (!daysBox.getSelectedItem().equals("Any")) {
	                int selectedDays = (int) daysBox.getSelectedItem();
	                if (booking.getNumberOfDays() != selectedDays) {
	                    isDaysFiltered = false;
	                }
	            }

	            // Apply filter based on booking ID selection
	            if (!bookingsBox.getSelectedItem().equals("Any")) {
	                String selectedBookingId = (String) bookingsBox.getSelectedItem();
	                if (!booking.getBookingNumber().equals(selectedBookingId)) {
	                    isBookingIdFiltered = false;
	                }
	            }

	            // Check if the booking passes all filter conditions
	            if (isRoomFiltered && isCustomerFiltered && isDaysFiltered && isBookingIdFiltered) {
	                filteredBookings.append(booking.toString()).append("\n");
	            }
	        }

	        // Set the textPane content with the filtered bookings
	        textPane.setText(filteredBookings.toString());
	    }

 }
	

