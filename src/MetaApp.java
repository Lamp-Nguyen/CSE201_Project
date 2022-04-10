import javax.swing.JFrame;

public class MetaApp {
	public static void main(String[] args) {
		GUI app = new GUI();
		app.setSize(1024, 768);
		app.setLocationRelativeTo(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
}
