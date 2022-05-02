import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Catalogs entry container component of the GUI. Contains an instance of the 
 * BusinessList object, which includes the working array list of businesses 
 * of the program
 * @author Lam_Nguyen
 *
 */

public class CatalogContainer extends JPanel {
	
	//------------------------------------------------------------------- Instance variables
	public BusinessList list;
	
	//------------------------------------------------------------------- Constructor
	public CatalogContainer() {
		list = new BusinessList();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	//------------------------------------------------------------------- Instance methods
	/**
	 * Load the businesses stored in the working array list. Each business's data is formatted
	 * into a JPanel and then added to the container
	 */
	public void loadBusinesses() {
		refresh();
		Business[] tmp = list.dataArary();
		for (int i = 0; i < tmp.length; i++) {
			JPanel toAdd = new JPanel();
			formatPanel(toAdd, tmp[i]);
			toAdd.revalidate();
			this.add(toAdd);
			this.add(Box.createRigidArea(new Dimension(10, 10)));
		}
	}
	
	/**
	 * Format the JPanel to be added to the container
	 * @param entry the entry to be added
	 * @param b the current business
	 */
	public void formatPanel(JPanel entry, Business b) {
		entry.setLayout(new GridLayout(4, 2));
		entry.setBorder(new LineBorder(Color.black));
		entry.setPreferredSize(new Dimension(650, 200));
		entry.setMaximumSize(entry.getPreferredSize());
		
		// Business's name
		JLabel businessName = new JLabel(b.getName());
		businessName.setFont(new Font("Arial", Font.BOLD, 15));
		entry.add(businessName);
		
		// Empty entry to occupy the next cell in the grid layout
		entry.add(new JLabel(""));
				
		// Business's type
		JLabel businessType = new JLabel("Type: " + b.getType());
		businessType.setFont(new Font("Arial", Font.PLAIN, 14));
		entry.add(businessType);
		
		// Business's rating
		JLabel businessRating = new JLabel("Rating: " + b.getRating());
		businessRating.setFont(new Font("Arial", Font.PLAIN, 14));
		entry.add(businessRating);
		
		// Business's established date
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		JLabel businessDate = new JLabel("Date established: " + dateFormat.format(b.getDate()));
		businessDate.setFont(new Font("Arial", Font.PLAIN, 14));
		entry.add(businessDate);
		
		// Business's expense type
		JLabel businessExpense = new JLabel("Expense category: " + b.getExpense());
		businessExpense.setFont(new Font("Arial", Font.PLAIN, 14));
		entry.add(businessExpense);
		
		// Business's owner and contact number
		JLabel businessContact = new JLabel("Contacts:  "  + b.getOwner() + " - " +
				b.getNumber());
		businessContact.setFont(new Font("Arial", Font.PLAIN, 14));
		entry.add(businessContact);
	}
	
	/**
	 * Calls the getData() method of the BusinessList instance to update the working array list.
	 * Calls the loadBusinesses() method to update the container
	 * @param searchStr
	 */
	public void getData(String searchStr) {
		list.getData(searchStr);
		loadBusinesses();
	}
	
	/**
	 * Calls the appropriate sorting method of the BusinessList instance to update the working
	 * array list. Calls the loadBusinesses() method to update the container
	 * @param sortCat the sorting category selected
	 * @param reverse does the list needs to be sort in reverse
	 */
	public void updateSort(String sortCat, boolean reverse) {
		if (sortCat.equals("Name")) {
			list.nameSort(reverse);
		} else if (sortCat.equals("Date")) {
			list.dateSort(reverse);
		} else if (sortCat.equals("Rating")) {
			list.ratingSort(reverse);
		} else if (sortCat.equals("Owner")) {
			list.ownerSort(reverse);
		}
		
		loadBusinesses();
	}
	
	/**
	 * Reload the container by removing the current components and repainting
	 */
	public void refresh() {
		removeAll();
		revalidate();
		repaint();
	}
	
	/**
	 * Calls the isEmpty() method of the BusinessList instance to check if the
	 * working array list is empty
	 * @return true if there are no records in the list, false otherwise
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
}

