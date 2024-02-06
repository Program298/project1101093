package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    private JFrame frame;
    private JTextField employeeid;
    private JPasswordField passwordEM;

    private static final String URL = "jdbc:mysql://localhost:3306/project93";
    private static final String USER = "root";
    private static final String PASSWORD = "Ss292546";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 549, 358);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        frame.setTitle("Login");

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 240, 222));
        frame.getContentPane().add(panel, "name_248888066190400");
        panel.setLayout(null);

        employeeid = new JTextField();
        employeeid.setBounds(215, 156, 96, 19);
        panel.add(employeeid);
        employeeid.setColumns(10);

        passwordEM = new JPasswordField();
        passwordEM.setBounds(215, 207, 96, 19);
        panel.add(passwordEM);
        passwordEM.setColumns(10);

        JLabel employeeLabel = new JLabel("Employee ID");
        employeeLabel.setBounds(121, 159, 84, 13);
        panel.add(employeeLabel);

        JLabel Passwordlabel = new JLabel("Password");
        Passwordlabel.setBounds(121, 210, 56, 13);
        panel.add(Passwordlabel);

        JButton btnlogin = new JButton("Login");
        btnlogin.setBounds(215, 258, 96, 21);
        btnlogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });
        panel.add(btnlogin);
        
        ImageIcon icon = new ImageIcon("F:\\project1101093\\image.png");
        JLabel lblNewLabel = new JLabel(icon);
        lblNewLabel.setBounds(170, 10, 169, 136);
        panel.add(lblNewLabel);
        
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private boolean isValidUser(String username, String password) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM project93.employee WHERE IDemployee=? AND password=?")) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void Login() {
        String username = employeeid.getText();
        String password = new String(passwordEM.getPassword());

        if (isValidUser(username, password)) {
            JOptionPane.showMessageDialog(frame, "Login successful");
            // เรียกหน้าต่างอื่น ๆ หรือทำตามที่ต้องการหลังจาก Login สำเร็จ
            
            frame.dispose();

            // สร้างหน้าต่างใหม่ (Queue)
            Queue.main(new String[]{});

        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password");
        }
    }
}
