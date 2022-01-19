package finanzen;
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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class FinanzPage extends JFrame{
	
	public FinanzPage() throws FileNotFoundException
	{
		Finanzdaten finanzdaten = new Finanzdaten("dat.csv");
		ArrayList<Finanzbewegung> einlesen = finanzdaten.leseCSV("dat.csv");
		finanzdaten.setKontobew(einlesen);
		
		JLabel gesamtzahl = new JLabel();
		JLabel einnahmenBetrag = new JLabel();
		JLabel ausgabenBetrag = new JLabel();
		JLabel differenzBetrag = new JLabel();
		
		ImageIcon bild = new ImageIcon("logo1.png");
		Border buttonBoder = BorderFactory.createLineBorder(new Color(47,85,178), 3);
		
		JFrame fenster = new JFrame();
		fenster.setLayout(new GridLayout());
		
		final TabelleModel model = new TabelleModel();
		for(Finanzbewegung f : einlesen)
		{
			model.hinzufFinanzbewegung(f);
		}
		
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
		
		tabelle.getColumnModel().getColumn(0).setPreferredWidth(110);
		tabelle.getColumnModel().getColumn(1).setPreferredWidth(110);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tabelle.getColumn("Betrag").setCellRenderer(rightRenderer);
		
		tabelle.getTableHeader().setBackground(new Color(47,85,178));
		tabelle.getTableHeader().setForeground(new Color(255,255,255));
		tabelle.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		tabelle.getTableHeader().setOpaque(false);
		
		
		JButton bewegung = new JButton("neue Kontobewegung");
		bewegung.setFocusable(false);
		bewegung.setPreferredSize(new Dimension(250,50));;
		bewegung.setBackground(new Color(255,255,255));
		bewegung.setBorder(buttonBoder);
		bewegung.setForeground(new Color(47,85,178));
		bewegung.setFont(new Font("Arial", Font.PLAIN, 20));
		bewegung.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == bewegung)
				{
					JFrame addFenster = new JFrame();
					addFenster.setLayout(new BorderLayout());
					
					JLabel datum = new JLabel("Buchungsdatum: ");
					datum.setForeground(new Color(47,85,178));
					datum.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JTextField datumIn = new JTextField();
					datumIn.setForeground(new Color(47,85,178));
					datumIn.setFont(new Font("Arial", Font.PLAIN, 16));
					
					JLabel name = new JLabel("Buchungstext:");
					name.setForeground(new Color(47,85,178));
					name.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JTextField nameIn = new JTextField();
					nameIn.setForeground(new Color(47,85,178));
					nameIn.setFont(new Font("Arial", Font.PLAIN, 16));
					
					JLabel abteilung = new JLabel("Abteilung:");
					abteilung.setForeground(new Color(47,85,178));
					abteilung.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JTextField abteilungIn = new JTextField();
					abteilungIn.setForeground(new Color(47,85,178));
					abteilungIn.setFont(new Font("Arial", Font.PLAIN, 16));
					
					JLabel betrag = new JLabel("Betrag:");
					betrag.setForeground(new Color(47,85,178));
					betrag.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JTextField betragIn = new JTextField();
					betragIn.setForeground(new Color(47,85,178));
					betragIn.setFont(new Font("Arial", Font.PLAIN, 16));
					
					JPanel addFensterOben = new JPanel();
					addFensterOben.setLayout(new GridLayout(4, 2));
					addFensterOben.setBackground(Color.white);
					Border borderOben = BorderFactory.createEmptyBorder(10, 10, 10, 10);
					addFensterOben.setBorder(borderOben);
					
					addFensterOben.add(datum);
					addFensterOben.add(datumIn);
					addFensterOben.add(name);
					addFensterOben.add(nameIn);
					addFensterOben.add(abteilung);
					addFensterOben.add(abteilungIn);
					addFensterOben.add(betrag);
					addFensterOben.add(betragIn);
					
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
								/*Finanzbewegung bewegung = new Finanzbewegung();
								bewegung.setName(nameIn.getText());
								bewegung.setDatumSt(datumIn.getText());
								bewegung.setAbteilung(abteilungIn.getText());
								bewegung.setBetrag(Double.parseDouble(betragIn.getText()));*/
								
								Finanzbewegung bewegung = new Finanzbewegung(nameIn.getText(), 
										datumIn.getText(), 
										Double.parseDouble(betragIn.getText()), 
										abteilungIn.getText());
								
								model.hinzufFinanzbewegung(bewegung);
								
								//bewegung.gebeInfos();
								try {
									finanzdaten.newFinanzBewegung(bewegung);
								} catch (FileNotFoundException e1) {
									e1.printStackTrace();
								}
								addFenster.dispose();
								
								gesamtzahl.setText("Gesamtzahl der Buchungen: " + finanzdaten.getKontobew().size());
								einnahmenBetrag.setText(String.format("%.2f",finanzdaten.getJaehrlEinnahmen()));
								ausgabenBetrag.setText(String.format("%.2f",finanzdaten.getJaehrlAusgaben()));
								
								try {
									FinanzPage fp = new FinanzPage();
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								fenster.dispose();
							}
							
						}
					});
					
					JPanel addFensterUnten = new JPanel();
					addFensterUnten.setBackground(Color.white);
					addFensterUnten.add(hinzufuegen);
					
					
					addFenster.add(addFensterOben, BorderLayout.NORTH);
					addFenster.add(addFensterUnten, BorderLayout.SOUTH);
					
					addFenster.setIconImage(bild.getImage());
					addFenster.setTitle("neue Bewegung");
					addFenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
					addFenster.setResizable(false);
					addFenster.pack();
					addFenster.setVisible(true);
				}
			}
		});
		
		JButton exportiereInExcel = new JButton("Exportiere Daten in Excel");
		exportiereInExcel.setFocusable(false);
		exportiereInExcel.setPreferredSize(new Dimension(250,50));;
		exportiereInExcel.setBackground(new Color(255,255,255));
		exportiereInExcel.setBorder(buttonBoder);
		exportiereInExcel.setForeground(new Color(47,85,178));
		exportiereInExcel.setFont(new Font("Arial", Font.PLAIN, 20));
		exportiereInExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exportiereInExcel.setText("Daten exportiert");
				Kassenbuch dat = new Kassenbuch(finanzdaten);
				try {
					dat.schreibeExcel();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//exportiereInExcel.setText("Exportiere Daten in Excel");
				
			}
		});
		
		//update nach hinzufuegen leider nicht moeglich
		DiagrammFinanz diagramm = new DiagrammFinanz();
		JPanel panelDiagramm = diagramm.createAndShowGUI(finanzdaten.getJaehrlEinnahmen(), finanzdaten.getJaehrlAusgaben());
		
		System.out.println();
		
		JLabel typ = new JLabel("Typ");
		typ.setForeground(new Color(47,85,178));
		typ.setFont(new Font("Arial", Font.BOLD, 18));
		typ.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(47,85,178)));
		typ.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel betrag = new JLabel("Betrag");
		betrag.setForeground(new Color(47,85,178));
		betrag.setFont(new Font("Arial", Font.BOLD, 18));
		betrag.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(47,85,178)));
		betrag.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel einnahmen = new JLabel("Einnahmen");
		einnahmen.setForeground(new Color(47,85,178));
		einnahmen.setFont(new Font("Arial", Font.PLAIN, 18));
		einnahmen.setBorder(BorderFactory.createMatteBorder(1, 0, 0,0 , new Color(47,85,178)));
		
		einnahmenBetrag.setForeground(new Color(47,85,178));
		einnahmenBetrag.setFont(new Font("Arial", Font.PLAIN, 18));
		einnahmenBetrag.setText(String.format("%.2f",finanzdaten.getJaehrlEinnahmen()));
		einnahmenBetrag.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(47,85,178)));
		einnahmenBetrag.setHorizontalAlignment(JLabel.RIGHT);
		
		JLabel ausgabe = new JLabel("Ausgabe");
		ausgabe.setForeground(new Color(47,85,178));
		ausgabe.setFont(new Font("Arial", Font.PLAIN, 18));
		ausgabe.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(47,85,178)));
		
		ausgabenBetrag.setForeground(new Color(47,85,178));
		ausgabenBetrag.setFont(new Font("Arial", Font.PLAIN, 18));
		ausgabenBetrag.setText(String.format("%.2f",finanzdaten.getJaehrlAusgaben()));
		ausgabenBetrag.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(47,85,178)));
		ausgabenBetrag.setHorizontalAlignment(JLabel.RIGHT);
		
		JLabel differenz = new JLabel("Differenz");
		differenz.setForeground(new Color(47,85,178));
		differenz.setFont(new Font("Arial", Font.PLAIN, 18));
		differenz.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(47,85,178)));
		
		differenzBetrag.setForeground(new Color(47,85,178));
		differenzBetrag.setFont(new Font("Arial", Font.PLAIN, 18));
		differenzBetrag.setText(String.format("%.2f", (finanzdaten.getJaehrlEinnahmen()-finanzdaten.getJaehrlAusgaben())));
		differenzBetrag.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(47,85,178)));
		differenzBetrag.setHorizontalAlignment(JLabel.RIGHT);
		
		JPanel leer = new JPanel();
		leer.setBackground(Color.white);
		leer.setPreferredSize(new Dimension(640,100));
				
		JPanel einAus = new JPanel();
		einAus.setBackground(Color.WHITE);
		
		einAus.setLayout(new GridLayout(4,2));
		einAus.add(typ);
		einAus.add(betrag);
		einAus.add(einnahmen);
		einAus.add(einnahmenBetrag);
		einAus.add(ausgabe);
		einAus.add(ausgabenBetrag);
		einAus.add(differenz);
		einAus.add(differenzBetrag);
		
		
		//JLabel gesamtzahl = new JLabel();
		gesamtzahl.setText("Gesamtzahl der Buchungen: " + finanzdaten.getKontobew().size());
		gesamtzahl.setPreferredSize(new Dimension(300,50));
		gesamtzahl.setBackground(Color.white);
		gesamtzahl.setForeground(new Color(47,85,178));
		gesamtzahl.setFont(new Font("Arial", Font.PLAIN, 20));
		
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("logo1.png"));
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
		
		JScrollPane scroll = new JScrollPane(tabelle);
		scroll.setPreferredSize(new Dimension(600,500));
		scroll.setBorder(BorderFactory.createMatteBorder(0,3,3,3, new Color(47,87,178)));
		scroll.setBackground(new Color(47,87,178));
		
		JPanel linksUnten = new JPanel();
		linksUnten.add(scroll);
		linksUnten.setBackground(new Color(255,255,255));
		
		JPanel linkeSeite = new JPanel();
		linkeSeite.setBackground(new Color(255,255,255));
		linkeSeite.setPreferredSize(new Dimension(640, 720));
		//linkeSeite.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(47,85,178)));
		linkeSeite.setLayout(new BorderLayout());
		
		linkeSeite.add(linksOben, BorderLayout.NORTH);
		linkeSeite.add(linksUnten, BorderLayout.CENTER);
		
		JPanel rechteSeite = new JPanel();
		rechteSeite.setBackground(new Color(255,255,255));
		rechteSeite.setPreferredSize(new Dimension(640, 720));
		
		//rechteSeite.add(stat);
		rechteSeite.add(leer);
		rechteSeite.add(panelDiagramm);
		rechteSeite.add(einAus);
		rechteSeite.add(bewegung);
		rechteSeite.add(exportiereInExcel);
		rechteSeite.add(gesamtzahl);
		
		fenster.add(linkeSeite);
		fenster.add(rechteSeite);
		
		fenster.setIconImage(bild.getImage());
		fenster.setTitle("Finanzverwaltung");
		fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fenster.setSize(1280,720);
		fenster.setResizable(true);
		
		fenster.setVisible(true);
	}
}
