package kalender;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.FileHandler;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class KalenderTabelleModel implements TableModel{
	private Vector kalenders = new Vector();
	private Vector listeners = new Vector();
	
	public void hinzufKalender(Event kalender)
	{
		int index = kalenders.size();
		kalenders.add(kalender);
		
		TableModelEvent e = new TableModelEvent(this, index, index, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		for(int i=0, n=listeners.size(); i<n; i++) {
			((TableModelListener)listeners.get(i)).tableChanged(e);
		}
	}
	
	public int getColumnCount() {
		return 6;
	}
	
	public int getRowCount() {
		return kalenders.size();
	}
	
	public String getColumnName(int column) {
		switch(column) {
		case 0: return "Tag";
		case 1: return "Datum";
		case 2: return "Event";
		case 3: return "Start";
		case 4: return "Ende";
		case 5: return "Raum";
		default: return null;
		}
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Event event = (Event) kalenders.get(rowIndex);
		
		switch(columnIndex) {
		case 0: return event.getWochenTag();
		case 1: return event.getDatum();
		case 2: return event.getEvent();
		case 3: return event.getAnfang();
		case 4: return event.getEnde();
		case 5: return event.getRaum().getBezeichnung();
		default: return null;
		}
	}
	
	public Class getColumnClass(int columnIndex) {
		switch(columnIndex) {
		case 0: return String.class;
		case 1: return String.class;
		case 2: return String.class;
		case 3: return LocalTime.class;
		case 4: return LocalTime.class;
		case 5: return String.class;
		default: return null;
		}
	}
	
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}
	
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}


}
