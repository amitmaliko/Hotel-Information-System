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
import model.Department;
/**
 * JInternalFrame To Remove existing Department
 * @author Amit Malik 207850074
 *
 */

public class RemoveDepartment extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public RemoveDepartment() {
		setBounds(100, 100, 624, 318);
		/*
		 * Uses Department Hash Map from hotel to fill combobox with all Departments
		 */
		HashMap<String, Department> departmentsHashmap = Main.hotel.getAllDepartments();
		JComboBox departmentsComboBox = new JComboBox();
		
		for (String key : departmentsHashmap.keySet()) {
			departmentsComboBox.addItem(key);
        }
		
		JLabel headlineLabel = new JLabel("Choose Department to Remove From System:");
		headlineLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		/**
		 * -Remove Button-
		 * After Choosing Department to remove , calls "removeDepartment" 
		 */
		JButton removeButton = new JButton("Remove Department");
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the selected Department name from the JComboBox
                String selectedDepartmentName = (String) departmentsComboBox.getSelectedItem();

                // Get the corresponding Department object from the HashMap
                Department selectedDepartment = departmentsHashmap.get(selectedDepartmentName);

                	Main.hotel.removeDepartment(selectedDepartment);
                
                dispose();
                JOptionPane.showMessageDialog(null, "Department Has Been Removed!");
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
						.addComponent(departmentsComboBox, 0, 342, Short.MAX_VALUE))
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
					.addComponent(departmentsComboBox, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
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

