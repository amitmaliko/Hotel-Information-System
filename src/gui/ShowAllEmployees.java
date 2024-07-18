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

import model.DepartmentManager;
import model.Employee;
import model.Hotel;
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
 * JInternalFrame To Display All existing Employees <br>
 * Using Employees Hash map from hotel and displays its toString() to textPane<br>
 * see below the FILTER bonus.
 * 
 * @author Amit Malik 207850074
 */
public class ShowAllEmployees extends JInternalFrame {
	HashMap<String, Employee> employeesHashmap = Main.hotel.getAllEmployees();
	JTextPane textPane = new JTextPane();
	JRadioButton rdbtnIsManager = new JRadioButton("Show only Managers");
	JComboBox areaBox = new JComboBox();
	JComboBox genderBox = new JComboBox();
    JComboBox departmentBox = new JComboBox();
    JComboBox yearBox = new JComboBox();
    JComboBox idBox = new JComboBox();

	public ShowAllEmployees() {
				
        setTitle("All Employees toString()");
        setSize(587, 513);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        
        JLabel lblHeadLabel = new JLabel("All Employees:");
        lblHeadLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        JButton btnExit = new JButton("");
        btnExit.setBorder(null);
        btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 9));
	    btnExit.addActionListener(e -> {
		   dispose();
		});
        
        JScrollPane scrollPane = new JScrollPane();
        
        JLabel lblFilter = new JLabel("Choose Filter :");
        lblFilter.setFont(new Font("Tahoma", Font.BOLD, 11));
        JLabel lblGenderFilter = new JLabel("Gender");
        JLabel lblAreaFilter = new JLabel("Area");
        JLabel lblDepartment = new JLabel("Department");      
        JLabel lblYearOfBirth = new JLabel("Year Of Birth");
        
        JLabel lblGetEmp = new JLabel("or Get by ID:");
        lblGetEmp.setFont(new Font("Tahoma", Font.BOLD, 11));
   
       
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(39)
        			.addComponent(lblFilter, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(458, Short.MAX_VALUE))
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(18)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblHeadLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
        				.addComponent(rdbtnIsManager))
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(lblGenderFilter, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
        						.addComponent(genderBox, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_panel.createSequentialGroup()
        							.addGap(29)
        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        								.addGroup(gl_panel.createSequentialGroup()
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(areaBox, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
        								.addComponent(lblAreaFilter, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
        							.addGap(18)
        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        								.addGroup(gl_panel.createSequentialGroup()
        									.addComponent(lblDepartment, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        									.addPreferredGap(ComponentPlacement.RELATED))
        								.addGroup(gl_panel.createSequentialGroup()
        									.addComponent(departmentBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        									.addGap(42)))
        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        								.addComponent(yearBox, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblYearOfBirth, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
        							.addGap(96))
        						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
        							.addGap(59))))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(lblGetEmp, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(idBox, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())))
        		.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        					.addComponent(lblGenderFilter)
        					.addComponent(lblAreaFilter)
        					.addComponent(lblDepartment)
        					.addComponent(lblYearOfBirth))
        				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        					.addComponent(lblHeadLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblFilter)
        				.addComponent(genderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(areaBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(departmentBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(yearBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(rdbtnIsManager)
        				.addComponent(lblGetEmp)
        				.addComponent(idBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        
        
        scrollPane.setViewportView(textPane);
        panel.setLayout(gl_panel);
        
		
		textPane.setText(employeesHashmap.values().toString());

/**
 * Filter Bonus:
 */
		// Initialize the areaBox with Area enums
		Area[] areas = Area.values();
		areaBox.addItem("Any");
		for (Area area : areas) {
		    areaBox.addItem(area);
		}

		// Initialize the genderBox with Gender enums
		Gender[] genders = Gender.values();
		genderBox.addItem("Any");
		for (Gender gender : genders) {
		    genderBox.addItem(gender);
		}

		// Initialize the departmentBox with unique departments from employeesHashmap
		HashSet<String> departmentsSet = new HashSet<>();
		for (Employee employee : employeesHashmap.values()) {
		    departmentsSet.add(employee.getDepartment().getDepartmentId());
		}
		departmentBox.addItem("Any");
		for (String departmentID : departmentsSet) {
		    departmentBox.addItem(departmentID);
		}

		// Initialize the yearBox with unique years from employeesHashmap
		HashSet<Integer> yearsSet = new HashSet<>();
		for (Employee employee : employeesHashmap.values()) {
		    yearsSet.add(employee.getYearOfBirth());
		}
		yearBox.addItem("Any");
		for (Integer year : yearsSet) {
		    yearBox.addItem(year);
		}
		
		// Initialize the idBox with unique employee IDs from employeesHashmap
		idBox.addItem("Any");
		for (String employeeId : employeesHashmap.keySet()) {
		    idBox.addItem(employeeId);
		}

		// Add action listeners to the combo boxes and radio button to apply filters
		areaBox.addActionListener(e -> updateFilteredEmployees());
		genderBox.addActionListener(e -> updateFilteredEmployees());
		departmentBox.addActionListener(e -> updateFilteredEmployees());
		yearBox.addActionListener(e -> updateFilteredEmployees());
		rdbtnIsManager.addActionListener(e -> updateFilteredEmployees());
		idBox.addActionListener(e -> updateFilteredEmployees());
   

   }
	
	// Method to update the textPane with the filtered employees
			private void updateFilteredEmployees() {
			    StringBuilder filteredEmployees = new StringBuilder();

			    for (Employee employee : employeesHashmap.values()) {
			        boolean isFiltered = true;


			        // Apply filter based on department manager selection
			        if (rdbtnIsManager.isSelected() && !(employee instanceof DepartmentManager)) {
			            isFiltered = false;
			        }

			     // Apply filter based on area selection
			        if (!areaBox.getSelectedItem().equals("Any")) {
			            Area selectedArea = (Area) areaBox.getSelectedItem();
			            if (employee.getArea() != selectedArea) {
			                isFiltered = false;
			            }
			        }

			        // Apply filter based on gender selection
			        if (!genderBox.getSelectedItem().equals("Any")) {
			            Gender selectedGender = (Gender) genderBox.getSelectedItem();
			            if (employee.getGender() != selectedGender) {
			                isFiltered = false;
			            }
			        }

			        // Apply filter based on department selection
			        if (!departmentBox.getSelectedItem().equals("Any")) {
			            String selectedDepartment = (String) departmentBox.getSelectedItem();
			            if (!employee.getDepartment().getDepartmentId().equals(selectedDepartment)) {
			                isFiltered = false;
			            }
			        }

			        // Apply filter based on year selection
			        if (!yearBox.getSelectedItem().equals("Any")) {
			            int selectedYear = (int) yearBox.getSelectedItem();
			            if (employee.getYearOfBirth() != selectedYear) {
			                isFiltered = false;
			            }    
			            
			        }
			        // Apply filter based on employee ID selection
			        if (!idBox.getSelectedItem().equals("Any")) {
			            String selectedEmployeeId = (String) idBox.getSelectedItem();
			            if (!employee.getId().equals(selectedEmployeeId)) {
			                isFiltered = false;
			            }
			        }

			        if (isFiltered) {
			            filteredEmployees.append(employee.toString()).append("\n");
			        }
			    }

			    // Set the textPane content with the filtered employees
			    textPane.setText(filteredEmployees.toString());
			}
}


