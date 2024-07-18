package exceptions;

import gui.SoundFile;

/**
* Wrong Username or password has entered in Login
*
*/
	public class InvalidLoginData extends Exception {


		public InvalidLoginData() {
			super("Couldn't log in to the system. Please check your User name and Password and try again!");
			SoundFile.uhoh();
		}

	}

