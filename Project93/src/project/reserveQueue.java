package project;

import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class reserveQueue extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public reserveQueue() {
		setLayout(new CardLayout(0, 0));
		
		JPanel panelreserve = new JPanel();
		add(panelreserve, "name_205195555115200");
		panelreserve.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fill in information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(138, 10, 275, 67);
		panelreserve.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(185, 181, 194, 40);
		panelreserve.add(textField);
		textField.setColumns(10);
		
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
    }
}
