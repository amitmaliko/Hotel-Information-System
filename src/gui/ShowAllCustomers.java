package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import model.Customer;
import model.Hotel;
import model.VIPCustomer;
import utils.Area;
import utils.Gender;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;

import main.Main;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
/**
 * JInternalFrame To Display All existing Customers <br>
 * Using Customers Hash map from hotel and displays its toString() to textPane<br>
 * see below the FILTER bonus.
 * 
 * @author Amit Malik 207850074
 */
public class ShowAllCustomers extends JInternalFrame {
	HashMap<String, Customer> customersHashmap = Main.hotel.getAllCustomers();
	JTextPane textPane = new JTextPane();
	JComboBox genderBox = new JComboBox();
	JRadioButton rdbtnIsVip = new JRadioButton("Show Only VIP Customers");
	JComboBox areaBox = new JComboBox();
	JComboBox yearBox = new JComboBox();
    JComboBox customersBox = new JComboBox();
	 
	/**
	 * Create the frame.
	 */

	public ShowAllCustomers() {
				
		 setTitle("All Customers toString()");
	        setSize(587, 513);
	        
	        JPanel panel = new JPanel();
	        getContentPane().add(panel, BorderLayout.CENTER);
	        
	        JLabel lblHeadLabel = new JLabel("All Customers:");
	        lblHeadLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	        
	        JButton btnExit = new JButton("");
	        btnExit.setBorder(null);
	        btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
	        btnExit.setFont(new Font("Tahoma", Font.BOLD, 9));
		    btnExit.addActionListener(e -> {
			   dispose();
			});
	        
	        JScrollPane scrollPane = new JScrollPane();   
	        JLabel lblFilters = new JLabel("Choose Filter:");
	        lblFilters.setFont(new Font("Tahoma", Font.BOLD, 11));
	        JLabel lblGender = new JLabel("Gender");	      
	        JLabel lblArea = new JLabel("Area");
	        JLabel lblYear = new JLabel("Year Of Birth");
	        
	        JLabel lblGetReal = new JLabel("or Get Customer by ID:");
	        lblGetReal.setFont(new Font("Tahoma", Font.BOLD, 11));
	        


	        GroupLayout gl_panel = new GroupLayout(panel);
	        gl_panel.setHorizontalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
	        			.addContainerGap())
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addGap(86)
	        			.addComponent(lblFilters, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(genderBox, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap(299, Short.MAX_VALUE))
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addGap(21)
	        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addComponent(rdbtnIsVip)
	        					.addGap(52)
	        					.addComponent(lblGetReal, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addComponent(customersBox, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
	        					.addContainerGap())
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addComponent(lblHeadLabel, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addPreferredGap(ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
	        							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
	        							.addGap(23))
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addGap(42)
	        							.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
	        							.addGap(45)
	        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        								.addComponent(lblArea, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
	        								.addComponent(areaBox, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
	        							.addGap(20)
	        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
	        								.addComponent(lblYear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        								.addComponent(yearBox, 0, 81, Short.MAX_VALUE))
	        							.addGap(104))))))
	        );
	        gl_panel.setVerticalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addGap(20)
	        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
	        				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
	        					.addComponent(lblHeadLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
	        				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        					.addComponent(lblGender)
	        					.addComponent(lblArea)
	        					.addComponent(lblYear)))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(lblFilters)
	        				.addComponent(genderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(yearBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(areaBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        					.addComponent(rdbtnIsVip)
	        					.addComponent(lblGetReal))
	        				.addComponent(customersBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap())
	        );
	        
	        
	        scrollPane.setViewportView(textPane);
	        panel.setLayout(gl_panel);
	        
			
			textPane.setText(customersHashmap.values().toString());
	     
/**
 * Filter Bonus:			
 */
			// Initialize the genderBox with Gender enums
			Gender[] genders = Gender.values();
			genderBox.addItem("Any");
			for (Gender gender : genders) {
			    genderBox.addItem(gender);
			}

			// Add action listener to genderBox to apply gender filter
			genderBox.addActionListener(e -> updateFilteredCustomers());

			// Add action listener to rdbtnIsVip to apply VIPCustomer filter
			rdbtnIsVip.addActionListener(e -> updateFilteredCustomers());

			// Initialize the areaBox with Area enums
			Area[] areas = Area.values();
			areaBox.addItem("Any");
			for (Area area : areas) {
			    areaBox.addItem(area);
			}

			// Initialize the yearBox with unique years of joining from customersHashmap
			HashSet<Integer> yearsSet = new HashSet<>();
			for (Customer customer : customersHashmap.values()) {
			    yearsSet.add(customer.getYearOfBirth());
			}
			yearBox.addItem("Any");
			for (Integer year : yearsSet) {
			    yearBox.addItem(year);
			}
			// Initialize the customersBox with unique customer IDs from customersHashmap
			customersBox.addItem("Any");
			for (String customerId : customersHashmap.keySet()) {
			    customersBox.addItem(customerId);
			}

			// Add action listeners to the combo boxes and radio button to apply filters
			areaBox.addActionListener(e -> updateFilteredCustomers());
			yearBox.addActionListener(e -> updateFilteredCustomers());
			customersBox.addActionListener(e -> updateFilteredCustomers());
	


   }
	
	

	// Method to update the textPane with the filtered customers
	private void updateFilteredCustomers() {
	    StringBuilder filteredCustomers = new StringBuilder();

	    for (Customer customer : customersHashmap.values()) {
	        boolean isFiltered = true;

	        // Apply filter based on gender selection
	        if (!genderBox.getSelectedItem().equals("Any")) {
	            Gender selectedGender = (Gender) genderBox.getSelectedItem();
	            if (customer.getGender() != selectedGender) {
	                isFiltered = false;
	            }
	        }

	        // Apply filter based on VIPCustomer selection
	        if (rdbtnIsVip.isSelected() && !(customer instanceof VIPCustomer)) {
	            isFiltered = false;
	        }

	        // Apply filter based on area selection
	        if (!areaBox.getSelectedItem().equals("Any")) {
	            Area selectedArea = (Area) areaBox.getSelectedItem();
	            if (customer.getArea() != selectedArea) {
	                isFiltered = false;
	            }
	        }

	        // Apply filter based on year of joining selection
	        if (!yearBox.getSelectedItem().equals("Any")) {
	            int selectedYear = (int) yearBox.getSelectedItem();
	            if (customer.getYearOfBirth() != selectedYear) {
	                isFiltered = false;
	            }
	        }
	        // Apply filter based on customer ID selection
	        if (!customersBox.getSelectedItem().equals("Any")) {
	            String selectedCustomerId = (String) customersBox.getSelectedItem();
	            if (!customer.getId().equals(selectedCustomerId)) {
	                isFiltered = false;
	            }
	        }

	        if (isFiltered) {
	            filteredCustomers.append(customer.toString()).append("\n");
	        }
	    }

	    // Set the textPane content with the filtered customers
	    textPane.setText(filteredCustomers.toString());
	}
	
	
}


