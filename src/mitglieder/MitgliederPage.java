package mitglieder;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

import gui.LaunchPage;

@SuppressWarnings("serial")
public class MitgliederPage extends JFrame{
	public MitgliederPage() throws FileNotFoundException {
		
		ArrayList<Mitglied> mitgliederliste = Mitgliederverwaltung.leseCSV("Mitgliederliste.txt");
		final MitgliedTabelleModel model = new MitgliedTabelleModel();
		
		for(Mitglied m : mitgliederliste)
		{
			model.hinzufMitglied(m);
		}
		
		ImageIcon bild = new ImageIcon("logo.png");
		Border buttonBoder = BorderFactory.createLineBorder(new Color(47,85,178), 3);
		
		JFrame fenster = new JFrame();
		fenster.setLayout(new BorderLayout());
		
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
				try {
					LaunchPage lp = new LaunchPage();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				
				JLabel abteilung = new JLabel("Abteilung:");
				abteilung.setForeground(new Color(47,85,178));
				abteilung.setFont(new Font("Arial", Font.PLAIN, 18));
				
				String[] artenAbt = {"Handball", "Fussball", "Basketball"};
				JComboBox abteilungIn = new JComboBox(artenAbt);
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
							String abtWahl = (String) abteilungIn.getItemAt(abteilungIn.getSelectedIndex());
							
							Abteilung abteilung = Abteilung.HANDBALL;
							
							if(abtWahl.equals("Fussball"))
								abteilung = abteilung.FUSSBALL;
							
							if(abtWahl.equals("Basketball"))
								abteilung = abteilung.BASKETBALL;
							
							if(artWahl.equals("Erwachsener"))
							{
								Erwachsener erw = new Erwachsener(name, adresse, email, geburtsjahr, abteilung);
								mitgliederliste.add(erw);
								model.hinzufMitglied(erw);
							}
							
							if(artWahl.equals("Kind"))
							{
								Kind kind = new Kind(name, adresse, email, geburtsjahr, abteilung);
								mitgliederliste.add(kind);
								model.hinzufMitglied(kind);
							}
							
							if(artWahl.equals("Student"))
							{
								Student stud = new Student(name, adresse, email, geburtsjahr, abteilung);
								mitgliederliste.add(stud);
								model.hinzufMitglied(stud);
							}
							
							try {
								Mitgliederverwaltung.schreibeCSV("Mitgliederliste.txt", mitgliederliste);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							addFenster.dispose();
							
						}
						
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
		
		JButton entferneMitglied = new JButton("entferne Mitglied");
		entferneMitglied.setFocusable(false);
		entferneMitglied.setPreferredSize(new Dimension(250,50));;
		entferneMitglied.setBackground(new Color(255,255,255));
		entferneMitglied.setBorder(buttonBoder);
		entferneMitglied.setForeground(new Color(47,85,178));
		entferneMitglied.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel stats = new JLabel("Statistiken");
		stats.setFont(new Font("Arial", Font.BOLD, 24));
		stats.setForeground(new Color(47,85,178));
		stats.setHorizontalAlignment(JLabel.CENTER);
		stats.setVerticalAlignment(JLabel.CENTER);
		
		JLabel gesamt = new JLabel("Mitgliederzahl: ");
		gesamt.setFont(new Font("Arial", Font.PLAIN, 20));
		gesamt.setForeground(new Color(47,85,178));
		
		JLabel gesamtZahl = new JLabel("" + mitgliederliste.size());
		gesamtZahl.setFont(new Font("Arial", Font.PLAIN, 20));
		gesamtZahl.setForeground(new Color(47,85,178));
		
		JLabel einnahmen = new JLabel("Mitgliedsbeitrag: ");
		einnahmen.setFont(new Font("Arial", Font.PLAIN, 20));
		einnahmen.setForeground(new Color(47,85,178));
		
		JLabel einnahmenZahl = new JLabel(String.format("%.2f", Mitgliederverwaltung.SummeBeitraege(mitgliederliste)));
		einnahmenZahl.setFont(new Font("Arial", Font.PLAIN, 20));
		einnahmenZahl.setForeground(new Color(47,85,178));
		
		JPanel statPanel = new JPanel();
		statPanel.setBackground(Color.WHITE);
		statPanel.setLayout(new GridLayout(2, 2));
		statPanel.add(gesamt);
		statPanel.add(gesamtZahl);
		statPanel.add(einnahmen);
		statPanel.add(einnahmenZahl);
		statPanel.setBorder(BorderFactory.createMatteBorder(5, 50, 50, 5, Color.WHITE));
		
		JPanel statistik = new JPanel();
		statistik.setBackground(Color.WHITE);
		statistik.setLayout(new BorderLayout());
		statistik.add(stats, BorderLayout.NORTH);
		statistik.add(statPanel, BorderLayout.CENTER);
		statistik.setBorder(BorderFactory.createMatteBorder(30, 0, 0, 30, Color.WHITE));
				
		JPanel linksUnten = new JPanel();
		linksUnten.setBackground(Color.WHITE);
		linksUnten.add(statistik);
		linksUnten.add(neuesMitglied);
		linksUnten.add(entferneMitglied);
		
		JPanel linkeSeite = new JPanel();
		linkeSeite.setBackground(Color.WHITE);
		linkeSeite.setPreferredSize(new Dimension(600,720));
		linkeSeite.setLayout(new BorderLayout());
		linkeSeite.add(linksOben, BorderLayout.NORTH);
		linkeSeite.add(linksUnten, BorderLayout.CENTER);
		
		JTable tabelle = new JTable(model);
		tabelle.setBackground(new Color(255,255,255));
		tabelle.setForeground(new Color(47,85,178));
		tabelle.setFont(new Font("Arial", Font.PLAIN, 18));
		tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//tabelle.setPreferredSize(new Dimension(600,1000));
		//tabelle.setBorder(BorderFactory.createMatteBorder(0, 3, 3, 3, new Color(47,85,178)));
		tabelle.setShowGrid(true);
		tabelle.setGridColor(new Color(47,85,178));
		tabelle.setRowHeight(30);
		tabelle.setIntercellSpacing(new Dimension(7,0));
		tabelle.setFocusable(false);
		
		tabelle.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelle.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabelle.getColumnModel().getColumn(2).setPreferredWidth(90);
		tabelle.getColumnModel().getColumn(3).setPreferredWidth(40);

		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tabelle.getColumn("Beitrag").setCellRenderer(rightRenderer);
		
		tabelle.getTableHeader().setBackground(new Color(47,85,178));
		tabelle.getTableHeader().setForeground(new Color(255,255,255));
		tabelle.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		tabelle.getTableHeader().setOpaque(false);
		
		tabelle.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					
					System.out.print("test");
					
					JFrame frame = new JFrame();
					frame.setTitle("Mitglied");
					frame.setIconImage(bild.getImage());
					frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
					
					JLabel name = new JLabel("Name: ");
					name.setForeground(new Color(47,85,178));
					name.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel nameDis = new JLabel(mitgliederliste.get(row).getName()[0] + " " + mitgliederliste.get(row).getName()[1]); 
					nameDis.setForeground(new Color(47,85,178));
					nameDis.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel adresse = new JLabel("Adresse: ");
					adresse.setForeground(new Color(47,85,178));
					adresse.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel adresseDis = new JLabel(mitgliederliste.get(row).getAdresse());
					adresseDis.setForeground(new Color(47,85,178));
					adresseDis.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel email = new JLabel("Email: ");
					email.setForeground(new Color(47,85,178));
					email.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel emailDis = new JLabel(mitgliederliste.get(row).getEmail());
					emailDis.setForeground(new Color(47,85,178));
					emailDis.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel geburtsjahr = new JLabel("Geburtsjahr: ");
					geburtsjahr.setForeground(new Color(47,85,178));
					geburtsjahr.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel geburtsjahrDis = new JLabel("" + mitgliederliste.get(row).getGeburtsjahr());
					geburtsjahrDis.setForeground(new Color(47,85,178));
					geburtsjahrDis.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel abteilung = new JLabel("Abteilung: ");
					abteilung.setForeground(new Color(47,85,178));
					abteilung.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel abteilungDis = new JLabel(mitgliederliste.get(row).getAbteilung().name());
					abteilungDis.setForeground(new Color(47,85,178));
					abteilungDis.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel beitrag = new JLabel("Beitrag: ");
					beitrag.setForeground(new Color(47,85,178));
					beitrag.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JLabel beitragDis = new JLabel("" + mitgliederliste.get(row).getBeitrag());
					beitragDis.setForeground(new Color(47,85,178));
					beitragDis.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JPanel pan = new JPanel();
					pan.setForeground(Color.WHITE);
					pan.setBackground(Color.WHITE);
					pan.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
					pan.setLayout(new GridLayout(6,2));
					
					pan.add(name);
					pan.add(nameDis);
					pan.add(adresse);
					pan.add(adresseDis);
					pan.add(email);
					pan.add(emailDis);
					pan.add(geburtsjahr);
					pan.add(geburtsjahrDis);
					pan.add(abteilung);
					pan.add(abteilungDis);
					pan.add(beitrag);
					pan.add(beitragDis);
					
					frame.add(pan);
					frame.setForeground(Color.WHITE);
					frame.pack();
					frame.setVisible(true);
				}
			}
		});
		
		JScrollPane pane = new JScrollPane(tabelle);
		pane.setPreferredSize(new Dimension(600,660));
		pane.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, new Color(47,85,178)));
		
		
		JPanel rechteSeite = new JPanel();
		rechteSeite.add(pane);
		rechteSeite.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		rechteSeite.setBackground(Color.WHITE);
		
		fenster.add(linkeSeite, BorderLayout.WEST);
		fenster.add(rechteSeite, BorderLayout.CENTER);
		
		fenster.setIconImage(bild.getImage());
		fenster.setTitle("Mitgliederverwaltung");
		fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fenster.setSize(1280,720);
		fenster.setResizable(true);
		
		fenster.setVisible(true);
	}
}
