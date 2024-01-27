package project;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import static project.reserveQueue.queueDataQueue;
import java.awt.List;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class Queue extends reserveQueue{
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
    
    public static void showQueueList(JTextArea Queuelist) {
    	
        Queuelist.setText("           Queue List:"+queueDataQueue.size()+"\n           -------------------------------\n");
        
        for (QueueData queueData : queueDataQueue) {
            Queuelist.append("           "+"Queue ID: " + queueData.getQueueID() +"\n"+"           "+"Name Customer:"+queueData.getname()+"\n"+"           "+"Number of People: " + queueData.getNumberOfPeople()+"\n"+"           "+"status:"+queueData.getstatus()+"\n"+"           "+"remaining: "+queueData.getremaining()+"\n"+"        "+"-------------------------------\n");
            
        }
    }
    
    public Queue() {
        initialize();
      
		
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
        btnReserve.setBounds(340, 25, 193, 48);
        queuePanel.add(btnReserve);
    }

    private JPanel createQueuePanel() {
        JPanel queuePanel = new JPanel();
        queuePanel.setBackground(new Color(255, 240, 222));
        queuePanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Queue list");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(44, -20, 168, 88);
        queuePanel.add(lblNewLabel);

        final JTextArea Queuelist = new JTextArea();
        Queuelist.setEditable(false);
        Queuelist.setBounds(26, 48, 233, 317);
        queuePanel.add(Queuelist);
        showQueueList(Queuelist);
        Queuelist.setLineWrap(true);
        
        JScrollPane scrollPane = new JScrollPane(Queuelist);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // แสดงแถบเลื่อนแนวตั้งเสมอ
        scrollPane.setBounds(26, 48, 233, 317);
        queuePanel.add(scrollPane);
        
        JButton btnRefresh = new JButton("refresh");
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		showQueueList(Queuelist);
        	}
        });
        btnRefresh.setBackground(Color.WHITE);
        btnRefresh.setBounds(340, 141, 193, 48);
        queuePanel.add(btnRefresh);
        
        JButton btnpass = new JButton("pass");
        btnpass.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dequeue(Queuelist);
        	}
        });
        btnpass.setBackground(Color.WHITE);
        btnpass.setBounds(340, 261, 193, 48);
        queuePanel.add(btnpass);
        
        JButton btncanel = new JButton("canel");
        btncanel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		CanelQueue Canelnew = new CanelQueue();
        		
        		Canelnew.setVisible(true);
        	}
        });
        btncanel.setBackground(Color.WHITE);
        btncanel.setBounds(340, 319, 193, 48);
        queuePanel.add(btncanel);
        
        JButton btneditStatus = new JButton("EditStatus");
        btneditStatus.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Editwindow editwindow = new Editwindow();
        		editwindow.setVisible(true);
        	}
        });
        btneditStatus.setBackground(Color.WHITE);
        btneditStatus.setBounds(340, 83, 193, 48);
        queuePanel.add(btneditStatus);

        return queuePanel;
    }

    private void switchToReserveQueuePanel() {
        cardLayout.show(mainPanel, "ReserveQueuePanel");
    }
}
