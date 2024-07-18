package exceptions;

import gui.SoundFile;

/**
* Not all fields were filled in the form
*
*/

public class EmptyFieldsException extends Exception {
	public EmptyFieldsException(String message) {
        super(message);
        SoundFile.uhoh();
    }

}
