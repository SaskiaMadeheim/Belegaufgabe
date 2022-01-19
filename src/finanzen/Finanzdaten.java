package finanzen;
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
	protected double einnahmen;
	protected double ausgaben;
	protected ArrayList<Finanzbewegung> kontobew;
	protected String dateiname;
	
	// Konstruktoren
	public Finanzdaten()
	{
		haben = 0;
		einnahmen = 0;
		ausgaben = 0;
		kontobew = new ArrayList<Finanzbewegung>();
		dateiname = "";
	}
	
	public Finanzdaten(String dateiname)
	{
		haben = 0;
		einnahmen = 0;
		ausgaben = 0;
		kontobew = new ArrayList<Finanzbewegung>();
		this.dateiname = dateiname;
	}
	
	public Finanzdaten(double anfangswert, ArrayList<Finanzbewegung> kontobew, String dateiname)
	{
		for(Finanzbewegung f: kontobew)					// alle Finanzbewegungen der Liste kontobew durchgehen
		{
			double betrag = f.getBetrag();				
			if (f.getBetrag() >= 0)						// Einnahmen berechnen
			{
				einnahmen = einnahmen + betrag;
			}
			else										// Ausgaben berechnen
			{
				ausgaben = ausgaben + betrag*(-1);
			}
		}
		haben = anfangswert + einnahmen - ausgaben;		// Habensbetrag berechnen
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
		return einnahmen;
	}
	
	public double getJaehrlAusgaben()
	{
		return ausgaben;
	}
	
	public ArrayList<Finanzbewegung> getKontobew()
	{
		return kontobew;
	}
	
	public void setKontobew(ArrayList<Finanzbewegung> kontobew) throws FileNotFoundException
	{
		for(Finanzbewegung f: kontobew)
		{
			newFinanzBewegung(f);
		}
	}
	
	//Kontobewegung hinzufuegen
	public void newFinanzBewegung(Finanzbewegung f) throws FileNotFoundException
	{
		haben = haben + f.getBetrag();					//Habensbetrag aktualisieren
		if (f.getBetrag() >= 0)
			einnahmen = einnahmen + f.getBetrag();		//Einnahmen aktualisieren
		else
			ausgaben = ausgaben + (-1)*f.getBetrag();	//Ausgaben aktualisieren
		kontobew.add(f);
		schreibeCSV();									// CSV aktualisieren
	}
	
	//Kontobewegung entfernen
	public void entferneFinanzBewegung(String datum, String name) throws FileNotFoundException
	{
		boolean finanzBewegungEntf = false;
		int i = 0;
		for (Finanzbewegung f: kontobew)
		{
			if(datum.equals(f.getDatumSt()) && name.equals(f.getName()))	// Finanzbewegung finden
			{
				haben = haben + f.getBetrag()*(-1);							// Habensbetrag aktualisieren
				if (f.getBetrag() >= 0)
				{
					einnahmen = einnahmen - f.getBetrag();					// Einnahmen aktualisieren
				}
				else
					ausgaben = ausgaben + f.getBetrag()*(-1);				// Ausgaben aktualisieren
				kontobew.remove(0);											// Finanzbewegung aus Liste entfernen
			}
			i++;
		}
		if (finanzBewegungEntf)
			System.out.printf("Die Kontobewegung mit Datum %s und Name %s wurde erfolgreich entfernt.\n", datum, name);									// Meldung über erfolgreiches Entfernen
		else 
			System.out.printf("Keine Uebereinstimmung mit Kontobewegung mit Datum %s und Name %s gefunden; bitte Angaben ueberpruefen", datum, name);	//Fehlermeldung bei falscher Eingabed
		schreibeCSV();		// CSV aktualisieren
	}
	
	// CSV einlesen
	public ArrayList<Finanzbewegung> leseCSV(String datname) throws FileNotFoundException
	{
		File datei = new File(datname);
		Scanner ein = new Scanner(datei);
		ArrayList<Finanzbewegung> liste = new ArrayList<Finanzbewegung>();
		// while-Schleife zum Lesen der Datei
		while (ein.hasNext())
		{
			// Lesen der Daten und Speichern in Array
			String zeile = ein.nextLine();
			String s[] = zeile.split(";");
			String datum = "0000-00-00";
			String bezeichnung = "";
			String abteilung = "";
			double betrag = 0;
			// Daten in String den Variablen zuordnen
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
			// neue Finanzbewegung aus Variablen erstellen und Liste hinzufuegen
			Finanzbewegung f = new Finanzbewegung(bezeichnung, datum, betrag, abteilung);
			liste.add(f);
		}
		ein.close();
		return liste;
	}
	
	// CSV schreiben aus Liste der Kontobewegungen
	public void schreibeCSV() throws FileNotFoundException
	{
		PrintWriter aus = new PrintWriter(dateiname);
		// for-Schleife zum Schreiben der Datei
		for(Finanzbewegung f: kontobew)
		{
			aus.printf(f.getDatumSt() + ";");
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
	
}
