/**
 * User object class. Contains the instance variables [username, role], the default constructor 
 * as well as getters and setters for the instance variables.
 * @author Lam_Nguyen
 *
 */

public class User {

	//------------------------------------------------------------------- Instance variables
	private String username;
	private String role;
	
	//------------------------------------------------------------------- Constructor
	public User() {
		username = null;
		role = null;
	}
	
	//------------------------------------------------------------------- Getters - Setters
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setName(String username) {
		this.username = username;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public String getName() {
		return this.username;
	}
}
