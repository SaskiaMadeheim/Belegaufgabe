
public class Erwachsener extends Mitglied
{

	public Erwachsener(String[] name, String adresse, String email, int geburtsjahr, Abteilung abteilung) 
	{
		super(name, adresse, email, geburtsjahr, abteilung);
	}
	
	@Override
	public double getBeitrag()
	{
		if(abteilung == Abteilung.HANDBALL)
			return 60.0;
		else if(abteilung == Abteilung.BASKETBALL)
			return 75.0;
		else if(abteilung == Abteilung.FUSSBALL)
			return 80.0;
		else
			return 20.0;	
	}

}
