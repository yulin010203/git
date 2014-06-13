package demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class SWTDemo {
	private Image image1;
	private Image image2;
	private Image image3;
	private Image image4;

	private Composite composite_button;

	private Composite composite_combox;

	private Composite composite_text;

	private Composite composite_list;

	private TabFolder tabFolder;

	private TabItem tab1TabItem;

	private TabItem tab2TabItem;

	private TabItem tab3TabItem;

	private TabItem tab4TabItem;

	private ButtonsDemo buttonsDemo;
	private ComboDemo comboDemo;
	private TextDemo textDemo;
	private ListDemo listDemo;

	public void open() {
		Display display = Display.getDefault();
		Shell demoShell = new Shell(display);
		demoShell.setText("My SWT Demo 大全");
		demoShell.setSize(400, 500);

		FillLayout shelllayout = new FillLayout(SWT.VERTICAL);
		shelllayout.marginHeight = 4;
		shelllayout.marginWidth = 6;
		shelllayout.spacing = 10;
		demoShell.setLayout(shelllayout);

		image1 = new Image(display, "./icons/0.gif");
		image2 = new Image(display, "./icons/1.gif");
		image3 = new Image(display, "./icons/2.gif");
		image4 = new Image(display, "./icons/3.gif");

		createTabFolderViewer(demoShell);
		demoShell.open();
		while (!demoShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		image1.dispose();
		image2.dispose();
		image3.dispose();
		image4.dispose();
		display.dispose();
	}

	private void createTabFolderViewer(Composite parent) {
		// 生成选项卡
		tabFolder = new TabFolder(parent, SWT.NONE);
		// 生成选项卡标签
		tab1TabItem = new TabItem(tabFolder, SWT.NONE);
		tab1TabItem.setImage(image1);
		tab1TabItem.setText("按钮例子");
		composite_button = new Composite(tabFolder, SWT.NONE);
		tab1TabItem.setControl(composite_button);
		createButtonsViewer(composite_button);
		tab2TabItem = new TabItem(tabFolder, SWT.NONE);
		tab2TabItem.setText("下拉框例子");
		tab2TabItem.setImage(image2);

		composite_combox = new Composite(tabFolder, SWT.NONE);
		tab2TabItem.setControl(composite_combox);
		createComboViewer(composite_combox);
		tab3TabItem = new TabItem(tabFolder, SWT.NONE);
		tab3TabItem.setText("文本框例子");
		tab3TabItem.setImage(image3);

		composite_text = new Composite(tabFolder, SWT.NONE);
		tab3TabItem.setControl(composite_text);
		createTextViewer(composite_text);
		tab4TabItem = new TabItem(tabFolder, SWT.NONE);
		tab4TabItem.setText("列表例子");
		tab4TabItem.setImage(image4);

		composite_list = new Composite(tabFolder, SWT.NONE);
		tab4TabItem.setControl(composite_list);
		createListViewer(composite_list);
		tabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int index = tabFolder.getSelectionIndex();
				TabItem item = tabFolder.getItem(index);
				System.out.println("The tab with name " + item.getText() + " is selected");
			}
		});
	}

	private void createButtonsViewer(Composite parent) {
		buttonsDemo = new ButtonsDemo(parent);
	}

	private void createComboViewer(Composite parent) {
		comboDemo = new ComboDemo(parent);
	}

	private void createTextViewer(Composite parent) {
		textDemo = new TextDemo(parent);
	}

	private void createListViewer(Composite parent) {
		listDemo = new ListDemo(parent);
	}

	private void createTableViewer(Composite parent) {
	}

	public static void main(String[] args) {
		SWTDemo demo = new SWTDemo();
		demo.open();
	}
}
