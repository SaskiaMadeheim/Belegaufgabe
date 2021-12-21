import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event 
{
	private String event;
	private Raum raum;
	private int tagNr;
	private int monatNr;
	private int jahresZahl;
	private LocalTime anfang;
	private LocalTime ende;
	
	public Event(String event, String datum, String anfang, String ende) 
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
		this.anfang = LocalTime.parse(anfang,DateTimeFormatter.ISO_TIME); 
		this.ende = LocalTime.parse(ende,DateTimeFormatter.ISO_TIME);
	}
	
	public Event(String event, Raum raum, int tagNr, int monatNr, int jahresZahl, String anfang, String ende) 
	{
		this.event = event;
		this.raum = raum;
		this.tagNr = tagNr;
		this.monatNr = monatNr;
		this.jahresZahl = jahresZahl;
		this.anfang = LocalTime.parse(anfang,DateTimeFormatter.ISO_TIME); 
		this.ende = LocalTime.parse(ende,DateTimeFormatter.ISO_TIME); 
	}
	public Event(String event, int tagNr, int monatNr, int jahresZahl, String anfang, String ende) 
	{
		this.event = event;
		this.tagNr = tagNr;
		this.monatNr = monatNr;
		this.jahresZahl = jahresZahl;
		this.anfang = LocalTime.parse(anfang,DateTimeFormatter.ISO_TIME); 
		this.ende = LocalTime.parse(ende,DateTimeFormatter.ISO_TIME); 
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
	
	public String schreibeEvent()
	{
		String ausgabe = this.event + "," + this.tagNr + "," + this.monatNr + "," + this.jahresZahl + "," + this.anfang + "," + this.ende + "," + this.raum.getBezeichnung() + ";";
		return ausgabe;
	}
	
	public String ausgabeEvent()
	{
		String ausgabe = this.event + ": " + this.anfang + "-" + this.ende + ", Ort " + this.raum.getBezeichnung() + ";";
		return ausgabe;
	}
	
}
