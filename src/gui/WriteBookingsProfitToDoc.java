package gui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import main.Main;
import model.Booking;
/**
 * Bonus .Docx File writer :all Bookings By their revenue.
 * @author Amit Malik 207850074
 *
 */
public class WriteBookingsProfitToDoc {

    public static void writeBookingDataToDocx() {
        TreeSet<Booking> bookings = Main.hotel.allBookingByRevenue();
        List<Booking> bookingList = new ArrayList<>(bookings);

        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setText("All Bookings By Revenue\n\n");

            for (Booking booking : bookingList) {
                XWPFParagraph entryParagraph = document.createParagraph();
                XWPFRun entryRun = entryParagraph.createRun();
                entryRun.setText(booking.toString());
                entryRun.addBreak();
            }

            String fileName = "BookingsByRevenue.docx";
            try (FileOutputStream out = new FileOutputStream(fileName)) {
                document.write(out);
                JOptionPane.showMessageDialog(null, "Word file created successfully: " + fileName, "File Created", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error creating Word file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error creating Word document: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
















