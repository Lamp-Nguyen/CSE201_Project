import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionManager {

	private static String url = "jdbc:mysql://127.0.0.1:3306/cse201project";
	private static String userName = "root";
	private static String password = "CSE201_Project_Group17";
	private Connection con = null;
	private ResultSet rs;
	private Statement stm;
	
	public ConnectionManager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet searchQuery(String query) {
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updateQuery(String query) {
		try {
			stm = con.createStatement();
			stm.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
