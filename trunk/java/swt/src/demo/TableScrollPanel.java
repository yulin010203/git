package demo;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.eclipse.swt.widgets.Display;

/**
 * @author 陈霖 2014-5-23
 */
public class TableScrollPanel extends JFrame {

	private JScrollPane myPane;
	private JTable myTable;
	private Display parent;
	private MyTableModel myTableModel;

	public TableScrollPanel(List<String> list) {
		this(list, null);
	}

	public TableScrollPanel(List<String> list, Display parent) {
		myTableModel = new MyTableModel(list);
		myTable = new JTable(myTableModel);
		myTable.setVisible(true);
		myTable.setRowHeight(100);
		myTable.setPreferredScrollableViewportSize(new Dimension(300, 100 * 9));
		myTable.setPreferredSize(new Dimension(300, 100 * 9));
		addLisenter();
		myPane = new JScrollPane(myTable);
		myPane.setSize(new Dimension(300, 330));
		myPane.setPreferredSize(new Dimension(300, 330));
		this.parent = parent;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}

		});
	}

	private void addLisenter() {
		myTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
					int row = myTable.getSelectedRow();
					String value = (String) myTable.getValueAt(row, 1);
					System.out.println(value);
				}
			}
		});
	}

	public JScrollPane getJScrollPane() {
		return myPane;
	}

	public JTable getJTable() {
		return myTable;
	}

	public void open() {
//		pack();
//		this.open();
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}

	public static void main(String[] args) {
		String[] strs = new String[] { "a", "a", "a", "a", "a", "a", "a", "a" };
		TableScrollPanel tsp = new TableScrollPanel(Arrays.asList(strs));
		tsp.open();
	}
}
