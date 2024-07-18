package gui;
import javax.sound.sampled.*;
import java.io.File;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * This is A Class to play "Lobby Music" , a background music in the main menu.
 * @author Amit Malik 207850074
 */
public class LobbyMusic {
    private Clip clip;

    public void loadAudio(String filePath) throws Exception {
        File audioFile = new File(filePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.flush();
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
    }
}


