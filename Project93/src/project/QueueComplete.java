package project;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QueueComplete {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel TableQueue;

    
    private static final String url = "jdbc:mysql://localhost:3306/project93";
    private static final String user = "root";
    private static final String password = "Ss292546";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QueueComplete window = new QueueComplete();
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
    public QueueComplete() {
        initialize();
        queuedatacompile();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 621, 467);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        frame.setTitle("Queue Data Complete");

        JPanel panel = new JPanel();
        panel.setBackground(new Color(250, 240, 222));
        frame.getContentPane().add(panel, "name_183997718743900");
        panel.setLayout(null);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 89, 520, 293);
        panel.add(scrollPane);


        TableQueue = new DefaultTableModel();
        table.setModel(TableQueue);
        
        JLabel lblNewLabel = new JLabel("Queue Complete");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(212, 33, 178, 31);
        panel.add(lblNewLabel);
        
        JButton btnDelete = new JButton("Delete Data");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		deleteData();
        		queuedatacompile() ;
        	}
        });
        btnDelete.setBackground(new Color(255, 0, 0));
        btnDelete.setForeground(new Color(0, 0, 0));
        btnDelete.setBounds(469, 392, 91, 28);
        panel.add(btnDelete);
        
        TableQueue.addColumn("numQueue");
        TableQueue.addColumn("idQueue");
        TableQueue.addColumn("person");
        TableQueue.addColumn("compilecolDate");
        
		frame.setVisible(true);
    }

    // Fetch data from the database and populate the table
    private void queuedatacompile() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM queuecompile";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            
            TableQueue.setRowCount(0);

           
            while (result.next()) {
                Object[] row = new Object[] {
                		result.getInt("numQueue"),
                        result.getString("idQueue"),
                        result.getInt("person"),
                        result.getString("compilecolDate"),
                        
                      
                };
                TableQueue.addRow(row);
            }

           
            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteData() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM queuecompile";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
