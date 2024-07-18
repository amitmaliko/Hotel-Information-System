package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import net.miginfocom.swing.MigLayout;
/**
 * DesktopPane to set in main Frame. 
 * @author Amit Malik 207850074
 *
 */
public class MyDesktopPane extends JDesktopPane {
	private Image backgroundImage;
//Setting Background from given image path:
    public MyDesktopPane(String imagePath, int width, int height) {
        setPreferredSize(new Dimension(width, height));
        try {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
   }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    
    
    
}
