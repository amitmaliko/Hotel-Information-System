package gui;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JInternalFrame;

import model.Hotel;
import model.Room;
import model.StandardRoom;
import model.Suite;
import model.SuperiorRoom;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import main.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * JInternalFrame To Remove existing Room
 * @author Amit Malik 207850074
 *
 */
public class RemoveRoom extends JInternalFrame {
	/**
	 * Create the frame.
	 */
	public RemoveRoom() {
		setBounds(100, 100, 450, 300);
		/*
		 * Uses Rooms Hash Map from hotel to fill combobox with all Rooms
		 */
		HashMap<String, Room> roomsHashmap = Main.hotel.getAllRooms();
		JComboBox roomsComboBox = new JComboBox();
		
		for (String key : roomsHashmap.keySet()) {
			roomsComboBox.addItem(key);
        }
		
		JLabel headlineLabel = new JLabel("Choose Room to Remove From System:");
		headlineLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton removeButton = new JButton("Remove Room");
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the selected room name from the JComboBox
                String selectedRoomName = (String) roomsComboBox.getSelectedItem();

                // Get the corresponding Room object from the HashMap
                Room selectedRoom = roomsHashmap.get(selectedRoomName);

                // Check the instance of the Room and call the appropriate remove function
                if (selectedRoom instanceof StandardRoom) {
                	Main.hotel.removeStandardRoom((StandardRoom) selectedRoom);
                } else if (selectedRoom instanceof SuperiorRoom) {
                	Main.hotel.removeSuperiorRoom((SuperiorRoom) selectedRoom);
                } else if (selectedRoom instanceof Suite) {
                	Main.hotel.removeSuite((Suite) selectedRoom);
                }
                dispose();
                JOptionPane.showMessageDialog(null, "Room Has Been Removed!");
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
								.addComponent(roomsComboBox, 0, 342, Short.MAX_VALUE))))
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
					.addComponent(roomsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
