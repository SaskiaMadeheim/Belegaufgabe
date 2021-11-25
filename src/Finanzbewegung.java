
public class Finanzbewegung
{
	// Instanzvariablen
	protected String name;
	protected int datum;
	protected double betrag;
	protected boolean positiv;
	
	// Konstruktoren
	public Finanzbewegung()
	{
		name = "";
		datum = 0;
		betrag = 0;
		positiv = true;
	}
	
	public Finanzbewegung(String name, int datum, double betrag)
	{
		this.name = name;
		this.datum = datum;
		this.betrag = betrag;
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
	
	public int getDatum()
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
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDatum(int datum)
	{
		this.datum = datum;
	}
	
	public void setBetrag(double betrag)
	{
		this.betrag = betrag;
	}
	
	
}
