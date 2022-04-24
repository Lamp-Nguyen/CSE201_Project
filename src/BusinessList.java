import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BusinessList {

	private ArrayList<Business> data = null;
	private ResultSet rs;
	private ConnectionManager cm;
	
	public BusinessList() {
		data = new ArrayList<Business>();
	}

	public int size() {
		return data.size();
	}
	
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	public Business[] dataArary() {
		return data.toArray(new Business[data.size()]);
	}
	
	public void getData(String searchStr) {
		data = new ArrayList<Business>();
		cm = new ConnectionManager();
		cm.getConnection();
		String query = "SELECT * FROM businesses WHERE NAME LIKE '%" + searchStr + "%';";
		rs = cm.searchQuery(query);
		try {
			while (rs.next()) {
				String name = rs.getString("NAME");
				String date = rs.getString("DATE");
				int rating = rs.getInt("RATING");
				String expense = rs.getString("EXPENSE");
				String type = rs.getString("TYPE");
				String owner = rs.getString("OWNER");
				String number = rs.getString("NUMBER");
				Business tmp = new Business(name, date, rating, expense, type, owner, number);
				data.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cm.closeConnection();
	}
	
	
	public boolean contains(String searchStr) {
		boolean ret = true;
		cm = new ConnectionManager();
		cm.getConnection();
		String query = "SELECT * FROM businesses WHERE NAME = '" + searchStr + "';";
		rs = cm.searchQuery(query);
		try {
			ret = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		cm.closeConnection();
		return ret;
	}
	
	public void addRecord(String name, String date, int rating, String expense, String type, String owner, String number) {
		cm = new ConnectionManager();
		cm.getConnection();
		String query = "INSERT INTO businesses VALUES ('" + name + "', '" + date + "', " + rating + ", '" + expense
				+ "', '" + type + "', '" + owner + "', '" + number + "')";
		cm.updateQuery(query);
		try {
			data.add(new Business(name, date, rating, expense, type, owner, number));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cm.closeConnection();
	}
	
	public void dateSort(boolean reverse) {
		Collections.sort(data, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				if (!reverse) {
					return o2.getDate().compareTo(o1.getDate());
				} else {
					return o1.getDate().compareTo(o2.getDate());
				}
			}
		});
	}
	
	public void nameSort(boolean reverse) {
		Collections.sort(data, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				String o1Name = o1.getName().replaceAll("[^A-Za-z0-9 ]", "");
				String o2Name = o2.getName().replaceAll("[^A-Za-z0-9 ]", "");
				if (!reverse) {
					return o1Name.compareTo(o2Name);
				} else {
					return o2Name.compareTo(o1Name);
				}
			}
		});
	}
	
	public void ratingSort(boolean reverse) {
		Collections.sort(data, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				if (!reverse) {
					return o2.getRating() - o1.getRating();
				} else {
					return o1.getRating() - o2.getRating();
				}
			}
		});
	}
	
	public void ownerSort(boolean reverse) {
		Collections.sort(data, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				if (!reverse) {
					return o1.getOwner().compareTo(o2.getOwner());
				} else {
					return o2.getOwner().compareTo(o1.getOwner());
				}
			}
		});
	}
}
