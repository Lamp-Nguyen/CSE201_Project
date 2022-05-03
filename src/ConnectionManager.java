import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Manages the connection to the database
 * @author Lam_Nguyen
 *
 */

public class ConnectionManager {

	//------------------------------------------------------------------- Instance variables
	/*
	 * Database's IP address, the root username and password
	 */
	private static String url = "jdbc:mysql:aws://cse201project.cl93exr6xql3.us-east-1.rds.amazonaws.com:3306/project";
	private static String userName = "root";
	private static String password = "business+index";
	
	private Connection con = null;
	private ResultSet rs;
	private Statement stm;
	
	//------------------------------------------------------------------- Constructor
	public ConnectionManager() {
	}
	
	//------------------------------------------------------------------- Instance methods
	/**
	 * Establish a connection to the database by initializing the Connection instance variable
	 */
	public void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close the opened connection
	 */
	public void closeConnection() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Search the database and create a ResulSet of the queried records
	 * @param query the query to be executed
	 * @return the ResultSet containing the queried results
	 */
	public ResultSet searchQuery(String query) {
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Update the database depending on the query
	 * @param query the query to be executed
	 * @return the ResultSet containing the queried results
	 */
	public void updateQuery(String query) {
		try {
			stm = con.createStatement();
			stm.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
