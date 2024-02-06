package project;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReserveUser {
    private JFrame frame;
    private JTextField textinputname;
    private JTextField textinputperson;
    private ReserveQueue queue; // อ็อบเจกต์ของ reserveQueue

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReserveUser window = new ReserveUser();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ReserveUser() {
        initialize();
        queue = new ReserveQueue(); // สร้างอ็อบเจกต์ของ reserveQueue
    }

    
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/project93"; 
        String username = "root"; 
        String password = "Ss292546"; 
        return DriverManager.getConnection(url, username, password);
        
    }
    
    
    private boolean isNameDuplicate(String name) {
        String query = "SELECT * FROM queuedata WHERE name = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // ถ้ามีชื่อซ้ำในฐานข้อมูลจะส่งค่า true กลับ
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 488, 328);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        frame.setTitle("Reserve");

        JPanel panel = new JPanel();
        panel.setBackground(new Color(250, 240, 222));
        frame.getContentPane().add(panel, "name_124565935674300");
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Reserve Queue");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(138, 0, 187, 46);
        panel.add(lblNewLabel);

        textinputname = new JTextField();
        textinputname.setBounds(153, 66, 187, 36);
        panel.add(textinputname);
        textinputname.setColumns(10);

        textinputperson = new JTextField();
        textinputperson.setColumns(10);
        textinputperson.setBounds(153, 133, 187, 36);
        panel.add(textinputperson);

        JLabel lblName = new JLabel("name :");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblName.setBounds(10, 66, 187, 46);
        panel.add(lblName);

        JLabel lblPerson = new JLabel("person :");
        lblPerson.setHorizontalAlignment(SwingConstants.CENTER);
        lblPerson.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPerson.setBounds(10, 133, 187, 46);
        panel.add(lblPerson);

        JButton btnNewButton = new JButton("Confirm");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numberOfPeopleStr = textinputperson.getText();
                String name = textinputname.getText();

                if (!numberOfPeopleStr.isEmpty()) {
                    try {
                        int numberOfPeople = Integer.parseInt(numberOfPeopleStr);

                        
                        if (isNameDuplicate(name)) {
                            JOptionPane.showMessageDialog(null, "Name already exists.");
                        } else {
                            //String queueID = queue.generateQueueID();
                            //queue.enqueue(new QueueData(queueID, numberOfPeople, name));

                            // เพิ่มข้อมูลลงในฐานข้อมูล
                        	insertDatabase(name,  numberOfPeople);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the number of people.");
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnNewButton.setBounds(184, 211, 103, 36);
        panel.add(btnNewButton);
    }
    
    
    private void insertDatabase(String name, int numberOfPeople) {
        String query = "INSERT INTO customerqueue (Customername, CustomerPerson) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
          
        	stmt.setString(1, name);
            stmt.setInt(2, numberOfPeople);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data saved to database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving data to database.");
        }
    }
}
