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
import model.Customer;
import model.VIPCustomer;
/**
 * JInternalFrame To Remove existing Customer
 * @author Amit Malik 207850074
 *
 */
public class RemoveCustomer extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public RemoveCustomer() {
		setBounds(100, 100, 609, 358);
		/*
		 * Uses Customer Hash Map from hotel to fill combobox with all customers
		 */
		HashMap<String, Customer> CustomersHashmap = Main.hotel.getAllCustomers();
		JComboBox customersComboBox = new JComboBox();
		
		for (String key : CustomersHashmap.keySet()) {
			customersComboBox.addItem(key);
        }
		
		JLabel headlineLabel = new JLabel("Choose Customer to Remove From System:");
		headlineLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		/**
		 * -Remove Button-
		 * After Choosing customer to remove , calls "removeCustomer" OR removeVIPCustomer from hotel
		 * Depends on object instance.
		 */
		JButton removeButton = new JButton("Remove Customer");
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the selected Customer name from the JComboBox
                String selectedCustomerName = (String) customersComboBox.getSelectedItem();

                // Get the corresponding Customer object from the HashMap
                Customer selectedCustomer = CustomersHashmap.get(selectedCustomerName);

                // Check the instance of the Customer and call the appropriate remove function
                if (selectedCustomer instanceof VIPCustomer) {
                	Main.hotel.removeVIPCustomer((VIPCustomer) selectedCustomer);
                } else if (selectedCustomer instanceof Customer) {
                	Main.hotel.removeCustomer((Customer) selectedCustomer);
                } 
                dispose();
                JOptionPane.showMessageDialog(null, "Customer Has Been Removed!");
			}
		});
		
		JButton btnExit = new JButton("");	
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(headlineLabel, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
					.addGap(49))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(customersComboBox, 0, 342, Short.MAX_VALUE)
					.addGap(71))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(210)
					.addComponent(removeButton, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(248))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(568, Short.MAX_VALUE)
					.addComponent(btnExit)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnExit)
					.addGap(65)
					.addComponent(headlineLabel, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(customersComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(removeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(100))
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
