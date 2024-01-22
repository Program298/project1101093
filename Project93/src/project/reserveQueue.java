package project;


import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;



public class reserveQueue extends JPanel {
	
	
	public static Queue<QueueData> queueDataQueue = new LinkedList<>();
    private static int currentQueueID = 1;
    
     private static String generateQueueID() {
        return "Q" + currentQueueID++;
    }
     private static void enqueueQueueData(QueueData queueData) {
        queueDataQueue.add(queueData);
        System.out.println("Queue data added to the queue. Queue ID: " + queueData.getQueueID());
    }

     
     
	

	private static final long serialVersionUID = 1L;
	private JTextField person;

	/**
	 * Create the panel.
	 */
	public reserveQueue() {
		setLayout(new CardLayout(0, 0));
		
		JPanel panelreserve = new JPanel();
		panelreserve.setBackground(new Color(255, 240, 222));
		add(panelreserve, "name_205195555115200");
		panelreserve.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fill in information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(138, 10, 275, 67);
		panelreserve.add(lblNewLabel);
		
		person = new JTextField();
		person.setBounds(185, 181, 194, 40);
		panelreserve.add(person);
		person.setColumns(10);
		
		JLabel lblPerson = new JLabel("person");
		lblPerson.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPerson.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerson.setBounds(74, 188, 111, 33);
		panelreserve.add(lblPerson);
		
		JButton btnreserve = new JButton("reserve");
		btnreserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToQueuePanel();
				
				
				
			}
		});
		btnreserve.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnreserve.setBounds(214, 259, 146, 40);
		panelreserve.add(btnreserve);

	}
	private void switchToQueuePanel() {
        // ให้ใช้ CardLayout ที่ถูกติดตั้งใน JPanel หลักของ Queue
        CardLayout cardLayout = (CardLayout) getParent().getLayout();
        cardLayout.show(getParent(), "QueuePanel");
        String numberOfPeopleStr = person.getText();

        if (!numberOfPeopleStr.isEmpty()) {
            try {
                int numberOfPeople = Integer.parseInt(numberOfPeopleStr);
                String queueID = generateQueueID();
                enqueueQueueData(new QueueData(queueID, numberOfPeople));
                
                //resultTextArea.append("Queue ID: " + queueID + "\nNumber of People: " + numberOfPeople + "\n\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter the number of people.");
        }   
        

    }
}
