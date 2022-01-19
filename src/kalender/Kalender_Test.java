package kalender;
import java.io.IOException;

public class Kalender_Test {

	public static void main(String[] args) throws IOException 
	{
		
		
		Kalender.addEventWoechentlich("Di.", 2022, "Tennistraining D Jugend", "Tennisplatz", "16:00", "17:30");
		Kalender.addEventWoechentlich("Di.", 2022, "Tennistraining E Jugend", "Tennisplatz", "14:00", "15:00");
		Kalender.addEventWoechentlich("Sa.", 2022, "Tennistraining Herren", "Tennisplatz", "10:00", "12:00");
		
		Kalender.addEvent("Tennisturnier", "Halle 1", "12.09.2022", "18:00", "20:30");
		Kalender.addEvent("Tuttlinger Winterspiele", "Halle 1", "20.11.2022", "10:00", "18:00");
		Kalender.addEvent("Tennisturnier", "Halle 1", "20.09.2022", "18:00", "20:30");
		Kalender.addEvent("Tuttlinger Festspiele", "Sportplatz", "15.06.2022", "10:00", "18:00");
		
		Kalender.addEventWoechentlich("Mi.", 2022, "Fu�balltraining A Jugend", "Halle 1", "18:00", "19:30");
		Kalender.addEventWoechentlich("Fr.", 2022, "Fu�balltraining Kinder", "Halle 2", "14:00", "15:00");
		
		
		Kalender.ausgabeEventliste(2022);
		Kalender.setMonatJahr(9, 2022);
		Kalender.entferneEventRegelmaessig("Tennistraining D Jugend", 2022);
		Kalender.ausgabeEventliste(2024);
		


		
		
		
	}

}
