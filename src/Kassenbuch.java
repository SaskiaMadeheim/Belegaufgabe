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

public class Kassenbuch extends Finanzdaten
{
	// Instanzvariablen
	protected Finanzdaten daten;
	
	
	// Konstruktoren
	public Kassenbuch()
	{
		daten = new Finanzdaten();
	}
	
	public Kassenbuch(Finanzdaten daten)
	{
		this.daten = daten;
	}
	
	// getter/setter
	public Finanzdaten getDaten()
	{
		return daten;
	}
	
	public void setDaten(Finanzdaten daten)
	{
		this.daten = daten;
	}
	
	// schreibe Kassenbuch in Excel
	public void schreibeExcel() throws FileNotFoundException, IOException
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet tab = workbook.createSheet("Kassenbuch");
		// Schreiben der ersten Zeile
		Row zeile1 = tab.createRow(0);
		createColoredCell(workbook, zeile1, 0, "Kassenbuch von: Sportverein Tuttlingen", HorizontalAlignment.CENTER, IndexedColors.PALE_BLUE.getIndex());
		tab.addMergedRegion((new CellRangeAddress(0, 0, 0, 9)));
		// Schreiben der zweiten Zeile
		Row zeile2 = tab.createRow(1);
		zeile2.createCell(0).setCellValue("Anfangsbestand");
		zeile2.createCell(1).setCellValue((daten.getHaben() - daten.getJaehrlEinnahmen() + daten.getJaehrlAusgaben()) + " €");
		// Schreiben der dritten Zeile
		Row zeile3 = tab.createRow(2);
		zeile3.createCell(0).setCellValue("Einnahmen");
		zeile3.createCell(1).setCellValue(daten.getJaehrlEinnahmen() + " €");
		// Schreiben der vierten Zeile
		Row zeile4 = tab.createRow(3);
		zeile4.createCell(0).setCellValue("Ausgaben");
		zeile4.createCell(1).setCellValue(daten.getJaehrlAusgaben() + " €");
		// Schreiben der fuenften Zeile
		Row zeile5 = tab.createRow(4);
		zeile5.createCell(0).setCellValue("Kassenbestand");
		zeile5.createCell(1).setCellValue(daten.getHaben() + " €");
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
			createColoredCell(workbook, zeile8, 4 + i*5, "Betrag/€", HorizontalAlignment.CENTER, IndexedColors.GREY_25_PERCENT.getIndex());
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
			if(f.getPositiv())
			{
				zaehlerE++;
				tab.getRow(zaehlerE+7).createCell(0).setCellValue(zaehlerE);
				tab.getRow(zaehlerE+7).createCell(1).setCellValue(f.getDatum());
				tab.getRow(zaehlerE+7).createCell(2).setCellValue(f.getName());
				tab.getRow(zaehlerE+7).createCell(3).setCellValue("Schwimmen");
				tab.getRow(zaehlerE+7).createCell(4).setCellValue(f.getBetrag());
			}
			else
			{
				zaehlerA++;
				tab.getRow(zaehlerA+7).createCell(5).setCellValue(zaehlerA);
				tab.getRow(zaehlerA+7).createCell(6).setCellValue(f.getDatum());
				tab.getRow(zaehlerA+7).createCell(7).setCellValue(f.getName());
				tab.getRow(zaehlerA+7).createCell(8).setCellValue("Schwimmen");
				tab.getRow(zaehlerA+7).createCell(9).setCellValue(f.getBetrag()*(-1));
			}
				
			
		}
		// Dicke Striche einfuegen
		borderStyleLeft(zeile7.getCell(5).getCellStyle());
		borderStyleLeft(zeile8.getCell(5).getCellStyle());
		for(int i = 0; i < 10; i++)
		{
			borderStyleBottom(zeile7.getCell(i).getCellStyle());
		}
		// Duenne Striche einfuegen
		for(int i = 0; i < zaehleEin(); i++)
		{
			for(int j = 0; j < 5; j++)
			{
				CellStyle cellStyle = workbook.createCellStyle();
				borderStyleAll(cellStyle);
				tab.getRow(8+i).getCell(j).setCellStyle(cellStyle);
			}
		}
		
		for(int i = 0; i < zaehleAus(); i++)
		{
			for(int j = 5; j < 10; j++)
			{
				CellStyle cellStyle = workbook.createCellStyle();
				borderStyleAll(cellStyle);
				tab.getRow(8+i).getCell(j).setCellStyle(cellStyle);
			}
		}
		// Zellenbreite automatisch anpassen
		for (int i = 0; i < 10; i++)
		{
			tab.autoSizeColumn(i);
		}		
		workbook.write(new FileOutputStream("Kassenbuch.xlsx"));
		workbook.close();
	}
			
	public void createColoredCell(Workbook wb, Row row, int column, String value, HorizontalAlignment halign, short colorIdx)
	{
	    Cell cell = row.createCell(column);
	    cell.setCellValue(value);
	    CellStyle cellStyle = wb.createCellStyle();
	    cellStyle.setAlignment(halign);
	 	// Farbe einstellen
	 	cellStyle.setFillForegroundColor(colorIdx);
	 	// Füllart festlegen
	 	cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//Einfach ausfüllen
	 	// Borderstyle
	 	borderStyleAll(cellStyle);
	 	// Zellenstil anwenden
	 	cell.setCellStyle(cellStyle);
	}
			
	public void createCell(Workbook wb, Row row, int column, String value, HorizontalAlignment halign)
	{
	    Cell cell = row.createCell(column);
	    cell.setCellValue(value);
	    CellStyle cellStyle = wb.createCellStyle();
	    cellStyle.setAlignment(halign);
	    cell.setCellStyle(cellStyle);
	}
			
	private static void borderStyleAll(CellStyle style)
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
			
	public void borderStyleLeft(CellStyle style)
	{
		style.setBorderLeft(BorderStyle.THICK);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	}
	
	public void borderStyleBottom(CellStyle style)
	{
		style.setBorderBottom(BorderStyle.THICK);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	}
	
	public int zaehleEin()
	{
		int zaehler = 0;
		for(Finanzbewegung f: daten.getKontobew())
		{
			if(f.getPositiv())
			{
				zaehler++;
			}
		}
		return zaehler;
	}
	
	public int zaehleAus()
	{
		int zaehler = 0;
		for(Finanzbewegung f: daten.getKontobew())
		{
			if(!f.getPositiv())
			{
				zaehler++;
			}
		}
		return zaehler;
	}
	
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
	
	public static void main(String args[]) throws IOException
	{
		Finanzbewegung test1 = new Finanzbewegung("Einnahme", 9052020, 500);
		Finanzbewegung test2 = new Finanzbewegung("Ausgabe", 10052020, -200);
		Finanzbewegung test3 = new Finanzbewegung("Ausgabe", 11052020, -100);
		ArrayList<Finanzbewegung> test = new ArrayList<Finanzbewegung>();
		test.add(test1);
		test.add(test2);
		test.add(test3);
		Finanzdaten f = new Finanzdaten (0, test);
		Kassenbuch k = new Kassenbuch (f);
		k.schreibeExcel();
		
	}
	
}
