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

public class GUI extends JFrame{

	private JPanel mainPanel, functionPanel, sortPanel, catalogPanel;
	private UserPanel userPanel;
	private JScrollPane catalogScroll;
	private JLabel sortLabel, searchLabel;
	private JComboBox<String> sortReverse;
	private JButton searchButton, addButton;
	private JRadioButton nameButton, dateButton, ratingButton, ownerButton;
	private ButtonGroup sortButtons;
	private JTextField searchText;
	private CatalogContainer container;
	private GridBagConstraints gc;
	private ConnectionManager cm;
	
	public GUI() {
		mainPanel = new JPanel(new BorderLayout());
		
		catalogPanel = new JPanel(new BorderLayout());
		catalogPanel.setBorder(new TitledBorder("Businesses"));
		mainPanel.add(catalogPanel, BorderLayout.CENTER);
		
		functionPanel = new JPanel(new GridBagLayout());
		mainPanel.add(functionPanel, BorderLayout.WEST);
		
		userPanel = new UserPanel();
		gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		functionPanel.add(userPanel, gc);
		
		searchLabel = new JLabel("Type business's name or owner's name: ");
		searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(90, 15, 0, 0);
		gc.weighty = 0.7;
		gc.gridx = 0;
		gc.gridy = 2;
		functionPanel.add(searchLabel, gc);
		
		searchText = new JTextField("", 30);
		gc = new GridBagConstraints();
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 3;
		functionPanel.add(searchText, gc);
		
		searchButton = new JButton("Search");
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.insets = new Insets(10, 0, 0, 0);
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 4;
		functionPanel.add(searchButton, gc);
		
		sortLabel = new JLabel("Sort by: ");
		sortLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(40, 15, 0, 0);
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 5;
		functionPanel.add(sortLabel, gc);
		
		nameButton = new JRadioButton("Name");
		nameButton.setFont(new Font("Arial", Font.PLAIN, 14));
		nameButton.setActionCommand("Name");
		dateButton = new JRadioButton("Date");
		dateButton.setFont(new Font("Arial", Font.PLAIN, 14));
		dateButton.setActionCommand("Date");
		ratingButton = new JRadioButton("Rating");
		ratingButton.setFont(new Font("Arial", Font.PLAIN, 14));
		ratingButton.setActionCommand("Rating");
		ownerButton = new JRadioButton("Owner");
		ownerButton.setFont(new Font("Arial", Font.PLAIN, 14));
		ownerButton.setActionCommand("Owner");
		
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
		sortReverse.addItem("Ascending");
		sortReverse.addItem("Descending");
		gc = new GridBagConstraints();
		gc.gridwidth = sortPanel.getWidth();
		gc.gridx = 0;
		gc.gridy = 2;
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
		gc = new GridBagConstraints();
		gc.insets = new Insets(0, 0, 100, 4);
		gc.weighty = 20;
		gc.gridx = 0;
		gc.gridy = 8;
		functionPanel.add(addButton, gc);
		
		container = new CatalogContainer();
		container.getData("");
		catalogScroll = new JScrollPane(container);
		catalogScroll.getVerticalScrollBar().setUnitIncrement(20);
		catalogPanel.add(catalogScroll, BorderLayout.CENTER);
		catalogPanel.revalidate();
		
		EventHandler listener = new EventHandler();
		searchButton.addActionListener(listener);
		nameButton.addActionListener(listener);
		dateButton.addActionListener(listener);
		ownerButton.addActionListener(listener);
		ratingButton.addActionListener(listener);
		sortReverse.addActionListener(listener);
		addButton.addActionListener(listener);
		
		add(mainPanel);
	}
	
	class EventHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == searchButton) {
				String searchStr = searchText.getText().trim();
				container.getData(searchStr);
				if (container.isEmpty()) {
					JLabel tmp = new JLabel("No such record in catalog!");
					tmp.setFont(new Font("Arial", Font.BOLD, 20));
					container.add(tmp);
				}
			} else if (sortButtons.getSelection() != null) {
				boolean reversed = false;
				if (sortReverse.getSelectedItem().equals("Descending")) reversed = true;
				String sortCat = sortButtons.getSelection().getActionCommand();
				container.updateSort(sortCat, reversed);
			} else if (e.getSource() == addButton) {
				if (userPanel.getUser() != null) {
					AddBusinessFrame tmp = new AddBusinessFrame(container);
					tmp.setSize(640, 400);
					tmp.setLocationRelativeTo(null);
					tmp.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Please login before using this feature!");
				}
			}
		}
		
	}
}
