import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event 
{
	private String event;
	private Raum raum;
	private LocalTime anfang;
	private LocalTime ende;
	
	public Event(String event, Raum raum, String anfang, String ende)
	{
		this.event = event;
		this.raum = raum;
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
	
	public String ausgabeEvent()
	{
		String ausgabe = this.anfang + "-" + this.ende + ": " + this.event + "; Ort: " + this.raum.getBezeichnung();
		return ausgabe;
	}
	
}
