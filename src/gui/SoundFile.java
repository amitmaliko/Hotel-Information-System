
package gui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 * A class to play different sounds
 * @author Amit Malik 207850074
 */

public class SoundFile {
	private  static Clip clip;
	private static AudioInputStream inputStream;
	private static SoundFile resourceFile;
/*
	 * 
	 * @param stream is the InputStream of the path file to play
	 */
	private static void playSound(InputStream stream) {
		try {
			final InputStream bufferedIn = new BufferedInputStream(stream);
			clip = AudioSystem.getClip();
			inputStream = AudioSystem.getAudioInputStream(bufferedIn);
			clip.open(inputStream);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			JOptionPane.showMessageDialog(null, "Unexpected sound Exception, " + e.getMessage(), "Sound not found",
					JOptionPane.INFORMATION_MESSAGE);
		}
		clip.start();
	}

	private static void getAndPlayResource(String path) {
		resourceFile = resourceFile == null ? new SoundFile() : resourceFile;
		final InputStream stream = resourceFile.getClass().getResourceAsStream(path);
		playSound(stream);
	}

		
	
	/**
	 * Play maHaluz.wav
	 */
	public static void maHaluz() {
		getAndPlayResource("/maHaluz.wav");
	}
	
	/**
	 * Play totah!.wav
	 */
	
	public static void totah() {
		getAndPlayResource("/totah!.wav");
	}
	/**
	 * Play uhoh!.wav
	 */
	public static void uhoh() {
		getAndPlayResource("/uhoh.wav");
	}
	
	/**
	 * Play Acces Denied!.wav
	 */
	public static void accessDenied() {
		getAndPlayResource("/accessDenied.wav");
	}
	
	/**
	 * Play logout byebye.wav
	 */
	public static void byeBye() {
		getAndPlayResource("/byeBye.wav");
	}
	
}

	
