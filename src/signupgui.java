import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * JDialog that pops up when the "Sign up" button is clicked. Prompt the user for their username
 * , password and password confirmation
 * @author Lam_Nguyen
 *
 */
public class signupgui extends JDialog implements ActionListener{

	//------------------------------------------------------------------- Instance variables
	private JLabel signUpL, create, newUser, newPass, rePass, notify;
	private JTextField textUser;
	private JPasswordField textPass, textRePass;
	private JButton signUpB;
	private ConnectionManager cm;
	private User user;
	
	//------------------------------------------------------------------- Constructor
	public signupgui(User user) {
		JPanel p = new JPanel();
		
		//================================================= Labels
		signUpL = new JLabel("Sign up");
		signUpL.setFont(new Font("Arial", Font.BOLD, 25));
		signUpL.setBounds(20, 10, 200, 50);
		p.add(signUpL);
		
		create = new JLabel("Create an account by filling in the form below");
		create.setFont(new Font("Arial", Font.ITALIC, 12));
		create.setBounds(20, 55, 300, 20);
		p.add(create);
		
		newUser = new JLabel("Username ");
		newUser.setFont(new Font("Arial", Font.PLAIN, 14));
		newUser.setBounds(20, 115, 100, 20);
		p.add(newUser);
		
		newPass = new JLabel("Password ");
		newPass.setFont(new Font("Arial", Font.PLAIN, 14));
		newPass.setBounds(20, 180, 100, 20);
		p.add(newPass);
		
		rePass = new JLabel("Re-enter password ");
		rePass.setFont(new Font("Arial", Font.PLAIN, 14));
		rePass.setBounds(20, 245, 150, 20);
		p.add(rePass);
		
		notify = new JLabel("");
		notify.setFont(new Font("Arial", Font.BOLD, 12));
		notify.setBounds(20, 350, 300, 20);
		p.add(notify);
		
		//================================================= Text fields
		textUser = new JTextField(20);
		textUser.setBounds(20, 135, 200, 25);
		p.add(textUser);
		
		textPass = new JPasswordField(20);
		textPass.setEchoChar('*');
		textPass.setBounds(20, 200, 200, 25);
		p.add(textPass);
		
		textRePass = new JPasswordField(20);
		textRePass.setEchoChar('*');
		textRePass.setBounds(20, 265, 200, 25);
		p.add(textRePass);
		
		//================================================= Buttons
		signUpB = new JButton("Sign Up");
		signUpB.addActionListener(this);
		signUpB.setBounds(65, 315, 100, 30);
		p.add(signUpB);
		
		this.user = user;
		
		add(p);
		p.setLayout(null);
	}

	//------------------------------------------------------------------- Instance methods
	/**
	 * Make a query to the database to check if the username is already in use
	 * @param username the username the user typed
	 * @return true if the username already existed, false otherwise
	 */
	public boolean existingUser(String username) {
		boolean ret = false;
		cm = new ConnectionManager();
		cm.getConnection();
		String query = "SELECT * FROM accounts WHERE username = '" + username + "';";
		ResultSet rs = cm.searchQuery(query);
		try {
			if (rs.next())
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cm.closeConnection();
		return ret;
	}
	
	/**
	 * Action listener method 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * Action performed when the sign up button is clicked. Checks if the username is already in use
		 * and compares the password and password confirmation. If they match, hash the password and insert
		 * the username and password into the database 
		 */
		if (e.getSource() == signUpB) {
			String pass = new String(textPass.getPassword());
			String rePass = new String(textRePass.getPassword());
			if (existingUser(textUser.getText().trim())) {
				JOptionPane.showMessageDialog(null, "Username already existed!");
			} else { 
				if (rePass.equals(pass)) {
					try {
						String query = "INSERT INTO accounts(username, password, role) VALUES "
								+ "('" + textUser.getText().trim() + "', '" + Hash.getInstance().generateStrongPasswordHash(pass)
								+ "', " + "'USER');"; 
						cm = new ConnectionManager();
						cm.getConnection();
						cm.updateQuery(query);
						JOptionPane.showMessageDialog(null, "Account created!");
						dispose();
						cm.closeConnection();
					} catch (Exception exc) {
						exc.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect password confirmation!");
				}
			}
		}
	} 
	
}
