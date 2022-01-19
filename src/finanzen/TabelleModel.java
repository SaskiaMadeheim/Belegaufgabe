package finanzen;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TabelleModel implements TableModel{
	private Vector finanzbewegungen = new Vector();
	private Vector listeners = new Vector();
	
	public void hinzufFinanzbewegung(Finanzbewegung bewegung)
	{
		int index = finanzbewegungen.size();
		finanzbewegungen.add(bewegung);
		
		TableModelEvent e = new TableModelEvent( this, index, index, 
				TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT );
		
		for( int i = 0, n = listeners.size(); i<n; i++ ){
			((TableModelListener)listeners.get( i )).tableChanged( e );
		}
	}
	
	public int getColumnCount() {
		return 4;
	}
	
	public int getRowCount() {
		return finanzbewegungen.size();
	}
	
	public String getColumnName(int column) {
		switch( column ){
			case 0: return "Buchungsdatum";
			case 1: return "Buchungstext";
			case 2: return "Abteilung";
			case 3: return "Betrag";
			default: return null;
		}
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Finanzbewegung finanzbewegung = (Finanzbewegung)finanzbewegungen.get(rowIndex);
		
		switch( columnIndex ){
			case 0: return finanzbewegung.getDatumSt();
			case 1: return finanzbewegung.getName();
			case 2: return finanzbewegung.getAbteilung();
			case 3: return String.format("%.2f",(finanzbewegung.getBetrag())); 
			default: return null;
		}
	}
	
	public Class getColumnClass(int columnIndex) {
		switch( columnIndex ){
			case 0: return Integer.class;
			case 1: return String.class;
			case 2: return String.class;
			case 3: return String.class; 
			default: return null;
		}	
	}
	
	public void addTableModelListener(TableModelListener l) {
		listeners.add( l );
	}
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove( l );
	}
	

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}
			
}
