package project;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsershowQueue {

    private JFrame frame;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UsershowQueue window = new UsershowQueue();
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
    public UsershowQueue() {
        initialize();
        DisplayQueue();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 571, 470);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        frame.setTitle("Main");
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 240, 222));
        frame.getContentPane().add(panel, "name_102084045173600");
        panel.setLayout(null);
        

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setBorder(null);
        JScrollPane scrollPane = new JScrollPane(list);
        list.setFont(new Font("Tahoma", Font.BOLD, 14));
        scrollPane.setBounds(114, 130, 333, 185);
        panel.add(scrollPane);
        
        JButton btnNewButton = new JButton("Reserve");
        btnNewButton.setBackground(new Color(0, 255, 128));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ReserveUser ReserveUser = new ReserveUser();
        		ReserveUser.main(null);
        	}
        });
        btnNewButton.setBounds(114, 325, 164, 38);
        panel.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("QueueList");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(196, 20, 161, 35);
        panel.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBackground(new Color(255, 255, 255));
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField.setBounds(196, 94, 58, 26);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("All Queue");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(114, 94, 78, 26);
        panel.add(lblNewLabel_1);
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(new Color(128, 255, 255));
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 listModel.clear();
        		 DisplayQueue();
        		
        	}
        });
        btnRefresh.setBounds(336, 325, 111, 38);
        panel.add(btnRefresh);
        
        JButton btnNewButton_1 = new JButton("Search Qeue");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SearchQueue  SearchQueue = new SearchQueue();
        		SearchQueue.main(null);
        	}
        });
        btnNewButton_1.setBackground(new Color(255, 255, 128));
        btnNewButton_1.setBounds(336, 94, 105, 25);
        panel.add(btnNewButton_1);
        
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                btnRefresh.doClick();
            }
        });
        timer.start();
    }
    
    /**
     * Fetch data from MySQL database and display in JList
     */
    private void DisplayQueue() {
        try {
           
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project93", "root", "Ss292546");
            
   
            PreparedStatement statement = connection.prepareStatement("SELECT queueID, name, numberOfPeople, status, remaining FROM queuedata");
            
         
            ResultSet resultSet = statement.executeQuery();
            
            
            int rowCount = 0; 
            while (resultSet.next()) {
            	rowCount++;
                String rowData = "Queue ID:"+resultSet.getString("queueID") + "\n" ;
                String rowData1	=	"Name :"+resultSet.getString("name") + "\n " ;
                String rowData2	=  "Person :"+resultSet.getInt("numberOfPeople") + "\n" ;
                String rowData3	=  "status :"+resultSet.getString("status") + "\n " ;
                              
 				String rowData4	=   "remaining :"+resultSet.getString("remaining") ;
                String rowData5  = "________________________________";
                listModel.addElement(rowData);
                listModel.addElement(rowData1);
                listModel.addElement(rowData2);
                listModel.addElement(rowData3);
                listModel.addElement(rowData4);
                listModel.addElement(rowData5);
            }
            
            
            textField.setText(String.valueOf(rowCount));
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
