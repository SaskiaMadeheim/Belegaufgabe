import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MitgliederPage extends JFrame{
	public MitgliederPage() {
		
		ImageIcon bild = new ImageIcon("logo.png");
		Border buttonBoder = BorderFactory.createLineBorder(new Color(47,85,178), 3);
		
		JFrame fenster = new JFrame();
		fenster.setLayout(new GridLayout());
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("logo.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(dimg);
		
		JLabel logoPanel = new JLabel();
		logoPanel.setIcon(logo);
		logoPanel.setBounds(5, 5, 235, 190);
		logoPanel.setVerticalAlignment(JLabel.BOTTOM);
		logoPanel.setHorizontalAlignment(JLabel.LEFT);
		
		JButton back = new JButton("zur\u00fcck");
		back.setFocusable(false);
		back.setPreferredSize(new Dimension(100,50));;
		back.setBackground(new Color(255,255,255));
		back.setBorder(buttonBoder);
		back.setForeground(new Color(47,85,178));
		back.setFont(new Font("Arial", Font.PLAIN, 20));
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fenster.dispose();
				LaunchPage lp = new LaunchPage();
			}
		});
		
		JPanel linksOben = new JPanel();
		linksOben.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		linksOben.setBackground(new Color(255,255,255));
		
		linksOben.add(logoPanel);
		linksOben.add(back);
		
		JButton neuesMitglied = new JButton("neues Mitglied");
		neuesMitglied.setFocusable(false);
		neuesMitglied.setPreferredSize(new Dimension(250,50));;
		neuesMitglied.setBackground(new Color(255,255,255));
		neuesMitglied.setBorder(buttonBoder);
		neuesMitglied.setForeground(new Color(47,85,178));
		neuesMitglied.setFont(new Font("Arial", Font.PLAIN, 20));
		neuesMitglied.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame addFenster = new JFrame();
				addFenster.setLayout(new BorderLayout());
				addFenster.setVisible(true);
				
				JLabel vorname = new JLabel("Vorname:");
				vorname.setForeground(new Color(47,85,178));
				vorname.setFont(new Font("Arial", Font.PLAIN, 18));
				
				JTextField vornameIn = new JTextField();
				vornameIn.setForeground(new Color(47,85,178));
				vornameIn.setFont(new Font("Arial", Font.PLAIN, 16));
				
				JLabel nachname = new JLabel("Nachname:");
				nachname.setForeground(new Color(47,85,178));
				nachname.setFont(new Font("Arial", Font.PLAIN, 18));
				
				JTextField nachnameIn = new JTextField();
				nachnameIn.setForeground(new Color(47,85,178));
				nachnameIn.setFont(new Font("Arial", Font.PLAIN, 16));
				
				JLabel adresse = new JLabel("Adresse:");
				adresse.setForeground(new Color(47,85,178));
				adresse.setFont(new Font("Arial", Font.PLAIN, 18));
				
				JTextField adresseIn = new JTextField();
				adresseIn.setForeground(new Color(47,85,178));
				adresseIn.setFont(new Font("Arial", Font.PLAIN, 16));
				
				JLabel email = new JLabel("Email:");
				email.setForeground(new Color(47,85,178));
				email.setFont(new Font("Arial", Font.PLAIN, 18));
				
				JTextField emailIn = new JTextField();
				emailIn.setForeground(new Color(47,85,178));
				emailIn.setFont(new Font("Arial", Font.PLAIN, 16));
				
				JLabel geburtsjahr = new JLabel("Geburtsjahr:");
				geburtsjahr.setForeground(new Color(47,85,178));
				geburtsjahr.setFont(new Font("Arial", Font.PLAIN, 18));
				
				JTextField geburtsjahrIn = new JTextField();
				geburtsjahrIn.setForeground(new Color(47,85,178));
				geburtsjahrIn.setFont(new Font("Arial", Font.PLAIN, 16));
				
				JLabel abteilung = new JLabel("Abteilung (int):");
				abteilung.setForeground(new Color(47,85,178));
				abteilung.setFont(new Font("Arial", Font.PLAIN, 18));
				
				JTextField abteilungIn = new JTextField();
				abteilungIn.setForeground(new Color(47,85,178));
				abteilungIn.setFont(new Font("Arial", Font.PLAIN, 16));
				
				JLabel art = new JLabel("Art:");
				art.setForeground(new Color(47,85,178));
				art.setFont(new Font("Arial", Font.PLAIN, 18));
				
				String[] arten = {"Erwachsener", "Kind", "Student"};
				JComboBox artIn = new JComboBox(arten);
				artIn.setForeground(new Color(47,85,178));
				artIn.setFont(new Font("Arial", Font.PLAIN, 16));
				
				
				JPanel addFensterOben = new JPanel();
				addFensterOben.setLayout(new GridLayout(7, 2));
				addFensterOben.setBackground(Color.white);
				Border borderOben = BorderFactory.createEmptyBorder(10, 10, 10, 10);
				addFensterOben.setBorder(borderOben);
				
				addFensterOben.add(vorname);
				addFensterOben.add(vornameIn);
				addFensterOben.add(nachname);
				addFensterOben.add(nachnameIn);
				addFensterOben.add(adresse);
				addFensterOben.add(adresseIn);
				addFensterOben.add(email);
				addFensterOben.add(emailIn);
				addFensterOben.add(geburtsjahr);
				addFensterOben.add(geburtsjahrIn);
				addFensterOben.add(abteilung);
				addFensterOben.add(abteilungIn);
				addFensterOben.add(art);
				addFensterOben.add(artIn);
				
				JButton hinzufuegen = new JButton("hinzuf\u00fcgen");
				hinzufuegen.setFocusable(false);
				hinzufuegen.setPreferredSize(new Dimension(150,40));;
				hinzufuegen.setBackground(new Color(255,255,255));
				hinzufuegen.setBorder(buttonBoder);
				hinzufuegen.setForeground(new Color(47,85,178));
				hinzufuegen.setFont(new Font("Arial", Font.PLAIN, 20));
				hinzufuegen.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == hinzufuegen)
						{
							String artWahl = (String) artIn.getItemAt(artIn.getSelectedIndex());
							String[] name = {vornameIn.getText(), nachnameIn.getText()};
							String adresse = adresseIn.getText();
							String email = emailIn.getText();
							int geburtsjahr = Integer.parseInt(geburtsjahrIn.getText());
							Abteilung abteilung = Integer.parseInt(abteilungIn.getText());
							
							if(artWahl.equals("Erwachsener"))
							{
								Erwachsener erw = new Erwachsener(name, adresse, email, geburtsjahr, abteilung);
							}
							
							if(artWahl.equals("Kind"))
							{
								Kind kind = new Kind(name, adresse, email, geburtsjahr, abteilung);
							}
							
							if(artWahl.equals("Student"))
							{
								Student stud = new Student(name, adresse, email, geburtsjahr, abteilung);
							}
							
							addFenster.dispose();
						}
						/*{
							Finanzbewegung bewegung = new Finanzbewegung();
							bewegung.setName(nameIn.getText());
							bewegung.setDatum(datumIn.getText());
							bewegung.setAbteilung(abteilungIn.getText());
							bewegung.setBetrag(Double.parseDouble(betragIn.getText()));
							
							model.hinzufFinanzbewegung(bewegung);
							
							//bewegung.gebeInfos();
							try {
								finanzdaten.newFinanzBewegung(bewegung);
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							addFenster.dispose();
						}*/
						
					}
				});
				JPanel addFensterUnten = new JPanel();
				addFensterUnten.setBackground(Color.white);
				addFensterUnten.add(hinzufuegen);
				
				
				addFenster.add(addFensterOben, BorderLayout.NORTH);
				addFenster.add(addFensterUnten, BorderLayout.SOUTH);
				
				addFenster.setIconImage(bild.getImage());
				addFenster.setTitle("neues Mitglied");
				addFenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
				addFenster.setResizable(false);
				addFenster.pack();
				addFenster.setVisible(true);
			}
		});
		
		JPanel linksUnten = new JPanel();
		linksUnten.add(neuesMitglied);
		
		JPanel linkeSeite = new JPanel();
		linkeSeite.add(linksOben);
		linkeSeite.add(linksUnten);
		
		JPanel rechteSeite = new JPanel();
		
		fenster.add(linkeSeite);
		fenster.add(rechteSeite);
		
		fenster.setIconImage(bild.getImage());
		fenster.setTitle("Mitgliederverwaltung");
		fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fenster.setSize(1280,720);
		fenster.setResizable(true);
		
		fenster.setVisible(true);
	}
}
