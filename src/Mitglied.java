public class Mitglied 
{
	public String[] name;
	public String adresse;
	public String email;
	public int geburtsjahr;
	public int abteilung;
	public boolean student;
	private static int mitgliederanzahl;
		
	public Mitglied(String[] name, String adresse, String email, int geburtsjahr, int abteilung, boolean student, int mitgliederanzahl) 
	{
		this.name = name;
		this.adresse = adresse;
		this.email = email;
		this.geburtsjahr = geburtsjahr;
		this.abteilung = abteilung;
		this.student = student;
		mitgliederanzahl += mitgliederanzahl;
	}

	public static int getMitgliederanzahl() 
	{
		return mitgliederanzahl;
	}

	public String[] getName() 
	{
		return name;
	}

	public void setName(String[] name) 
	{
		this.name = name;
	}

	public String getAdresse() 
	{
		return adresse;
	}

	public void setAdresse(String adresse) 
	{
		this.adresse = adresse;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public int getGeburtsjahr() 
	{
		return geburtsjahr;
	}

	public void setGeburtsjahr(int geburtsjahr) 
	{
		this.geburtsjahr = geburtsjahr;
	}

	public int getAbteilung() 
	{
		return abteilung;
	}

	public void setAbteilung(int abteilung) 
	{
		this.abteilung = abteilung;
	}

	public boolean istStudent() 
	{
		return student;
	}

	public void setStudent(boolean student) 
	{
		this.student = student;
	}

	public String getInfo()
	{
		return name[0] + " " +  name[1] + ", " + adresse + " " + email + " Abteilung: " + abteilung + ", Beitrag: " + getBeitrag() + " Euro im Quartal";
	}

	public double getBeitrag()
	{
		return 20.0;
	}
}

