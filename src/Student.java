
public class Student extends Mitglied
{

	public Student(String[] name, String adresse, String email, int geburtsjahr, int abteilung, int mitgliederanzahl) 
	{
		super(name, adresse, email, geburtsjahr, abteilung, true, mitgliederanzahl);
	}

	@Override
	public double getBeitrag()
	{
		if(abteilung == 1)
			return 40.0;
		else if(abteilung == 2)
			return 55.0;
		else
			return 20.0;	
	}
}
