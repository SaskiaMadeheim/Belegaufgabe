
public class Termin 
{
	private int tagNr;
	private String wochenTag;
	private int monatNr;
	private String monatN;
	private String ereignis;
	private int zustand;
	
	public Termin(int tagNr, String wochenTag, int monatNr) 
	{
		this.tagNr = tagNr;
		this.wochenTag = wochenTag;
		this.monatNr = monatNr;
		zustand = 0;
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

	public String getMonatN() {
		return monatN;
	}

	public void setMonatN(String monatN) {
		this.monatN = monatN;
	}

	public String getEreignis() {
		return ereignis;
	}

	public void setEreignis(String ereignis) {
		this.ereignis = ereignis;
	}

	public int getZustand() {
		return zustand;
	}

	public void setZustand(int zustand) {
		this.zustand = zustand;
	}
	
	public String getTermin()
	{
		if (tagNr < 10)
		{
			if (zustand == 0)
				return " " + tagNr + ". " + wochenTag;
			else
				return " " + tagNr + ". " + wochenTag + " " + ereignis;
		}
		else
		{
			if (zustand == 0)
				return tagNr + ". " + wochenTag;
			else
				return tagNr + ". " + wochenTag + " " + ereignis;
		}
		
	}

	
	
	
}
