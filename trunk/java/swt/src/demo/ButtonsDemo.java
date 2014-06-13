package demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class ButtonsDemo {

	private Composite composite_button;
	private FillLayout filllayout;
	private GridLayout gridLayout;
	private RowLayout rowlayout;
	private Group group1;
	private Group group2;
	private Group group3;
	private Group group4;
	private Group group5;
	private Group group6;

	public ButtonsDemo(Composite parent) {
		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;

		parent.setLayout(gridLayout);

		filllayout = new FillLayout(SWT.HORIZONTAL);
		rowlayout = new RowLayout(SWT.HORIZONTAL);

		group1 = new Group(parent, SWT.NONE);
		group1.setText("Buttons");
		group1.setLayout(rowlayout);
		group1.setLayoutData(new GridData(GridData.FILL_BOTH));// SWT.FILL,
																// SWT.CENTER,
																// true, true,
																// 1, 1));
		createButton(group1);

		group2 = new Group(parent, SWT.NONE);
		group2.setText("Arrow Buttons");
		group2.setLayout(rowlayout);
		group2.setLayoutData(new GridData(GridData.FILL_BOTH));// SWT.FILL,
																// SWT.CENTER,
																// true, true,
																// 1, 1));
		createArrowButton(group2);

		group3 = new Group(parent, SWT.NONE);
		group3.setText("Check Buttons");
		group3.setLayout(rowlayout);
		group3.setLayoutData(new GridData(GridData.FILL_BOTH));// SWT.FILL,
																// SWT.CENTER,
																// true, true,
																// 1, 1));
		createCheckButton(group3);

		group4 = new Group(parent, SWT.NONE);
		group4.setText("Push Buttons");
		group4.setLayout(rowlayout);
		group4.setLayoutData(new GridData(GridData.FILL_BOTH));// SWT.FILL,
																// SWT.CENTER,
																// true, true,
																// 1, 1));
		createPushButton(group4);

		group5 = new Group(parent, SWT.NONE);
		group5.setText("Radio Buttons");
		group5.setLayout(rowlayout);
		group5.setLayoutData(new GridData(GridData.FILL_BOTH));// SWT.FILL,
																// SWT.CENTER,
																// true, true,
																// 1, 1));
		createRadioButton(group5);

		group6 = new Group(parent, SWT.NONE);
		group6.setText("Toggle Buttons");
		group6.setLayout(rowlayout);
		group6.setLayoutData(new GridData(GridData.FILL_BOTH));// SWT.FILL,
																// SWT.CENTER,
																// true, true,
																// 1, 1));
		createToggleButton(group6);

	}

	private void createButton(Composite parent) {
		Button button = new Button(parent, SWT.NONE);
		button.setText("按钮 1");
		Button button_1 = new Button(parent, SWT.NONE);
		button_1.setText("按钮 2");
		Button button_2 = new Button(parent, SWT.NONE);
		button_2.setText("按钮 3");
	}

	private void createArrowButton(Composite parent) {
		Button bt1 = new Button(parent, SWT.ARROW | SWT.UP);
		bt1.setToolTipText("SWT.LEFT");
		Button bt2 = new Button(parent, SWT.ARROW | SWT.DOWN);
		bt2.setToolTipText("SWT.RIGHT");
		Button bt3 = new Button(parent, SWT.ARROW | SWT.LEFT);
		bt3.setToolTipText("SWT.CENTER");
		Button bt4 = new Button(parent, SWT.ARROW | SWT.RIGHT);
		bt4.setToolTipText("SWT.FLAT");
		Button bt5 = new Button(parent, SWT.ARROW | SWT.FLAT);
		bt5.setToolTipText("SWT.BORDER");
		Button bt6 = new Button(parent, SWT.ARROW | SWT.BORDER);
		bt6.setToolTipText("SWT.BORDER");
	}

	private void createCheckButton(Composite parent) {
		Button awtButton = new Button(parent, SWT.CHECK);
		awtButton.setText("AWT");
		Button swingButton = new Button(parent, SWT.CHECK);
		swingButton.setText("Swing");
		Button swtButton = new Button(parent, SWT.CHECK);
		swtButton.setText("SWT");
	}

	private void createPushButton(Composite parent) {
		Button bt1 = new Button(parent, SWT.PUSH | SWT.LEFT);
		// 设置文本
		bt1.setText("SWT.LEFT");
		// 设置悬浮提示
		bt1.setToolTipText("SWT.LEFT");
		Button bt2 = new Button(parent, SWT.PUSH | SWT.RIGHT);
		bt2.setText("SWT.RIGHT");
		bt2.setToolTipText("SWT.RIGHT");
		Button bt3 = new Button(parent, SWT.PUSH | SWT.CENTER);
		bt3.setText("SWT.CENTER");
		bt3.setToolTipText("SWT.CENTER");
		Button bt4 = new Button(parent, SWT.PUSH | SWT.FLAT);
		bt4.setText("SWT.FLAT");
		bt4.setToolTipText("SWT.FLAT");
		Button bt5 = new Button(parent, SWT.PUSH | SWT.BORDER);
		bt5.setText("SWT.BORDER");
		bt5.setToolTipText("SWT.BORDER");
	}

	private void createRadioButton(Composite parent) {
		// 这是第一组单选按钮
		Group group1 = new Group(parent, SWT.SHADOW_ETCHED_OUT);
		group1.setLayout(new FillLayout(SWT.VERTICAL));
		group1.setText("这是一组样式");
		Button bt1 = new Button(group1, SWT.RADIO | SWT.LEFT);
		bt1.setText("SWT.LEFT");
		bt1.setToolTipText("SWT.LEFT");
		Button bt2 = new Button(group1, SWT.RADIO | SWT.RIGHT);
		bt2.setText("SWT.RIGHT");
		bt2.setToolTipText("SWT.RIGHT");
		Button bt3 = new Button(group1, SWT.RADIO | SWT.CENTER);
		bt3.setText("SWT.CENTER");
		bt3.setToolTipText("SWT.CENTER");
		// 这是第二组单选按钮
		Group group2 = new Group(parent, SWT.SHADOW_ETCHED_OUT);
		group2.setLayout(new FillLayout(SWT.VERTICAL));
		group2.setText("这是另一组样式");
		Button bt4 = new Button(group2, SWT.RADIO | SWT.FLAT);
		bt4.setText("SWT.FLAT");
		bt4.setToolTipText("SWT.FLAT");
		bt4.setSelection(true);
		Button bt5 = new Button(group2, SWT.RADIO | SWT.BORDER);
		bt5.setText("SWT.BORDER");
		bt5.setToolTipText("SWT.BORDER");
		Button bt6 = new Button(group2, SWT.RADIO);
		bt6.setText("SWT.RADIO");
		bt6.setToolTipText("SWT.RADIO");
	}

	private void createToggleButton(Composite parent) {
		Button bt1 = new Button(parent, SWT.TOGGLE | SWT.LEFT);
		bt1.setText("SWT.LEFT");
		bt1.setToolTipText("SWT.LEFT");
		final Button bt2 = new Button(parent, SWT.TOGGLE | SWT.FLAT);
		bt2.setText("▲");
		bt2.setToolTipText("SWT.FLAT");
		bt2.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (bt2.getSelection()){
					System.out.println("select");
				} else {
					System.out.println("unselect");
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		Button bt3 = new Button(parent, SWT.TOGGLE | SWT.BORDER);
		bt3.setText("SWT.BORDER");
		bt3.setToolTipText("SWT.BORDER");
	}
}
