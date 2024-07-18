package gui;

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

import main.Main;

import javax.swing.GroupLayout.Alignment;

import model.Employee;
import model.Hotel;
/**
 * JInternalFrame To Remove existing Employee
 * @author Amit Malik 207850074
 *
 */
	public class RemoveEmployee extends JInternalFrame {

		/**
		 * Create the frame.
		 */
		public RemoveEmployee() {
			setBounds(100, 100, 624, 318);
			/*
			 * Uses Employees Hash Map from hotel to fill combobox with all Employees
			 */
			HashMap<String, Employee> employeesHashmap = Main.hotel.getAllEmployees();
			JComboBox employeesComboBox = new JComboBox();
			
			for (String key : employeesHashmap.keySet()) {
				employeesComboBox.addItem(key);
	        }
			
			JLabel headlineLabel = new JLabel("Choose Employee to Remove From System:");
			headlineLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			
			JButton removeButton = new JButton("Remove Employee");
			removeButton.setFont(new Font("Tahoma", Font.BOLD, 12));
			removeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Get the selected Employee name from the JComboBox
	                String selectedEmployeeName = (String) employeesComboBox.getSelectedItem();

	                // Get the corresponding Employee object from the HashMap
	                Employee selectedEmployee = employeesHashmap.get(selectedEmployeeName);

	                	Main.hotel.removeEmployee(selectedEmployee);
	                
	                dispose();
	                JOptionPane.showMessageDialog(null, "Employee Has Been Removed!");
				}
			});
			
			JButton btnExit = new JButton("");	
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(32)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(headlineLabel, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
							.addComponent(employeesComboBox, 0, 342, Short.MAX_VALUE))
						.addGap(66))
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(575, Short.MAX_VALUE)
						.addComponent(btnExit)
						.addGap(18))
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(198)
						.addComponent(removeButton, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
						.addGap(261))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnExit)
						.addGap(81)
						.addComponent(headlineLabel, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(employeesComboBox, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addGap(30)
						.addComponent(removeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(63))
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


