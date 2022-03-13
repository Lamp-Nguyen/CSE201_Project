import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * Testing class
 */

public class Tester {
	
	private static Sort<Business> businesses = new Sort<>();
	private static ArrayList<Business> aLBusinesses = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		Business test = new Business("Bob�s Burgers", "04/20/1970", 4, "Not Expensive", "Restaurant", "Bob Robertson", "6145555555");
		System.out.println(test);

		//Loading data from file
		LoadData();
		Collections.sort(aLBusinesses);
		


	}

	private static void LoadData() {
		try( Scanner fin = new Scanner(new File("Data.txt")) ) {
			fin.nextLine();
			while(fin.hasNextLine()){
				Business b = new Business(fin.nextLine());
				businesses.add(b);
				aLBusinesses.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
