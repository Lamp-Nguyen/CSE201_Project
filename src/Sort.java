import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort {
	
	public static void defaultSort(ArrayList<Business> arr, boolean reverse) {
		Collections.sort(arr, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				if (!reverse) {
					return o1.getName().compareTo(o2.getName());
				} else {
					return o2.getName().compareTo(o1.getName());
				}
			}
		});
	}
	
	public static void dateSort(ArrayList<Business> arr, boolean reverse) {
		Collections.sort(arr, new Comparator<Business>() {
			@Override
			public int compare(Business o1, Business o2) {
				if (!reverse) {
					return o2.getDate().compareTo(o1.getDate());
				} else {
					return o2.getDate().compareTo(o1.getDate());
				}
			}
		});
	}
	
	public static void ratingSort(ArrayList<Business> arr, boolean reverse) {
		Collections.sort(arr, new Comparator<Business>() {
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
	
	public static void ownerSort(ArrayList<Business> arr, boolean reverse) {
		Collections.sort(arr, new Comparator<Business>() {
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
	
	public static void main(String[] args) throws Exception {
		ArrayList<Business> list = new ArrayList<Business>();
		
		list.add(new Business("Jakubowski, Schulist and Stiedemann", "1/28/1961", 4, "moderately expensive", "Restaurant", "Amaleta Poveleye", "408-832-4848"));
		list.add(new Business("Flatley, Collier and Bailey", "10/9/2020", 4, "inexpensive", "Restaurant", "Jervis Klemke", "719-819-9061"));
		list.add(new Business("Schiller LLC", "3/29/1963", 2, "moderately expensive", "Restaurant", "Casie Jiranek", "871-628-2423"));
		list.add(new Business("Emmerich-Strosin", "7/15/1950", 5, "inexpensive", "Bank", "Lane Pentecost", "564-996-4195"));
		list.add(new Business("Walker-Nitzsche", "5/23/1996", 1, "inexpensive", "Bar", "Philly Bollans", "442-956-5807"));
		list.add(new Business("Lesch-Hagenes", "8/30/1980", 2, "moderately expensive", "Hotel", "Christel Keam", "568-750-6816"));
		list.add(new Business("Kihn, Kuhn and Medhurst", "11/19/1969", 2, "very expensive", "Restaurant", "Ignacio Zannuto", "699-601-9170"));
		list.add(new Business("Mann, Smitham and McKenzie", "9/3/1996", 2, "very expensive", "Hotel", "Verine Smidmor", "975-785-7390"));
		list.add(new Business("Littel, Padberg and Davis", "3/30/1983", 1, "moderately expensive", "Bar", "Fan Musico", "862-931-4067"));
		list.add(new Business("Maggio Group", "4/3/1992", 2, "moderately expensive", "Bank", "Marillin Rootham", "883-511-3199"));
		list.add(new Business("Johns-Swift", "3/11/2020", 1, "very expensive", "Bar", "Ron Kalinowsky", "836-239-2478"));	
	}
}
