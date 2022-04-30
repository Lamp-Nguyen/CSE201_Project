public class User {

	private String username;
	private String role;
	private ConnectionManager cm;
	
	public User() {
		username = null;
		role = null;
	}
	
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
