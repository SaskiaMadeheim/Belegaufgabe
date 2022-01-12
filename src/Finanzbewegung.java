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
	
	public LocalDate getDatumLD()
	{
		return datum;
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
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDatumLD(LocalDate datum)
	{
		this.datum = datum;
	}
	
	public void setDatumSt(String datum)
	{
		this.datum = LocalDate.parse(datum);
	}
	
	public void setBetrag(double betrag)
	{
		this.betrag = betrag;
	}
	
	public void setAbteilung(String abteilung)
	{
		this.abteilung = abteilung;
	}
	
	// Infos ausgeben
	public void gebeInfos()
	{
		System.out.printf("%s: Datum %s, Abteilung %s, Betrag %1.2f\n", getName(), getDatumSt(), getAbteilung(), getBetrag());
	}
}
