import java.time.LocalTime;
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
	
	public void sortEvents()
	{
		boolean flag = false;
		do
		{	
			flag = true;
			for (int i = 0; i < events.size()-1; i++)
			{
				LocalTime anfang1 = events.get(i).getAnfang();
				LocalTime anfang2 = events.get(i+1).getAnfang();
				
				if(anfang1.isAfter(anfang2))
				{
					flag = false;
					Event h = events.get(i);
					events.set(i, events.get(i+1));
					events.set(i+1, h);
				}
			
			}
		}
		while (!flag);
	}

}
