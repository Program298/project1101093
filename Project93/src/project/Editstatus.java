package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Editstatus extends ReserveQueue{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editstatus window = new Editstatus();
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
	public Editstatus() {
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
		 frame.setTitle("Edit Status");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 240, 222));
		frame.getContentPane().add(panel, "name_1939884772700");
		panel.setLayout(null);
		
		JLabel lblEditstatus = new JLabel("EditStatus");
		lblEditstatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditstatus.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblEditstatus.setBounds(74, 0, 275, 67);
		panel.add(lblEditstatus);
		
		final JComboBox<String> comboBoxIDqueue = new JComboBox();
		comboBoxIDqueue.setBounds(148, 84, 125, 29);
		panel.add(comboBoxIDqueue);
		
		for (QueueData data: queueDataQueue) {
			comboBoxIDqueue.addItem(data.queueID);
        }
		
		
		final JComboBox<String> comboBoxStatus = new JComboBox<String>();
		comboBoxStatus.setBounds(148, 130, 125, 29);
		panel.add(comboBoxStatus);
		comboBoxStatus.addItem(" ");
		comboBoxStatus.addItem("yourtrun");
		comboBoxStatus.addItem("Waiting");
		comboBoxStatus.addItem("service");
		comboBoxStatus.addItem("CancelQueue");
		
		
		JButton btnNewEdit = new JButton("Edit");
		btnNewEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    String selectedQueueID = (String) comboBoxIDqueue.getSelectedItem();
			    String selectedStatus = (String) comboBoxStatus.getSelectedItem();
			    
		      
		        if (selectedQueueID != null && selectedStatus != null) {
		       
		            ReserveQueue.editQueueStatus(selectedQueueID, selectedStatus);
		        } else {
		            
		        	 JOptionPane.showMessageDialog(frame,"Please select Queue ID.\nPlease select a new status.");
		        	
		        }

			}
		});
		btnNewEdit.setBounds(159, 186, 101, 29);
		panel.add(btnNewEdit);
		
		
		
		
		
		frame.setVisible(true);
	}
}
