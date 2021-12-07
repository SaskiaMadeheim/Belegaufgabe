import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Monat 
{
	private ArrayList<Tag> monat;
	private int monatNr;
	private String monatN;
	private int jahr;
	
	public ArrayList<Tag> getMonat() {
		return monat;
	}
	public void setMonat(ArrayList<Tag> monat) {
		this.monat = monat;
	}
	public int getMonatNr() {
		return monatNr;
	}
	public void setMonatNr(int monatNr) {
		this.monatNr = monatNr;
	}
	public String getMonatN() {
		return monatN;
	}
	public void setMonatN(String monatN) {
		this.monatN = monatN;
	}
	public int getJahr() {
		return jahr;
	}
	public void setJahr(int jahr) {
		this.jahr = jahr;
	}
	
	//---Erstellen eines Monats mit unbelegten Tagen
	public Monat(int monatNr, int jahr)
	{
		this.monatNr = monatNr;
		this.jahr = jahr;
		//---Bestimmen der Anzahl der Tage im Monat
		GregorianCalendar cal = new GregorianCalendar(jahr, monatNr-1, 1);
		int tageInMonat = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		Date date = cal.getTime();
		this.monatN = new SimpleDateFormat("MMMM", Locale.GERMANY).format(date.getTime()).toString();
	    
		ArrayList<Tag> monat = new ArrayList<Tag>();
		//---Füllen des Monats mit Tagen in Form von Tag
		for (int i=1; i<tageInMonat+1; i++)
		{
			cal.set(GregorianCalendar.DAY_OF_MONTH, i);
			
			//---Ermitteln des Wochentages
			Date dateTag = cal.getTime();
			String wochenTag = new SimpleDateFormat("EE", Locale.GERMANY).format(dateTag.getTime()).toString(); 
			
			//---Anlegen eines Tags mit tagNr und Wochentag
			Tag tag = new Tag(i, wochenTag, monatNr);
			monat.add(tag);
		}
		this.monat = monat;
	}
	//---Erstellen eines Monats mit unbelegten Tagen, aktueller Monat
	public Monat()
	{
		//---Bestimmen der Anzahl der Tage im Monat
		Calendar cal =Calendar.getInstance();
		int tageInMonat = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date date = cal.getTime();
		this.monatN = new SimpleDateFormat("MMMM", Locale.GERMANY).format(date.getTime()).toString();
	    
		ArrayList<Tag> monat = new ArrayList<Tag>();
		//---Füllen des Monats mit Tagen in Form von Tag
		for (int i=1; i<tageInMonat+1; i++)
		{
			cal.set(Calendar.DAY_OF_MONTH, i);
			
			//---Ermitteln des Wochentages
			Date dateTag = cal.getTime();
			String wochenTag = new SimpleDateFormat("EE", Locale.GERMANY).format(dateTag.getTime()).toString(); 
			
			//---Anlegen eines Tags mit tagNr und Wochentag
			Tag tag = new Tag(i, wochenTag, monatNr);
			monat.add(tag);
		}
		this.monat = monat;
		this.monatNr = cal.get(Calendar.MONTH);
		this.jahr = cal.get(Calendar.YEAR);
	}
	//---Hinzufuegen eines Events zu bestehendem Monat am angegebenen Tag 
	public void addEvent(int tagNr, Event event)
	{
		Tag tag = this.monat.get(tagNr);
		tag.addEvent(event);
	}
	
	public void ausgabeMonat()
	{
		System.out.println(monatN + " " + jahr);
		for (Tag tag : this.monat)
		{
			System.out.println(tag.getTag());
		}
	}
}