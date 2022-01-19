package mitglieder;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MitgliedTabelleModel implements TableModel {
	private Vector mitglieder = new Vector();
	private Vector listeners = new Vector();
	
	public void hinzufMitglied(Mitglied mitglied)
	{
		int index = mitglieder.size();
		mitglieder.add(mitglied);
		
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
		return mitglieder.size();
	}
	
	public String getColumnName(int column) {
		switch( column ){
			case 0: return "Vorname";
			case 1: return "Nachname";
			case 2: return "Abteilung";
			case 3: return "Beitrag";
			default: return null;
		}
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Mitglied mitglied = (Mitglied)mitglieder.get(rowIndex);
		
		switch( columnIndex ){
			case 0: return mitglied.getName()[0];
			case 1: return mitglied.getName()[1];
			case 2: return mitglied.getAbteilung().name();
			case 3: return String.format("%.2f", mitglied.getBeitrag());
			default: return null;
		}
	}
	
	public Class getColumnClass(int columnIndex) {
		switch( columnIndex ){
			case 0: return String.class;
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
