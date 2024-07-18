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
import java.awt.Color;
/**
 * JInternalFrame to Activate Room Upgrade interface for Superior Room and Suite
 * @author Amit Malik 207850074
 *
 */
public class UpgradeRoom extends JInternalFrame {
	boolean flag;
	/**
	 * Create the frame.
	 */
	public UpgradeRoom() {
		getContentPane().setBackground(new Color(255, 215, 0));
		setBounds(100, 100, 450, 300);
		HashMap<String, Room> roomsHashmap = Main.hotel.getAllRooms();
		JComboBox roomsComboBox = new JComboBox();
		
		for (String key : roomsHashmap.keySet()) {
			if (roomsHashmap.get(key) instanceof SuperiorRoom ||roomsHashmap.get(key) instanceof Suite ) {
				roomsComboBox.addItem(key);
			}		
        }
		
		JLabel headlineLabel = new JLabel("Choose Room To Upgrade");
		headlineLabel.setFont(new Font("Calibri", Font.BOLD, 33));
		
		JButton upgradeButton = new JButton("UPGRADE");
		upgradeButton.setFont(new Font("Calibri", Font.BOLD, 13));
		upgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (roomsHashmap.isEmpty()) {
					 JOptionPane.showMessageDialog(UpgradeRoom.this,"No Rooms To Upgrade!", "Null List", JOptionPane.ERROR_MESSAGE);
					 dispose();
					 return;
				}
					
				// Get the selected room name from the JComboBox
                String selectedRoomName = (String) roomsComboBox.getSelectedItem();

                // Get the corresponding Room object from the HashMap
                Room selectedRoom = roomsHashmap.get(selectedRoomName);             
                // Check the instance of the Room and call the appropriate remove function
                if (selectedRoom instanceof SuperiorRoom) {
                	 flag = ((SuperiorRoom) selectedRoom).upgrade();
                } else if (selectedRoom instanceof Suite) {
                	 flag = ((Suite) selectedRoom).upgrade();
                }
                if (flag==true) {
                JOptionPane.showMessageDialog(null,selectedRoom.getRoomNumber()+"Has Now Max Capacity Of:\n"+selectedRoom.getMaxPopulationCapacity());
                }else {
                	JOptionPane.showMessageDialog(UpgradeRoom.this,"This Room has already been Upgraded!", "Can't upgrade", JOptionPane.ERROR_MESSAGE);
                }
                
                dispose();
			}
		});
		
		JButton btnExit = new JButton("");	
		
		JLabel lblNotice = new JLabel("You can upgrade only Superior Room or Suite");
		lblNotice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotice.setForeground(new Color(0, 0, 139));
		
		JLabel lblSR = new JLabel("Suprerior Room Capacity will increase by 2 ");
		
		JLabel lblS = new JLabel("Suite Capacuty will increase by 4");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(174)
					.addComponent(upgradeButton)
					.addContainerGap(179, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(headlineLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(roomsComboBox, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExit)
					.addGap(173))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(80)
					.addComponent(lblNotice, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(235, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSR)
					.addGap(301))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(118)
					.addComponent(lblS, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
					.addGap(163))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(headlineLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(roomsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNotice)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSR)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblS)
					.addGap(21)
					.addComponent(upgradeButton)
					.addGap(19))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnExit)
					.addContainerGap(239, Short.MAX_VALUE))
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
