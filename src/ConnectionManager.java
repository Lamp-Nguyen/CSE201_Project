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
	private static Connection con = null;
	private static ResultSet rs;
	private static Statement stm;
	
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
	
	public ArrayList<Business> getData() {
		ArrayList<Business> ret = new ArrayList<Business>();
		try {
			stm = con.createStatement();
			String query = "SELECT * from businesses";
			rs = stm.executeQuery(query);
			while (rs.next()) {
				String name = rs.getString("NAME");
				String date = rs.getString("DATE");
				int rating = rs.getInt("RATING");
				String expense = rs.getString("EXPENSE");
				String type = rs.getString("TYPE");
				String owner = rs.getString("OWNER");
				String number = rs.getString("NUMBER");
				Business toAdd = new Business(name, date, rating, expense, type, owner, number);
				ret.add(toAdd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
