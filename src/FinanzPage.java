import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class FinanzPage extends JFrame{
	
	private ArrayList<Finanzbewegung> kontobw = new ArrayList<Finanzbewegung>();
	
	public FinanzPage()
	{
		ImageIcon bild = new ImageIcon("logo.png");
		Border buttonBoder = BorderFactory.createLineBorder(new Color(47,85,178), 3);
		
		JFrame fenster = new JFrame();
		fenster.setLayout(new GridLayout());
		
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
					
					JLabel name = new JLabel("Buchungstext:");
					name.setForeground(new Color(47,85,178));
					name.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JTextField nameIn = new JTextField();
					nameIn.setForeground(new Color(47,85,178));
					nameIn.setFont(new Font("Arial", Font.PLAIN, 16));
					
					JLabel datum = new JLabel("Buchungsdatum: ");
					datum.setForeground(new Color(47,85,178));
					datum.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JTextField datumIn = new JTextField();
					datumIn.setForeground(new Color(47,85,178));
					datumIn.setFont(new Font("Arial", Font.PLAIN, 16));
					
					JLabel betrag = new JLabel("Betrag:");
					betrag.setForeground(new Color(47,85,178));
					betrag.setFont(new Font("Arial", Font.PLAIN, 18));
					
					JTextField betragIn = new JTextField();
					betragIn.setForeground(new Color(47,85,178));
					betragIn.setFont(new Font("Arial", Font.PLAIN, 16));
					
					JPanel addFensterOben = new JPanel();
					addFensterOben.setLayout(new GridLayout(3, 2));
					addFensterOben.setBackground(Color.white);
					Border borderOben = BorderFactory.createEmptyBorder(10, 10, 10, 10);
					addFensterOben.setBorder(borderOben);
					
					addFensterOben.add(name);
					addFensterOben.add(nameIn);
					addFensterOben.add(datum);
					addFensterOben.add(datumIn);
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
								Finanzbewegung bewegung = new Finanzbewegung();
								bewegung.setName(nameIn.getText());
								bewegung.setDatum(Integer.parseInt(datumIn.getText()));
								bewegung.setBetrag(Double.parseDouble(betragIn.getText()));
								
								//System.out.println(bewegung.getName() + bewegung.getDatum() + bewegung.getBetrag());
								kontobw.add(bewegung);
								
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
					addFenster.setTitle("neue Bewegung");
					addFenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
					addFenster.setResizable(false);
					addFenster.pack();
					addFenster.setVisible(true);
				}
			}
		});
		
		String[][] daten = new String[3][10];
		for(int i=0; i<10; i++)
		{
			for(int j=kontobw.size(); j>(kontobw.size()-10); j--)
			{
				Finanzbewegung fd = kontobw.get(j);
				daten[1][i] = fd.getName();
				Integer dat = fd.getDatum();
				daten[2][i] = dat.toString();
				Double bet = fd.getBetrag();
				daten[3][i] = bet.toString();
			}
			
			
		}
		//String daten[][]={ {"101","Amit","670000"}, {"102","Jai","780000"}, {"101","Sachin","700000"}};    
		String ueberschrift[]={"Buchungsdatum","Buchungstext","Betrag"};         
		JTable jt=new JTable(daten,ueberschrift); 
		jt.setFocusable(false);
		jt.setCellSelectionEnabled(false);
		jt.setEnabled(false);
		jt.setBounds(30,40,200,300);   
		jt.setFont(new Font("Arial", Font.PLAIN, 18));
		jt.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 20));
		
		JPanel linkeSeite = new JPanel();
		linkeSeite.setBackground(new Color(255,255,255));
		linkeSeite.setPreferredSize(new Dimension(640, 720));
		//linkeSeite.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(47,85,178)));
		linkeSeite.setLayout(new BorderLayout(5, 5));
		linkeSeite.add(jt.getTableHeader(), BorderLayout.PAGE_START);
		linkeSeite.add(jt, BorderLayout.CENTER);
		
		JPanel rechteSeite = new JPanel();
		rechteSeite.setBackground(new Color(255,255,255));
		rechteSeite.setPreferredSize(new Dimension(640, 720));
		rechteSeite.add(bewegung);
		
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
