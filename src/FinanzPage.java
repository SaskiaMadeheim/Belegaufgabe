import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FinanzPage extends JFrame {
	public FinanzPage()
	{
		JFrame fenster = new JFrame();
		fenster.setLayout(new BorderLayout());
		
		/*
		fenster.add(oberesPanel, BorderLayout.NORTH);
		fenster.add(linkesPanel, BorderLayout.WEST);
		fenster.add(mittleresPanel, BorderLayout.CENTER);
		fenster.add(rechtesPanel, BorderLayout.EAST);
		
		fenster.setIconImage(bild.getImage()); */
		fenster.setTitle("Vereinsverwaltung");
		fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fenster.setSize(1280,720);
		fenster.setResizable(false);
		
		fenster.setVisible(true);
	}
}
