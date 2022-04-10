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
	
	public ArrayList<Business> getData() {
		ArrayList<Business> ret = new ArrayList<Business>();
		try {
			stm = con.createStatement();
			String query = "SELECT * FROM businesses";
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
	
	public ArrayList<Business> dbSearch(String searchStr) {
		ArrayList<Business> ret = new ArrayList<Business>();
		try {
			stm = con.createStatement();
			String query = "SELECT * FROM businesses WHERE NAME LIKE '%" + searchStr + "%';";
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
	
	public void addRecord(String name, String date, int rating, String expense, String type, String owner, String number) {
		try {
			stm = con.createStatement();
			String query = "INSERT INTO businesses VALUES ('" + name + "', '" + date + "', " + rating + ", '" + expense
					+ "', '" + type + "', '" + owner + "', '" + number + "')";
			stm.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean dbContains(String searchStr) {
		boolean ret = true;
		try {
			stm = con.createStatement();
			String query = "SELECT * FROM businesses WHERE NAME = '" + searchStr + "';";
			rs = stm.executeQuery(query);
			ret = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}
