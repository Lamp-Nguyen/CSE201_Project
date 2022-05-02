import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * JPanel for managing the users currently using the program. Allows the user to log in
 * and sign up. Contains an instance of the User class.
 * @author Lam_Nguyen
 *
 */
public class UserPanel extends JPanel implements ActionListener {

	//------------------------------------------------------------------- Instance variables
	private JLabel loggedIn;
	private JPanel display;
	private JButton signupButton = new JButton("Sign up");
	private JButton loginButton = new JButton("Login");
	private JButton logoutButton = new JButton("Logout");
	private GridBagConstraints gc;
	private User user;
	
	//------------------------------------------------------------------- Constructor
	public UserPanel() {
		this.setLayout(new GridBagLayout());
		setBorder(new TitledBorder("User"));
		signupButton.addActionListener(this);
		loginButton.addActionListener(this);
		defaultView();
	}
	
	//------------------------------------------------------------------- Instance methods
	/**
	 * Get the reference to the current user
	 * @returnt the current user
	 */
	public User getUser() {
		return this.user;
	}
	
	/**
	 * Instaniate the User object
	 */
	public void setUser() {
		this.user = new User();
	}

	/**
	 * The default layout of the UserPanel, containg a button to login and signup
	 */
	public void defaultView() {
		display = new JPanel(new GridBagLayout());
		
		// Login in button
		gc = new GridBagConstraints();
		gc.ipadx = 18;
		gc.weighty = 7;
		gc.gridx = 0;
		gc.gridy = 0;
		display.add(loginButton, gc);

		// Sign up button
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
	
	/**
	 * Layout of the UserPanel when there is a logged in user
	 */
	public void userView() {
		display = new JPanel(new GridBagLayout());
		
		// Label for logged in user
		loggedIn = new JLabel("Welcome " + user.getName() + "!");
		logoutButton.addActionListener(this);
		gc = new GridBagConstraints();
		gc.weighty = 7;
		gc.gridx = 0;
		gc.gridy = 0;
		display.add(loggedIn, gc);

		// Log out button
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
	
	/**
	 * Action listener method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * Action performed when the log in button is clicked. Create an instance of
		 * the logingui class. If logging in is successful, the display is updated
		 */
		if (e.getSource() == loginButton) {
			logingui tmp = new logingui(this);
			tmp.setSize(330, 450);
			tmp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			tmp.setLocationRelativeTo(null);
			tmp.setVisible(true);
		/*
		 * Action performed when the sign up button is clicked. Create an instance of the
		 * signupgui class
		 */
		} else if (e.getSource() == signupButton) {
			signupgui tmp = new signupgui(user);
			tmp.setSize(330, 450);
			tmp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			tmp.setLocationRelativeTo(null);
			tmp.setVisible(true);
		/*
		 * Action performed when the log out button is clicked. The current user is set to null,
		 * update the display back to the default view
		 */
		} else if (e.getSource() == logoutButton) {
			user = null;
			this.removeAll();
			defaultView();
			revalidate();
			repaint();
		}
	}
	
}
