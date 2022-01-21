package kalender;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Jahr 
{
	private ArrayList<Monat> jahr;
	private int jahresZahl;
	
	//Durch Jahreskonstruktor wird der Kalender mit Monaten und Tagen gefuellt
	public Jahr(int jahresZahl)
	{
		ArrayList<Monat> jahr= new ArrayList<Monat>();
		for (int i = 1; i<13; i++)
		{
			GregorianCalendar cal = new GregorianCalendar(jahresZahl, i-1, 1);
			Date date = cal.getTime();
			String jahresMonat = new SimpleDateFormat("MMMM", Locale.GERMANY).format(date.getTime()).toString();
			Monat monat = new Monat(i, jahresMonat, jahresZahl);
			jahr.add(monat);
			
			int tageInMonat = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			for (int j=1; j<tageInMonat+1; j++)
			{
				cal.set(GregorianCalendar.DAY_OF_MONTH, j);
				
				//---Ermitteln des Wochentages
				Date dateTag = cal.getTime();
				String wochenTag = new SimpleDateFormat("EE", Locale.GERMANY).format(dateTag.getTime()).toString(); 
				
				//---Hinzufuegen der Tage
				Tag tag = new Tag(j, wochenTag, i, jahresZahl);
				monat.getMonat().add(tag);
			}
		}
		this.jahr = jahr;
	}

	public ArrayList<Monat> getJahr() {
		return jahr;
	}

	public void setJahr(ArrayList<Monat> jahr) {
		this.jahr = jahr;
	}

	public int getJahresZahl() {
		return jahresZahl;
	}

	public void setJahresZahl(int jahresZahl) {
		this.jahresZahl = jahresZahl;
	}
	
}
