package demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TreeAdapter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ToolBar;

public class TreeSWT {

	public static void main(String[] args) {
		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("tree test");
		shell.setLocation(200, 200);
		shell.setSize(300, 400);
		// ColorDialog cd = new ColorDialog(shell, SWT.NONE);
		// cd.setText("color test");
		// RGB open = cd.open();
		// System.out.println(open);
		// FontDialog fd = new FontDialog(shell, SWT.NONE);
		// fd.setText("font test");
		// FontData open = fd.open();
		// System.out.println(open);
		// DirectoryDialog dd = new DirectoryDialog(shell);
		// dd.setText("directory test");
		// String open = dd.open();
		// System.out.println(open);
		// FileDialog fd = new FileDialog(shell, SWT.OPEN);
		// fd.setText("file test");
		// fd.setFilterExtensions(new String[]{"csv", "jpg"});
		// fd.open();
//		final Tree tree = new Tree(shell, SWT.SINGLE);
//		tree.setLocation(0, 0);
//		tree.setSize(300, 100);
//		TreeItem tree1 = new TreeItem(tree, SWT.NO);
//		tree1.setText("tree1");
//		tree1.addListener(SWT.MouseDoubleClick, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				System.out.println("select " + ((TreeItem) event.item).getText());
//			}
//		});
//		TreeItem tree11 = new TreeItem(tree1, SWT.NO);
//		tree11.setText("tree11");
//		tree11.addListener(SWT.MouseDoubleClick, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				System.out.println("select " + ((TreeItem) event.item).getText());
//			}
//		});
//		TreeItem tree12 = new TreeItem(tree1, SWT.NO);
//		tree12.setText("tree12");
//		tree12.addListener(SWT.MouseDoubleClick, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				System.out.println("select " + ((TreeItem) event.item).getText());
//			}
//		});
//		TreeItem tree13 = new TreeItem(tree1, SWT.NO);
//		tree13.setText("tree13");
//		tree13.addListener(SWT.MouseDoubleClick, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				System.out.println("select " + ((TreeItem) event.item).getText());
//			}
//		});
//		TreeItem tree2 = new TreeItem(tree, SWT.NO);
//		tree2.setText("tree2");
//		
//		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
//		toolBar.setBounds(32, 206, 97, 25);
//		tree.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				TreeItem[] items = tree.getSelection();
//				System.out.println(items[0].getData());
//			}
//			
//		});
		ToolBar tb = new ToolBar(shell, SWT.WRAP);
		tb.setSize(300, 200);
		final Button button = new Button(tb, SWT.TOGGLE);
		button.setSize(80, 50);
//		button.setText("button");
		button.setLocation(10, 10);
		button.addMouseTrackListener(new MouseTrackListener() {
			
			@Override
			public void mouseHover(MouseEvent e) {
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
			}
		});
		ToolItem ti = new ToolItem(tb, SWT.WRAP);
		ti.setControl(button);
		ti.setWidth(80);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
