package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import main.Main;
import model.Customer;
import model.Hotel;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JButton;
/**
 * This is JInternalFrame For AllBookingsOfSpecCustomer Query.<br>
 * @author Amit Malik 207850074
 *
 */
public class AllBookingsOfSpecCustomer extends JInternalFrame {


	public AllBookingsOfSpecCustomer() {
		setBounds(100, 100, 861, 434);
		/*
		 * We Are using Customers Hashmap From hotel in order to fill a combobox 
		 */
		HashMap<String, Customer> CustomersHashmap = Main.hotel.getAllCustomers();
		JComboBox comboBox = new JComboBox();
		for (String key : CustomersHashmap.keySet()) {
			comboBox.addItem(key);
        }
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(144, 238, 144));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JInternalFrame internalFrame = new JInternalFrame("OutPut");
		
		JTextPane textPane = new JTextPane();
		internalFrame.getContentPane().add(textPane, BorderLayout.CENTER);
		
		JLabel lblHeadline = new JLabel("Choose Customer:");
		lblHeadline.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		
		JButton btnSubmitButton = new JButton("Show All Bookings");
		btnSubmitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		/*
		 * After Choosing Specific Customer, All of his bookings being Displayed:
		 */
		
		btnSubmitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	String customerID = (String) comboBox.getSelectedItem();
	            String output = Main.hotel.allBookingsOfSpecCustomer(customerID).toString();
	            textPane.setText(output);
	        }
	    });
		
		
		JButton btnExitButton = new JButton("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(internalFrame, GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(92)
							.addComponent(lblHeadline, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
							.addGap(55)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(286)
					.addComponent(btnSubmitButton, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addGap(456))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(746, Short.MAX_VALUE)
					.addComponent(btnExitButton)
					.addGap(32))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addComponent(btnExitButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeadline, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
					.addGap(14)
					.addComponent(btnSubmitButton, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(internalFrame, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		
	
		btnExitButton.setBorder(null);
		btnExitButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
		btnExitButton.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnExitButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
		   dispose();
		   }
		   });
	
		panel.setLayout(gl_panel);
		internalFrame.setVisible(true);

	}
}

