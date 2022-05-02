import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Creates the main GUI for the program
 * @author Lam_Nguyen
 *
 */

public class GUI extends JFrame{

	//------------------------------------------------------------------- Instance variables
	private JPanel mainPanel, functionPanel, sortPanel, catalogPanel;
	private JScrollPane catalogScroll;
	private JLabel sortLabel, searchLabel;
	private JComboBox<String> sortReverse;
	private JButton searchButton, addButton, deleteButton;
	private JRadioButton nameButton, dateButton, ratingButton, ownerButton;
	private JTextField searchText;
	private UserPanel userPanel;
	private ButtonGroup sortButtons;
	private CatalogContainer container;
	private GridBagConstraints gc;
	private ConnectionManager cm;
	
	//------------------------------------------------------------------- Constructor
	public GUI() {
		mainPanel = new JPanel(new BorderLayout());
		
		// Panel for the catalog container
		catalogPanel = new JPanel(new BorderLayout());
		catalogPanel.setBorder(new TitledBorder("Businesses"));
		mainPanel.add(catalogPanel, BorderLayout.CENTER);
		
		// Initializing the catalog container by getting all the data from the database
		container = new CatalogContainer();
		container.getData("");
		catalogScroll = new JScrollPane(container);
		catalogScroll.getVerticalScrollBar().setUnitIncrement(20);
		catalogPanel.add(catalogScroll, BorderLayout.CENTER);
		catalogPanel.revalidate();
		
		// Panel for the function buttons
		functionPanel = new JPanel(new GridBagLayout());
		mainPanel.add(functionPanel, BorderLayout.WEST);
		
		// Initialize the user panel
		userPanel = new UserPanel();
		gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		functionPanel.add(userPanel, gc);
		
		// Text field for searching
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
		
		// Button for searching entered text
		searchButton = new JButton("Search");
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.insets = new Insets(10, 0, 0, 0);
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 4;
		functionPanel.add(searchButton, gc);
		
		// Radio buttons for sorting categories
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
		
		/*
		 * Adding the sorting radio buttons into a sort panel and the
		 * combobox for selecting whether the sort is in reversed
		 */
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
		
		// Adding the sort panel to the function panel
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.ipady = 70;
		gc.ipadx = 200;
		gc.weighty = 15;
		gc.gridx = 0;
		gc.gridy = 6;
		functionPanel.add(sortPanel, gc);
		
		// Button for adding bussiness
		addButton = new JButton("Add Business");
		gc = new GridBagConstraints();
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 8;
		functionPanel.add(addButton, gc);
		
		// Button for deleting business
		deleteButton = new JButton("Delete Business");
		gc = new GridBagConstraints();
		gc.insets = new Insets(0, 4, 50, 4);
		gc.weighty = 15;
		gc.gridx = 0;
		gc.gridy = 9;
		functionPanel.add(deleteButton, gc);
		
		// Adding action listeners to the components
		EventHandler listener = new EventHandler();
		searchButton.addActionListener(listener);
		nameButton.addActionListener(listener);
		dateButton.addActionListener(listener);
		ownerButton.addActionListener(listener);
		ratingButton.addActionListener(listener);
		sortReverse.addActionListener(listener);
		addButton.addActionListener(listener);
		deleteButton.addActionListener(listener);
		
		add(mainPanel);
	}
	
	//------------------------------------------------------------------- Inner class
	/**
	 * Inner class to handle the action events of the components
	 * @author Lam_Nguyen
	 *
	 */
	class EventHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			/*
			 * Action performed when the search button is clicked. The container
			 * calls the getData() method with the string typed in the search text field as
			 * the paramter
			 */
			if (e.getSource() == searchButton) {
				String searchStr = searchText.getText().trim();
				container.getData(searchStr);
				sortButtons.clearSelection();
				if (container.isEmpty()) {
					JLabel tmp = new JLabel("No such record in catalog!");
					tmp.setFont(new Font("Arial", Font.BOLD, 20));
					container.add(tmp);
				}
			/*
			 * Action performed when the sorting categories are selected. See if the list
			 * needs to be sorted by ascending or descending order. Calls the updateSort() method
			 * of the container to update the display.
			 */
			} else if (sortButtons.getSelection() != null) {
				boolean reversed = false;
				if (sortReverse.getSelectedItem().equals("Descending")) reversed = true;
				String sortCat = sortButtons.getSelection().getActionCommand();
				container.updateSort(sortCat, reversed);
			/*
			 * Action performed when the add button is clicked. Only usable for signed in users.
			 * Opens the add business frame.
			 */
			} else if (e.getSource() == addButton) {
				if (userPanel.getUser() != null) {
					AddBusinessFrame tmp = new AddBusinessFrame(container);
					tmp.setSize(640, 400);
					tmp.setLocationRelativeTo(null);
					tmp.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Please login before using this feature!");
				}
			/*
			 * ACtion performed when the delete button is clicked. Only usable for signed in users
			 */
			} else if (e.getSource() == deleteButton) {
				if (userPanel.getUser() != null) {
					ExtraFeature.getExtraInstance().openWebpage();
				} else {
					JOptionPane.showMessageDialog(null, "Please login before using this feature!");
				}
			}
		}
		
	}
}
