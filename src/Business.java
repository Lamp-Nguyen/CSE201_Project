import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Business object class. Contains the instance variables [name, date, rating,
 * expense, type, owner, number], the default constructor as well as getters and
 * setters for the instance variables.
 * @author Lam_Nguyen
 *
 */

public class Business {
	
	//------------------------------------------------------------------- Instance variables
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private String name;
	private Date date;
	private int rating;
	private String expense;
	private String type;
	private String owner;
	private String number;
	
	//------------------------------------------------------------------- Constructor
	public Business(String name, String date, int rating, String expense, String type, String owner, String number) throws Exception {
		this.name = name;
		this.date = dateFormat.parse(date);
		this.rating = rating;
		this.expense = expense;
		this.type = type;
		this.owner = owner;
		this.number = number;
	}
	
	//------------------------------------------------------------------- Getters - Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
