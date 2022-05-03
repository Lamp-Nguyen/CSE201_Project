import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The working array list for the program that stores a list of Business objects. Contains methods
 * for connecting to the database to make queries and sorting methods
 * @author Lam_Nguyen
 *
 */

public class BusinessList {

	//------------------------------------------------------------------- Instance variables
	private ArrayList<Business> data = null;
	private ResultSet rs;
	private ConnectionManager cm;
	
	//------------------------------------------------------------------- Constructor
	public BusinessList() {
		data = new ArrayList<Business>();
	}

	//------------------------------------------------------------------- Instance methods
	/**
	 * Returns the size of the array list
	 * @return array list size
	 */
	public int size() {
		return data.size();
	}
	
	/**
	 * Check if array list is empty
	 * @return true if there is no records in the array list, false otherwise
	 */
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	/**
	 * Convert the array list of businesses into an array
	 * @return the array of businesses
	 */
	public Business[] dataArary() {
		return data.toArray(new Business[data.size()]);
	}
	
	/**
	 * Get the records from the database that contains the search string and form a ResultSet.
	 * Then create the business objects from the ResultSet and update the working list
	 * @param searchStr
	 */
	public void getData(String searchStr) {
		data = new ArrayList<Business>();
		cm = new ConnectionManager();
		cm.getConnection();
		String query = "SELECT * FROM business WHERE NAME LIKE '%" + searchStr + "%';";
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
	
	/**
	 * Get the records from the database that has the searchStr as its business's name
	 * and form a ResultSet. Check if the ResultSet is empty, which implies that there is
	 * no record that has the searchStr as the name.
	 * @param searchStr
	 * @return true if the result set contains the record, false otherwise
	 */
	public boolean contains(String searchStr) {
		boolean ret = true;
		cm = new ConnectionManager();
		cm.getConnection();
		String query = "SELECT * FROM business WHERE NAME = '" + searchStr + "';";
		rs = cm.searchQuery(query);
		try {
			ret = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		cm.closeConnection();
		return ret;
	}
	
	/**
	 * Add a new record into the database. Create a new Business object from the parameters
	 * and add it to the working array list
	 * @param name the business's name
	 * @param date the business's date
	 * @param rating the business's rating
	 * @param expense the business's expense type
	 * @param type the business's type
	 * @param owner the business's owner
	 * @param number the business's number
	 */
	public void addRecord(String name, String date, int rating, String expense, String type, String owner, String number) {
		cm = new ConnectionManager();
		cm.getConnection();
		String query = "INSERT INTO business VALUES ('" + name + "', '" + date + "', " + rating + ", '" + expense
				+ "', '" + type + "', '" + owner + "', '" + number + "')";
		cm.updateQuery(query);
		try {
			data.add(new Business(name, date, rating, expense, type, owner, number));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cm.closeConnection();
	}
	
	/**
	 * Sort the working array list by the business's date
	 * @param reverse does the list needs to be sort in reverse
	 */
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
	
	/**
	 * Sort the working array list by the business's name
	 * @param reverse does the list needs to be sort in reverse
	 */
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
	
	/**
	 * Sort the working array list by the business's rating
	 * @param reverse does the list needs to be sort in reverse
	 */
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
	
	/**
	 * Sort the working array list by the business's owner name
	 * @param reverse does the list needs to be sort in reverse
	 */
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
