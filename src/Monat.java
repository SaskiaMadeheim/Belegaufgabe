import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Monat 
{
	private ArrayList<Termin> monat;
	private int monatNr;
	private String monatN;
	private int jahr;
	
	public Monat(int monatNr, int jahr)
	{
		this.monatNr = monatNr;
		this.jahr = jahr;
		//---Bestimmen der Anzahl der Tage im Monat
		GregorianCalendar cal = new GregorianCalendar(jahr, monatNr-1, 1);
		int tageInMonat = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		Date date = cal.getTime();
		this.monatN = new SimpleDateFormat("MMMM", Locale.GERMANY).format(date.getTime()).toString();
	    
		ArrayList<Termin> monat = new ArrayList<Termin>();
		//---Füllen des Monats mit Tagen in Form von Termin
		for (int i=1; i<tageInMonat+1; i++)
		{
			cal.set(GregorianCalendar.DAY_OF_MONTH, i);
			
			//---Ermitteln des Wochentages
			Date dateTag = cal.getTime();
			String wochenTag = new SimpleDateFormat("EE", Locale.GERMANY).format(dateTag.getTime()).toString(); 
			
			//---Anlegen eines Termins mit tagNr und Wochentag
			Termin tag = new Termin(i, wochenTag, monatNr);
			monat.add(tag);
		}
		this.monat = monat;
	}
	public Monat()
	{
		
		//---Bestimmen der Anzahl der Tage im Monat
		Calendar cal =Calendar.getInstance();
		int tageInMonat = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date date = cal.getTime();
		this.monatN = new SimpleDateFormat("MMMM", Locale.GERMANY).format(date.getTime()).toString();
	    
		ArrayList<Termin> monat = new ArrayList<Termin>();
		//---Füllen des Monats mit Tagen in Form von Termin
		for (int i=1; i<tageInMonat+1; i++)
		{
			cal.set(Calendar.DAY_OF_MONTH, i);
			
			//---Ermitteln des Wochentages
			Date dateTag = cal.getTime();
			String wochenTag = new SimpleDateFormat("EE", Locale.GERMANY).format(dateTag.getTime()).toString(); 
			
			//---Anlegen eines Termins mit tagNr und Wochentag
			Termin tag = new Termin(i, wochenTag, monatNr);
			monat.add(tag);
		}
		this.monat = monat;
		this.monatNr = cal.get(Calendar.MONTH);
		this.jahr = cal.get(Calendar.YEAR);
		
	}
	public void ausgabeMonat()
	{
		System.out.println(monatN + " " + jahr);
		for (Termin tag : this.monat)
		{
			System.out.println(tag.getTermin());
		}
	}
}
