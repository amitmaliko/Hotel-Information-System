package main;

import gui.Login;
import model.Hotel;
/**
 * 
 * @author Amit Malik 207850074
 * This is the Main class.<br>
 * Hotel instance is being loaded or new one is created.<br>
 * From there, Login Frame Pops up.
 * @see Serialize
 * @see Login
 */

/*
 * 
 */
public class Main {

	public static  Hotel hotel;

	public static void main(String[] args) {
		
		hotel = load();				
		final Login login = new Login();
		login.setVisible(true);
		
	
	}
	

	/**
	 * Saves the hotel
	 */
	public static void save() {
		Serialize.serialize();
	}

	/**
	 * Loads the hotel
	 */
	public static Hotel load() {
		return Serialize.deserialize();
	}



}