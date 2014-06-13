package demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class Test {

	private Display display;
	private Shell shell;

	public Test(Display display, Shell shell) {
		this.display = display;
		this.shell = shell;
		init();
	}

	private void init() {
		if (display == null || shell == null) {
			throw new NullPointerException();
		}
		shell.setText("test");
		shell.setLocation(200, 300);
		shell.setSize(300, 400);
		createButton(shell);
	}

	private void createButton(Shell shell) {
		Button b1 = new Button(shell, SWT.PUSH | SWT.FLAT);
		b1.setText("send");
		b1.setLocation(10, 20);
		b1.setSize(70, 30);

		Button button = new Button(shell, SWT.PUSH | SWT.FLAT);
		button.setText("▲");
		button.setLocation(78, 20);
		button.setSize(30, 30);

		final Menu menu = new Menu(button);
		// final Menu menu = new Menu(shell, SWT.DROP_DOWN);
		MenuItem item1 = new MenuItem(menu, SWT.CASCADE);
		item1.setText("&open");
//		MenuItem[] items = new MenuItem[5];
//		for (int i = 0; i < items.length; i++){
//			items[i] = new MenuItem(menu, SWT.CHECK);
//		}
//		int i = 0;
//		items[i++].setText("item" + i);
//		items[i++].setText("item" + i);
//		items[i++].setText("item" + i);
//		items[i++].setText("item" + i);
//		items[i++].setText("item" + i);
//		addMenuListener(items);
		menu.setVisible(false);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				menu.setVisible(true);
			}
		});
	}

	private void addMenuListener(final MenuItem[] items) {
		for (final MenuItem item : items){
			item.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (item.getSelection()){
						System.out.println(item.getText());
					}
				}
			});
		}
	}

	public void open() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		Test t = new Test(display, shell);
		t.open();
	}
}
