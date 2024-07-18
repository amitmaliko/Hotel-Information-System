package exceptions;

import gui.SoundFile;
/**
 * 
 * If object Already Exist or trying to enter duplicated PK 
 *
 */

public class AlreadyExistsException extends Exception {
	public AlreadyExistsException(String message) {
        super(message);
        SoundFile.uhoh();
    }
}


