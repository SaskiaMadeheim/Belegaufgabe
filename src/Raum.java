
public class Raum 
{
	private String bezeichnung;
	private double[] flaeche;
	private int kapazitaet;
	
	public Raum(String bezeichnung, double[] flaeche, int kapazitaet)
	{
		this.bezeichnung = bezeichnung;
		this.flaeche = flaeche;
		this.kapazitaet = kapazitaet;
	}
	
	public Raum()
	{
		this.bezeichnung = "steht noch nicht fest";
	}
	
	public Raum(String bezeichnung)
	{
		this.bezeichnung = bezeichnung;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public double[] getFlaeche() {
		return flaeche;
	}

	public void setFlaeche(double[] flaeche) {
		this.flaeche = flaeche;
	}

	public int getKapazitaet() {
		return kapazitaet;
	}

	public void setKapazitaet(int kapazitaet) {
		this.kapazitaet = kapazitaet;
	}
	
}
