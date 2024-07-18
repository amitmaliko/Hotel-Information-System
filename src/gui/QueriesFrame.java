package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import main.Main;
import model.Employee;
import model.Hotel;
/**
 * This is a JInternalFrame that displays Relevant Query output <br>
 * using "Switch Case" with given string from Main Frame run request.
 * @author Amit Malik 207850074
 *
 */
public class QueriesFrame extends JInternalFrame {
String output;

	/**
	 * Create the frame.
	 * @param whatToRun - which query to run 
	 */
	public QueriesFrame(String whatToRun) {
		setBounds(100, 100, 652, 407);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JInternalFrame internalFrame = new JInternalFrame("OutPut");
		internalFrame.setVisible(true);
		
		JLabel lblHeadlineLabel = new JLabel("");
		lblHeadlineLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnExit2 = new JButton("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(internalFrame, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(24)
					.addComponent(lblHeadlineLabel, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
					.addGap(181))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(543, Short.MAX_VALUE)
					.addComponent(btnExit2)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnExit2)
					.addGap(7)
					.addComponent(lblHeadlineLabel, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(internalFrame, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		panel.setLayout(gl_panel);
		
	
		btnExit2.setBorder(null);
	       btnExit2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
	       btnExit2.setFont(new Font("Tahoma", Font.BOLD, 9));
		   btnExit2.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
		   dispose();	
		   }
		   });
				
			switch (whatToRun) {
			  case "allCustomersByPK":
				  	lblHeadlineLabel.setText("All Customers sorted by PK:");
		            output = Main.hotel.allCustomersByPK().toString();
		            textPane.setText(output);
			    break;
			    
			  case "allBookingByRevenue":
				  	lblHeadlineLabel.setText("All Bookings sorted by Revenue:");
		            output = Main.hotel.allBookingByRevenue().toString();
		            textPane.setText(output);
			    break;
			    
			  case "allCustomersCmp":
				  	lblHeadlineLabel.setText("All Customers sorted by Bookings amount and Name:");
		            output = Main.hotel.allCustomersCmp().toString();
		            textPane.setText(output);
			    break;
			    
			  case "totalProfit":
				  	lblHeadlineLabel.setText("Hotel Total Profit:");
		            output = Integer.toString(Main.hotel.totalProfit());
		            textPane.setText(output);
			    break;
			    
			    
			  case "customerBookedTheMostRooms":
				  	lblHeadlineLabel.setText("Customer who Booked most:");
		            output = Main.hotel.customerBookedTheMostRooms().toString();
		            textPane.setText(output);
			    break;
			
			}



	}
}


