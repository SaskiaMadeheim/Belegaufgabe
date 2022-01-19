package mitglieder;
public class Mitglied 
{
	public String[] name;
	public String adresse;
	public String email;
	public int geburtsjahr;
	public Abteilung abteilung;
		
	public Mitglied(String[] name, String adresse, String email, int geburtsjahr, Abteilung abteilung) 
	{
		this.name = name;
		this.adresse = adresse;
		this.email = email;
		this.geburtsjahr = geburtsjahr;
		this.abteilung = abteilung;
	}

	public String[] getName() 
	{
		return name;
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

	public Abteilung getAbteilung() 
	{
		return abteilung;
	}

	public String getInfo()
	{
		return name[0] + " " +  name[1] + ", " + adresse + ", " + email + ", Abteilung: " + abteilung + ", Beitrag: " + getBeitrag() + " Euro im Quartal";
	}

	public double getBeitrag()
	{
		return 20.0;
	}
}

