import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Mitgliederverwaltung 
{
	public static void main(String[] args) throws IOException
	{
		ArrayList<Mitglied> mitglieder = new ArrayList<>();
		Erwachsener m1 = new Erwachsener(new String[] {"Max", "Mustermann"}, "Teststra�e 1, 78532 Tuttlingen", "max.mustermann@hs-furtwangen.de", 1990, Abteilung.BASKETBALL);
		Erwachsener m2 = new Erwachsener(new String[] {"Martina", "Mustermann"}, "Teststra�e 3, 78532 Tuttlingen", "martina.mustermann@hs-furtwangen.de", 1987, Abteilung.HANDBALL);
		Kind k1 = new Kind(new String[] {"Matilda", "Mustermann"}, "Teststra�e 1, 78532 Tuttlingen", "matilda.musterman@hs-furtwangen.de", 2012, Abteilung.HANDBALL);
		Student s1 = new Student(new String[] {"Mario", "Mustermann"}, "Teststra�e 2, 78532 Tuttlingen", "mario.mustermann@hs-furtwangen.de", 2000, Abteilung.FUSSBALL);
		
		mitglieder.add(m1);
		mitglieder.add(m2);
		mitglieder.add(k1);
		mitglieder.add(s1);
				
		/*System.out.println(SummeBeitr�ge(mitglieder));
		System.out.println(AnzahlMitglieder(mitglieder));
		System.out.println(Mitgliederinfo(mitglieder));*/
		
		schreibeCSV("Mitgliederliste", mitglieder);
		
	}
	
	public static double SummeBeitraege(ArrayList<Mitglied> mitglieder)
	{
		double summe = 0;
		for(Mitglied m : mitglieder)
			summe += m.getBeitrag();
		return summe;
	}
	
	public static int AnzahlMitglieder(ArrayList<Mitglied> mitglieder)
	{
		return mitglieder.size();
	}
	
	public static String Mitgliederinfo(ArrayList<Mitglied> mitglieder)
	{
		String info = "";
		for(Mitglied m : mitglieder)
		    info += m.getInfo() + "\n";
		return info;
	}
	
	public static void schreibeCSV(String dateiname, ArrayList<Mitglied> mitglieder) throws IOException
	{
		PrintWriter pW = new PrintWriter(dateiname);
		for(Mitglied m : mitglieder)
		{
			pW.print(m.getName()[0] + "; " + m.getName()[1] + "; " + m.adresse + "; " + m.email + "; " + m.abteilung + "; " + m.getClass().getSimpleName());
			pW.print("\n");
		}
		
		pW.close();
	}
       
}
