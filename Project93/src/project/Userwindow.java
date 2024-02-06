package project;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Userwindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userwindow window = new Userwindow();
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
	public Userwindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 363);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		 frame.setTitle("Main");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 240, 222));
		frame.getContentPane().add(panel, "name_82558571472100");
		panel.setLayout(null);
		 
		
        ImageIcon icon = new ImageIcon("F:\\project1101093\\image.png");
		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setBounds(191, 22, 185, 172);
		panel.add(lblNewLabel);
		
		JButton btnrerve = new JButton("RESERVE");
		btnrerve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScanQRcode  scanQRcode = new ScanQRcode();
				scanQRcode.main(null);
			}
		});
		btnrerve.setBounds(192, 224, 184, 49);
		panel.add(btnrerve);
	}
}
