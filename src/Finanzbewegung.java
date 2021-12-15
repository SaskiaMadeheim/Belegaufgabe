import java.util.Scanner;

public class Finanzbewegung
{
	// Instanzvariablen
	protected String name;
	protected String datum;
	protected double betrag;
	protected boolean positiv;
	protected String abteilung;
	
	// Konstruktoren
	public Finanzbewegung()
	{
		name = "";
		datum = "";
		betrag = 0;
		positiv = true;
		abteilung = "";
	}
	
	public Finanzbewegung(String name, String datum, double betrag, String abteilung)
	{
		this.name = name;
		this.datum = datum;
		this.betrag = betrag;
		this.abteilung = abteilung;
		if (betrag < 0)
			positiv = false;
		else
			positiv = true;
	}
	
	// getter/setter
	public String getName()
	{
		return name;
	}
	
	public String getDatum()
	{
		return datum;
	}
	
	public double getBetrag()
	{
		return betrag;
	}
	
	public boolean getPositiv()
	{
		return positiv;
	}
	
	public String getAbteilung()
	{
		return abteilung;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDatum(String datum)
	{
		this.datum = datum;
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
		System.out.printf("%s: Datum %s, Abteilung %s, Betrag %1.2f\n", getName(), getDatum(), getAbteilung(), getBetrag());
	}
}
