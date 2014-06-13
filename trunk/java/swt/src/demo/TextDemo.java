package demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

public class TextDemo {
	private GridLayout gridLayout;
	private GridLayout gridLayout2;
	private GridLayout gridLayout3;
	private RowLayout rowlayout;
	private FormLayout formLayout;
	private Group group1;
	private Group group2;
	private Group group3;

	public TextDemo(Composite parent) {
		gridLayout = new GridLayout();
		gridLayout.numColumns = 1;

		parent.setLayout(gridLayout);

		gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 4;

		gridLayout3 = new GridLayout();
		gridLayout3.numColumns = 3;

		rowlayout = new RowLayout(SWT.HORIZONTAL);

		group1 = new Group(parent, SWT.NONE);
		group1.setText("Texts");
		group1.setLayout(gridLayout2);
		group1.setLayoutData(new GridData(GridData.FILL_BOTH));// SWT.FILL,
																// SWT.CENTER,
																// true, true,
																// 1, 1));
		createText(group1);

		group2 = new Group(parent, SWT.NONE);
		group2.setText("NumberAndMultiLine Text");
		group2.setLayout(gridLayout3);
		group2.setLayoutData(new GridData(GridData.FILL_BOTH));// SWT.FILL,
																// SWT.CENTER,
																// true, true,
																// 1, 1));
		createNumberAndMultiLineText(group2);

		formLayout = new FormLayout();
		group3 = new Group(parent, SWT.NONE);
		group3.setText("Text程序示例");
		group3.setLayout(formLayout);
		group3.setLayoutData(new GridData(GridData.FILL_BOTH));// SWT.FILL,
																// SWT.CENTER,
																// true, true,
																// 1, 1));
		createContentText(group3);
	}

	private void createText(Composite parent) {
		Text normalText = new Text(parent, SWT.BORDER);
		normalText.setText("abc");
		normalText.setLayoutData(new GridData(GridData.FILL_BOTH));
		Text passwordText = new Text(parent, SWT.PASSWORD | SWT.BORDER);
		passwordText.setText("abc");
		passwordText.setLayoutData(new GridData(GridData.FILL_BOTH));
		Text readonlyText = new Text(parent, SWT.READ_ONLY | SWT.BORDER);
		readonlyText.setText("abc");
		readonlyText.setLayoutData(new GridData(GridData.FILL_BOTH));
		Text borderlessText = new Text(parent, SWT.NONE);
		borderlessText.setText("abc");
		borderlessText.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private void createNumberAndMultiLineText(Composite parent) {
		Text text = new Text(parent, SWT.BORDER); // 定义一个文本框
		// text.setBounds(18, 20, 153, 25); //文本框的定位
		text.setTextLimit(10); // 最多只能输入10个字符
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		// 检验监听器，每键入一个字符前都会触发
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				// 检查输入的字符(e.text)是否在0123456789这个字符串中，不在indexOf会返回-1
				boolean b = ("0123456789".indexOf(e.text) >= 0);
				e.doit = b; // doit属性如果为true，则字符允许输入。反之不允许
			}
		});

		Text text2 = new Text(parent, SWT.V_SCROLL | SWT.BORDER | SWT.H_SCROLL);
		text2.setLayoutData(new GridData(GridData.FILL_BOTH));

		Text text3 = new Text(parent, SWT.V_SCROLL | SWT.BORDER | SWT.WRAP | SWT.H_SCROLL);
		text3.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private void createContentText(Composite parent) {
		final Text content = new Text(parent, SWT.WRAP | SWT.V_SCROLL);
		FormData contentDataA = new FormData();
		contentDataA.left = new FormAttachment(5);
		contentDataA.right = new FormAttachment(95);
		contentDataA.top = new FormAttachment(5);
		contentDataA.bottom = new FormAttachment(80);
		content.setLayoutData(contentDataA);
		Button selectAll = new Button(parent, SWT.NONE);
		selectAll.setText("全选");
		FormData buttonDataselectAll = new FormData();
		buttonDataselectAll.left = new FormAttachment(5);
		buttonDataselectAll.right = new FormAttachment(15);
		buttonDataselectAll.top = new FormAttachment(85);
		buttonDataselectAll.bottom = new FormAttachment(95);
		selectAll.setLayoutData(buttonDataselectAll);
		selectAll.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 选中所有文本
				content.selectAll();
			}
		});
		Button cancel = new Button(parent, SWT.NONE);
		cancel.setText("取消全选");
		FormData buttonDataCancel = new FormData();
		buttonDataCancel.left = new FormAttachment(20);
		buttonDataCancel.right = new FormAttachment(35);
		buttonDataCancel.top = new FormAttachment(85);
		buttonDataCancel.bottom = new FormAttachment(95);
		cancel.setLayoutData(buttonDataCancel);
		cancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 如果有选中的文本
				if (content.getSelectionCount() > 0)
					content.clearSelection();// 清除选择
			}
		});
		Button copy = new Button(parent, SWT.NONE);
		copy.setText("复制");
		FormData buttonDataCopy = new FormData();
		buttonDataCopy.left = new FormAttachment(40);
		buttonDataCopy.right = new FormAttachment(55);
		buttonDataCopy.top = new FormAttachment(85);
		buttonDataCopy.bottom = new FormAttachment(95);
		copy.setLayoutData(buttonDataCopy);
		copy.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 复制到剪切板
				content.copy();
			}
		});
		Button paste = new Button(parent, SWT.NONE);
		paste.setText("粘贴");
		FormData buttonDataPaste = new FormData();
		buttonDataPaste.left = new FormAttachment(60);
		buttonDataPaste.right = new FormAttachment(70);
		buttonDataPaste.top = new FormAttachment(85);
		buttonDataPaste.bottom = new FormAttachment(95);
		paste.setLayoutData(buttonDataPaste);
		paste.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 将剪切板的内容粘贴
				content.paste();
			}
		});
	}
}