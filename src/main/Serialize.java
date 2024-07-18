/**
 * 
 */
package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import model.Hotel;



/**
 * @author Amit Malik 207850074
 *
 */
public class Serialize {

	private static final String filename = "hotel.ser";

	/**
	 * Saves the current hotel
	 *  if there was a problem with writing the file, pops an Error message 
	 */
	public static void serialize() {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			//out.writeObject(Main.hotel);
			out.writeObject(Main.hotel);
			out.close();
			fos.close();
			JOptionPane.showMessageDialog(null, "Hotel Data Has Been Saved", "Save", JOptionPane.INFORMATION_MESSAGE);
		} catch (final IOException e) {
			e.printStackTrace(); // To follow the stack trace through the bat file
			JOptionPane.showMessageDialog(null, "Saving file was failed ", "Save Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Makes a New hotel if this is the First Time the file is being created,<br>
	 * if the file exists, the system will try to load it.
	 * @return a loaded hotel from .ser file
	 */
	public static Hotel deserialize() {
		Hotel libr = null;
		try {
			final FileInputStream fis = new FileInputStream(filename);
			final ObjectInputStream in = new ObjectInputStream(fis);
			libr = (Hotel) in.readObject();
			fis.close();
			in.close();
			JOptionPane.showMessageDialog(null, "Loading Hotel\nFrom Saved Data...", "Loading", JOptionPane.INFORMATION_MESSAGE);
			return libr;
		}
		// in case the file is not found
		catch (final FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Welcome!\nLooks like it's the first time here.\nMaking a new database file", "First time",
					JOptionPane.INFORMATION_MESSAGE);
			return Hotel.getInstance();
		}
		// in case there was a problem loading data from file
		catch (final IOException eio) {
			JOptionPane.showMessageDialog(null, eio.getMessage(), "Error reading the file as input",
					JOptionPane.ERROR_MESSAGE);
			return Hotel.getInstance();
		} catch (final ClassNotFoundException ec) {
			JOptionPane.showMessageDialog(null, "Unexpected Exception, " + ec.getMessage(), "Class not found",
					JOptionPane.ERROR_MESSAGE);
			return Hotel.getInstance();
		}
	}

}
