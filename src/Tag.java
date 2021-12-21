import java.util.ArrayList;

public class Tag 
{
	private ArrayList<Event> events;
	private String wochenTag;
	private int tagNr;
	private int monatNr;
	private int jahrNr;
	
	public Tag(int tagNr, String wochenTag, int monatNr, int jahrNr) 
	{
		this.events = new ArrayList<Event>();
		this.tagNr = tagNr;
		this.wochenTag = wochenTag;
		this.monatNr = monatNr;
		this.jahrNr = jahrNr;
		
	}
	public ArrayList<Event> getEvent() {
		return events;
	}
	
	public void setEvent(ArrayList<Event> events) {
		this.events = events;
	}
	public String getWochenTag() {
		return wochenTag;
	}
	public void setWochenTag(String wochenTag) {
		this.wochenTag = wochenTag;
	}
	public int getTagNr() {
		return tagNr;
	}
	public void setTagNr(int tagNr) {
		this.tagNr = tagNr;
	}
	public ArrayList<Event> getEvents() {
		return events;
	}
	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	public int getMonatNr() {
		return monatNr;
	}
	public void setMonatNr(int monatNr) {
		this.monatNr = monatNr;
	}
	public int getJahrNr() {
		return jahrNr;
	}
	public void setJahrNr(int jahrNr) {
		this.jahrNr = jahrNr;
	}

}
