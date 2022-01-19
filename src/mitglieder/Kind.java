package mitglieder;

public class Kind extends Mitglied
{

	public Kind(String[] name, String adresse, String email, int geburtsjahr, Abteilung abteilung) 
	{
		super(name, adresse, email, geburtsjahr, abteilung);
	}
	
	@Override
	public double getBeitrag()
	{
		if(abteilung == Abteilung.HANDBALL)
			return 30.0;
		else if(abteilung == Abteilung.BASKETBALL)
			return 45.0;
		else if(abteilung == Abteilung.FUSSBALL)
			return 50.0;
		else
			return 20.0;	
	}
}
