import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event 
{
	private String event;
	private Raum raum;
	private boolean veranstaltung;
	private int tagNr;
	private int monatNr;
	private int jahresZahl;
	private String wochenTag;
	private LocalTime anfang;
	private LocalTime ende;
	
	public Event(String event, String datum, boolean veranstaltung, String wochenTag, String anfang, String ende) 
	{
		String[] datumStr = datum.split("\\.");
		tagNr = Integer.parseInt(datumStr[0]); 
		monatNr = Integer.parseInt(datumStr[1]);
		if (datumStr.length == 2)
		{
			LocalDate currentDate = LocalDate.now();
			jahresZahl = currentDate.getYear();
		}
		else
			jahresZahl = Integer.parseInt(datumStr[2]);
		
		this.event = event;
		this.veranstaltung = veranstaltung;
		this.raum = new Raum();
		this.wochenTag = wochenTag;
		this.anfang = LocalTime.parse(anfang,DateTimeFormatter.ISO_TIME); 
		this.ende = LocalTime.parse(ende,DateTimeFormatter.ISO_TIME);
	}
	
	public Event(String event, Raum raum, boolean veranstaltung, int tagNr, int monatNr, int jahresZahl, String wochenTag, String anfang, String ende) 
	{
		this.event = event;
		this.raum = raum;
		this.veranstaltung = veranstaltung;
		this.tagNr = tagNr;
		this.monatNr = monatNr;
		this.jahresZahl = jahresZahl;
		this.wochenTag = wochenTag;
		this.anfang = LocalTime.parse(anfang,DateTimeFormatter.ISO_TIME); 
		this.ende = LocalTime.parse(ende,DateTimeFormatter.ISO_TIME); 
	}
	public Event(String event, boolean veranstaltung, int tagNr, int monatNr, int jahresZahl, String wochenTag, String anfang, String ende) 
	{
		this.event = event;
		this.raum = new Raum();
		this.veranstaltung = veranstaltung;
		this.tagNr = tagNr;
		this.monatNr = monatNr;
		this.jahresZahl = jahresZahl;
		this.wochenTag = wochenTag;
		this.anfang = LocalTime.parse(anfang,DateTimeFormatter.ISO_TIME); 
		this.ende = LocalTime.parse(ende,DateTimeFormatter.ISO_TIME); 
	}
	
	public Event()
	{
		
	}
	
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}

	
	public boolean isVeranstaltung() {
		return veranstaltung;
	}

	public void setVeranstaltung(boolean veranstaltung) {
		this.veranstaltung = veranstaltung;
	}

	public String getWochenTag() {
		return wochenTag;
	}

	public void setWochenTag(String wochenTag) {
		this.wochenTag = wochenTag;
	}

	public LocalTime getAnfang() {
		return anfang;
	}

	public void setAnfang(String anfang) {
		this.anfang = LocalTime.parse(anfang,DateTimeFormatter.ISO_TIME); 
	}

	public LocalTime getEnde() {
		return ende;
	}

	public void setEnde(String ende) {
		this.ende = LocalTime.parse(ende,DateTimeFormatter.ISO_TIME);
	}
	
	//String zum Schreiben des events in die Datei
	public String schreibeEvent()
	{
		String ausgabe = this.event + "," + this.veranstaltung + "," + this.anfang + "," + this.ende + "," + this.raum.getBezeichnung() + ";";
		return ausgabe;
	}
	
	//String zur Ausgabe des Events
	public String ausgabeEvent()
	{
		String ausgabe = "";
		
		if (this.veranstaltung == false)
			ausgabe = this.event + ": " + this.anfang + "-" + this.ende + " Uhr; Ort: " + this.raum.getBezeichnung() + " | ";
		
		else
			ausgabe ="---" +this.event + ": " + this.anfang + "-" + this.ende + " Uhr; Ort: " + this.raum.getBezeichnung() + "--- | ";
		
		return ausgabe;
	}
	
	public String ausgabeEventMitDatum()
	{
		String ausgabe ="";
		if ((this.tagNr < 10) && (this.monatNr < 10))
			ausgabe =wochenTag  + " 0" + this.tagNr + ".0" + this.monatNr + ": " + this.event + ": " + this.anfang + "-" + this.ende + ", Ort " + this.raum.getBezeichnung() + "; ";
		else if ((this.tagNr > 10) && (this.monatNr < 10))
			ausgabe =wochenTag  + " " + this.tagNr + ".0" + this.monatNr + ": " + this.event + ": " + this.anfang + "-" + this.ende + ", Ort " + this.raum.getBezeichnung() + "; ";
		else if ((this.tagNr > 10) && (this.monatNr > 10))
			ausgabe =wochenTag  + " " + this.tagNr + "." + this.monatNr + ": " + this.event + ": " + this.anfang + "-" + this.ende + ", Ort " + this.raum.getBezeichnung() + "; ";
		else if ((this.tagNr < 10) && (this.monatNr > 10))
			ausgabe =wochenTag  + " 0" + this.tagNr + "." + this.monatNr + ": " + this.event + ": " + this.anfang + "-" + this.ende + ", Ort " + this.raum.getBezeichnung() + "; ";

		return ausgabe;
	}
	
}
