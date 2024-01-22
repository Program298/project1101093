package project;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import static project.reserveQueue.queueDataQueue;


public class Queue {
    private reserveQueue reserveQueue;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Queue window = new Queue();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private static void showQueueList(JTextArea Queuelist) {
    	
        Queuelist.setText("Queue List:"+queueDataQueue.size()+"\n");
        
        for (QueueData queueData : queueDataQueue) {
            Queuelist.append("Queue ID: " + queueData.getQueueID() + "\nNumber of People: " + queueData.getNumberOfPeople() + "\n\n");
            
        }
    }
    
    public Queue() {
        initialize();
      
		
    }
    private void storeInDatabase(QueueData queueData) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO project93.queuecompile (idQueue, person) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(2, queueData.getQueueID());
                preparedStatement.setInt(3, queueData.getNumberOfPeople());
                preparedStatement.executeUpdate();
            }
            connection.close();
            System.out.println("Data stored in the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void dequeue(JTextArea resultTextArea) {
        if (!queueDataQueue.isEmpty()) {
            QueueData dequeuedData = queueDataQueue.poll();
            resultTextArea.append("Dequeued Queue ID: " + dequeuedData.getQueueID() +
                    "\nNumber of People: " + dequeuedData.getNumberOfPeople() + "\n\n");

            // Store the dequeued data in the database
            storeInDatabase(dequeuedData);
        } else {
            resultTextArea.append("Queue is empty. Cannot dequeue.\n\n");
        }
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 599, 425);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel queuePanel = createQueuePanel();

        reserveQueue = new reserveQueue();

        mainPanel.add(queuePanel, "QueuePanel");
        mainPanel.add(reserveQueue, "ReserveQueuePanel");

        frame.getContentPane().add(mainPanel);

        JButton btnReserve = new JButton("Reserve");
        btnReserve.setBackground(new Color(255, 255, 255));
        btnReserve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchToReserveQueuePanel();
            }
        });
        btnReserve.setBounds(354, 37, 151, 58);
        queuePanel.add(btnReserve);
    }

    private JPanel createQueuePanel() {
        JPanel queuePanel = new JPanel();
        queuePanel.setBackground(new Color(255, 240, 222));
        queuePanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Queue list");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(50, -24, 168, 88);
        queuePanel.add(lblNewLabel);

        final JTextArea Queuelist = new JTextArea();
        Queuelist.setEditable(false);
        Queuelist.setBounds(26, 37, 233, 317);
        queuePanel.add(Queuelist);
        showQueueList(Queuelist);
        
        JButton btnRefresh = new JButton("refresh");
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		showQueueList(Queuelist);
        	}
        });
        btnRefresh.setBackground(Color.WHITE);
        btnRefresh.setBounds(354, 129, 151, 58);
        queuePanel.add(btnRefresh);
        
        JButton btnpass = new JButton("pass");
        btnpass.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dequeue(null);
        	}
        });
        btnpass.setBackground(Color.WHITE);
        btnpass.setBounds(354, 306, 151, 58);
        queuePanel.add(btnpass);

        return queuePanel;
    }

    private void switchToReserveQueuePanel() {
        cardLayout.show(mainPanel, "ReserveQueuePanel");
    }
}
