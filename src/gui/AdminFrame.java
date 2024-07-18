package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import main.Main;
import model.Customer;
import model.Department;
import model.Employee;
import model.StandardRoom;
import model.Suite;
import model.SuperiorRoom;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;

import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JDesktopPane;
import javax.swing.JToggleButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
/**
 * This is the Main Frame, After Login this Frame opens.<br>
 * All actions done through it's menu bar <br>
 * Admin Frame allows all actions to be performed.
 * @see EmployeeFrame<br>
 *  that extends from this main frame with some restrictions.
 *  
 * @author Amit Malik 207850074
 *
 */
public class AdminFrame extends JFrame {
	
	private AdminFrame adminFrame;
	private JMenuItem addNewEmpMenu;
	private JMenuItem removeEmpMenu;
	private JMenu systemMenu;
	/**
	 * Creating audio player for lobby music to play in background<br>
	 * Creating New Desktop Pane for all internal components
	 * @see LobbyMusic 
	 * @see MyDesktopPane
	 */
	LobbyMusic audioPlayer = new LobbyMusic();
	MyDesktopPane myDesktopPane = new MyDesktopPane("resources/HotelVideo.gif", 1200, 800);

	public AdminFrame() {
		setTitle("Welcome HRS Hotel ADMIN ! ");
		/**
		 * Loading Hotel Lobby Music
		 */
		try {      
            // Load the WAV file          
            audioPlayer.loadAudio("resources/HotelLobby.wav");

            // Start audio playback
            audioPlayer.play();
		}catch (Exception e) {
			SoundFile.totah(); //"Plan B" if lobby music fails (play another sound)
		}
		
		/**
		 * If Window Closed: While pressing 'x' checks if user want to close the system. 
		 * if yes, Saves Hotel and closes window:
		 */
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Set DO_NOTHING_ON_CLOSE to handle closing manually
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    // Perform the save operation before closing the frame
                    Main.save();
                    // Exit the application after saving
                    System.exit(0);
                    //Stops Lobby Music
                    audioPlayer.stop();
                    audioPlayer.close();
                }
                // If the user chooses not to exit, do nothing (the window will stay open)
            }
        });
    
		
		//Add custom desktop pane to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(myDesktopPane);
        myDesktopPane.setLayout(null);
        
        //TurnOff music Button:
        JToggleButton tglbtnSoundOff = new JToggleButton("");
        tglbtnSoundOff.setBounds(0, 0, 40, 40);
        myDesktopPane.add(tglbtnSoundOff);
        ImageIcon show = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/soundON.png")));
		ImageIcon hide = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/soundOFF.png")));
		tglbtnSoundOff.setIcon(show);
		tglbtnSoundOff.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                	audioPlayer.stop();
                	tglbtnSoundOff.setIcon(hide);
                } else {
                	audioPlayer.play();
                	tglbtnSoundOff.setIcon(show);
                }
            }
        });
        

        
        getContentPane().add(scrollPane);
        
        pack();
        setLocationRelativeTo(null);
        
	//Gets Screen Dimension to open frame in middle:
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);		
		scrollPane.setSize(850, 900);
		this.setPreferredSize(new Dimension(850,900));
		
		//Sets Hotel icon:
		Image icon = Toolkit.getDefaultToolkit().getImage("resources/hotel_icon.png");
		this.setIconImage(icon);

	
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/**
		 * Adding Menu components:
		 */
		
		//LOGOUT Menu:
		systemMenu = new JMenu("");
		systemMenu.setIcon(new ImageIcon("resources/Logout.png"));
		systemMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		menuBar.add(systemMenu);
		
		final JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(e -> {
            audioPlayer.stop();
            audioPlayer.close();
			SoundFile.byeBye();
			final Login newFrame = new Login();
			newFrame.setVisible(true);
			setVisible(false);
		});
		mntmLogout.setFont(new Font("SansSerif", Font.PLAIN, 15));
		systemMenu.add(mntmLogout);
	

	   /**
	    * Add New Menu:
	    */
		
		JMenu addNewMenu = new JMenu("Add New ▼");
		addNewMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		addNewMenu.setIcon(new ImageIcon("resources/AddNew.png"));
		menuBar.add(addNewMenu);		
		/**
		 * Add New Room Menu:
		 */
		final JMenu addRoomMenu = new JMenu("Room");
		addRoomMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		addRoomMenu.setIcon(new ImageIcon("resources/Room.png"));
		addRoomMenu.setIconTextGap(5);
		addNewMenu.add(addRoomMenu);
		
		//Add new Standard Room
		final JMenuItem addStandardMenu = new JMenuItem("Standard Room");
		addStandardMenu.setIcon(new ImageIcon("resources/StandradRoom.png"));
		addStandardMenu.setIconTextGap(0);
		addStandardMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		addStandardMenu.addActionListener(e -> {
		openAddStandardFrame();	
		});
		addRoomMenu.add(addStandardMenu);
		
		//Add new Superior Room
		final JMenuItem addSuperiorMenu = new JMenuItem("Superior Room");
		addSuperiorMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		addSuperiorMenu.setIcon(new ImageIcon("resources/SuperiorRoom.png"));
		addSuperiorMenu.addActionListener(e -> {
		openAddSuperiorFrame();	
		});
		addRoomMenu.add(addSuperiorMenu);
		
		//Add new Suite
		final JMenuItem addSuiteMenu = new JMenuItem("Suite");
		addSuiteMenu.setIcon(new ImageIcon("resources/Suite.png"));
		addSuiteMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		addSuiteMenu.addActionListener(e -> {
			openAddSuiteFrame();
		});
		addRoomMenu.add(addSuiteMenu);

		/**
		 * Add New Customer Menu:
		 */
		final JMenuItem addCustomerMenu = new JMenuItem("Customer");
		addCustomerMenu.setIcon(new ImageIcon("resources/AddCustomer.png"));
		addCustomerMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		addCustomerMenu.addActionListener(e -> {
			openAddCustomerFrame();
		});
		addNewMenu.add(addCustomerMenu);
		/**
		 * Add New Booking Menu:
		 */	
		final JMenuItem addBookingMenu = new JMenuItem("Booking");
		addBookingMenu.setIcon(new ImageIcon("resources/AddBooking.png"));
		addBookingMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		addBookingMenu.addActionListener(e -> {
			openAddBookingFrame();
		});
		addNewMenu.add(addBookingMenu);	
		/**
		 * Add New Department Menu:
		 */
		final JMenuItem addNewDepMenu = new JMenuItem("Department");
		addNewDepMenu.setIcon(new ImageIcon("resources/AddDepartment.png"));
		addNewDepMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		addNewDepMenu.addActionListener(e -> {
			openAddDepartmentFrame();
		});
		addNewMenu.add(addNewDepMenu);	
		
		/**
		 * Add New Employee Menu:
		 */
		addNewEmpMenu = new JMenuItem("Employee");
		addNewEmpMenu.setIcon(new ImageIcon("resources/AddEmployee.png"));
		addNewEmpMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		addNewEmpMenu.addActionListener(e -> {
			openAddEmployeeFrame();
		});
		addNewMenu.add(addNewEmpMenu);
		
		
		/**
		 * Remove Menu:
		 */
		JMenu removeMenu = new JMenu("Remove From System ▼");
		removeMenu.setIcon(new ImageIcon("resources/Remove.png"));
		removeMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		menuBar.add(removeMenu);
//Remove Room:
		final JMenuItem removeRoomMenu = new JMenuItem("Remove Room");
		removeRoomMenu.setIcon(new ImageIcon("resources/RemoveRoom.png"));
		removeRoomMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		removeRoomMenu.addActionListener(e -> {
			openRemoveRoom();		
		});
		removeMenu.add(removeRoomMenu);
		
//Remove Customer:
		final JMenuItem removeCustomerMenu = new JMenuItem("Remove Customer");
		removeCustomerMenu.setIcon(new ImageIcon("resources/RemoveCustomer.png"));
		removeCustomerMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		removeCustomerMenu.addActionListener(e -> {
			openRemoveCustomer();
		});
		removeMenu.add(removeCustomerMenu);
//Remove Booking:
		final JMenuItem removeBookingMenu = new JMenuItem("Remove Booking");
		removeBookingMenu.setIcon(new ImageIcon("resources/RemoveBooking.png"));
		removeBookingMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		removeBookingMenu.addActionListener(e -> {
			openRemoveBooking();
		});
		removeMenu.add(removeBookingMenu);
//Remove Department:
		final JMenuItem removeDepMenu = new JMenuItem("Remove Department");
		removeDepMenu.setIcon(new ImageIcon("resources/RemoveDepartment.png"));
		removeDepMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		removeDepMenu.addActionListener(e -> {
			openRemoveDepartment();
		});
		removeMenu.add(removeDepMenu);
		
//Remove Employee:
		removeEmpMenu = new JMenuItem("Remove Employee");
		removeEmpMenu.setIcon(new ImageIcon("resources/RemoveEmployee.png"));
		removeEmpMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		removeEmpMenu.addActionListener(e -> {
			openRemoveEmployee();
		});
		removeMenu.add(removeEmpMenu);
		
		
		/**
		 * Get/Show All Menu:
		 */
		JMenu getAllMenu = new JMenu("Show All ▼");
		getAllMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		getAllMenu.setIcon(new ImageIcon("resources/ShowAll.png"));
		menuBar.add(getAllMenu);
//Show all Rooms:		
		JMenuItem roomsMenu = new JMenuItem("Rooms");
		roomsMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		roomsMenu.setIcon(new ImageIcon("resources/rooms.png"));
		roomsMenu.addActionListener(e -> {
			openShowAllRooms();
		});
		getAllMenu.add(roomsMenu);
//Show all Customers:	
		final JMenuItem customersMenu = new JMenuItem("Customers");
		customersMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		customersMenu.setIcon(new ImageIcon("resources/customers.png"));
		customersMenu.addActionListener(e -> {
			openShowAllCustomers();
		});
		getAllMenu.add(customersMenu);
//Show all Bookings:	
		final JMenuItem bookingMenu = new JMenuItem("Bookings");
		bookingMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		bookingMenu.setIcon(new ImageIcon("resources/bookings.png"));
		bookingMenu.addActionListener(e -> {
			openShowAllBookings();
		});
		getAllMenu.add(bookingMenu);
//Show all Departments:	
		final JMenuItem departmentsMenu = new JMenuItem("Departments");
		departmentsMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		departmentsMenu.setIcon(new ImageIcon("resources/departments.png"));
		departmentsMenu.addActionListener(e -> {
			openShowAllDepartments();
		});
		getAllMenu.add(departmentsMenu);
//Show all Employees:	
		final JMenuItem employeeMenu = new JMenuItem("Employees");
		employeeMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		employeeMenu.setIcon(new ImageIcon("resources/employees.png"));
		employeeMenu.addActionListener(e -> {
			openShowAllEmployees();
		});
		getAllMenu.add(employeeMenu);
		
		
		/**
		 * Queries Menu: 
		 */
		JMenu queriesMenu = new JMenu("Hotel Queries ▼");
		queriesMenu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		queriesMenu.setIcon(new ImageIcon("resources/query.png"));
		menuBar.add(queriesMenu);
		
		//kEmployees
		final JMenuItem kEmployees = new JMenuItem("kEmployees");
		kEmployees.setFont(new Font("SansSerif", Font.PLAIN, 15));
		kEmployees.addActionListener(e -> {
			openKEmployeesFrame();
		});
		queriesMenu.add(kEmployees);
		
		//allCustomersByPK
		final JMenuItem allCustomersByPK = new JMenuItem("allCustomersByPK");
		allCustomersByPK.setFont(new Font("SansSerif", Font.PLAIN, 15));
		allCustomersByPK.addActionListener(e -> {
			openQueriesFrame("allCustomersByPK");
		});
		queriesMenu.add(allCustomersByPK);
		
		//allBookingByRevenue
		final JMenuItem allBookingByRevenue = new JMenuItem("allBookingByRevenue");
		allBookingByRevenue.setFont(new Font("SansSerif", Font.PLAIN, 15));
		allBookingByRevenue.addActionListener(e -> {
			openQueriesFrame("allBookingByRevenue");
		});
		queriesMenu.add(allBookingByRevenue);
		
		//allCustomersCmp
		final JMenuItem allCustomersCmp = new JMenuItem("allCustomersCmp");
		allCustomersCmp.setFont(new Font("SansSerif", Font.PLAIN, 15));
		allCustomersCmp.addActionListener(e -> {
			openQueriesFrame("allCustomersCmp");
		});
		queriesMenu.add(allCustomersCmp);
		
		//totalProfit
		final JMenuItem totalProfit = new JMenuItem("totalProfit");
		totalProfit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		totalProfit.addActionListener(e -> {
			openQueriesFrame("totalProfit");
		});
		queriesMenu.add(totalProfit);
		
		//allBookingsOfSpecCustomer
		final JMenuItem allBookingsOfSpecCustomer = new JMenuItem("allBookingsOfSpecCustomer");
		allBookingsOfSpecCustomer.setFont(new Font("SansSerif", Font.PLAIN, 15));
		allBookingsOfSpecCustomer.addActionListener(e -> {
			openAllBookingsOfSpecCustomer();
		});
		queriesMenu.add(allBookingsOfSpecCustomer);
		
		//customerBookedTheMostRooms
		final JMenuItem customerBookedTheMostRooms = new JMenuItem("customerBookedTheMostRooms");
		customerBookedTheMostRooms.setFont(new Font("SansSerif", Font.PLAIN, 15));
		customerBookedTheMostRooms.addActionListener(e -> {
			openQueriesFrame("customerBookedTheMostRooms");
		});
		queriesMenu.add(customerBookedTheMostRooms);

		//Room UPGRADE
		final JMenuItem upgradeInterface = new JMenuItem("Room UPGRADE");
		upgradeInterface.setFont(new Font("SansSerif", Font.PLAIN, 15));
		upgradeInterface.addActionListener(e -> {
			openUpgradeRoom();
		});
		queriesMenu.add(upgradeInterface);

		
		//Bonus Word
		JMenu bonusWord = new JMenu("Write To File▼");
		bonusWord.setFont(new Font("SansSerif", Font.PLAIN, 15));
		bonusWord.setIcon(new ImageIcon("resources/Write.png"));
		menuBar.add(bonusWord);
		final JMenuItem bookingRevenue = new JMenuItem("Bookings Revenue");
		bookingRevenue.setFont(new Font("SansSerif", Font.PLAIN, 15));
		bookingRevenue.addActionListener(e -> {WriteBookingsProfitToDoc.writeBookingDataToDocx();
		});
		bonusWord.add(bookingRevenue);	
		
		
 
	
	}
	
	/**
	 * Functions: 
	 */
	
	/**
	 * Getters for Frame and menu components, in order to get & restrict access to "son" EmployeeFrame
	 * 
	 */
	public AdminFrame getAdminFrame() {
		return adminFrame;
	}
	
	public JMenuItem getAddEmployee() {
		return addNewEmpMenu;
	}
	public JMenuItem getRemoveEmployee() {
		return removeEmpMenu;
	}
	public JMenuItem getSystemMenu() {
		return systemMenu;
	}	
	
	
	/**
	 * Opens Relevant "ADD" IntrenalJframe :
	 */	
	 private void openAddStandardFrame() {
	        AddStandardRoomFrame newFrame = new AddStandardRoomFrame();
	        myDesktopPane.add(newFrame);
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
	    }
	 
	 private void openAddSuperiorFrame() {
		    AddSuperiorRoomFrame newFrame = new AddSuperiorRoomFrame();
	        myDesktopPane.add(newFrame);
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
	    }
	 private void openAddSuiteFrame() {
		    AddSuiteFrame newFrame = new AddSuiteFrame();
	        myDesktopPane.add(newFrame);
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
	    }	 
	 private void openAddCustomerFrame() {
		    AddCustomerFrame newFrame = new AddCustomerFrame();
	        myDesktopPane.add(newFrame);
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
	 }
	 
//To Add Booking, we first ensures that all needed object exists to fill the form	 
	 private void openAddBookingFrame() {
		 if(!Main.hotel.getAllRooms().isEmpty()||Main.hotel.getAllCustomers().isEmpty()) {
		 	AddBookingFrame newFrame = new AddBookingFrame();
	        myDesktopPane.add(newFrame);
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
		 } else {
			 JOptionPane.showMessageDialog(AdminFrame.this,"Please Make sure you added at least one Room and Customer to system\nCan't Create Booking without them :(", "Missing Objects!", JOptionPane.ERROR_MESSAGE);
			 return;
		 }
		 
	}
	 private void openAddDepartmentFrame() {
		 	AddDepartmentFrame newFrame = new AddDepartmentFrame();
	        myDesktopPane.add(newFrame);
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
	 }
//To Add Employee, we first ensures that all needed object exists to fill the form
	 private void openAddEmployeeFrame() {
		 if(!Main.hotel.getAllDepartments().isEmpty()) {
		 	AddEmployeeFrame newFrame = new AddEmployeeFrame();
	        myDesktopPane.add(newFrame);
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
		 }else {
			 JOptionPane.showMessageDialog(AdminFrame.this,"Please Make sure you added Department\nCan't Create Employee without Any :(", "Missing Objects!", JOptionPane.ERROR_MESSAGE);
			 return;
		 }
	 }
	 
		/**
		 * Opens Relevant "REMOVE" IntrenalJframe :
		 * Firstly checks if there are objects to remove..
		 */
	 private void openRemoveRoom() {
		 if(!Main.hotel.getAllRooms().isEmpty()) {
		 	RemoveRoom newFrame = new RemoveRoom();
	        myDesktopPane.add(newFrame);
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
		 }else {
			 JOptionPane.showMessageDialog(AdminFrame.this,"No Rooms in Hotel system", "Nothing to remove!", JOptionPane.ERROR_MESSAGE);
			 return;
		 }
	 }
	 private void openRemoveCustomer() {
		 if(!Main.hotel.getAllCustomers().isEmpty()) {
		 	RemoveCustomer newFrame = new RemoveCustomer();
	        myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
		 }else {
			 JOptionPane.showMessageDialog(AdminFrame.this,"No Customers in Hotel system", "Nothing to remove!", JOptionPane.ERROR_MESSAGE);
			 return;
		 }
	 }
	 private void openRemoveBooking() {
		 if(!Main.hotel.getAllBookings().isEmpty()) {
		 	RemoveBooking newFrame = new RemoveBooking();
	        myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
		 }else {
			 JOptionPane.showMessageDialog(AdminFrame.this,"No Bookings in Hotel system", "Nothing to remove!", JOptionPane.ERROR_MESSAGE);
			 return;
		 }
	 }
	 private void openRemoveDepartment() {
		 if(!Main.hotel.getAllDepartments().isEmpty()) {
		 	RemoveDepartment newFrame = new RemoveDepartment();
	        myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
		 }else {
			 JOptionPane.showMessageDialog(AdminFrame.this,"No Departments in Hotel system", "Nothing to remove!", JOptionPane.ERROR_MESSAGE);
			 return;
		 }
	 }
	 
	 private void openRemoveEmployee() {
		 if(!Main.hotel.getAllEmployees().isEmpty()) {
		 	RemoveEmployee newFrame = new RemoveEmployee();
		    myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
		 }else {
			 JOptionPane.showMessageDialog(AdminFrame.this,"No Employees in Hotel system", "Nothing to remove!", JOptionPane.ERROR_MESSAGE);
			 return;
		 }
	 }
	 
	 /**
	  * Opens Relevant "Show All" IntrenalJframe :
	  */
	 
	 private void openShowAllRooms() {
		 	ShowAllRooms newFrame = new ShowAllRooms();
		    myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
	 }
	 
	 private void openShowAllEmployees() {
		 	ShowAllEmployees newFrame = new ShowAllEmployees();
		 	myDesktopPane.add(newFrame);	     	
	     	int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	     	int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	     	newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
	 }	
	 
	 private void openShowAllBookings() {
		 	ShowAllBookings newFrame = new ShowAllBookings();
		    myDesktopPane.add(newFrame);	  
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);	        
	 }	  
	 
	 private void openShowAllDepartments() {
		 	ShowAllDepartments newFrame = new ShowAllDepartments();
		    myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
	 }	
	 
	 private void openShowAllCustomers() {
		 	ShowAllCustomers newFrame = new ShowAllCustomers();
		    myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);
	 }
	 
	 
	 private void openKEmployeesFrame() {
		 	KEmployeesFrame newFrame = new KEmployeesFrame();
		    myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true); 
	 }

	 //Generic Frame for queries that displayed similar and do not need input
	 private void  openQueriesFrame(String whatToRun) {
		 	QueriesFrame newFrame = new QueriesFrame(whatToRun);
		    myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true); 
	 }
	 
	 private void  openAllBookingsOfSpecCustomer() {
		 	AllBookingsOfSpecCustomer newFrame = new AllBookingsOfSpecCustomer();
		    myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true); 
	 } 
	 
	 private void  openUpgradeRoom() {
		 	UpgradeRoom newFrame = new UpgradeRoom();
		    myDesktopPane.add(newFrame);	        
	        int x = (myDesktopPane.getWidth() - newFrame.getWidth()) / 2;
	        int y = (myDesktopPane.getHeight() - newFrame.getHeight()) / 2;
	        newFrame.setLocation(x, y);
	        newFrame.setVisible(true);	 
	 }


}