
public class Finanzdaten
{
	// Instanzvariablen
	protected double haben;
	protected double jaehrlEinnahmen;
	protected double jaehrlAusgaben;
	
	// Konstruktoren
	public Finanzdaten()
	{
		haben = 0;
		jaehrlEinnahmen = 0;
		jaehrlAusgaben = 0;
	}
	
	public Finanzdaten(double haben, double jaehrlEinnahmen, double jaehrlAusgaben)
	{
		this.haben = haben;
		this.jaehrlEinnahmen = jaehrlEinnahmen;
		this.jaehrlAusgaben = jaehrlAusgaben;
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
	
	//Ausgabe hinzufuegen
	public void ausgeben(double betrag)
	{
		haben = haben - betrag;
	}
	
	//Einnahme hinzufuegen
	public void einnehmen(double betrag)
	{
		haben = haben + betrag;
	}
}
