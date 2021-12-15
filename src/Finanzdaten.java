import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Finanzdaten
{
	// Instanzvariablen
	protected double haben;
	protected double jaehrlEinnahmen;
	protected double jaehrlAusgaben;
	protected ArrayList<Finanzbewegung> kontobew;
	protected String dateiname;
	
	// Konstruktoren
	public Finanzdaten()
	{
		haben = 0;
		jaehrlEinnahmen = 0;
		jaehrlAusgaben = 0;
		kontobew = new ArrayList<Finanzbewegung>();
		dateiname = "";
	}
	
	public Finanzdaten(double haben, double jaehrlEinnahmen, double jaehrlAusgaben, String dateiname)
	{
		this.haben = haben;
		this.jaehrlEinnahmen = jaehrlEinnahmen;
		this.jaehrlAusgaben = jaehrlAusgaben;
		kontobew = new ArrayList<Finanzbewegung>();
		this.dateiname = dateiname;
	}
	
	public Finanzdaten(double anfangswert, ArrayList<Finanzbewegung> kontobew, String dateiname)
	{
		for(Finanzbewegung f: kontobew)
		{
			double betrag = f.getBetrag();
			if (f.getPositiv())
			{
				jaehrlEinnahmen = jaehrlEinnahmen + betrag;
			}
			else
			{
				jaehrlAusgaben = jaehrlAusgaben + betrag*(-1);
			}
		}
		haben = anfangswert + jaehrlEinnahmen - jaehrlAusgaben;
		this.kontobew = kontobew;
		this.dateiname = dateiname;
	}
	
	// getter/setter
	public double getHaben() 
	{
		return haben;
	}
	
	public double getJaehrlEinnahmen()
	{
		return jaehrlEinnahmen;
	}
	
	public double getJaehrlAusgaben()
	{
		return jaehrlAusgaben;
	}
	
	public ArrayList<Finanzbewegung> getKontobew()
	{
		return kontobew;
	}
	
	public void setKontobew(ArrayList<Finanzbewegung> kontobew)
	{
		this.kontobew = kontobew;
	}
	
	//Kontobewegung hinzufuegen
	public void newFinanzBewegung(Finanzbewegung f) throws FileNotFoundException
	{
		haben = haben + f.getBetrag();
		if (f.getPositiv())
			jaehrlEinnahmen = jaehrlEinnahmen + f.getBetrag();
		else
			jaehrlAusgaben = jaehrlAusgaben + (-1)*f.getBetrag();
		kontobew.add(f);
		schreibeCSV();
	}
	
	// CSV einlesen
	public ArrayList<Finanzbewegung> leseCSV() throws FileNotFoundException
	{
		File datei = new File(dateiname);
		Scanner ein = new Scanner(datei);
		ArrayList<Finanzbewegung> liste = new ArrayList<Finanzbewegung>();
		// while-Schleife zum Lesen der Datei
		while (ein.hasNext())
		{
			String zeile = ein.nextLine();
			String s[] = zeile.split(";");
			String datum = "";
			String bezeichnung = "";
			String abteilung = "";
			double betrag = 0;
			if (!s[0].equals(" "))
			{
				datum = s[0];
			}
			if (!s[1].equals(" "))
			{
				bezeichnung = s[1];
			}
			if (!s[2].equals(" "))
			{
				abteilung = s[2];
			}
			if (!s[3].equals(" "))
			{
				betrag = Double.parseDouble(s[3].replace(",", "."));
			}
			Finanzbewegung f = new Finanzbewegung(bezeichnung, datum, betrag, abteilung);
			liste.add(f);
		}
		ein.close();
		return liste;
	}
	
	// CSV schreiben
	public void schreibeCSV() throws FileNotFoundException
	{
		PrintWriter aus = new PrintWriter(dateiname);
		// for-Schleife zum Schreiben der Datei
		for(Finanzbewegung f: kontobew)
		{
			aus.printf(f.getDatum() + ";");
			aus.printf(f.getName() + ";");
			aus.printf(f.getAbteilung() + ";");
			aus.printf("%1.2f	", f.getBetrag());
			aus.printf("\n");			
		}
		aus.close();		
	}
	
	// Infos ausgeben
	public void gebeInfos(ArrayList<Finanzbewegung> liste)
	{
		for(Finanzbewegung f: liste)
		{
			f.gebeInfos();
		}
	}
	
	// Testen
	public static void main(String args[]) throws IOException
	{
		/*Finanzdaten f = new Finanzdaten();
		ArrayList<Finanzbewegung> test = f.leseCSV("TestFinanzbewegungen.csv");
		f.gebeInfos(test);
		f.setKontobew(test);
		f.schreibeCSV("TestFinanzbewegungen2.csv");*/
	}
	
}
