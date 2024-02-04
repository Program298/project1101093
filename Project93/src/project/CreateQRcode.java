package project;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateQRcode {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateQRcode window = new CreateQRcode();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public CreateQRcode() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 311);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 240, 222));
        frame.getContentPane().add(panel, "name_41063203617400");
        panel.setLayout(null);

        JLabel QRcode = new JLabel();
        QRcode.setBounds(112, 23, 200, 200); // Set bounds to accommodate the QR code
        panel.add(QRcode);

        // Generate QR code
        String qrCodeData = "POIU9546";
        String charset = "UTF-8";
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);
            ImageIcon icon = new ImageIcon(qrImage);
            QRcode.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setVisible(true);
    }

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
