import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class MetaApp {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		/*
		 * Flat Look & Feel for the GUI
		 * Source: https://github.com/JFormDesigner/FlatLaf#applications-using-flatlaf
		 */
		FlatIntelliJLaf.setup();
		GUI app = new GUI();
		app.setSize(1024, 768);
		app.setLocationRelativeTo(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
}
