import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class AddBusinessFrame extends JDialog implements ActionListener {
	
	private JPanel mainPanel;
	private JTextField nameText, onwerText, typeText, ownerText;
	private JFormattedTextField dateText, numberText;
	private JComboBox<String> expenseBox;
	private JButton confirmButton;
	private JLabel nameLabel, dateLabel, expenseLabel, typeLabel, ownerLabel, numberLabel;
	private GridBagConstraints gc;
	
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
	
	public AddBusinessFrame() {
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
		nameText= new JTextField();
		nameText.setColumns(30);
		mainPanel.add(nameText, gc);
		
		gc.gridy++;
		dateText = new JFormattedTextField(createFormatter("##/##/####"));
		dateText.setColumns(6);
		mainPanel.add(dateText, gc);
		
		gc.gridy++;
		expenseBox = new JComboBox<String>();
		expenseBox.addItem("Inexpensive");
		expenseBox.addItem("Moderately Expensive");
		expenseBox.addItem("Very Expensive");
		mainPanel.add(expenseBox, gc);
		gc.gridy++;
		typeText = new JTextField();
		typeText.setColumns(30);
		mainPanel.add(typeText, gc);
		gc.gridy++;
		ownerText = new JTextField();
		ownerText.setColumns(20);
		mainPanel.add(ownerText, gc);
		gc.gridy++;
		numberText = new JFormattedTextField();
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
		
		pack();
		add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Confirm")) {
			
		}
	}
}
