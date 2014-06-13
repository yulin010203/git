package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	String[] title = { "a", "b", "c" };
	String[][] data = null;
	List<String> list = new ArrayList<String>();
	AtomicLong id = new AtomicLong();

	public MyTableModel(List<String> list) {
		data = new String[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = String.valueOf(id.incrementAndGet());
			data[i][1] = list.get(i);
			data[i][2] = "P";
		}
	}

	// @Override
	// public int getRowCount() {
	// // return data.length;
	// return list.size();
	// }

	@Override
	public int getColumnCount() {
		return title.length;
	}

	@Override
	public String getColumnName(int column) {
		return title[column];
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return data[row][column];
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		data[row][column] = (String) aValue;
		fireTableCellUpdated(row, column);
	}

}