package exceptions;

import gui.SoundFile;
/**
* Illegal input has been filled i.e-chars instead of ints, logical error
*
*/

public class IllegalInputException extends Exception {
	public IllegalInputException(String message) {
        super(message);
        SoundFile.uhoh();
        
    }
}
