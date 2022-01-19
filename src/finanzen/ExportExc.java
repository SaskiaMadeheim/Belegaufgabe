package finanzen;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExc extends Finanzdaten
{
	// Instanzvariablen
	protected Finanzdaten daten;
	
	
	// Konstruktoren
	public ExportExc()
	{
		daten = new Finanzdaten();
	}
	
	public ExportExc(Finanzdaten daten)
	{
		this.daten = daten;
	}
	
	// schreibe Kassenbuch in Excel
	public void schreibeExcel() throws FileNotFoundException, IOException
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet tab = workbook.createSheet("Kassenbuch");
		
		// -------------- Schreiben der Datei ----------------
		// Schreiben der ersten Zeile
		Row zeile1 = tab.createRow(0);
		createColoredCell(workbook, zeile1, 0, "Kassenbuch von: Sportverein Tuttlingen", HorizontalAlignment.CENTER, IndexedColors.LIGHT_TURQUOISE.getIndex());
		tab.addMergedRegion((new CellRangeAddress(0, 0, 0, 9)));
		// Schreiben der zweiten Zeile
		Row zeile2 = tab.createRow(1);
		zeile2.createCell(0).setCellValue("Anfangsbestand/Euro");
		zeile2.createCell(1).setCellValue((daten.getHaben() - daten.getJaehrlEinnahmen() + daten.getJaehrlAusgaben()));
		// Schreiben der dritten Zeile
		Row zeile3 = tab.createRow(2);
		zeile3.createCell(0).setCellValue("Einnahmen/Euro");
		zeile3.createCell(1).setCellValue(daten.getJaehrlEinnahmen());
		// Schreiben der vierten Zeile
		Row zeile4 = tab.createRow(3);
		zeile4.createCell(0).setCellValue("Ausgaben/Euro");
		zeile4.createCell(1).setCellValue(daten.getJaehrlAusgaben());
		// Schreiben der fuenften Zeile
		Row zeile5 = tab.createRow(4);
		zeile5.createCell(0).setCellValue("Kassenbestand/Euro");
		zeile5.createCell(1).setCellValue(daten.getHaben());
		// Schreiben der siebten Zeile
		Row zeile7 = tab.createRow(6);
		for(int i = 0; i < 10; i++)
		{
			if (i < 5)
			{
				createColoredCell(workbook, zeile7, i, "", HorizontalAlignment.CENTER, IndexedColors.LIGHT_GREEN.getIndex());
			}
			else
			{
				createColoredCell(workbook, zeile7, i, "", HorizontalAlignment.CENTER, IndexedColors.CORAL.getIndex());
			}
		}
		createColoredCell(workbook, zeile7, 0, "Einnahmen", HorizontalAlignment.CENTER, IndexedColors.LIGHT_GREEN.getIndex());
		tab.addMergedRegion((new CellRangeAddress(6, 6, 0, 4)));
		createColoredCell(workbook, zeile7, 5, "Ausgaben", HorizontalAlignment.CENTER, IndexedColors.CORAL.getIndex());
		tab.addMergedRegion((new CellRangeAddress(6, 6, 5, 9)));
		// Schreiben der achten Zeile
		Row zeile8 = tab.createRow(7);
		for(int i = 0; i < 2; i++)
		{
			createColoredCell(workbook, zeile8, 0 + i*5, "Nr.", HorizontalAlignment.CENTER, IndexedColors.GREY_25_PERCENT.getIndex());
			createColoredCell(workbook, zeile8, 1 + i*5, "Datum", HorizontalAlignment.CENTER, IndexedColors.GREY_25_PERCENT.getIndex());
			createColoredCell(workbook, zeile8, 2 + i*5, "Bezeichnung", HorizontalAlignment.CENTER, IndexedColors.GREY_25_PERCENT.getIndex());
			createColoredCell(workbook, zeile8, 3 + i*5, "Abteilung", HorizontalAlignment.CENTER, IndexedColors.GREY_25_PERCENT.getIndex());
			createColoredCell(workbook, zeile8, 4 + i*5, "Betrag/Euro", HorizontalAlignment.CENTER, IndexedColors.GREY_25_PERCENT.getIndex());
		}
		// Schreiben der Einnahmen und Ausgaben
		int n = berechneZeilen();
		for(int i = 0; i < n; i++)
		{
			Row zeile = tab.createRow(8+i);
		}
		int zaehlerE = 0;
		int zaehlerA = 0;
		for(Finanzbewegung f: daten.getKontobew())
		{
			if(f.getBetrag() >= 0) 			// Schreiben der Zeilen der Einnahmen
			{
				zaehlerE++;
				tab.getRow(zaehlerE+7).createCell(0).setCellValue(zaehlerE);
				tab.getRow(zaehlerE+7).createCell(1).setCellValue(f.getDatumSt());
				tab.getRow(zaehlerE+7).createCell(2).setCellValue(f.getName());
				tab.getRow(zaehlerE+7).createCell(3).setCellValue(f.getAbteilung());
				tab.getRow(zaehlerE+7).createCell(4).setCellValue(f.getBetrag());
			}
			else						   // Schreiben der Zeilen der Ausgaben
			{
				zaehlerA++;
				tab.getRow(zaehlerA+7).createCell(5).setCellValue(zaehlerA);
				tab.getRow(zaehlerA+7).createCell(6).setCellValue(f.getDatumSt());
				tab.getRow(zaehlerA+7).createCell(7).setCellValue(f.getName());
				tab.getRow(zaehlerA+7).createCell(8).setCellValue(f.getAbteilung());
				tab.getRow(zaehlerA+7).createCell(9).setCellValue(f.getBetrag()*(-1));
			}
		}
		
		// -------------- Formatierungen ----------------
		// Duenne Striche einfuegen
		duenneStriche(zaehleEin(), 0, 5, workbook, tab);
		duenneStriche(zaehleAus(), 5, 10, workbook, tab);
		
		// Dicke Striche einfuegen zwischen Einnahmen und Ausgaben
		if (zaehleEin() >= zaehleAus())
		{
			for (int i = 6; i<(8+zaehleEin()); i++)
			{
				CellStyle cellStyle = tab.getRow(i).getCell(4).getCellStyle();
				dickeStricheRechts(cellStyle);
				tab.getRow(i).getCell(4).setCellStyle(cellStyle);
			}
		}
		else
		{
			for (int i = 6; i<(8+zaehleAus()); i++)
			{
				CellStyle cellStyle = tab.getRow(i).getCell(5).getCellStyle();
				dickeStricheLinks(cellStyle);
				tab.getRow(i).getCell(5).setCellStyle(cellStyle);
			}
		}
		// Dicke Striche einfuegen zwischen Einnahmen/Ausgaben und Eigenschaften
		for(int i = 0; i < 10; i++)
		{
			dickeStricheUnten(zeile7.getCell(i).getCellStyle());
		}
		
		// Zellenbreite automatisch anpassen
		for (int i = 0; i < 10; i++)
		{
			tab.autoSizeColumn(i);
		}		
		workbook.write(new FileOutputStream("Kassenbuch.xlsx"));
		workbook.close();
	}
	
// -------------- Hilfsmethoden ----------------	
	
	// neue Zelle erstellen
	public void createColoredCell(Workbook wb, Row row, int column, String value, HorizontalAlignment halign, short colorIdx)
	{
	    Cell cell = row.createCell(column);
	    cell.setCellValue(value);
	    CellStyle cellStyle = wb.createCellStyle();
	    // Textausrichtung
	    cellStyle.setAlignment(halign);
	 	// Farbe einstellen
	 	cellStyle.setFillForegroundColor(colorIdx);
	 	// Fuellart festlegen
	 	cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//Einfach ausfuellen
	 	// Borderstyle
	 	duenneStricheAll(cellStyle);
	 	// Zellenstil anwenden
	 	cell.setCellStyle(cellStyle);
	}
	
	// duenne Striche für alle Ein-/Ausgaben einfuegen
		public void duenneStriche(int zaehler, int anfang, int ende, XSSFWorkbook wb, XSSFSheet tab)
		{
			for(int i = 0; i < zaehler; i++)
			{
				for(int j = anfang; j < ende; j++)
				{
					CellStyle cellStyle = wb.createCellStyle();
					duenneStricheAll(cellStyle);
					tab.getRow(8+i).getCell(j).setCellStyle(cellStyle);
				}
			}
		}
		
	// duenne Striche um einzelne Zelle einfuegen (alle Seiten)
	private static void duenneStricheAll(CellStyle style)
	{
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	}
			
	// dicken Strich an linker Zellgrenze einfuegen
	public void dickeStricheLinks(CellStyle style)
	{
		style.setBorderLeft(BorderStyle.THICK);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	}
	
	// dicken Strich an rechter Zellgrenze einfuegen
	public void dickeStricheRechts(CellStyle style)
	{
		style.setBorderRight(BorderStyle.THICK);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	}
	
	// dicken Strich an unterer Zellgrenze einfuegen
	public void dickeStricheUnten(CellStyle style)
	{
		style.setBorderBottom(BorderStyle.THICK);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	}
	
	// Einnahmen zaehlen
	public int zaehleEin()
	{
		int zaehler = 0;
		for(Finanzbewegung f: daten.getKontobew())
		{
			if(f.getBetrag() >= 0)
			{
				zaehler++;
			}
		}
		return zaehler;
	}
	
	// Ausgaben zaehlen
	public int zaehleAus()
	{
		int zaehler = 0;
		for(Finanzbewegung f: daten.getKontobew())
		{
			if(!(f.getBetrag() >= 0))
			{
				zaehler++;
			}
		}
		return zaehler;
	}
	
	// Zeilenanzahl fuer Ein-/Ausgaben berechnen
	public int berechneZeilen()
	{
		int n = 0;
		if (zaehleEin() < zaehleAus())
		{
			n = zaehleAus();
		}
		else
		{
			n = zaehleEin();
		}
		return n;
	}

	// Testen
	/*public static void main(String args[]) throws IOException
	{
		Finanzbewegung test1 = new Finanzbewegung("Einnahme", "2020-05-09", 500, "Schwimmen");
		Finanzbewegung test2 = new Finanzbewegung("Ausgabe", "2020-05-10", -200, "Handball");
		Finanzbewegung test3 = new Finanzbewegung("Ausgabe", "2020-05-11", -100, "Volleyball");
		ArrayList<Finanzbewegung> test = new ArrayList<Finanzbewegung>();
		test.add(test1);
		test.add(test2);
		test.add(test3);
		Finanzdaten f = new Finanzdaten (0, test, "XY.csv");
		Kassenbuch k = new Kassenbuch (f);
		k.schreibeExcel();
		
	}*/
	
}
