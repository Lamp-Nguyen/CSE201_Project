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
	
	public CatalogContainer() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	public void loadBusinesses(ArrayList<Business> arr) {
		refresh();
		for (int i = 0; i < arr.size(); i++) {
			JPanel toAdd = new JPanel();
			formatPanel(toAdd, arr, i);
			toAdd.revalidate();
			this.add(toAdd);
			this.add(Box.createRigidArea(new Dimension(10, 10)));
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
	
	public void refresh() {
		removeAll();
		revalidate();
		repaint();
	}
}

