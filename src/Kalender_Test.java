import java.io.FileNotFoundException;

public class Kalender_Test {

	public static void main(String[] args) throws FileNotFoundException 
	{
		Event tennis = new Event("Tennisturnier", "26.05.2023", "12:00", "16:30");
		System.out.println(tennis.ausgabeEvent());
		
		Kalender test = new Kalender();
		test.schreibeKalender("Kalender.txt");
		
	}
}
