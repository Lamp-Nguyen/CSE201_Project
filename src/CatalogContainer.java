import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CatalogContainer extends JPanel {
	
	public BusinessList list;
	
	public CatalogContainer() {
		list = new BusinessList();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	public void getData(String searchStr) {
		list.getData(searchStr);
		loadBusinesses();
	}
	
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
	
	public void formatPanel(JPanel entry, Business b) {
		entry.setLayout(new GridLayout(4, 2));
		entry.setBorder(new LineBorder(Color.black));
		entry.setPreferredSize(new Dimension(650, 200));
		entry.setMaximumSize(entry.getPreferredSize());
		
		JLabel businessName = new JLabel(b.getName());
		businessName.setFont(new Font("Arial", Font.BOLD, 15));
		entry.add(businessName);
		entry.add(new JLabel(""));
				
		JLabel businessType = new JLabel("Type: " + b.getType());
		entry.add(businessType);
		
		JLabel businessRating = new JLabel("Rating: " + b.getRating());
		entry.add(businessRating);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		JLabel businessDate = new JLabel("Date established: " + dateFormat.format(b.getDate()));
		entry.add(businessDate);
		
		JLabel businessExpense = new JLabel("Expense category: " + b.getExpense());
		entry.add(businessExpense);
		
		JLabel businessContact = new JLabel("Contact information:  "  + b.getOwner()
				+ "  -  " + b.getNumber());
		entry.add(businessContact);
	}
	
	public void refresh() {
		removeAll();
		revalidate();
		repaint();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
}

