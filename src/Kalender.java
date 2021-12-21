import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

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
	
	public void schreibeKalender(String datei) throws FileNotFoundException
	{
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
						aus.printf(event.ausgabeEvent());
					}
					aus.printf("\n");
				}
			}
		}
		aus.close();
	}
	
	public void addEvent(String event, String datum, String anfang, String ende)
	{
		//---Festlegen des Datums des Events
		String[] datumStr = datum.split("\\.");
		int tagNr = Integer.parseInt(datumStr[0]); 
		int monatNr = Integer.parseInt(datumStr[1]);
		int jahresZahl = 0;
		if (datumStr.length == 2)
		{
			LocalDate currentDate = LocalDate.now();
			jahresZahl = currentDate.getYear();
		}
		else
			jahresZahl = Integer.parseInt(datumStr[2]);
		
		//--Hinzufuegen des Events zum angegeben Tag; dazu durchlaufen der ArrayListen bis zum angegebenen Tag
		Event thisEvent = new Event(event, tagNr, monatNr, jahresZahl, anfang, ende);
		
		Jahr jahr = this.getKalender().get(jahresZahl-2021);
		Monat monat = jahr.getJahr().get(monatNr);
		Tag tag = monat.getMonat().get(tagNr);
		tag.getEvent().add(thisEvent);
	}
	
	public void naechsterMonat()
	{
		if (monatZaehler < 12)
			monatZaehler++;
		
		else 
		{
			monatZaehler = 1;
			jahrZaehler++;
		}	
	}
	public void vorherigerMonat()
	{
		if (monatZaehler > 2)
			monatZaehler--;
		
		else 
		{
			monatZaehler = 12;
			jahrZaehler--;
		}
	}
	
	public void setAktuell()
	{
		LocalDate currentDate = LocalDate.now();
		int monat = currentDate.getMonthValue();
		int jahr = currentDate.getYear();
		monatZaehler = monat;
		jahrZaehler = jahr;
	}
	
	public void setMonatJahr(int monat, int jahr)
	{
		if ((monat < 0) && (monat >13))
			monatZaehler = monat;
		if ((jahr < 2021) && (jahr > 2101))
			jahrZaehler = jahr;
	}

}
