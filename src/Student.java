
public class Student extends Mitglied
{

	public Student(String[] name, String adresse, String email, int geburtsjahr, Abteilung abteilung) 
	{
		super(name, adresse, email, geburtsjahr, abteilung);
	}

	@Override
	public double getBeitrag()
	{
		if(abteilung == Abteilung.HANDBALL)
			return 40.0;
		else if(abteilung == Abteilung.BASKETBALL)
			return 55.0;
		else if(abteilung == Abteilung.FUSSBALL)
			return 60.0;
		else
			return 20.0;	
	}
}
