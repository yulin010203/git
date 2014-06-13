package demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;

public class ListDemo {
	private Group group1;
	FillLayout fillLayout;
	private FormLayout formLayout;

	public ListDemo(Composite parent) {
		fillLayout = new FillLayout(SWT.VERTICAL);
		formLayout = new FormLayout();
		parent.setLayout(fillLayout);

		group1 = new Group(parent, SWT.NONE);
		group1.setText("List示例");
		group1.setLayout(formLayout);
		group1.setLayoutData(new GridData(GridData.FILL_BOTH));// SWT.FILL,
																// SWT.CENTER,
																// true, true,
																// 1, 1));
		createList(group1);
	}

	private void createList(Composite parent) {
		String[] itemLeft = new String[20]; // 定义保存左侧列表中的数据
		String[] itemRight = new String[0]; // 定义保存右侧列表中的数据
		// 初始化左侧字符串数组
		for (int i = 0; i < 20; i++)
			itemLeft[i] = "item" + i;
		// 定义左侧列表，可选择多个并且带有垂直滚动条
		final List left = new List(parent, SWT.MULTI | SWT.V_SCROLL);
		left.setItems(itemLeft);// 设置选项数据
		left.setToolTipText("请选择列表项");// 设置提示
		FormData dataLeft = new FormData();
		dataLeft.left = new FormAttachment(5);
		dataLeft.right = new FormAttachment(35);
		dataLeft.top = new FormAttachment(5);
		dataLeft.bottom = new FormAttachment(90);
		left.setLayoutData(dataLeft);
		// 定义右侧列表，可选择多个并且带有垂直滚动条
		final List right = new List(parent, SWT.MULTI | SWT.V_SCROLL);
		right.setItems(itemRight);
		right.setToolTipText("已选择的列表项");
		FormData dataRight = new FormData();
		dataRight.left = new FormAttachment(55);
		dataRight.right = new FormAttachment(85);
		dataRight.top = new FormAttachment(5);
		dataRight.bottom = new FormAttachment(90);
		right.setLayoutData(dataRight);
		// 创建事件监听类，为内部类
		SelectionAdapter listener = new SelectionAdapter() {
			// 按钮单击事件的处理方法
			public void widgetSelected(SelectionEvent e) {
				// 取得触发事件的控件对象
				Button b = (Button) e.widget;
				if (b.getText().equals(">"))// 如果是">"按钮
					verifyValue(left.getSelection(), left, right);
				else if (b.getText().equals(">>"))// 如果是">>"按钮
					verifyValue(left.getItems(), left, right);
				else if (b.getText().equals("<"))// 如果是"<"按钮
					verifyValue(right.getSelection(), right, left);
				else if (b.getText().equals("<<"))// 如果是"<"按钮
					verifyValue(right.getItems(), right, left);
				else if (b.getText().equals("上"))// 如果是"上"按钮
				{
					// 获得当前选中选项的索引值
					int index = right.getSelectionIndex();
					if (index <= 0)// 如果没有选中，则返回
						return;
					// 如果选中了选项值，获得当前选项的值
					String currentValue = right.getItem(index);
					// 将选中的选项与上一个选项交换值
					right.setItem(index, right.getItem(index - 1));
					right.setItem(index - 1, currentValue);
					// 设定上一个选项为选中状态
					right.setSelection(index - 1);
				} else if (b.getText().equals("下"))// 如果是"下"按钮
				{
					// 与上移按钮的逻辑相同
					int index = right.getSelectionIndex();
					if (index >= right.getItemCount() - 1)
						return;
					String currentValue = right.getItem(index);
					right.setItem(index, right.getItem(index + 1));
					right.setItem(index + 1, currentValue);
					right.setSelection(index + 1);
				}
			}

			// 改变左右列表值
			public void verifyValue(String[] select, List from, List to) {
				// 循环所有选中的选项值
				for (int i = 0; i < select.length; i++) {
					// 从一个列表中移出该选项值
					from.remove(select[i]);
					// 添加到另一个列表中
					to.add(select[i]);
				}
			}
		};
		// 定义右移按钮
		Button bt1 = new Button(parent, SWT.NONE);
		bt1.setText(">");
		// 并为按钮注册事件，其他的按钮类似
		bt1.addSelectionListener(listener);
		FormData dataBt1 = new FormData();
		dataBt1.left = new FormAttachment(42);
		dataBt1.right = new FormAttachment(50);
		dataBt1.top = new FormAttachment(15);
		dataBt1.bottom = new FormAttachment(20);
		bt1.setLayoutData(dataBt1);
		Button bt2 = new Button(parent, SWT.NONE);
		bt2.setText(">>");
		bt2.addSelectionListener(listener);
		FormData dataBt2 = new FormData();
		dataBt2.left = new FormAttachment(42);
		dataBt2.right = new FormAttachment(50);
		dataBt2.top = new FormAttachment(25);
		dataBt2.bottom = new FormAttachment(30);
		bt2.setLayoutData(dataBt2);
		Button bt3 = new Button(parent, SWT.NONE);
		bt3.setText("<<");
		bt3.addSelectionListener(listener);
		FormData dataBt3 = new FormData();
		dataBt3.left = new FormAttachment(42);
		dataBt3.right = new FormAttachment(50);
		dataBt3.top = new FormAttachment(35);
		dataBt3.bottom = new FormAttachment(40);
		bt3.setLayoutData(dataBt3);
		Button bt4 = new Button(parent, SWT.NONE);
		bt4.setText("<");
		bt4.addSelectionListener(listener);
		FormData dataBt4 = new FormData();
		dataBt4.left = new FormAttachment(42);
		dataBt4.right = new FormAttachment(50);
		dataBt4.top = new FormAttachment(45);
		dataBt4.bottom = new FormAttachment(50);
		bt4.setLayoutData(dataBt4);
		Button bt5 = new Button(parent, SWT.NONE);
		bt5.setText("上");
		bt5.addSelectionListener(listener);
		FormData dataBt5 = new FormData();
		dataBt5.left = new FormAttachment(90);
		dataBt5.right = new FormAttachment(98);
		dataBt5.top = new FormAttachment(30);
		dataBt5.bottom = new FormAttachment(40);
		bt5.setLayoutData(dataBt5);
		Button bt6 = new Button(parent, SWT.NONE);
		bt6.setText("下");
		bt6.addSelectionListener(listener);
		FormData dataBt6 = new FormData();
		dataBt6.left = new FormAttachment(90);
		dataBt6.right = new FormAttachment(98);
		dataBt6.top = new FormAttachment(60);
		dataBt6.bottom = new FormAttachment(70);
		bt6.setLayoutData(dataBt6);
	}
}