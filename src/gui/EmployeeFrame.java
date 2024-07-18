package gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Employee;
import gui.SoundFile;
/**
 * This Frame extends Admin MAIN Frame , but limits the Employee that logged in <br>
 * He can't add/remove other employees .<br>
 * Also, the Employee Frame displays his Profile picture (If defined in creation).
 * @author Amit Malik 207850074
 *
 */
public class EmployeeFrame extends AdminFrame {

	private static final String NO_PERMISSION_TEXT = "You have no permission to access this window!";
	private static final String NO_PERMISSION_TITLE = "No permission!";
	private Employee user;
	private JLabel lblIcon;

	public EmployeeFrame(Employee user) {		
		super();
		//Greets employee in window title:
		setTitle("Hi " + user.getFullName() + "!");
		setUser(user);
		
//Sets Profile picture:
		if (user.getPicturePath() != null) {
				
	        ImageIcon imageIcon = new ImageIcon(user.getPicturePath());
	        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);	        
	        this.getSystemMenu().setIcon(new ImageIcon(image));	
		}

		setNoPermissionButtons();
	}

	/**
	 * 
	 * @return ActionListener that pops a no permissions dialog
	 */
	private ActionListener noPermissionDialog() {
		return e -> {
			SoundFile.accessDenied();
			JOptionPane.showMessageDialog(null, NO_PERMISSION_TEXT, NO_PERMISSION_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		};
   
    
	}
	
	
	/**
	 * Removes Actions From  relevant JMenuItem s
	 */
	
	
	    public void DisableAddEmployeeListener() {
	        JMenuItem addNewEmpMenu = this.getAddEmployee();
	        if (addNewEmpMenu != null) {
	            for (ActionListener listener : addNewEmpMenu.getActionListeners()) {
	                addNewEmpMenu.removeActionListener(listener);
	            }
	        }
	       
	    }

	    
	    public void DisableRemoveEmployeeListener() {
	        JMenuItem removeEmpMenu = super.getRemoveEmployee();
	        if (removeEmpMenu != null) {
	            for (ActionListener listener : removeEmpMenu.getActionListeners()) {
	                removeEmpMenu.removeActionListener(listener);
	            }
	        }
	    }

	/**
	 * sets noPermissionDialog on relevant JMenuItem s
	 */
	private void setNoPermissionButtons() {	
		DisableRemoveEmployeeListener();
		DisableAddEmployeeListener();
		this.getAddEmployee().addActionListener(noPermissionDialog());
		this.getRemoveEmployee().addActionListener(noPermissionDialog());
		
	}

	/**
	 * @return the user
	 */
	public Employee getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Employee user) {
		this.user = user;
	}


}
