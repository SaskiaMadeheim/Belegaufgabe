import java.util.ArrayList;

public class Monat 
{
	private ArrayList<Tag> monat;
	private int monatNr;
	private String jahresMonat;
	private int jahresZahl;
	
	public Monat(int monatNr, String jahresMonat, int jahresZahl)
	{
		ArrayList<Tag> monat = new ArrayList<Tag>();
		this.monat = monat;
		this.monatNr = monatNr;
		this.jahresMonat = jahresMonat;
		this.jahresZahl = jahresZahl;
	}

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

	public String getJahresMonat() {
		return jahresMonat;
	}

	public void setJahresMonat(String jahresMonat) {
		this.jahresMonat = jahresMonat;
	}

	public int getJahresZahl() {
		return jahresZahl;
	}

	public void setJahresZahl(int jahresZahl) {
		this.jahresZahl = jahresZahl;
	}
	
	
}
