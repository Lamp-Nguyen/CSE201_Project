import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class logingui extends JDialog implements ActionListener{
	
	private JLabel loginL, userL, passL, accL, notifyL;
	private JTextField userT;
	private JPasswordField passT;
	private JButton loginB;
	private ConnectionManager cm;
	private UserPanel userP;
	
	public logingui(UserPanel userP) {
		JPanel p = new JPanel();
		
		//================================================= Labels
		loginL = new JLabel("Sign in");
		loginL.setFont(new Font("Arial", Font.BOLD, 25));
		loginL.setBounds(20, 10, 200, 50);
		p.add(loginL);
		
		userL = new JLabel("Username ");
		userL.setFont(new Font("Arial", Font.PLAIN, 14));
		userL.setBounds(20, 115, 100, 20);
		p.add(userL);
		
		accL = new JLabel("Fill in the information below to sign in");
		accL.setFont(new Font("Arial", Font.ITALIC, 12));
		accL.setBounds(20, 55, 300, 20);
		p.add(accL);
		
		passL = new JLabel("Password ");
		passL.setFont(new Font("Arial", Font.PLAIN, 14));
		passL.setBounds(20, 180, 100, 20);
		p.add(passL);
		
		notifyL = new JLabel("");
		notifyL.setFont(new Font("Arial", Font.BOLD, 12));
		notifyL.setBounds(20, 300, 300, 20);
		p.add(notifyL);
		
		//================================================= Text fields
		userT = new JTextField(20);
		userT.setBounds(20, 135, 200, 25);
		p.add(userT);
		
		passT = new JPasswordField(20);
		passT.setEchoChar('*');
		passT.setBounds(20, 200, 200, 25);
		p.add(passT);
		
		//================================================= Buttons
		loginB = new JButton("Sign in");
		loginB.addActionListener(this);
		loginB.setBounds(65, 250, 100, 30);
		p.add(loginB);
		
		this.userP = userP;
		add(p);
		
		p.setLayout(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginB) {
			String username = userT.getText().trim();
			cm = new ConnectionManager();
			cm.getConnection();
			
			String query = "SELECT * FROM accounts WHERE username = '" + username + "';";
			ResultSet rs = cm.searchQuery(query);
			
			String pass = new String(passT.getPassword());
			
			try {
				if (rs.next() && Hash.getInstance().validatePassword(pass, rs.getString("password"))) {
					userP.getUser().setName(rs.getString("username"));		
					userP.getUser().setRole(rs.getString("role"));
					dispose();
					
					userP.removeAll();
					userP.userView();
					userP.revalidate();
					userP.repaint();
					
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect username or password!");
				}
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			cm.closeConnection();
		}
	}
}
