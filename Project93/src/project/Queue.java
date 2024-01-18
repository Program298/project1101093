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

        JTextArea textArea = new JTextArea();
        textArea.setBounds(26, 37, 233, 317);
        queuePanel.add(textArea);

        return queuePanel;
    }

    private void switchToReserveQueuePanel() {
        cardLayout.show(mainPanel, "ReserveQueuePanel");
    }
}
