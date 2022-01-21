package kalender;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Kalender 
{
	private ArrayList<Jahr> kalender;
	
	private static int jahrZaehler;
	private static int monatZaehler;
	
	public Kalender()
	{
		ArrayList<Jahr> kalender = new ArrayList<Jahr>();
		for (int i =2021; i<2100; i++)
		{
			Jahr jahr = new Jahr(i);
			kalender.add(jahr);
		}
		this.kalender = kalender;
		
		LocalDate currentDate = LocalDate.now();
		int monat = currentDate.getMonthValue();
		int jahr = currentDate.getYear();
		monatZaehler = monat;
		jahrZaehler = jahr;
	}
	
	public ArrayList<Jahr> getKalender() {
		return kalender;
	}

	public void setKalender(ArrayList<Jahr> kalender) {
		this.kalender = kalender;
	}
	
	//------------------------Direkt fuer GUI relevante Methoden----------------------------
	
	public static void addEvent(String event, String raumBez, String datum, String anfang, String ende) throws IOException
	{
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		//Umwandlung der String Eingabe in int Werte 
		String[] datumStr = datum.split("\\.");
		int tagNr = Integer.parseInt(datumStr[0]); 
		int monatNr = Integer.parseInt(datumStr[1]);
		LocalDate currentDate = LocalDate.now();
		int jahresZahl = currentDate.getYear();
		
		if (datumStr.length == 3)
		{
			jahresZahl = Integer.parseInt(datumStr[2]);
		}
		
		//--Hinzufuegen des Events zum angegeben Tag; dazu durchlaufen der ArrayListen bis zum angegebenen Tag
		Jahr jahr = kalender.getKalender().get(jahresZahl-2021);
		Monat monat = jahr.getJahr().get(monatNr-1);
		Tag tag = monat.getMonat().get(tagNr-1);
		
		Raum raum = new Raum(raumBez);
		String wochenTag = tag.getWochenTag();
		boolean veranstaltung = true;
		Event thisEvent = new Event(event, raum, veranstaltung, tagNr, monatNr, jahresZahl, wochenTag, anfang, ende);
		tag.getEvents().add(thisEvent);
		
		tag.sortEvents();
		System.out.printf("%s am %s von %s bis %s in Kalender eingetragen \n", event, datum, anfang, ende);
		kalender.schreibeKalender();
	}
	
	public static void addEventWoechentlich(String wochenTag, int jahresZahl, String event, String raumBez, String anfang, String ende) throws IOException
	{
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		Raum raum = new Raum(raumBez);
		
		Jahr jahr = kalender.getKalender().get(jahresZahl-2021);		
		for (Monat monat : jahr.getJahr())
		{
			for (Tag tag : monat.getMonat())
			{
				if (tag.getWochenTag().matches(wochenTag))
				{
					boolean veranstaltung = false;
					int tagNr = tag.getTagNr();
					int monatNr = tag.getMonatNr();
					
					Event thisEvent = new Event(event, raum, veranstaltung, tagNr, monatNr, jahresZahl, wochenTag, anfang, ende);
					tag.getEvents().add(thisEvent);
				}
			}
		}
		kalender.schreibeKalender();
	}
	
	//Entfernen des angegebnene Events bei Uebereinstimmung von "Event" und Datum
	public static void entferneEvent(String eventStr, String datum) throws IOException
	{
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		//Umwandlung der String Eingabe in int Werte 
		String[] datumStr = datum.split("\\.");
		int tagNr = Integer.parseInt(datumStr[0]); 
		int monatNr = Integer.parseInt(datumStr[1]);
		LocalDate currentDate = LocalDate.now();
		int jahresZahl = currentDate.getYear();
		
		if (datumStr.length == 3)
		{
			jahresZahl = Integer.parseInt(datumStr[2]);
		}
		
		//alle Events im Kalender werden durchlaufen und bei Uebereinstimmung entfernt
		boolean eventEntfernt = false;
		Event event = new Event();
		for (Jahr jahr : kalender.getKalender())
		{
			for (Monat monat : jahr.getJahr())
			{
				for (Tag tag : monat.getMonat())
				{
					for (int i = 0; i < tag.getEvents().size(); i++)
					{
							boolean datumMatch = ((tagNr == tag.getTagNr()) && (monatNr == tag.getMonatNr()) && (jahresZahl == tag.getJahrNr()));
							
							event = tag.getEvents().get(i);
							boolean eventStrMatch = event.getEvent().equals(eventStr);
							if (datumMatch && eventStrMatch)
							{
								tag.getEvents().remove(i);
								eventEntfernt = true;
							}
					}
				}
			}
		}
		if (eventEntfernt)
			System.out.printf("%s am %s von %s bis %s Uhr wurde erfolgreich entfernt\n", eventStr, datum, event.getAnfang(), event.getEnde());
		
		else 
			System.out.printf("Keine Uebereinstimmung mit  \"%s\" am %s gefunden; bitte Angaben ueberpruefen", eventStr, datum);
		kalender.schreibeKalender();
	}
	
	//Entfernt alle Vorkommen des Events im angegebenen Jahr bei Uebereinstimmung von "Event"; einmalige Veranstaltungen werden nicht entfernt
	public static void entferneEventRegelmaessig(String eventStr, int jahresZahl) throws IOException
	{
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		//alle Events im Kalender werden durchlaufen und bei Uebereinstimmung entfernt
		boolean eventEntfernt = false;
		Event event = new Event();
		
		Jahr jahr = kalender.getKalender().get(jahresZahl-2021);	
		for (Monat monat : jahr.getJahr())
		{
			for (Tag tag : monat.getMonat())
			{
				for (int i = 0; i < tag.getEvents().size(); i++)
				{									
						event = tag.getEvents().get(i);
						boolean eventStrMatch = event.getEvent().equals(eventStr);
						if (eventStrMatch)
						{
							tag.getEvents().remove(i);
							eventEntfernt = true;
						}
				}
			}
		}
		
		if (eventEntfernt)
			System.out.printf("%s von %s bis %s Uhr wurde erfolgreich f�r das Jahr %d entfernt\n", eventStr, event.getAnfang(), event.getEnde(), jahresZahl);
		
		else 
			System.out.printf("Keine Uebereinstimmung mit  \"%s\" im Jahr %d gefunden; bitte Angaben ueberpruefen\n", eventStr, jahresZahl);
		kalender.schreibeKalender();
	}

	
	//------------------------Methoden, die nicht direkt mit GUI zusammenhaengen
	
	//Schreiben der Informationen des Kalenders in die Datei Kalender.txt
	public void schreibeKalender() throws FileNotFoundException
	{
		String datei = "Kalender.txt";
		PrintWriter aus = new PrintWriter(datei);
		aus.printf("%d\n%d\n", monatZaehler, jahrZaehler);
		for (Jahr jahr : kalender)
		{
			for (Monat monat : jahr.getJahr())
			{
				for (Tag tag : monat.getMonat())
				{
					if (tag.getEvents().size() != 0)
					{
						aus.printf("%s;%d;%d;%d;", tag.getWochenTag(), tag.getTagNr(), tag.getMonatNr(), tag.getJahrNr());
						for (Event event : tag.getEvents())
						{
							aus.printf(event.schreibeEvent());
						}
						aus.printf("\n");
					}
				}
			}
		}
		aus.close();
	}
	
	//Erstellen eines Kalenders mit Informationen aus der Datei Kalender.txt
	public void leseKalender() throws IOException  
	{
		File datei = new File("Kalender.txt");
		Scanner ein = new Scanner(datei);
		
		monatZaehler = ein.nextInt();
		jahrZaehler = ein.nextInt();
		ein.nextLine();
		
		while (ein.hasNext())
		{
			String inhaltString = ein.nextLine();
			String[] inhalt = inhaltString.split(";");
			
			int TagNr = Integer.parseInt(inhalt[1]);
			int monatNr = Integer.parseInt(inhalt[2]);
			int JahresZahl = Integer.parseInt(inhalt[3]);
			
			for (int i = 4; i<inhalt.length; i++)
			{
				String eventInhaltString = inhalt[i]; 
				String[] eventInhalt = eventInhaltString.split("\\,");
				boolean veranstaltung = Boolean.parseBoolean(eventInhalt[1]);
				this.addEventDatei(eventInhalt[0], veranstaltung, TagNr, monatNr, JahresZahl, eventInhalt[2], eventInhalt[3], eventInhalt[4]);
			}
		}
		ein.close();
	}
	
	public void addEventDatei(String eventBez, boolean veranstaltung, int tagNr, int monatNr, int jahresZahl, String anfang, String ende, String raumBez)
	{	
		//--Hinzufuegen des Events zum angegeben Tag; dazu durchlaufen der ArrayListen vom Jahr bis zum angegebenen Tag		
		Jahr jahr = this.getKalender().get(jahresZahl-2021);
		Monat monat = jahr.getJahr().get(monatNr-1);
		Tag tag = monat.getMonat().get(tagNr-1);
		
		String wochenTag = tag.getWochenTag();
		Raum raum = new Raum(raumBez);
		Event thisEvent = new Event(eventBez, raum, veranstaltung, tagNr, monatNr, jahresZahl, wochenTag, anfang, ende);
		
		tag.getEvents().add(thisEvent);
	}
	
	//------------------------Im finalen Projekt nicht genutzte Methoden------------------------
	
	//Entfernt alle regelmaessigen Events mit uebereinstimmender Bezeichnung; Veranstaltungen werden nicht entfernt	
	public static void entferneEventRegelmaessig(String eventStr, int jahresZahl, int monatNr) throws IOException
	{
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		//alle Events im Kalender werden durchlaufen und bei Uebereinstimmung entfernt
		boolean eventEntfernt = false;
		Event event = new Event();
		
		Jahr jahr = kalender.getKalender().get(jahresZahl-2021);	
		Monat monat = jahr.getJahr().get(monatNr-1);
		
		for (Tag tag : monat.getMonat())
		{
			for (int i = 0; i < tag.getEvents().size(); i++)
			{									
					event = tag.getEvents().get(i);
					boolean eventStrMatch = event.getEvent().equals(eventStr);
					if (eventStrMatch && (event.isVeranstaltung() == false))
					{
						tag.getEvents().remove(i);
						eventEntfernt = true;
					}
			}
		}
		
		
		if (eventEntfernt)
			System.out.printf("%s von %s bis %s Uhr wurde erfolgreich f�r den Monat %s im  Jahr %d entfernt\n", eventStr, event.getAnfang(), event.getEnde(), monat.getJahresMonat(), jahresZahl);
		
		else 
			System.out.printf("Keine Uebereinstimmung mit  \"%s\" im Monat %s Jahr %d gefunden; bitte Angaben ueberpruefen\n", eventStr, monat.getJahresMonat(), jahresZahl);
		kalender.schreibeKalender();
	}
	public static void ausgabeMonat() throws IOException
	{
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		Jahr jahr = kalender.getKalender().get(jahrZaehler-2021);
		Monat monat = jahr.getJahr().get(monatZaehler-1);
		
		System.out.printf("%s %d \n",monat.getJahresMonat(), jahrZaehler);
		
		for (Tag tag : monat.getMonat())
		{
			if (tag.getTagNr() < 10)
				System.out.printf("%s 0%d ", tag.getWochenTag(), tag.getTagNr());
			else
				System.out.printf("%s %d ", tag.getWochenTag(), tag.getTagNr());
			
			for (Event event : tag.getEvents())
			{
				System.out.printf(event.ausgabeEvent());
			}
			System.out.printf("\n");
		}
	}
	
	public static void ausgabeEventliste(int jahresZahl) throws IOException
	{
		Kalender kalender = new Kalender();
		kalender.leseKalender();
				
		Jahr jahr = kalender.getKalender().get(jahresZahl-2021);
	
		System.out.printf("Termine im Jahr %d: \n", jahresZahl);
		
		for (Monat monat : jahr.getJahr())
		{
			for (Tag tag : monat.getMonat())
			{
				for (Event event : tag.getEvents())
				{
					if (event.isVeranstaltung() == true)
						System.out.printf(" %s \n", event.ausgabeEventMitDatum());
				}
			}
		}
	}
	
	public static void naechsterMonat() throws IOException
	{
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		if (monatZaehler < 12)
			monatZaehler++;
		else 
		{
			monatZaehler = 1;
			jahrZaehler++;
		}
		kalender.schreibeKalender();
		ausgabeMonat();
	}
	
	public static void vorherigerMonat() throws IOException
	{
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		if (monatZaehler > 2)
			monatZaehler--;
		
		else 
		{
			monatZaehler = 12;
			jahrZaehler--;
		}
		kalender.schreibeKalender();
		ausgabeMonat();
	}
	
	public static void setAktuell() throws IOException
	{	
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		LocalDate currentDate = LocalDate.now();
		int monat = currentDate.getMonthValue();
		int jahr = currentDate.getYear();
		monatZaehler = monat;
		jahrZaehler = jahr;
		
		kalender.schreibeKalender();
		ausgabeMonat();
	}
	
	public static void setMonatJahr(int monat, int jahr) throws IOException
	{	
		Kalender kalender = new Kalender();
		kalender.leseKalender();
		
		if ((monat > 0) && (monat < 13))
			monatZaehler = monat;
		if ((jahr > 2021) && (jahr < 2101))
			jahrZaehler = jahr;
		
		kalender.schreibeKalender();
		ausgabeMonat();
	}
	
	public void schreibeKalenderAlt() throws FileNotFoundException
	{
		String datei = "Kalender.txt";
		PrintWriter aus = new PrintWriter(datei);
		aus.printf("%d\n%d\n", monatZaehler, jahrZaehler);
		for (Jahr jahr : kalender)
		{
			for (Monat monat : jahr.getJahr())
			{
				for (Tag tag : monat.getMonat())
				{
					aus.printf("%d;%d;%d;%s;%s;", tag.getTagNr(), tag.getMonatNr(), tag.getJahrNr(), tag.getWochenTag(), monat.getJahresMonat());
					for (Event event : tag.getEvents())
					{
						aus.printf(event.schreibeEvent());
						
					}
					aus.printf("\n");
				}
			}
		}
		aus.close();
	}
	
}
