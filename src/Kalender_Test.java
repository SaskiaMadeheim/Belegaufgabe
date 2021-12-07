
public class Kalender_Test {

	public static void main(String[] args) 
	{
	//--Erstellen von Events; dazu erstmal Erstellen eines Raumes
	Raum halle1 = new Raum("Halle 1");
	Raum tennisPlatz = new Raum("Tennisplatz");
	Event tennis = new Event("Tennistraining", halle1, "09:00", "10:30");
	Event tennisTurnier = new Event("Tennisturnier", tennisPlatz, "12:30", "14:00");
	System.out.println(tennis.ausgabeEvent());
	
	
	//--Erstellen eines Jahres und hinzufuegen von Events und eines regelmaessigen Events am genannten Wochentag
	Jahr testJahr = new Jahr(2021);
	testJahr.addEventRegelmaessig("Mo.", tennis);
	testJahr.addEvent(8, 2, tennisTurnier);
	testJahr.addEvent(23, 2, tennisTurnier);
	testJahr.ausgabeMonat(2);
	//testJahr.ausgabeMonat(6);

	}

}
