package project;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchQueue {

	private JFrame frame;
	private JTextField textname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchQueue window = new SearchQueue();
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
	public SearchQueue() {
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
		 frame.setTitle("Search Queue");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(250, 240, 222));
		frame.getContentPane().add(panel, "name_181234835571800");
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search QueueID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(129, 10, 170, 37);
		panel.add(lblNewLabel);
		
		textname = new JTextField();
		textname.setBounds(135, 94, 142, 37);
		panel.add(textname);
		textname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(68, 106, 57, 25);
		panel.add(lblNewLabel_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchname = textname.getText();
				searchdata(searchname);
			}
		});
		btnSearch.setBounds(149, 168, 108, 37);
		panel.add(btnSearch);
	}

	public static void searchdata(String searchname)
	{ 		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project93", "root", "Ss292546");

           
            String sql = "SELECT * FROM queuedata WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

           
            stmt.setString(1, searchname);
            ResultSet ResultSet = stmt.executeQuery();
            
            boolean found = false;
            while(ResultSet.next()) {
            	found = true ;
            	JOptionPane.showMessageDialog(null,"Queue ID :"+ResultSet.getString("queueID"));
            }
            if (!found) {
            	JOptionPane.showMessageDialog(null,"No information found   "+searchname);
            }
            
            ResultSet.close();
            stmt.close();
            conn.close();
            
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
