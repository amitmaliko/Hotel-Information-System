package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import main.Main;
import model.Hotel;
import model.Booking;
/**
 * JInternalFrame To Remove existing Booking
 * @author Amit Malik 207850074
 *
 */

public class RemoveBooking extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public RemoveBooking() {
		setBounds(100, 100, 450, 300);
		/*
		 * Uses Bookings Hash Map from hotel to fill combobox with all bookings
		 */
		HashMap<String, Booking> bookingsHashmap = Main.hotel.getAllBookings();
		JComboBox bookingsComboBox = new JComboBox();
		
		for (String key : bookingsHashmap.keySet()) {
			bookingsComboBox.addItem(key);
        }
		
		JLabel headlineLabel = new JLabel("Choose Booking to Remove From System:");
		headlineLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		/**
		 * -Remove Button-
		 * After Choosing booking to remove , calls "removeBooking" from hotel:
		 */
		JButton removeButton = new JButton("Remove Booking");
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the selected Booking name from the JComboBox
                String selectedBookingName = (String) bookingsComboBox.getSelectedItem();

                // Get the corresponding Booking object from the HashMap
                Booking selectedBooking = bookingsHashmap.get(selectedBookingName);

                	Main.hotel.removeBooking(selectedBooking);
                
                dispose();
                JOptionPane.showMessageDialog(null, "Booking Has Been Removed!");
			}
		});
		
		JButton btnExit = new JButton("");	
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(158)
							.addComponent(removeButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(headlineLabel, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
								.addComponent(bookingsComboBox, 0, 342, Short.MAX_VALUE))))
					.addContainerGap(66, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(351, Short.MAX_VALUE)
					.addComponent(btnExit))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnExit)
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addComponent(headlineLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(bookingsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(removeButton)
					.addGap(55))
		);
	       btnExit.setBorder(null);
	       btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
	       btnExit.setFont(new Font("Tahoma", Font.BOLD, 9));
		   btnExit.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
		   dispose();	
		   }
		   });
		   
		getContentPane().setLayout(groupLayout);

	}

}
