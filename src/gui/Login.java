package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import exceptions.InvalidLoginData;
import main.Main;
import model.Hotel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;


/**
 * This is a JFrame for system Login 
 * You can log in as an Admin or as an Employee
 * 
 * @author Amit Malik 207850074
 *
 * @see AdminFrame
 * @see EmployeeFrame
 */
public class Login extends JFrame {


	private static final long serialVersionUID = 1L;
	private JTextField userNameTXT;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private static Hotel HOTEL;
	private JPanel loginPanel;

	/**
	 * Creates new Login Frame
	 */
	public Login() {
		
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

                }

            }
        });
		
		Image icon = Toolkit.getDefaultToolkit().getImage("resources/hotel_icon.png");
		this.setIconImage(icon);
		
		setResizable(false);
		setFont(new Font("Varela Round", Font.PLAIN, 14));
		getContentPane().setBackground(new Color(176, 196, 222));
		setTitle("System Login");
		getContentPane().setLayout(null);
		setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2.43),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2.68), 320, 200);
		initialize();
	}

	/**
	 * initializes the LoginFrame
	 */
	private void initialize() {
		setHotel();
		setUserNameTXT();
		setPasswordField();
		setBtnLogin();
		setLblUserName();
		setLblPassword();
		setPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	public void setPanel() {
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(245, 255, 250));
		loginPanel.setBounds(10, 10, 284, 139);
		loginPanel.add(userNameTXT);
		loginPanel.add(passwordField);
		loginPanel.add(btnLogin);
		loginPanel.add(lblUserName);
		loginPanel.add(lblPassword);
		loginPanel.setLayout(null);
		getContentPane().add(loginPanel);

		final JButton btnHelp = new JButton();
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		/*
		 * Help "?" Button:
		 * Displays instructions and plays "Ma Haluz?"
		 */
		btnHelp.setToolTipText("Click for help");
		btnHelp.addActionListener(e -> {
			SoundFile.maHaluz();
			JOptionPane.showMessageDialog(null,
					"Your Username is your ID.\nYour password was given to you by the Administrator.", "Help!",
					JOptionPane.INFORMATION_MESSAGE);
		});
		btnHelp.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				btnHelp.transferFocus();
			}
		});
		btnHelp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/help.png"))));
		btnHelp.setBounds(255, 20, 25, 23);
		loginPanel.add(btnHelp);
		
		/*
		 * Hide / Show Password toggle button:
		 */
		JToggleButton tglbtnShowPass = new JToggleButton("");
		tglbtnShowPass.setBackground(new Color(255, 255, 255));
		tglbtnShowPass.setBounds(255, 52, 25, 20);
		loginPanel.add(tglbtnShowPass);
		ImageIcon show = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/Eye.png")));
		ImageIcon hide = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/hideEye.png")));
		tglbtnShowPass.setIcon(show);
		tglbtnShowPass.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    passwordField.setEchoChar((char) 0); // Show password as plain text
                    tglbtnShowPass.setIcon(hide);
                } else {
                    passwordField.setEchoChar('•'); // Hide password with asterisks
                    tglbtnShowPass.setIcon(show);
                }
            }
        });
		

	}

	/**
	 * @param userNameTXT the userNameTXT to set
	 */
	public void setUserNameTXT() {
		userNameTXT = new JTextField();
		userNameTXT.setBounds(90, 21, 161, 20);
		userNameTXT.setColumns(10);

	}

	/**
	 * @param passwordField the passwordField to set
	 */
	public void setPasswordField() {
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 52, 161, 20);

	}

	private String getPasswordField() {
		String password = "";
		for (final char c : passwordField.getPassword())
			password += c;
		return password;
	}

	/**
	 * @param btnLogin the btnLogin to set
	 */
	public void setBtnLogin() {
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.setBounds(95, 105, 89, 23);
		btnLogin.addActionListener(e -> loginAction());

	}

	/**
	 * @param lblUserName the lblUserName to set
	 */
	public void setLblUserName() {
		lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(5, 24, 111, 14);
	}

	/**
	 * @param lblPassword the lblPassword to set
	 */
	public void setLblPassword() {
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(5, 55, 106, 14);
	}

	/**
	 * Method to check if the typed details are of an Admin, Employee or neither
	 * 
	 * @return true if admin, false if Employee
	 * @throws InvalidDetailsException if it's neither an Admin or Employee
	 */
	private boolean checkAdmin() throws InvalidLoginData {
		if (userNameTXT.getText().compareTo("admin") == 0 && getPasswordField().compareTo("admin") == 0)
			return true;
		if (getHotel().getAllEmployees().containsKey(userNameTXT.getText()))
			if (getHotel().getAllEmployees().get(userNameTXT.getText()).getPassword()
					.compareTo(getPasswordField()) == 0)
				return false;
		throw new InvalidLoginData();
	}

	/**
	 * @return the system (Hotel)
	 */
	public static Hotel getHotel() {
		return HOTEL;
	}

	/**
	 * @param  the system to set (Hotel)
	 */
	private static void setHotel() {
		HOTEL = Main.hotel;
	}

	/**
	 * the login action, checks if an Admin tries to log in.
	 * if not, it's either an Employee or an Exception <br>
	 * If it's an Admin loads a new AdminFrame<br>
	 * If it's an Employee loads a new EmployeeFrame<br>
	 * If it's an exception it pops an Error window to check the typed details
	 */
	private void loginAction() {
		try {
			if (checkAdmin()) {
				final AdminFrame newFrame = new AdminFrame();
				newFrame.setVisible(true);
				setVisible(false);
				dispose();
			} else {
				final EmployeeFrame newFrame = new EmployeeFrame(
						getHotel().getAllEmployees().get(userNameTXT.getText()));
				newFrame.setVisible(true);
				setVisible(false);
				dispose();
			}

		} catch (final InvalidLoginData exception) {
			JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}
}		
