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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CaneclQueue extends ReserveQueue{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaneclQueue window = new CaneclQueue();
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
	public CaneclQueue() {
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
		 frame.setTitle("Canel Queue");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 240, 222));
		frame.getContentPane().add(panel, "name_95623943652800");
		panel.setLayout(null);
		
		JLabel lblCanelqueue = new JLabel("CancelQueue");
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
		
		
		JButton btncanel = new JButton("cancel");
	
		btncanel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		        if (comboBoxIdqueue.getSelectedItem() != null) {
		            String selectedQueueID = (String) comboBoxIdqueue.getSelectedItem();
		            
		        
		            String url = "jdbc:mysql://localhost:3306/project93";
		            String username = "root";
		            String password = "Ss292546";
		            
		            try (Connection connection = DriverManager.getConnection(url, username, password)) {
		             
		                String sql = "DELETE FROM queuedata WHERE queueID = ?";
		                
		                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		                 
		                    preparedStatement.setString(1, selectedQueueID);
		                    
		                   
		                    int rowsAffected = preparedStatement.executeUpdate();
		                    
		                    if (rowsAffected > 0) {
		                        JOptionPane.showMessageDialog(frame, "Queue canceled successfully.");
		                        
		                
		                        comboBoxIdqueue.removeItem(selectedQueueID);
		                        
		        
		                        ReserveQueue.cancelQueue(selectedQueueID);
		                        
		                   
		                    
		                    } else {
		                        JOptionPane.showMessageDialog(frame, "Failed to cancel queue.");
		                    }
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(frame, "An error occurred while canceling queue.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(frame, "Please select a queue to cancel.");
		        }
		    }
		});

		btncanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncanel.setBounds(162, 166, 97, 36);
		panel.add(btncanel);
		
		
		
		 frame.setVisible(true);
	}
}
