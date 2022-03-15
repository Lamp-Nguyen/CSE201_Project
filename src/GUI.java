/**
* Group 17
* Last Updated: 3/14/2022
* This is the main GUI of our CSE 201 Project.
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javax.swing.text.DefaultCaret;

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

	public static ArrayList<Business> search(ArrayList<Business> arr, String searchStr) {
		ArrayList<Business> ret = new ArrayList<Business>();
		for (Business b : arr) {
			if (b.getName().toLowerCase().contains(searchStr.toLowerCase()) || 
					b.getOwner().toLowerCase().contains(searchStr.toLowerCase())) {
				ret.add(b);
			}
		}
		return ret;
	}
	
	public static void defaultSort(ArrayList<Business> arr, boolean reverse) {
		Collections.sort(arr, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				if (!reverse) {
					return o1.getName().compareTo(o2.getName());
				} else {
					return o2.getName().compareTo(o1.getName());
				}
			}
		});
	}
	
	public static void dateSort(ArrayList<Business> arr, boolean reverse) {
		Collections.sort(arr, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				if (!reverse) {
					return o2.getDate().compareTo(o1.getDate());
				} else {
					return o2.getDate().compareTo(o1.getDate());
				}
			}
		});
	}
	
	public static void ratingSort(ArrayList<Business> arr, boolean reverse) {
		Collections.sort(arr, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				if (!reverse) {
					return o2.getRating() - o1.getRating();
				} else {
					return o1.getRating() - o2.getRating();
				}
			}
		});
	}
	
	public static void ownerSort(ArrayList<Business> arr, boolean reverse) {
		Collections.sort(arr, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				if (!reverse) {
					return o1.getOwner().compareTo(o2.getOwner());
				} else {
					return o2.getOwner().compareTo(o1.getOwner());
				}
			}
		});
	}
	
	public static void main(String[] args) throws Exception {

		ArrayList<Business> catalog = new ArrayList<Business>();
		
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
		
		passText = new JPasswordField();
		passText.setBounds(130, 70, 165, 25);
		panel.add(passText);

		//========================================== All data text field
		display = new JTextArea();
		DefaultCaret caret = (DefaultCaret)display.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		display.setEditable(false);
		scroll = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		display.setBounds(25, 210, 350, 250);
		scroll.setBounds(25, 210, 350, 250);

		panel.add(scroll);
    	
		File file = new File("Mock_data.txt");
		String line;
		Business tmp;
		try{
			Scanner sc = new Scanner(file);
			while(sc.hasNext()){
				line = sc.nextLine();
				tmp = new Business(line);
				catalog.add(tmp);
				display.append(tmp + "\n");
			}
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//========================================== Complete Label
		complete = new JLabel("");
		complete.setBounds(130, 150, 300, 25);
		panel.add(complete);
		
		//========================================== Login button
		login = new JButton("Login");
		login.setBounds(130, 120, 80, 25);
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
		clear.setBounds(210, 120, 80, 25);
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
		search.setBounds(300, 170, 80, 25);
		search.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String searchStr = searchText.getText();
				ArrayList<Business> result = search(catalog, searchStr);
				display.selectAll();
				display.setText("");
				if (result.isEmpty()) {
					display.append("No such record in catalog");
				} else {
					for (Business b : result)
						display.append(b + "\n");
				}
			} 
		  } );
		panel.add(search);

		//========================================== Categories and Search Box
		String[] options = {"Name", "Date", "Rating", "Owner"};
		JComboBox<String> category = new JComboBox<>(options);
		category.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sortCat = (String) category.getSelectedItem();
				if (sortCat.equals("Name"))
					defaultSort(catalog, false);
				else if (sortCat.equals("Date"))
					dateSort(catalog, false);
				else if (sortCat.equals("Rating"))
					ratingSort(catalog, false);
				else if (sortCat.equals("Owner"))
					ownerSort(catalog, false);
				display.selectAll();
				display.setText("");
				for (Business b : catalog)
					display.append(b + "\n");
			}
			
		});
		category.setBounds(20, 170, 110, 25);
		panel.add(category);

		searchText = new JTextField("");
		searchText.setBounds(130, 169, 168, 25);
		panel.add(searchText);

		frame.setVisible(true);
	}
}
