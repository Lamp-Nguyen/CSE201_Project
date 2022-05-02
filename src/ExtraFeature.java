import java.awt.Desktop;
import java.net.URI;
import java.net.URL;

/**
 * Singleton class for random and uncesssary features
 * @author Lam_Nguyen
 *
 */

public class ExtraFeature {

	private static ExtraFeature extraInstance;
	private String stringUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
	
	private ExtraFeature() {
	}
	
	public static ExtraFeature getExtraInstance() {
		if (extraInstance == null)
			extraInstance = new ExtraFeature();
		return extraInstance;
	}
	
	/**
	 * Open a webpage with the specified URL
	 * @param uri
	 */
	public void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	/**
	 * Open a webpage in your default browser
	 */
	public void openWebpage() {
	    try {
	    	URL url = new URL(stringUrl);
	        openWebpage(url.toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}
