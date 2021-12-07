import java.util.ArrayList;

public class Tag 
{
	private int tagNr;
	private String wochenTag;
	private int monatNr;
	private ArrayList<Event> events;
	private boolean zustand;
	
	public Tag(int tagNr, String wochenTag, int monatNr) 
	{
		this.events = new ArrayList<Event>();
		this.tagNr = tagNr;
		this.wochenTag = wochenTag;
		this.monatNr = monatNr;
		zustand = false;
	}
	
	public Tag(int tagNr, String wochenTag, int monatNr, ArrayList<Event> events) 
	{
		this.tagNr = tagNr;
		this.wochenTag = wochenTag;
		this.monatNr = monatNr;
		this.events = events;
		zustand = true;
	}

	public int getTagNr() {
		return tagNr;
	}

	public void setTagNr(int tagNr) {
		this.tagNr = tagNr;
	}

	public String getWochenTag() {
		return wochenTag;
	}

	public void setWochenTag(String wochenTag) {
		this.wochenTag = wochenTag;
	}

	public int getMonatNr() {
		return monatNr;
	}

	public void setMonatNr(int monatNr) {
		this.monatNr = monatNr;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
		this.zustand = true;
	}
	
	public void addEvent(Event event)
	{
		this.events.add(event);
	}
	//---Entfernen eines Events
	public void delEvent() {
		this.events = new ArrayList<Event>();
		this.zustand = false;
	}
	
	public boolean getZustand() {
		return zustand;
	}

	public void setZustand(boolean zustand) {
		this.zustand = zustand;
	}

	public String getTag()
	{
		if (tagNr < 10)
		{	
				StringBuffer sb = new StringBuffer();
				for (Event event : this.events)
				{
					sb.append(event.ausgabeEvent()).append(" | ");
				}
				String alleEventsAmTag = sb.toString();
				return " " + tagNr + ". " + wochenTag + " " + alleEventsAmTag;
		}
		else
		{	
				StringBuffer sb = new StringBuffer();
				for (Event event : this.events)
				{
					sb.append(event.ausgabeEvent()).append(" | ");
				}
				String alleEventsAmTag = sb.toString();
				return tagNr + ". " + wochenTag + " " + alleEventsAmTag;	
		}
		
	}

	
	
	
}


