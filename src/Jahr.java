import java.util.ArrayList;

public class Jahr 
{
	private int jahreszahl;
	private ArrayList<Monat> jahr;
	
	public Jahr(int jahresZahl)
	{
		ArrayList<Monat> jahr = new ArrayList<Monat>();
		for (int i = 0; i<13; i++)
		{
			Monat monat = new Monat(i, jahresZahl);
			jahr.add(monat);
		}
		this.jahreszahl = jahresZahl;
		this.jahr = jahr;
	}
	
	public void ausgabeMonat(int monatNr)
	{
		jahr.get(monatNr).ausgabeMonat();
	}
	
	public void addEvent(int tagNr, int monatNr, Event event)
	{
		Monat monat =this.jahr.get(monatNr);
		monat.addEvent(tagNr-1, event);
	}
	
	//---hinzufuegen eines regelmaessigen Events fuer jeden entsprechenden Wochentag
	public void addEventRegelmaessig(String wochenTag, Event event)
	{
		for (Monat monat : jahr)
		{
			for (Tag tag : monat.getMonat())
			{
				if (tag.getWochenTag().matches(wochenTag))
				{
					tag.addEvent(event);
				}
			}
		}
	}
}

