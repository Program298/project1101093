package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private JFrame frame;
    private JTextField employeeid;
    private JPasswordField passwordEM;

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
                onLogin();
            }
        });
        panel.add(btnlogin);
        
        ImageIcon icon = new ImageIcon("F:\\project1101093\\image.png");
        JLabel lblNewLabel = new JLabel(icon);
        lblNewLabel.setBounds(170, 10, 169, 136);
        panel.add(lblNewLabel);
        
    }

    private void onLogin() {
        String username = employeeid.getText();
        String password = new String(passwordEM.getPassword());

        if (DatabaseConnection.isValidUser(username, password)) {
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
