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

import model.Room;
import model.StandardRoom;
import model.Suite;
import model.SuperiorRoom;
import model.Hotel;
import model.Room;
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
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
/**
 * JInternalFrame To Display All existing Rooms <br>
 * Using Rooms Hash map from hotel and displays its toString() to textPane<br>
 * see below the FILTER bonus.
 * 
 * @author Amit Malik 207850074
 */
public class ShowAllRooms extends JInternalFrame {
	HashMap<String, Room> roomsHashmap = Main.hotel.getAllRooms();
	JTextPane textPane = new JTextPane();
	JScrollPane scrollPane = new JScrollPane();
    JComboBox priceFilterBox = new JComboBox();
    JComboBox floorFilterBox = new JComboBox();
    JComboBox gradeFilterBox = new JComboBox();
    JComboBox sizeFilterBox = new JComboBox();
    JComboBox typeBox = new JComboBox();
    JComboBox roomsBox = new JComboBox();
	
	    public ShowAllRooms() {
	        setTitle("All Rooms toString()");
	        setSize(719, 513);
	        
	        JPanel panel = new JPanel();
	        getContentPane().add(panel, BorderLayout.CENTER);
	        
	        JLabel lblHeadLabel = new JLabel("All Rooms:");
	        lblHeadLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
	        
	        JButton btnExit = new JButton("");
	        btnExit.setBorder(null);
	        btnExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/exit.png"))));
	        btnExit.setFont(new Font("Tahoma", Font.BOLD, 9));
		    btnExit.addActionListener(e -> {
			   dispose();
			});
	        
	        JLabel lblChoose = new JLabel("Choose Filter :");
	        lblChoose.setFont(new Font("Tahoma", Font.BOLD, 11));
	        
	        JLabel lblPrice = new JLabel("Price");
	        
	        JLabel lblFloor = new JLabel("Floor");
	        
	        JLabel lblGrade = new JLabel("Grade");
	        
	        JLabel lblSize = new JLabel("Size");
	        	        
	        JLabel lblRoomType = new JLabel("Room Type:");
	        
	        JLabel lblGetRoom = new JLabel("or Get Room by Room Number:");
	        lblGetRoom.setFont(new Font("Tahoma", Font.BOLD, 11));
	        

	        GroupLayout gl_panel = new GroupLayout(panel);
	        gl_panel.setHorizontalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
	        			.addContainerGap())
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addGap(20)
	        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
	        				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
	        					.addGroup(gl_panel.createSequentialGroup()
	        						.addComponent(lblChoose, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
	        						.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
	        						.addComponent(priceFilterBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        					.addGroup(gl_panel.createSequentialGroup()
	        						.addComponent(lblHeadLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
	        						.addGap(30)
	        						.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
	        				.addComponent(lblGetRoom, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
	        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addGap(18)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addGap(28)
	        							.addComponent(lblFloor, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addGap(18)
	        							.addComponent(floorFilterBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	        					.addGap(20)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addComponent(gradeFilterBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        							.addGap(27)
	        							.addComponent(sizeFilterBox, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
	        							.addGap(9))
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addComponent(lblGrade, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
	        							.addGap(42)
	        							.addComponent(lblSize, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
	        							.addGap(27)))
	        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addGap(18)
	        							.addComponent(typeBox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
	        							.addContainerGap())
	        						.addGroup(gl_panel.createSequentialGroup()
	        							.addGap(22)
	        							.addComponent(lblRoomType, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
	        							.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
	        							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
	        							.addGap(27))))
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addPreferredGap(ComponentPlacement.UNRELATED)
	        					.addComponent(roomsBox, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
	        					.addContainerGap())))
	        );
	        gl_panel.setVerticalGroup(
	        	gl_panel.createParallelGroup(Alignment.LEADING)
	        		.addGroup(gl_panel.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
	        				.addComponent(lblHeadLabel, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
	        				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        					.addComponent(lblPrice)
	        					.addComponent(lblFloor)
	        					.addComponent(lblGrade)
	        					.addComponent(lblSize)
	        					.addComponent(lblRoomType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        				.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	        				.addGroup(gl_panel.createSequentialGroup()
	        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        						.addComponent(priceFilterBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(floorFilterBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(gradeFilterBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(sizeFilterBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        						.addComponent(typeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	        					.addGap(34)
	        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	        						.addComponent(lblGetRoom)
	        						.addComponent(roomsBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	        				.addComponent(lblChoose, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
	        			.addGap(17)
	        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
	        			.addGap(7))
	        );
	        
	        
	        scrollPane.setViewportView(textPane);
	        panel.setLayout(gl_panel);
	        
			
			textPane.setText(roomsHashmap.values().toString());
			
/**
* Filter Bonus:
 */		
	
			// Populate the combo boxes with the unique values for each field
			HashSet<Double> prices = new HashSet<>();
			HashSet<Integer> floors = new HashSet<>();
			HashSet<Double> grades = new HashSet<>();
			HashSet<Integer> sizes = new HashSet<>();

			for (Room room : roomsHashmap.values()) {
			    prices.add(room.getDailyPrice());
			    floors.add(room.getFloor());
			    grades.add(room.getRoomGrade());
			    sizes.add(room.getSize());
			}

			// Add the unique values to the combo boxes
			priceFilterBox.addItem("Any");
			for (Double price : prices) {
			    priceFilterBox.addItem(price);
			}

			floorFilterBox.addItem("Any");
			for (Integer floor : floors) {
			    floorFilterBox.addItem(floor);
			}

			gradeFilterBox.addItem("Any");
			for (Double grade : grades) {
			    gradeFilterBox.addItem(grade);
			}

			sizeFilterBox.addItem("Any");
			for (Integer size : sizes) {
			    sizeFilterBox.addItem(size);
			}
			
			// Initialize the typeBox with room types
			typeBox.addItem("Any");
			typeBox.addItem("StandardRoom");
			typeBox.addItem("SuperiorRoom");
			typeBox.addItem("Suite");
			
			// Initialize the roomsBox with unique room IDs from roomsHashmap
			roomsBox.addItem("Any");
			for (String roomId : roomsHashmap.keySet()) {
			    roomsBox.addItem(roomId);
			}


			// Add action listeners to the combo boxes and radio buttons to apply filters
			priceFilterBox.addActionListener(e -> updateFilteredRooms());
			floorFilterBox.addActionListener(e -> updateFilteredRooms());
			gradeFilterBox.addActionListener(e -> updateFilteredRooms());
			sizeFilterBox.addActionListener(e -> updateFilteredRooms());
			typeBox.addActionListener(e -> updateFilteredRooms());
			roomsBox.addActionListener(e -> updateFilteredRooms());


		
	     
	   

   }
	    
		// Method to update the textPane with the filtered rooms
		private void updateFilteredRooms() {
		    StringBuilder filteredRooms = new StringBuilder();	    

		    for (Room room : roomsHashmap.values()) {
		        boolean isFiltered = true;

		        // Apply filters based on combo box selections 
		        if (!priceFilterBox.getSelectedItem().equals("Any")) {
		        	Double selectedPrice = (Double) priceFilterBox.getSelectedItem();
		            if (room.getDailyPrice() != selectedPrice) {
		                isFiltered = false;
		            }
		        }

		        if (!floorFilterBox.getSelectedItem().equals("Any")) {
		            int selectedFloor = (int) floorFilterBox.getSelectedItem();
		            if (room.getFloor() != selectedFloor) {
		                isFiltered = false;
		            }
		        }

		        if (!gradeFilterBox.getSelectedItem().equals("Any")) {
		        	Double selectedGrade = (Double) gradeFilterBox.getSelectedItem();
		            if (room.getRoomGrade()!=(selectedGrade)) {
		                isFiltered = false;
		            }
		        }

		        if (!sizeFilterBox.getSelectedItem().equals("Any")) {
		            int selectedSize = (int) sizeFilterBox.getSelectedItem();
		            if (room.getSize() != selectedSize) {
		                isFiltered = false;
		            }
		        }

		     // Apply filter based on room type selection
		        if (!typeBox.getSelectedItem().equals("Any")) {
		            String selectedType = (String) typeBox.getSelectedItem();

		            // Reset the isFiltered flag to false initially.
		            isFiltered = false;

		            // Check if the room's class name matches the selected type.
		            if (room.getClass().getSimpleName().equals(selectedType)) {
		                isFiltered = true;
		            }
		        }      
		        
		        // Apply filter based on room ID selection
		        if (!roomsBox.getSelectedItem().equals("Any")) {
		            String selectedRoomId = (String) roomsBox.getSelectedItem();
		            if (!room.getRoomNumber().equals(selectedRoomId)) {
		                isFiltered = false;
		            }
		        }

		        if (isFiltered) {
		            filteredRooms.append(room.toString()).append("\n");
		        }
		    }

		    // Set the textPane content with the filtered rooms
		    textPane.setText(filteredRooms.toString());
		}
}


