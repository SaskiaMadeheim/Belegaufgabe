import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FinanzPage extends JFrame {
	public FinanzPage()
	{
		ImageIcon bild = new ImageIcon("logo.png");
		JFrame fenster = new JFrame();
		fenster.setLayout(new BorderLayout());
		
		fenster.setIconImage(bild.getImage());
		fenster.setTitle("Finanzverwaltung");
		fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fenster.setSize(1280,720);
		fenster.setResizable(true);
		
		fenster.setVisible(true);
	}
}
