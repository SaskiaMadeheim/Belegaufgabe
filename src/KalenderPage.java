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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

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

@SuppressWarnings("serial")
public class KalenderPage extends JFrame{
	public KalenderPage() throws IOException {
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		JFrame fenster = new JFrame();
		fenster.setLayout(new GridLayout());
		
		ImageIcon bild = new ImageIcon("logo.png");
		Border buttonBoder = BorderFactory.createLineBorder(new Color(47,85,178), 3);
		
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
		
		JPanel linkeSeite = new JPanel();
		linkeSeite.setBackground(new Color(255,255,255));
		linkeSeite.setPreferredSize(new Dimension(640, 720));
		//linkeSeite.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(47,85,178)));
		linkeSeite.setLayout(new BorderLayout());
		
		linkeSeite.add(linksOben, BorderLayout.NORTH);
		
		Kalender tableKalender = new Kalender();
		tableKalender.leseKalender();
		ArrayList<Event> events = new ArrayList<Event>();
		
		Jahr jahr = tableKalender.getKalender().get(Calendar.getInstance().get(Calendar.YEAR)-2021);
		
		for (Monat monat : jahr.getJahr())
		{
			for (Tag tag : monat.getMonat())
			{
				for (Event event : tag.getEvents())
				{
					if (event.isVeranstaltung() == true)
						events.add(event);
				}
			}
		}
		
		final KalenderTabelleModel model = new KalenderTabelleModel();
		for(Event e : events)
		{
			model.hinzufKalender(e);
		}
		
		
		JTable tabelle = new JTable();
		tabelle.setModel(model);
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
		
		tabelle.getColumnModel().getColumn(0).setPreferredWidth(20);
		tabelle.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabelle.getColumnModel().getColumn(2).setPreferredWidth(140);
		tabelle.getColumnModel().getColumn(3).setPreferredWidth(20);
		tabelle.getColumnModel().getColumn(4).setPreferredWidth(20);
		tabelle.getColumnModel().getColumn(5).setPreferredWidth(50);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tabelle.getColumn("Datum").setCellRenderer(rightRenderer);
		tabelle.getColumn("Start").setCellRenderer(rightRenderer);
		tabelle.getColumn("Ende").setCellRenderer(rightRenderer);
		
		tabelle.getTableHeader().setBackground(new Color(47,85,178));
		tabelle.getTableHeader().setForeground(new Color(255,255,255));
		tabelle.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		tabelle.getTableHeader().setOpaque(false);
		
		JScrollPane scroll = new JScrollPane(tabelle);
		scroll.setPreferredSize(new Dimension(630,520));
		scroll.setBackground(new Color(47,87,178));
		
		JPanel barOben = new JPanel();
		barOben.setPreferredSize(new Dimension(630,20));
		barOben.setBackground(Color.white);
		
		JPanel barRechts = new JPanel();
		barRechts.setPreferredSize(new Dimension(10,520));
		barRechts.setBackground(Color.white);
		
		JPanel rechtsOben = new JPanel();
		rechtsOben.setLayout(new BorderLayout());
		rechtsOben.setBackground(Color.white);
		rechtsOben.add(barRechts, BorderLayout.EAST);
		rechtsOben.add(scroll, BorderLayout.CENTER);
		rechtsOben.add(barOben, BorderLayout.NORTH);
		
		JButton addEvent = new JButton("neues Event");
		addEvent.setPreferredSize(new Dimension(310,50));
		addEvent.setFocusable(false);
		addEvent.setBackground(new Color(255,255,255));
		addEvent.setBorder(buttonBoder);
		addEvent.setForeground(new Color(47,85,178));
		addEvent.setFont(new Font("Arial", Font.PLAIN, 20));
		addEvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == addEvent)
				{
					JFrame addFenster = new JFrame();
					addFenster.setLayout(new BorderLayout());
						
					JLabel event = new JLabel("Event: ");
					event.setForeground(new Color(47,85,178));
					event.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField eventIn = new JTextField();
					eventIn.setForeground(new Color(47,85,178));
					eventIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
					JLabel raumBez = new JLabel("Raumbezeichnung:");
					raumBez.setForeground(new Color(47,85,178));
					raumBez.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField raumBezIn = new JTextField();
					raumBezIn.setForeground(new Color(47,85,178));
					raumBezIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
					JLabel datum = new JLabel("Datum:");
					datum.setForeground(new Color(47,85,178));
					datum.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField datumIn = new JTextField();
					datumIn.setForeground(new Color(47,85,178));
					datumIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
					JLabel anfang = new JLabel("Anfang:");
					anfang.setForeground(new Color(47,85,178));
					anfang.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField anfangIn = new JTextField();
					anfangIn.setForeground(new Color(47,85,178));
					anfangIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
					JLabel ende = new JLabel("Ende");
					ende.setForeground(new Color(47,85,178));
					ende.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField endeIn = new JTextField();
					endeIn.setForeground(new Color(47,85,178));
					endeIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
						
					JPanel addFensterOben = new JPanel();
					addFensterOben.setLayout(new GridLayout(5, 2));
					addFensterOben.setBackground(Color.white);
					Border borderOben = BorderFactory.createEmptyBorder(10, 10, 10, 10);
					addFensterOben.setBorder(borderOben);
						
					addFensterOben.add(event);
					addFensterOben.add(eventIn);
					addFensterOben.add(raumBez);
					addFensterOben.add(raumBezIn);
					addFensterOben.add(datum);
					addFensterOben.add(datumIn);
					addFensterOben.add(anfang);
					addFensterOben.add(anfangIn);
					addFensterOben.add(ende);
					addFensterOben.add(endeIn);
						
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
							try {
								Kalender.addEvent(
										eventIn.getText(),
										raumBezIn.getText(),
										datumIn.getText(),
										anfangIn.getText(),
										endeIn.getText());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							addFenster.dispose();						

							try {
								KalenderPage kp = new KalenderPage();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							fenster.dispose();
							
						}
					});

					
					JPanel addFensterUnten = new JPanel();
					addFensterUnten.setBackground(Color.white);
					addFensterUnten.add(hinzufuegen);
					
					
					addFenster.add(addFensterOben, BorderLayout.NORTH);
					addFenster.add(addFensterUnten, BorderLayout.SOUTH);
					
					addFenster.setIconImage(bild.getImage());
					addFenster.setTitle("neues Event");
					addFenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
					addFenster.setResizable(false);
					addFenster.pack();
					addFenster.setVisible(true);
				}
			}
		});
		
		JButton addEventWoechentlich = new JButton("neues w\u00f6chentliches Event");
		addEventWoechentlich.setPreferredSize(new Dimension(310,50));
		addEventWoechentlich.setFocusable(false);
		addEventWoechentlich.setBackground(new Color(255,255,255));
		addEventWoechentlich.setBorder(buttonBoder);
		addEventWoechentlich.setForeground(new Color(47,85,178));
		addEventWoechentlich.setFont(new Font("Arial", Font.PLAIN, 20));
		addEventWoechentlich.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == addEventWoechentlich)
				{
					JFrame addFenster = new JFrame();
					addFenster.setLayout(new BorderLayout());
						
					JLabel event = new JLabel("Event: ");
					event.setForeground(new Color(47,85,178));
					event.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField eventIn = new JTextField();
					eventIn.setForeground(new Color(47,85,178));
					eventIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
					JLabel raumBez = new JLabel("Raumbezeichnung:");
					raumBez.setForeground(new Color(47,85,178));
					raumBez.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField raumBezIn = new JTextField();
					raumBezIn.setForeground(new Color(47,85,178));
					raumBezIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
					JLabel wochentag = new JLabel("Wochentag:");
					wochentag.setForeground(new Color(47,85,178));
					wochentag.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField wochentagIn = new JTextField();
					wochentagIn.setForeground(new Color(47,85,178));
					wochentagIn.setFont(new Font("Arial", Font.PLAIN, 16));
					
					JLabel jahreszahl = new JLabel("Jahreszahl:");
					jahreszahl.setForeground(new Color(47,85,178));
					jahreszahl.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField jahreszahlIn = new JTextField();
					jahreszahlIn.setForeground(new Color(47,85,178));
					jahreszahlIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
					JLabel anfang = new JLabel("Anfang:");
					anfang.setForeground(new Color(47,85,178));
					anfang.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField anfangIn = new JTextField();
					anfangIn.setForeground(new Color(47,85,178));
					anfangIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
					JLabel ende = new JLabel("Ende");
					ende.setForeground(new Color(47,85,178));
					ende.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField endeIn = new JTextField();
					endeIn.setForeground(new Color(47,85,178));
					endeIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
						
					JPanel addFensterOben = new JPanel();
					addFensterOben.setLayout(new GridLayout(6, 2));
					addFensterOben.setBackground(Color.white);
					Border borderOben = BorderFactory.createEmptyBorder(10, 10, 10, 10);
					addFensterOben.setBorder(borderOben);
						
					addFensterOben.add(event);
					addFensterOben.add(eventIn);
					addFensterOben.add(raumBez);
					addFensterOben.add(raumBezIn);
					addFensterOben.add(wochentag);
					addFensterOben.add(wochentagIn);
					addFensterOben.add(jahreszahl);
					addFensterOben.add(jahreszahlIn);
					addFensterOben.add(anfang);
					addFensterOben.add(anfangIn);
					addFensterOben.add(ende);
					addFensterOben.add(endeIn);
						
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
							try {
								Kalender.addEventWoechentlich(
										wochentagIn.getText(),
										Integer.parseInt(jahreszahlIn.getText()),
										eventIn.getText(),
										raumBezIn.getText(),
										anfangIn.getText(),
										endeIn.getText());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							addFenster.dispose();
						
							try {
								KalenderPage kp = new KalenderPage();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							fenster.dispose();
						}
					});

					
					JPanel addFensterUnten = new JPanel();
					addFensterUnten.setBackground(Color.white);
					addFensterUnten.add(hinzufuegen);
					
					
					addFenster.add(addFensterOben, BorderLayout.NORTH);
					addFenster.add(addFensterUnten, BorderLayout.SOUTH);
					
					addFenster.setIconImage(bild.getImage());
					addFenster.setTitle("neues w\u00f6chentliches Event");
					addFenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
					addFenster.setResizable(false);
					addFenster.pack();
					addFenster.setVisible(true);
				}
			}
		});
		
		JButton entferneEvent = new JButton("entferne Event");
		entferneEvent.setPreferredSize(new Dimension(310,50));
		entferneEvent.setFocusable(false);
		entferneEvent.setBackground(new Color(255,255,255));
		entferneEvent.setBorder(buttonBoder);
		entferneEvent.setForeground(new Color(47,85,178));
		entferneEvent.setFont(new Font("Arial", Font.PLAIN, 20));
		entferneEvent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == entferneEvent)
				{
					JFrame addFenster = new JFrame();
					addFenster.setLayout(new BorderLayout());
						
					JLabel event = new JLabel("Event: ");
					event.setForeground(new Color(47,85,178));
					event.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField eventIn = new JTextField();
					eventIn.setForeground(new Color(47,85,178));
					eventIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
					JLabel datum = new JLabel("Datum:");
					datum.setForeground(new Color(47,85,178));
					datum.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField datumIn = new JTextField();
					datumIn.setForeground(new Color(47,85,178));
					datumIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
						
						
					JPanel addFensterOben = new JPanel();
					addFensterOben.setLayout(new GridLayout(2, 2));
					addFensterOben.setBackground(Color.white);
					Border borderOben = BorderFactory.createEmptyBorder(10, 10, 10, 10);
					addFensterOben.setBorder(borderOben);
						
					addFensterOben.add(event);
					addFensterOben.add(eventIn);
					addFensterOben.add(datum);
					addFensterOben.add(datumIn);
					
						
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
							try {
								Kalender.entferneEvent(
										eventIn.getText(),
										datumIn.getText());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							addFenster.dispose();
							
							try {
								KalenderPage kp = new KalenderPage();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							fenster.dispose();
						}
					});

					
					JPanel addFensterUnten = new JPanel();
					addFensterUnten.setBackground(Color.white);
					addFensterUnten.add(hinzufuegen);
					
					
					addFenster.add(addFensterOben, BorderLayout.NORTH);
					addFenster.add(addFensterUnten, BorderLayout.SOUTH);
					
					addFenster.setIconImage(bild.getImage());
					addFenster.setTitle("entferne Event");
					addFenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
					addFenster.setResizable(false);
					addFenster.pack();
					addFenster.setVisible(true);
				}
			}
		});
		
		JButton entferneEventRegelmaessig = new JButton("entferne regelm\u00e4\u00dfiges Event");
		entferneEventRegelmaessig.setPreferredSize(new Dimension(310,50));
		entferneEventRegelmaessig.setFocusable(false);
		entferneEventRegelmaessig.setBackground(new Color(255,255,255));
		entferneEventRegelmaessig.setBorder(buttonBoder);
		entferneEventRegelmaessig.setForeground(new Color(47,85,178));
		entferneEventRegelmaessig.setFont(new Font("Arial", Font.PLAIN, 20));
		entferneEventRegelmaessig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == entferneEventRegelmaessig)
				{
					JFrame addFenster = new JFrame();
					addFenster.setLayout(new BorderLayout());
						
					JLabel event = new JLabel("Event: ");
					event.setForeground(new Color(47,85,178));
					event.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField eventIn = new JTextField();
					eventIn.setForeground(new Color(47,85,178));
					eventIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
					JLabel jahreszahl = new JLabel("Jahreszahl:");
					jahreszahl.setForeground(new Color(47,85,178));
					jahreszahl.setFont(new Font("Arial", Font.PLAIN, 18));
						
					JTextField jahreszahlIn = new JTextField();
					jahreszahlIn.setForeground(new Color(47,85,178));
					jahreszahlIn.setFont(new Font("Arial", Font.PLAIN, 16));
						
						
						
					JPanel addFensterOben = new JPanel();
					addFensterOben.setLayout(new GridLayout(2, 2));
					addFensterOben.setBackground(Color.white);
					Border borderOben = BorderFactory.createEmptyBorder(10, 10, 10, 10);
					addFensterOben.setBorder(borderOben);
						
					addFensterOben.add(event);
					addFensterOben.add(eventIn);
					addFensterOben.add(jahreszahl);
					addFensterOben.add(jahreszahlIn);
					
						
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
							try {
								Kalender.entferneEventRegelmaessig(
										eventIn.getText(),
										Integer.parseInt(jahreszahlIn.getText()));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							addFenster.dispose();
							
							try {
								KalenderPage kp = new KalenderPage();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							fenster.dispose();
						}
					});

					
					JPanel addFensterUnten = new JPanel();
					addFensterUnten.setBackground(Color.white);
					addFensterUnten.add(hinzufuegen);
					
					
					addFenster.add(addFensterOben, BorderLayout.NORTH);
					addFenster.add(addFensterUnten, BorderLayout.SOUTH);
					
					addFenster.setIconImage(bild.getImage());
					addFenster.setTitle("entferne regelm\u00e4\u00dfiges Event");
					addFenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
					addFenster.setResizable(false);
					addFenster.pack();
					addFenster.setVisible(true);
				}
			}
		});
		
		
		JPanel rechtsUnten = new JPanel();
		rechtsUnten.setBackground(Color.white);
		//rechtsUnten.setLayout(new GridLayout(2,2));
		rechtsUnten.add(addEvent);
		rechtsUnten.add(entferneEvent);
		rechtsUnten.add(addEventWoechentlich);
		rechtsUnten.add(entferneEventRegelmaessig);
		
		JPanel rechteSeite = new JPanel();
		rechteSeite.setBackground(Color.white);
		rechteSeite.setPreferredSize(new Dimension(640, 720));
		rechteSeite.setLayout(new BorderLayout());

		rechteSeite.add(rechtsOben, BorderLayout.NORTH);
		rechteSeite.add(rechtsUnten, BorderLayout.CENTER);
		
		
		fenster.setIconImage(bild.getImage());
		fenster.setTitle("Kalender");
		fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fenster.setSize(1280,720);
		fenster.setResizable(true);
		
		fenster.add(linkeSeite);
		fenster.add(rechteSeite);
		
		fenster.setVisible(true);
	}
}
