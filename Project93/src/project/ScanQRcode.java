package project;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScanQRcode {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor(); 

        executor.submit(() -> {
            Webcam webcam = Webcam.getDefault(); 
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            webcam.open();
            SwingUtilities.invokeLater(() -> { 
                WebcamPanel webcamPanel = new WebcamPanel(webcam); 
                webcamPanel.setMirrored(false); 

                JFrame jFrame = new JFrame(); 
                jFrame.add(webcamPanel);
                jFrame.pack(); 
                jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
                jFrame.setLocationRelativeTo(null); 
                jFrame.setVisible(true); 
                jFrame.setTitle("ScanQRcode");
            });

            startQRCodeScanner(webcam); 
        });
    }

    private static void startQRCodeScanner(Webcam webcam) {
        do {
            try {
                BufferedImage image = webcam.getImage(); 
                LuminanceSource source = new BufferedImageLuminanceSource(image); 
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source)); 
                Result result = new MultiFormatReader().decode(bitmap);

                if (result.getText() != null && result.getText().equals("POIU9546")) {
                     
                    webcam.close(); 
                    UsershowQueue.main(null);
                    break; 
                }else {
                	JOptionPane.showMessageDialog(null, result.getText(), "Invalid value", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NotFoundException e) {
                
            }
        } while (true); 
    }
}
