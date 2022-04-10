import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.jdatepicker.impl.*;

public class AddBusinessFrame extends JDialog implements ActionListener {
	
	private ArrayList<Business> catalogRecords;
	private JPanel mainPanel;
	private JTextField nameText, typeText, ownerText, numberText;
	private JComboBox<String> expenseBox;
	private JButton confirmButton;
	private JLabel nameLabel, dateLabel, expenseLabel, typeLabel, ownerLabel, numberLabel;
	private GridBagConstraints gc;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private ConnectionManager cm;
	private CatalogContainer container;
	
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
	
	public AddBusinessFrame(ArrayList<Business> arr, CatalogContainer cc) {
		setTitle("Add your business");
		
		mainPanel = new JPanel(new GridBagLayout());
		
		gc = new GridBagConstraints();
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		
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
		
		gc.gridy++;
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		mainPanel.add(datePicker, gc);
		
		gc.gridy++;
		expenseBox = new JComboBox<String>();
		expenseBox.addItem("Inexpensive");
		expenseBox.addItem("Moderately Expensive");
		expenseBox.addItem("Very Expensive");
		mainPanel.add(expenseBox, gc);
		gc.gridy++;
		typeText = new JTextField("");
		typeText.setColumns(30);
		mainPanel.add(typeText, gc);
		gc.gridy++;
		ownerText = new JTextField("");
		ownerText.setColumns(20);
		mainPanel.add(ownerText, gc);
		gc.gridy++;
		numberText = new JTextField("");
		numberText.setColumns(15);
		mainPanel.add(numberText, gc);
		
		gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 2;
		gc.gridy = 6;
		
		gc.gridwidth = mainPanel.getWidth();
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(this);
		mainPanel.add(confirmButton, gc);
		
		catalogRecords = arr;
		container = cc;
		
		pack();
		add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Confirm")) {
			String name = nameText.getText().trim();
			String date = datePicker.getJFormattedTextField().getText();
			String expense = (String) expenseBox.getSelectedItem();
			String type = typeText.getText().trim();
			String owner = ownerText.getText().trim();
			String number = numberText.getText().trim();
			boolean valid = isValid(name, date, owner);
			if (valid) {
				try {
					cm = new ConnectionManager();
					boolean existingRec = cm.dbContains(name);
					if (!existingRec) {
						cm.addRecord(name, date, 0, expense, type, owner, number);
						catalogRecords.add(new Business(name, date, 0, expense, type, owner, number));
						container.loadBusinesses(catalogRecords);
					} else {
						JOptionPane.showMessageDialog(null, "This is an existing business!");
					}
				} catch (Exception exc) {
					exc.printStackTrace();
				} finally {
					JOptionPane.showMessageDialog(null, "Business added!");
					cm.closeConnection();
					dispose();
				}
			}
		}
	}
	
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
