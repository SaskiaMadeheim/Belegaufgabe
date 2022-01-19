package finanzen;
import java.time.LocalDate;

public class Finanzbewegung
{
	// Instanzvariablen
	protected String name;
	protected LocalDate datum;
	protected double betrag;
	protected String abteilung;
	
	// Konstruktoren
	public Finanzbewegung()
	{
		name = "";
		datum = LocalDate.of(0000, 0, 0);
		betrag = 0;
		abteilung = "";
	}
	
	public Finanzbewegung(String name, String datum, double betrag, String abteilung)
	{
		this.name = name;
		this.datum = LocalDate.parse(datum);
		this.betrag = betrag;
		this.abteilung = abteilung;
	}
	
	// getter/setter
	public String getName()
	{
		return name;
	}
	
	public String getDatumSt()
	{
		return datum.toString();
	}
	
	public double getBetrag()
	{
		return betrag;
	}
	
	public String getAbteilung()
	{
		return abteilung;
	}
	
	// Infos ausgeben (nur zum Testen notwendig gewesen)
	public void gebeInfos()
	{
		System.out.printf("%s: Datum %s, Abteilung %s, Betrag %1.2f\n", getName(), getDatumSt(), getAbteilung(), getBetrag());
	}
}
