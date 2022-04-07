/**
* Group 17
* Last Updated: 3/14/2022
* This is the main GUI of our CSE 201 Project.
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame implements ActionListener {

	private ArrayList<Business> catalogRecords = new ArrayList<Business>();
	private ArrayList<Business> workingArr;
	private JPanel mainPanel, functionPanel, userPanel, sortPanel, catalogPanel, catalogContainer;
	private JScrollPane catalogScroll;
	private JLabel sortLabel, searchLabel;
	private JComboBox<String> sortReverse;
	private JButton searchButton, loginButton, signupButton, addButton;
	private JRadioButton nameButton, dateButton, ratingButton, ownerButton;
	private ButtonGroup sortButtons;
	private JTextField searchText;
	private GridBagConstraints gc;
	private ConnectionManager cm;
	
	public GUI() {
		mainPanel = new JPanel(new BorderLayout());
		
		catalogPanel = new JPanel(new BorderLayout());
		catalogPanel.setBorder(new TitledBorder("Businesses"));
		mainPanel.add(catalogPanel, BorderLayout.CENTER);
		
		functionPanel = new JPanel(new GridBagLayout());
		mainPanel.add(functionPanel, BorderLayout.WEST);
		
		userPanel = new JPanel(new GridBagLayout());
		userPanel.setBorder(new TitledBorder("User"));
		
		loginButton = new JButton("Login");
		gc = new GridBagConstraints();
		gc.ipadx = 26;
		gc.weighty = 7;
		gc.gridx = 0;
		gc.gridy = 0;
		userPanel.add(loginButton, gc);

		signupButton = new JButton("Sign up");
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.ipadx = 15;
		gc.weighty = 7;
		gc.gridx = 0;
		gc.gridy = 1;
		userPanel.add(signupButton, gc);
		
		// First row
		gc = new GridBagConstraints();
		gc.ipady = 70;
		gc.ipadx = 200;
		gc.gridx = 0;
		gc.gridy = 0;
		functionPanel.add(userPanel, gc);
		
		// Second row
		searchLabel = new JLabel("Type business's name or owner's name: ");
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(90, 15, 0, 0);
		gc.weighty = 0.7;
		gc.gridx = 0;
		gc.gridy = 2;
		functionPanel.add(searchLabel, gc);
		
		// Third row
		searchText = new JTextField("", 30);
		gc = new GridBagConstraints();
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 3;
		functionPanel.add(searchText, gc);
		
		// Fourth Row
		searchButton = new JButton("Search");
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.insets = new Insets(10, 0, 0, 0);
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 4;
		searchButton.addActionListener(this);
		functionPanel.add(searchButton, gc);
		
		// Fifth row
		sortLabel = new JLabel("Sort by: ");
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(40, 15, 0, 0);
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 5;
		functionPanel.add(sortLabel, gc);
		
		// Sixth row
		nameButton = new JRadioButton("Name");
		nameButton.setActionCommand("Name");
		nameButton.addActionListener(this);
		dateButton = new JRadioButton("Date");
		dateButton.setActionCommand("Date");
		dateButton.addActionListener(this);
		ratingButton = new JRadioButton("Rating");
		ratingButton.setActionCommand("Rating");
		ratingButton.addActionListener(this);
		ownerButton = new JRadioButton("Owner");
		ownerButton.setActionCommand("Owner");
		ownerButton.addActionListener(this);
		
		sortButtons = new ButtonGroup();
		sortButtons.add(nameButton);
		sortButtons.add(dateButton);
		sortButtons.add(ratingButton);
		sortButtons.add(ownerButton);
		
		sortPanel = new JPanel(new GridBagLayout());
		
		gc = new GridBagConstraints();
		gc.weightx = 1.5;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		sortPanel.add(nameButton, gc);
		
		gc.gridy = 1;
		sortPanel.add(dateButton, gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		sortPanel.add(ratingButton, gc);
		
		gc.gridy = 1;
		sortPanel.add(ownerButton, gc);
		
		sortReverse = new JComboBox<String>();
		sortReverse.setActionCommand("Reverse");
		sortReverse.addItem("Ascending");
		sortReverse.addItem("Descending");
		gc = new GridBagConstraints();
		gc.gridwidth = sortPanel.getWidth();
		gc.gridx = 0;
		gc.gridy = 2;
		sortReverse.addActionListener(this);
		sortPanel.add(sortReverse, gc);
		
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.ipady = 70;
		gc.ipadx = 200;
		gc.weighty = 15;
		gc.gridx = 0;
		gc.gridy = 6;
		functionPanel.add(sortPanel, gc);
		
		addButton = new JButton("Add Business");
		addButton.addActionListener(this);
		gc = new GridBagConstraints();
		gc.insets = new Insets(0, 0, 100, 4);
		gc.weighty = 20;
		gc.gridx = 0;
		gc.gridy = 8;
		functionPanel.add(addButton, gc);

		loadData();
		
		catalogContainer = new JPanel();
		catalogContainer.setLayout(new BoxLayout(catalogContainer, BoxLayout.PAGE_AXIS));
		catalogScroll = new JScrollPane(catalogContainer);
		catalogScroll.getVerticalScrollBar().setUnitIncrement(20);
		catalogPanel.add(catalogScroll, BorderLayout.CENTER);
		loadBusinesses(catalogRecords, catalogContainer);
		catalogPanel.revalidate();
		
		add(mainPanel);
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Search")) {
			String searchStr = searchText.getText().trim();
			workingArr = search(catalogRecords, searchStr);
			catalogContainer.removeAll();
			if (workingArr.isEmpty()) {
				JLabel tmp = new JLabel("No such record in catalog!");
				tmp.setFont(new Font("Arial", Font.BOLD, 20));
				catalogContainer.add(tmp);
			} else {
				loadBusinesses(workingArr, catalogContainer);
			}
		} else if (e.getActionCommand().equals("Add Business")) {
			AddBusinessFrame tmp = new AddBusinessFrame();
			tmp.setSize(640, 400);
			tmp.setLocationRelativeTo(null);
			tmp.setVisible(true);
		} else if (sortButtons.getSelection().getActionCommand() != null){
			String cmd = sortButtons.getSelection().getActionCommand();
			boolean reversed = false;
			if (sortReverse.getSelectedItem().equals("Descending")) reversed = true;
			if (cmd.equals("Name")) {
				nameSort(workingArr, reversed);
				catalogContainer.removeAll();
				loadBusinesses(workingArr, catalogContainer);
			} else if (cmd.equals("Date")) {
				defaultSort(workingArr, reversed);
				catalogContainer.removeAll();
				loadBusinesses(workingArr, catalogContainer);
			} else if (cmd.equals("Rating")) {
				ratingSort(workingArr, reversed);
				catalogContainer.removeAll();
				loadBusinesses(workingArr, catalogContainer);
			} else if (cmd.equals("Owner")) {
				ownerSort(workingArr, reversed);
				catalogContainer.removeAll();
				loadBusinesses(workingArr, catalogContainer);
			}
		}
		
		catalogPanel.revalidate();
	}
	
	public void loadData() {
		try {
			cm = new ConnectionManager();
			catalogRecords = cm.getData();
			workingArr = catalogRecords;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}
	}
	
	public ArrayList<Business> search(ArrayList<Business> arr, String searchStr) {
		ArrayList<Business> ret = new ArrayList<Business>();
		for (Business b : arr) {
			if (b.getName().toLowerCase().contains(searchStr.toLowerCase()) || 
					b.getOwner().toLowerCase().contains(searchStr.toLowerCase())) {
				ret.add(b);
			}
		}
		return ret;
	}
	
	public void defaultSort(ArrayList<Business> arr, boolean reverse) {
		Collections.sort(arr, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				if (!reverse) {
					return o2.getDate().compareTo(o1.getDate());
				} else {
					return o1.getDate().compareTo(o2.getDate());
				}
			}
		});
	}
	
	public void nameSort(ArrayList<Business> arr, boolean reverse) {
		Collections.sort(arr, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				String o1Name = o1.getName().replaceAll("[^A-Za-z0-9 ]", "");
				String o2Name = o2.getName().replaceAll("[^A-Za-z0-9 ]", "");
				if (!reverse) {
					return o1Name.compareTo(o2Name);
				} else {
					return o2Name.compareTo(o1Name);
				}
			}
		});
	}
	
	public void ratingSort(ArrayList<Business> arr, boolean reverse) {
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
	
	public void ownerSort(ArrayList<Business> arr, boolean reverse) {
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
	
	public void loadBusinesses(ArrayList<Business> arr, JPanel container) {
		for (int i = 0; i < arr.size(); i++) {
			JPanel toAdd = new JPanel();
			formatPanel(toAdd, arr, i);
			toAdd.revalidate();
			container.add(toAdd);
			container.add(Box.createRigidArea(new Dimension(10, 10)));
		}
	}
	
	public void formatPanel(JPanel entry, ArrayList<Business> arr, int index) {
		entry.setLayout(new GridLayout(4, 2));
		entry.setBorder(new LineBorder(Color.black));
		entry.setPreferredSize(new Dimension(650, 200));
		entry.setMaximumSize(entry.getPreferredSize());
		Business tmp = arr.get(index);
		
		JLabel businessName = new JLabel(tmp.getName());
		businessName.setFont(new Font("Arial", Font.BOLD, 15));
		entry.add(businessName);
		entry.add(new JLabel(""));
				
		JLabel businessType = new JLabel("Type: " + tmp.getType());
		entry.add(businessType);
		
		JLabel businessRating = new JLabel("Rating: " + tmp.getRating());
		entry.add(businessRating);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		JLabel businessDate = new JLabel("Date established: " + dateFormat.format(tmp.getDate()));
		entry.add(businessDate);
		
		JLabel businessExpense = new JLabel("Expense category: " + tmp.getExpense());
		entry.add(businessExpense);
		
		JLabel businessContact = new JLabel("Contact information:  "  + tmp.getOwner()
				+ "  -  " + tmp.getNumber());
		entry.add(businessContact);
	}
	
	public static void main(String[] args) {
		GUI app = new GUI();
		app.setSize(1024, 768);
		app.setLocationRelativeTo(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
}
