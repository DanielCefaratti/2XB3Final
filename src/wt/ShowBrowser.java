package wt;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

public class ShowBrowser extends JFrame {
//	public static void main(String args[]) {
//		new ShowBrowser().start();
//	}

	void start(String html) {
		try {
			JEditorPane ed1 = new JEditorPane("text/html", html);
			add(ed1);
			setVisible(true);
			setSize(600, 600);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem has occured" + e.getMessage());
		}
	}
}