import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Mitgliederverwaltung 
{
	public static void main(String[] args) throws IOException
	{
		ArrayList<Mitglied> mitglieder = new ArrayList<>();
		Erwachsener m1 = new Erwachsener(new String[] {"Max", "Mustermann"}, "Teststrasse 1, 78532 Tuttlingen", "max.mustermann@hs-furtwangen.de", 1990, Abteilung.BASKETBALL);
		Erwachsener m2 = new Erwachsener(new String[] {"Martina", "Mustermann"}, "Teststrasse 3, 78532 Tuttlingen", "martina.mustermann@hs-furtwangen.de", 1987, Abteilung.HANDBALL);
		Kind k1 = new Kind(new String[] {"Matilda", "Mustermann"}, "Teststrasse 1, 78532 Tuttlingen", "matilda.musterman@hs-furtwangen.de", 2012, Abteilung.HANDBALL);
		Student s1 = new Student(new String[] {"Mario", "Mustermann"}, "Teststrasse 2, 78532 Tuttlingen", "mario.mustermann@hs-furtwangen.de", 2000, Abteilung.FUSSBALL);
		
		mitglieder.add(m1);
		mitglieder.add(m2);
		mitglieder.add(k1);
		mitglieder.add(s1);
				
		/*System.out.println(SummeBeitraege(mitglieder));
		System.out.println(AnzahlMitglieder(mitglieder));
		System.out.println(Mitgliederinfo(mitglieder));*/
		
		schreibeCSV("Mitgliederliste.txt", mitglieder);
		String[][] mitgliederliste = leseCSV("Mitgliederliste", 4, 6);
		
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
	
	public static String[][] leseCSV(String dateiname, int zeilen, int spalten) throws FileNotFoundException
	{
		final String trennzeichen = ";|(\\r?\\n)";
		File datei = new File(dateiname);
		Scanner eingabe = new Scanner(datei);
		eingabe.useDelimiter(Pattern.compile(trennzeichen));
		String[][] mitgliederliste = new String[zeilen][spalten];
		while(eingabe.hasNext())
		{
			for(int i = 0; i < mitgliederliste.length; i++)
			{
				for(int j = 0; j < mitgliederliste[0].length; j++)
				{
					if(eingabe.hasNext())
					{
						String ergebnis = eingabe.next();
						mitgliederliste[i][j] = ergebnis;
					}
				}
			}
		}
		eingabe.close();
		return mitgliederliste;
	}
       
}
