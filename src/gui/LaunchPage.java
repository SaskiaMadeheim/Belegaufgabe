package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.border.Border;

import finanzen.*;
import kalender.*;
import mitglieder.*;

public class LaunchPage extends JFrame{
	
	//Instanzvariablen
	private JFrame fenster;
	private JButton mitgliederverwaltung;
	private JButton kalender;
	private JButton finanzen;
	
	public LaunchPage() throws FileNotFoundException
	{
		ImageIcon bild = new ImageIcon("logo.png");
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("logo.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(190, 190, Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(dimg);
		
		Border buttonBoder = BorderFactory.createLineBorder(new Color(255,255,255), 3);
		
		//Buttons
		mitgliederverwaltung = new JButton("Mitgliederverwaltung");
		mitgliederverwaltung.setFocusable(false);
		mitgliederverwaltung.setBounds(50, 40, 220, 100);
		mitgliederverwaltung.setBackground(new Color(47,85,178));
		mitgliederverwaltung.setBorder(buttonBoder);
		mitgliederverwaltung.setForeground(new Color(255,255,255));
		mitgliederverwaltung.setFont(new Font("Arial", Font.PLAIN, 20));
		mitgliederverwaltung.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mitgliederverwaltung)
				{
					fenster.dispose();
					try {
						MitgliederPage mp = new MitgliederPage();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
				}
				
			}
		});;
		kalender = new JButton("Kalender");
		kalender.setFocusable(false);
		kalender.setBounds(50, 160, 220, 100);
		kalender.setBackground(new Color(47,85,178));
		kalender.setBorder(buttonBoder);
		kalender.setForeground(new Color(255,255,255));
		kalender.setFont(new Font("Arial", Font.PLAIN, 20));
		kalender.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == kalender)
				{
					fenster.dispose();
					try {
						KalenderPage kp = new KalenderPage();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		finanzen = new JButton("Finanzen");
		finanzen.setFocusable(false);
		finanzen.setBounds(50, 280, 220, 100);
		finanzen.setBackground(new Color(47,85,178));
		finanzen.setBorder(buttonBoder);
		finanzen.setForeground(new Color(255,255,255));
		finanzen.setFont(new Font("Arial", Font.PLAIN, 20));
		finanzen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == finanzen)
				{
					fenster.dispose();
					try {
						FinanzPage fs = new FinanzPage();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		//oberes Label
		JLabel oLabel = new JLabel();
		oLabel.setText("MeinVerein");
		oLabel.setIcon(logo);	
		oLabel.setForeground(new Color(47,85,178));
		oLabel.setFont(new Font("Arial", Font.BOLD, 85));
		oLabel.setHorizontalTextPosition(JLabel.RIGHT);
		oLabel.setVerticalTextPosition(JLabel.CENTER);
		oLabel.setVerticalAlignment(JLabel.CENTER);
		oLabel.setHorizontalAlignment(JLabel.CENTER);
		oLabel.setIconTextGap(20);
		
		//rechtes Label1
		JLabel rLabel1 = new JLabel();
		rLabel1.setText("\u00dcberblick");
		rLabel1.setForeground(new Color(255, 255, 255));
		rLabel1.setFont(new Font("Arial", Font.BOLD, 25));
		rLabel1.setBounds(60, 10, 200, 100);
		rLabel1.setHorizontalAlignment(JLabel.CENTER);
		rLabel1.setVerticalAlignment(JLabel.CENTER);
		
		//rechtes Label2
		ArrayList<Mitglied> mitgliederliste = Mitgliederverwaltung.leseCSV("Mitgliederliste.txt");
		JLabel rLabel2 = new JLabel();
		rLabel2.setText("Mitgliederzahl: " + mitgliederliste.size());
		rLabel2.setForeground(new Color(255, 255, 255));
		rLabel2.setFont(new Font("Arial", Font.PLAIN, 20));
		rLabel2.setBounds(60, 80, 200, 100);
		rLabel2.setHorizontalAlignment(JLabel.CENTER);
		rLabel2.setVerticalAlignment(JLabel.CENTER);
		
		//rechtes Label3
		Finanzdaten fd = new Finanzdaten("dat.csv");
		ArrayList<Finanzbewegung> einlesen= fd.leseCSV("dat.csv");
		fd.setKontobew(einlesen);
		
		JLabel rLabel3 = new JLabel();
		rLabel3.setText("Finanzen: " + String.format("%.2f", fd.getHaben()) +"\u20ac");
		rLabel3.setForeground(new Color(255, 255, 255));
		rLabel3.setFont(new Font("Arial", Font.PLAIN, 20));
		rLabel3.setBounds(60, 150, 200, 100);
		rLabel3.setHorizontalAlignment(JLabel.CENTER);
		rLabel3.setVerticalAlignment(JLabel.CENTER);
				
		//rechtes Label4
		/*JLabel rLabel4 = new JLabel();
		rLabel4.setText("Kalenderausschnitt");
		rLabel4.setForeground(new Color(255, 255, 255));
		rLabel4.setFont(new Font("Arial", Font.PLAIN, 20));
		rLabel4.setBounds(60, 220, 200, 100);
		rLabel4.setHorizontalAlignment(JLabel.CENTER);
		rLabel4.setVerticalAlignment(JLabel.CENTER);*/
				
		//mittleresLabel
		JLabel mLabel = new JLabel();
		//ImageIcon teamBild = new ImageIcon("Trikots.jpeg");
		BufferedImage mimg = null;
		try {
			mimg = ImageIO.read(new File("Trikots.jpeg"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Image mdimg = mimg.getScaledInstance(640, 655, Image.SCALE_SMOOTH);
		ImageIcon teamBild = new ImageIcon(mdimg);
		mLabel.setIcon(teamBild);
		mLabel.setBounds(3, 3, 634, 514);
		
		//oberes Panel
		JPanel oberesPanel = new JPanel();
		oberesPanel.setPreferredSize(new Dimension(1280, 200));
		oberesPanel.add(oLabel);
		oberesPanel.setBackground(new Color(255, 255, 255));
		oberesPanel.setVisible(true);
		
		//linkes Panel
		JPanel linkesPanel = new JPanel();
		linkesPanel.setLayout(null);
		linkesPanel.setBackground(new Color(47, 85, 178));
		linkesPanel.setPreferredSize(new Dimension(320, 520));
		
		linkesPanel.add(mitgliederverwaltung);
		linkesPanel.add(kalender);
		linkesPanel.add(finanzen);
		linkesPanel.setVisible(true);
		
		//mittleres Panel
		JPanel mittleresPanel = new JPanel();
		Border mBorder = BorderFactory.createLineBorder(new Color(47,85,178), 3);
		mittleresPanel.setPreferredSize(new Dimension(640, 520));
		mittleresPanel.setBackground(new Color(255,255,255));
		mittleresPanel.setVisible(true);
		mittleresPanel.add(mLabel);
		mittleresPanel.setBorder(mBorder);
		mittleresPanel.setLayout(null);
		
		//rechtes Panel
		JPanel rechtesPanel = new JPanel();
		rechtesPanel.setPreferredSize(new Dimension(320, 520));
		rechtesPanel.setBackground(new Color(47,85,178));
		rechtesPanel.setVisible(true);
		rechtesPanel.setLayout(null);
		rechtesPanel.add(rLabel1);
		rechtesPanel.add(rLabel2);
		rechtesPanel.add(rLabel3);
		//rechtesPanel.add(rLabel4);
		
		//Fenster
		fenster = new JFrame();
		fenster.setLayout(new BorderLayout());
		
		fenster.add(oberesPanel, BorderLayout.NORTH);
		fenster.add(linkesPanel, BorderLayout.WEST);
		fenster.add(mittleresPanel, BorderLayout.CENTER);
		fenster.add(rechtesPanel, BorderLayout.EAST);
		
		fenster.setIconImage(bild.getImage());
		fenster.setTitle("Vereinsverwaltung");
		fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fenster.setSize(1280,720);
		fenster.setResizable(false);
		
		fenster.setVisible(true);
	}

}
