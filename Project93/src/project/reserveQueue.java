package project;


import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;



public class reserveQueue extends JPanel {
	
	
	public static LinkedList<QueueData> queueDataQueue = new LinkedList<>();
    private static int currentQueueID = 1;
    
     private static String generateQueueID() {
        return "Q" + currentQueueID++;
    }
     private static void enqueueQueueData(QueueData queueData) {
        queueDataQueue.add(queueData);
        System.out.println("Queue data added to the queue. Queue ID: " + queueData.getQueueID());
        updateStatus();
    }
     public static void dequeue(JTextArea Queuelist) {
         if (!queueDataQueue.isEmpty()) {
             QueueData data = queueDataQueue.poll();
             
             storeInDatabase(data);
             
         } else {
             Queuelist.append("     "+"Queue is empty. Cannot dequeue.\n\n");
         }
         updateStatus();
     }
     
     private static void updateStatus() {
         for (int i = 0; i < queueDataQueue.size(); i++) {
        	 QueueData data = queueDataQueue.get(i);
             if ("CanelQueue".equals(data.status)) {
                 // ถ้าสถานะเป็น "ยกเลิก" ให้นำคิวออก
            	 queueDataQueue.remove(data);
                 i--;  // ลดค่า i เนื่องจากคิวถูกนำออก
             } else {
                 if (i == 0) {
                     data.status = "yourturn";
                 } else {
                     data.status = "Waiting" ;
                     data.remaining = i;
                     
                 }
             }
         }
     }
     
     public static void cancelQueue(String queueID) {
         for (QueueData data : queueDataQueue) {
             if (data.queueID == queueID) {
                 data.status = "CanelQueue";
                 updateStatus();
                 break;
             }
         }
     }
     
     
     public static void editQueueStatus(String queueID, String newStatus) {
         for (QueueData data : queueDataQueue) {
             if (data.queueID == queueID) {
            	 data.status = newStatus;
                 break;
             }
         }
     }
     
     
     private static void storeInDatabase(QueueData data) {
    	   
    	    String Url = "jdbc:mysql://localhost:3306/project93";
    	    String username = "root";
    	    String password = "Ss292546";

    	    try {
    	        
    	        Connection connection = DriverManager.getConnection(Url, username, password);

    	   
    	        String sql = "INSERT INTO queuecompile (idQueue, person, compilecolData) VALUES (?, ?, ?)";

    	        // Creating a prepared statement
    	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
    	        preparedStatement.setString(1, data.getQueueID());
    	        preparedStatement.setInt(2, data.getNumberOfPeople());

    	        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
    	        preparedStatement.setTimestamp(3, currentTimestamp);

    	        // Executing the query
    	        preparedStatement.executeUpdate();

    	        // Closing resources
    	        preparedStatement.close();
    	        connection.close();

    	        System.out.println("Data stored in the database: " + data);
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}


	private static final long serialVersionUID = 1L;
	private JTextField person;
	private JTextField Name;

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
		
		Name = new JTextField();
		Name.setColumns(10);
		Name.setBounds(185, 131, 194, 40);
		panelreserve.add(Name);
		
		JLabel lblname = new JLabel("name");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblname.setBounds(74, 146, 111, 25);
		panelreserve.add(lblname);

	}
	private void switchToQueuePanel() {
        // ให้ใช้ CardLayout ที่ถูกติดตั้งใน JPanel หลักของ Queue
        CardLayout cardLayout = (CardLayout) getParent().getLayout();
        cardLayout.show(getParent(), "QueuePanel");
        String numberOfPeopleStr = person.getText();
        String name = Name.getText();

        if (!numberOfPeopleStr.isEmpty()) {
            try {
                int numberOfPeople = Integer.parseInt(numberOfPeopleStr);
                String queueID = generateQueueID();
                enqueueQueueData(new QueueData(queueID, numberOfPeople, name));
                
                
                //resultTextArea.append("Queue ID: " + queueID + "\nNumber of People: " + numberOfPeople + "\n\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter the number of people.");
        }   
        

    }
	
}
