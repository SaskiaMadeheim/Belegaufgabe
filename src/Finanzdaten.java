import java.util.ArrayList;

public class Finanzdaten
{
	// Instanzvariablen
	protected double haben;
	protected double jaehrlEinnahmen;
	protected double jaehrlAusgaben;
	protected ArrayList<Finanzbewegung> kontobew;
	
	// Konstruktoren
	public Finanzdaten()
	{
		haben = 0;
		jaehrlEinnahmen = 0;
		jaehrlAusgaben = 0;
		kontobew = new ArrayList<Finanzbewegung>();
	}
	public Finanzdaten(double haben, double jaehrlEinnahmen, double jaehrlAusgaben)
	{
		this.haben = haben;
		this.jaehrlEinnahmen = jaehrlEinnahmen;
		this.jaehrlAusgaben = jaehrlAusgaben;
		kontobew = new ArrayList<Finanzbewegung>();
	}
	
	public Finanzdaten(double haben, double jaehrlEinnahmen, double jaehrlAusgaben, ArrayList<Finanzbewegung> kontobew)
	{
		this.haben = haben;
		this.jaehrlEinnahmen = jaehrlEinnahmen;
		this.jaehrlAusgaben = jaehrlAusgaben;
		this.kontobew = kontobew;
	}
	
	// getter/setter
	public double getHaben() 
	{
		return haben;
	}
	
	public double getJaehrlEinnahmen()
	{
		return jaehrlEinnahmen;
	}
	
	public double getJaehrlAusgaben()
	{
		return jaehrlAusgaben;
	}
	
	//Kontobewegung hinzufuegen
	public void kontoBewegung(Finanzbewegung f)
	{
		haben = haben + f.getBetrag();
		if (f.getPositiv())
			jaehrlEinnahmen = jaehrlEinnahmen + f.getBetrag();
		else
			jaehrlAusgaben = jaehrlAusgaben + (-1)*f.getBetrag();
		kontobew.add(f);
	}
	
}
