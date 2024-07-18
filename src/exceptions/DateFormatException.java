package exceptions;

import gui.SoundFile;

/**
* if Data not in dd/MM/yyyy Format.
*
*/

public class DateFormatException extends Exception {
	public DateFormatException() {
		super("Please enter a valid date (dd/MM/yyyy)");
		SoundFile.uhoh();
	}

}
