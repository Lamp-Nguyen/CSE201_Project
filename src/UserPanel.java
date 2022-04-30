import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class UserPanel extends JPanel implements ActionListener {

	private JLabel loggedIn;
	private JPanel display;
	private JButton signupButton = new JButton("Sign up");
	private JButton loginButton = new JButton("Login");
	private JButton logoutButton = new JButton("Logout");
	private GridBagConstraints gc;
	private User user = new User();
	
	public UserPanel() {
		this.setLayout(new GridBagLayout());
		setBorder(new TitledBorder("User"));
		defaultView();
	}
	
	public User getUser() {
		return this.user;
	}

	public void defaultView() {
		display = new JPanel(new GridBagLayout());
		signupButton.addActionListener(this);
		loginButton.addActionListener(this);
		
		gc = new GridBagConstraints();
		gc.ipadx = 26;
		gc.weighty = 7;
		gc.gridx = 0;
		gc.gridy = 0;
		display.add(loginButton, gc);

		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.ipadx = 15;
		gc.weighty = 7;
		gc.gridx = 0;
		gc.gridy = 1;
		display.add(signupButton, gc);
		
		gc = new GridBagConstraints();
		gc.ipady = 70;
		gc.ipadx = 200;
		gc.gridx = 0;
		gc.gridy = 0;
		add(display, gc);
	}
	
	public void userView() {
		display = new JPanel(new GridBagLayout());
		loggedIn = new JLabel("Welcome " + user.getName() + "!");
		logoutButton.addActionListener(this);
		
		gc = new GridBagConstraints();
		gc.weighty = 7;
		gc.gridx = 0;
		gc.gridy = 0;
		display.add(loggedIn, gc);

		gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.ipadx = 15;
		gc.weighty = 7;
		gc.gridx = 0;
		gc.gridy = 1;
		display.add(logoutButton, gc);
		
		gc = new GridBagConstraints();
		gc.ipady = 70;
		gc.ipadx = 200;
		gc.gridx = 0;
		gc.gridy = 0;
		
		add(display, gc);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			logingui tmp = new logingui(this);
			tmp.setSize(330, 450);
			tmp.setLocationRelativeTo(null);
			tmp.setVisible(true);

		} else if (e.getSource() == signupButton) {
			signupgui tmp = new signupgui(user);
			tmp.setSize(330, 450);
			tmp.setLocationRelativeTo(null);
			tmp.setVisible(true);
		} else if (e.getSource() == logoutButton) {
			user = null;
			this.removeAll();
			defaultView();
			revalidate();
			repaint();
		}
	}
	
}
