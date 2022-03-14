/**
* Group 17
* Last Updated: 3/7/2022
* This is the main GUI of our CSE 201 Project.
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener {
	
	private static JLabel userName;
	private static JTextField userText;
	private static JLabel password;
	private static JTextField passText;
	private static JButton login;
	private static JLabel complete;
	private static JButton clear;
	private static JTextField searchText;
	private static JButton search;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);
		
		//========================================== User name text and field
		userName = new JLabel("Username: ");
		userName.setBounds(50, 20, 80, 25);
		panel.add(userName);
		
		userText = new JTextField(20);
		userText.setBounds(130, 20, 165, 25);
		panel.add(userText);
		
		//========================================== Password text and field
		password = new JLabel("Password: ");
		password.setBounds(50, 70, 80, 25);
		panel.add(password);
		
		passText = new JTextField();
		passText.setBounds(130, 70, 165, 25);
		panel.add(passText);

		//========================================== All data text field

		
		//========================================== Login button
		login = new JButton("Login");
		login.setBounds(130, 120, 80, 25);
		login.addActionListener(new GUI());
		panel.add(login);
		
		complete = new JLabel("");
		complete.setBounds(130, 150, 300, 25);
		panel.add(complete);
		
		//========================================== Clear button
		clear = new JButton("Clear");
		clear.setBounds(210, 120, 80, 25);
		clear.addActionListener(new GUI());
		panel.add(clear);

		//========================================== Search Button
		search = new JButton("Search");
		search.setBounds(300, 170, 80, 25);
		search.addActionListener(new GUI());
		panel.add(search);

		//========================================== Categories and Search Box
		String[] options = {"Category", "Name", "Date", "Rating", "Expense", "Type", "Owner", "Number"};
		JComboBox<String> category = new JComboBox<>(options);
		category.setBounds(20, 170, 110, 25);
		panel.add(category);

		searchText = new JTextField("Search");
		searchText.setBounds(130, 169, 168, 25);
		panel.add(searchText);





		frame.setVisible(true);

	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		//========================================== login/clear button action listener
		String user = userText.getText();
		String pass = passText.getText();
		
		if(user.equals("abc") && pass.equals("123")) {
			complete.setText("Login success");
		} else {
			complete.setText("Login unsuccessful");
		}
		
		if(clear.isEnabled()) {
			userText.setText("");
			passText.setText("");
			complete.setText("");
		}
	}
}
