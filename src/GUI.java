/**
* Group 17
* Last Updated: 3/14/2022
* This is the main GUI of our CSE 201 Project.
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
	
	private static JLabel userName;
	private static JTextField userText;
	private static JLabel password;
	private static JPasswordField passText;
	private static JButton login;
	private static JLabel complete;
	private static JButton clear;
	private static JTextField searchText;
	private static JButton search;
	private static JTextArea display;
	private static JScrollPane scroll;

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setSize(600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);
		
		//========================================== User name text and field
		userName = new JLabel("Username: ");
		userName.setBounds(150, 20, 80, 25);
		panel.add(userName);
		
		userText = new JTextField(20);
		userText.setBounds(230, 20, 165, 25);
		panel.add(userText);
		
		//========================================== Password text and field
		password = new JLabel("Password: ");
		password.setBounds(150, 70, 80, 25);
		panel.add(password);
		
		passText = new JPasswordField();
		passText.setBounds(230, 70, 165, 25);
		panel.add(passText);

		//========================================== All data text field
		display = new JTextArea();
		display.setEditable(false);
		scroll = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		display.setBounds(100, 260, 400, 280); 
		scroll.setBounds(100, 260, 400, 280);

		panel.add(scroll);
    	
		File file = new File("Mock_data.txt");
		String line;
		try{
			Scanner sc = new Scanner(file);
			while(sc.hasNext()){
				line = sc.nextLine();
				display.append(line + "\n");
			}
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//========================================== Complete Label
		complete = new JLabel("");
		complete.setBounds(230, 150, 300, 25);
		panel.add(complete);
		
		//========================================== Login button
		login = new JButton("Login");
		login.setBounds(230, 120, 80, 25);
		login.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			  	String user = userText.getText();
			  	String pass = passText.getText();

			  	if(user.equals("abc") && pass.equals("123")){
					complete.setText("Login succesful");
			  	} else {
				  	complete.setText("Login unsuccesful");
			  	}
			}	 
		  } );
		panel.add(login);
		
		//========================================== Clear button
		clear = new JButton("Clear");
		clear.setBounds(310, 120, 80, 25);
		clear.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passText.setText("");
				complete.setText("");
			} 
		  } );
		panel.add(clear);

		//========================================== Search Button
		search = new JButton("Search");
		search.setBounds(420, 190, 80, 25);
		search.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String search = searchText.getText();

			} 
		  } );
		panel.add(search);

		//========================================== Categories and Search Box
		String[] options = {"Category", "Name", "Date", "Rating", "Expense", "Type", "Owner", "Number"};
		JComboBox<String> category = new JComboBox<>(options);
		category.setBounds(100, 190, 110, 25);
		panel.add(category);

		searchText = new JTextField("Search");
		searchText.setBounds(230, 189, 168, 25);
		panel.add(searchText);





		frame.setVisible(true);
	}
}
