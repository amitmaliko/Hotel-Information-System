package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import model.Department;
import model.Hotel;
import utils.Specialization;

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
import javax.swing.DropMode;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
/**
 * JInternalFrame To Display All existing Departments <br>
 * Using Departments Hash map from hotel and displays its toString() to textPane<br>
 * see below the FILTER bonus.
 * 
 * @author Amit Malik 207850074
 */
public class ShowAllDepartments extends JInternalFrame {
	HashMap<String, Department> departmentsHashmap = Main.hotel.getAllDepartments();
	JTextPane textPane = new JTextPane();
	 JComboBox specBox = new JComboBox();
     JComboBox depIdBox = new JComboBox();
	
	/**
	 * Create the frame.
	 */

	public ShowAllDepartments() {
				
		 setTitle("All Departments toString()");
	        setSize(587, 513);
	        
	        JPanel panel = new JPanel();
	        getContentPane().add(panel, BorderLayout.CENTER);
	        
	        JLabel lblHeadLabel = new JLabel("All Departments:");
	        lblHeadLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	        
	        JButton btnExit = new JButton("");
	        btnExit.setBorder(null);
	        btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
	        btnExit.setFont(new Font("Tahoma", Font.BOLD, 9));
		    btnExit.addActionListener(e -> {
			   dispose();
			});
	        
	        JScrollPane scrollPane = new JScrollPane();
	        
	        JLabel lblFilter = new JLabel("Choose Specialization :");
	        lblFilter.setFont(new Font("Tahoma", Font.BOLD, 11));
	        
	        JLabel lblGetDep = new JLabel("or Get Department by ID:");
	        lblGetDep.setFont(new Font("Tahoma", Font.BOLD, 11));
	        
	       
	        GroupLayout gl_panel = new GroupLayout(panel);
	        gl_panel.setHorizontalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
	        					.addContainerGap())
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addGap(12)
	        							.addComponent(lblHeadLabel, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addComponent(lblFilter)
	        							.addGap(18)
	        							.addComponent(specBox, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
	        					.addPreferredGap(ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addComponent(lblGetDep, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
	        							.addPreferredGap(ComponentPlacement.RELATED)
	        							.addComponent(depIdBox, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
	        						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
	        					.addGap(29))))
	        );
	        gl_panel.setVerticalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addGap(20)
	        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addComponent(lblHeadLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
	        					.addPreferredGap(ComponentPlacement.RELATED)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        						.addComponent(lblGetDep)
	        						.addComponent(lblFilter)
	        						.addComponent(specBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(depIdBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	        				.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
	        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap())
	        );
	        
	        
	        scrollPane.setViewportView(textPane);
	        panel.setLayout(gl_panel);
	        
			
			textPane.setText(departmentsHashmap.values().toString());
			
/**
 * Filter Bonus:
 */

			// Initialize the specBox with Specialization values
			Specialization[] specValues = Specialization.values();
			specBox.addItem("Any");
			for (Specialization specialization : specValues) {
			    specBox.addItem(specialization);
			}
			// Initialize the depIdBox with unique department IDs from departmentsHashmap
			depIdBox.addItem("Any");
			for (String departmentId : departmentsHashmap.keySet()) {
			    depIdBox.addItem(departmentId);
			}

			// Add action listeners to apply filters
			specBox.addActionListener(e -> updateFilteredDepartments());
			depIdBox.addActionListener(e -> updateFilteredDepartments());
		
	   

   }
	
	// Method to update the textPane with the filtered departments
				private void updateFilteredDepartments() {
				    StringBuilder filteredDepartments = new StringBuilder();

				    for (Department department : departmentsHashmap.values()) {
				        boolean isFiltered = true;

				        // Apply filter based on specialization selection
				        if (!specBox.getSelectedItem().equals("Any")) {
				            Specialization selectedSpec = (Specialization) specBox.getSelectedItem();
				            if (department.getSpecialization() != selectedSpec) {
				                isFiltered = false;
				            }
				        }
				        // Apply filter based on department ID selection
				        if (!depIdBox.getSelectedItem().equals("Any")) {
				            String selectedDepartmentId = (String) depIdBox.getSelectedItem();
				            if (!department.getDepartmentId().equals(selectedDepartmentId)) {
				                isFiltered = false;
				            }
				        }

				        if (isFiltered) {
				            filteredDepartments.append(department.toString()).append("\n");
				        }
				    }

				    // Set the textPane content with the filtered departments
				    textPane.setText(filteredDepartments.toString());
				}
}


