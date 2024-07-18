package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import main.Main;
import model.Employee;
import model.Hotel;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
/**
 * This is JInternalFrame For KEmployees Query.<br>
 * @author Amit Malik 207850074
 *
 */
public class KEmployeesFrame extends JInternalFrame {
	private JTextField Kfield;

	HashMap<String, Employee> employeesHashmap = Main.hotel.getAllEmployees();

	/**
	 * Create the frame.
	 */
	public KEmployeesFrame() {
		setBounds(100, 100, 652, 407);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(123, 104, 238));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblHeadlineLabel = new JLabel("How much hotel employees would you like to display sorted?");
		lblHeadlineLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		Kfield = new JTextField();
		Kfield.setColumns(10);
		
		JInternalFrame internalFrame = new JInternalFrame("Output");
		internalFrame.setVisible(true);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
	
		JButton btnSubmitButton = new JButton("Run Query");
		btnSubmitButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		/**
		 * After inserting "k" , Check conditions:
		 */
	
		btnSubmitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String kStr = Kfield.getText();
	            int k;

	            // Validate if the Kfield is empty
	            if (kStr.isEmpty()) {
	                JOptionPane.showMessageDialog(KEmployeesFrame.this, "Please fill in the field.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Validate if the Kfield contains a valid integer
	            try {
	                k = Integer.parseInt(kStr);
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(KEmployeesFrame.this, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Validate if k is greater than empHashMap.size()
	            if (k > employeesHashmap.size()) {
	                JOptionPane.showMessageDialog(KEmployeesFrame.this, "cannot display larger than the number of employees in the system.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // All validations passed, call the KEmployeesStream method and display the output in the textPane
	            String output = Main.hotel.KEmployees(k).toString();
	            textPane.setText(output);
	        }
	    });
		
		JButton btnExit = new JButton("");
		btnExit.setBorder(null);
	       btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
	       btnExit.setFont(new Font("Tahoma", Font.BOLD, 9));
		   btnExit.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
		   dispose();	
		   }
		   });
				
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(internalFrame, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblHeadlineLabel, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Kfield, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
							.addGap(182))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(246)
					.addComponent(btnSubmitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(307))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(566, Short.MAX_VALUE)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addComponent(btnExit)
					.addGap(46)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHeadlineLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(Kfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSubmitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(2)
					.addComponent(internalFrame, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addGap(54))
		);
		
		panel.setLayout(gl_panel);


	}
}













    



