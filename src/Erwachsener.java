
public class Erwachsener extends Mitglied
{

	public Erwachsener(String[] name, String adresse, String email, int geburtsjahr, int abteilung, int mitgliederanzahl) 
	{
		super(name, adresse, email, geburtsjahr, abteilung, false, mitgliederanzahl);
	}
	
	@Override
	public double getBeitrag()
	{
		if(abteilung == 1)
			return 60.0;
		else if(abteilung == 2)
			return 75.0;
		else
			return 20.0;	
	}

}
