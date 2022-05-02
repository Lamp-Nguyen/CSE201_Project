import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.impl.*;

/**
 * JDialog that pops up when the "Add business" button is clicked on, prompting the user
 * to enter the necessary fields required to create a business. Contains a reference to the
 * instance of the CatalogContainer class
 * @author Lam_Nguyen
 *
 */

public class AddBusinessFrame extends JDialog implements ActionListener {
	
	//------------------------------------------------------------------- Instance variables
	private JPanel mainPanel;
	private JTextField nameText, typeText, ownerText, numberText;
	private JComboBox<String> expenseBox;
	private JButton confirmButton;
	private JLabel nameLabel, dateLabel, expenseLabel, typeLabel, ownerLabel, numberLabel;
	private GridBagConstraints gc;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private CatalogContainer container;
	
	//------------------------------------------------------------------- Constructor
	public AddBusinessFrame(CatalogContainer cc) {
		setTitle("Add your business");
		
		mainPanel = new JPanel(new GridBagLayout());
		
		gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		
		// Adding the labels
		gc.anchor = GridBagConstraints.EAST;
		gc.fill = GridBagConstraints.NONE;
		nameLabel = new JLabel("Business's name: ");
		mainPanel.add(nameLabel, gc);
		gc.gridy++;
		dateLabel = new JLabel("Date established: ");
		mainPanel.add(dateLabel, gc);
		gc.gridy++;
		expenseLabel = new JLabel("Business expense: ");
		mainPanel.add(expenseLabel, gc);
		gc.gridy++;
		typeLabel = new JLabel("Business type: ");
		mainPanel.add(typeLabel, gc);
		gc.gridy++;
		ownerLabel = new JLabel("Owner's name: ");
		mainPanel.add(ownerLabel, gc);
		gc.gridy++;
		numberLabel = new JLabel("Contact number: ");
		mainPanel.add(numberLabel, gc);
		
		// Text field to enter name
		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridx = 1;
		gc.gridy = 0;
		nameText= new JTextField("");
		nameText.setColumns(30);
		mainPanel.add(nameText, gc);
		
		/*
		 * Initialize the date picking interface, which includes the JDatePanel
		 * and JDatePicker dependencies added via the xml file
		 * Source: https://jdatepicker.org/
		 */
		gc.gridy++;
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		mainPanel.add(datePicker, gc);
		
		// Combo box to select the business's expense type
		gc.gridy++;
		expenseBox = new JComboBox<String>();
		expenseBox.addItem("Inexpensive");
		expenseBox.addItem("Moderately Expensive");
		expenseBox.addItem("Very Expensive");
		mainPanel.add(expenseBox, gc);
		gc.gridy++;
		
		// Text field to enter business's type
		typeText = new JTextField("");
		typeText.setColumns(30);
		mainPanel.add(typeText, gc);
		gc.gridy++;
		
		// Text field to enter business's owner 
		ownerText = new JTextField("");
		ownerText.setColumns(20);
		mainPanel.add(ownerText, gc);
		gc.gridy++;
		
		// Text field to enter business's contact number
		numberText = new JTextField("");
		numberText.setColumns(15);
		mainPanel.add(numberText, gc);
		
		// Confirm button to add the business
		gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 2;
		gc.gridy = 6;
		gc.gridwidth = mainPanel.getWidth();
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(this);
		mainPanel.add(confirmButton, gc);
		
		// Initialize the reference to the CatalogContainer object
		container = cc;
		
		pack();
		add(mainPanel);
	}

	//------------------------------------------------------------------- Instance methods
	
	/**
	 * DateLabelFormatter to be used with the JDatePicker object
	 * @author Lam_Nguyen
	 *
	 */
	protected class DateLabelFormatter extends AbstractFormatter {
	    private String datePattern = "MM/dd/yyyy";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }
	        return "";
	    }
	}
	
	/**
	 * ActionListener class.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * Action performed when the confirm button is clicked. The data entered into the fields
		 * is used as parameters for the addRecord() method of the CatalogContainer object
		 */
		if (e.getSource() == confirmButton) {
			String name = nameText.getText().trim();
			String date = datePicker.getJFormattedTextField().getText();
			String expense = (String) expenseBox.getSelectedItem();
			String type = typeText.getText().trim();
			String owner = ownerText.getText().trim();
			String number = numberText.getText().trim();
			boolean valid = isValid(name, date, owner);
			if (valid) {
				try {
					boolean existingRec = container.list.contains(name);
					if (!existingRec) {
						container.list.addRecord(name, date, 0, expense, type, owner, number);
						container.loadBusinesses();
						JOptionPane.showMessageDialog(null, "Business added!");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "This is an existing business!");
					}
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Check if the business's name, date and owner are entered
	 * @param name the business's name
	 * @param date the business's established date
	 * @param owner the business's owner
	 * @return true if the three fields have data, false otherwise.
	 */
	private boolean isValid(String name, String date, String owner) {
		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter business's name!");
			return false;
		} else if (date.equals("")) {
			JOptionPane.showMessageDialog(null, "Please select valid date!");
			return false;
		} else if (owner.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter business's owner!");
			return false;
		} else {
			return true;
		}
	}
}
