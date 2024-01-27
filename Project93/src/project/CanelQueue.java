package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.LinkedList;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CanelQueue extends reserveQueue{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CanelQueue window = new CanelQueue();
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
	public CanelQueue() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 240, 222));
		frame.getContentPane().add(panel, "name_95623943652800");
		panel.setLayout(null);
		
		JLabel lblCanelqueue = new JLabel("CanelQueue");
		lblCanelqueue.setHorizontalAlignment(SwingConstants.CENTER);
		lblCanelqueue.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblCanelqueue.setBounds(75, 0, 275, 67);
		panel.add(lblCanelqueue);
		
		
		
		final JComboBox <String> comboBoxIdqueue = new JComboBox();
		comboBoxIdqueue.setBounds(125, 88, 173, 36);
		panel.add(comboBoxIdqueue);
		for (QueueData data: queueDataQueue) {
			comboBoxIdqueue.addItem(data.queueID);
        }
		
		
		JButton btncanel = new JButton("canel");
		btncanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			     String selectedQueueID = (String) comboBoxIdqueue.getSelectedItem();

			        // ตรวจสอบว่ามีการเลือก queueID หรือไม่
			        if (selectedQueueID != null) {
			            // เรียกใช้เมทอด cancelQueue() ของ reserveQueue โดยใช้ queueID ที่เลือก
			            reserveQueue.cancelQueue(selectedQueueID);
			            Queue.main(null);
			        } else {
			            // ใส่โค้ดที่ต้องการให้ทำงานเมื่อไม่ได้เลือก queueID
			            // เช่นแสดงข้อความแจ้งเตือน
			        	JOptionPane.showMessageDialog(frame,"Please select a queue to cancel.");
			        }

				
			}
		});
		btncanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncanel.setBounds(162, 166, 97, 36);
		panel.add(btncanel);
		
		
		
		 frame.setVisible(true);
	}
}
