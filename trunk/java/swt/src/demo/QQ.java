package demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class QQ {

	private Text introText;
	private Combo constellationCombo;
	private Combo jobCombo;
	private Combo animalCombo;
	private Combo bloodCombo;
	private Text schoolText;
	private Text frontPageText;
	private Text oleText;
	private Text nameText;
	private Combo sexCombo;
	private Canvas rank;
	private Text attachName;
	private Text nickName;
	private Text text;
	private List selectList;
	private StackLayout stackLayout = new StackLayout();
	private Composite composite;
	private Composite composite_1;
	private Composite rightComp;
	private static Image image;
	private static Image rankimage;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			QQ window = new QQ();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window
	 */
	public void open() {
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(103, 172, 231));
		shell.setLayout(new GridLayout());
		shell.setSize(500, 400);
		shell.setLocation(262, 184);
		shell.setText("QQ2006设置");
		//

		shell.open();

		final SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setForeground(SWTResourceManager.getColor(103, 172, 231));
		sashForm.setLayoutData(new GridData(GridData.FILL_BOTH));
		selectList = new List(sashForm, SWT.NONE);
		selectList.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent arg0) {
				int selectionIndex = selectList.getSelectionIndex();
				if (selectionIndex == 0)
					stackLayout.topControl = composite;
				else
					stackLayout.topControl = composite_1;
				rightComp.layout();
			}
		});
		selectList.setItems(new String[] { "个人资料", "联系方式" });

		rightComp = new Composite(sashForm, SWT.NONE);
		rightComp.setBackground(SWTResourceManager.getColor(23, 255, 255));

		rightComp.setLayout(stackLayout);

		composite = new Composite(rightComp, SWT.BORDER);
		composite.setForeground(SWTResourceManager.getColor(0, 0, 255));
		composite.setBackground(SWTResourceManager.getColor(255, 255, 255));
		GridLayout gridLayout = new GridLayout(6, false);
		gridLayout.horizontalSpacing = 10;
		composite.setLayout(gridLayout);
		final Label titleLabel = new Label(composite, SWT.NONE);
		titleLabel.setForeground(SWTResourceManager.getColor(1, 25, 97));
		titleLabel.setBackground(SWTResourceManager.getColor(229, 241, 253));
		titleLabel.setFont(SWTResourceManager.getFont("", 11, SWT.BOLD));
		titleLabel.setText("个人设置－个人资料");
		GridData gridData3 = new GridData(GridData.FILL_HORIZONTAL);
		gridData3.horizontalSpan = 6;
		titleLabel.setLayoutData(gridData3);
		final Label label1 = new Label(composite, SWT.NONE);
		label1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label1.setText("用户号码:");
		text = new Text(composite, SWT.READ_ONLY | SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(255, 255, 255));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		text.setLayoutData(gridData);
		final Composite photoComp = new Composite(composite, SWT.NONE);
		photoComp.setBackground(SWTResourceManager.getColor(255, 255, 255));
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
		gridData.horizontalSpan = 2;
		gridData.verticalSpan = 2;
		photoComp.setLayoutData(gridData);
		photoComp.setLayout(new GridLayout(2, false));

		final Composite tempComp = new Composite(photoComp, SWT.BORDER);
		tempComp.setLayout(new FillLayout());
		tempComp.setLayoutData(new GridData(30, 30));

		final Canvas canvas = new Canvas(tempComp, SWT.NONE);
		image = new Image(display, "./icons/qq.png");
		canvas.redraw();
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent event) {
				if (image != null)
					event.gc.drawImage(image, 0, 0);
			}
		});

		final Button setPhotoButton = new Button(photoComp, SWT.DOWN);
		setPhotoButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_END));
		setPhotoButton.setText("更改");

		final Label label2 = new Label(composite, SWT.NONE);
		label2.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label2.setText("用户昵称:");

		nickName = new Text(composite, SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		nickName.setLayoutData(gridData);

		final Label label3 = new Label(composite, SWT.NONE);
		label3.setBackground(SWTResourceManager.getColor(255, 255, 255));
		gridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		label3.setLayoutData(gridData);
		label3.setText("个性签名:");

		attachName = new Text(composite, SWT.BORDER);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 5;
		gridData.verticalSpan = 2;
		attachName.setLayoutData(gridData);
		final Label label22 = new Label(composite, SWT.NONE);
		final Label labe24 = new Label(composite, SWT.NONE);
		labe24.setBackground(SWTResourceManager.getColor(255, 255, 255));
		labe24.setText("会员阶段:");
		final Label labe23 = new Label(composite, SWT.NONE);
		final Label label4 = new Label(composite, SWT.NONE);
		label4.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label4.setText("QQ等级:");

		rank = new Canvas(composite, SWT.NONE);
		rank.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent event) {
				event.gc.drawImage(rankimage, 0, 0);
			}
		});
		rank.setBackground(SWTResourceManager.getColor(255, 255, 255));
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gridData.heightHint = 17;
		rank.setLayoutData(gridData);
		rankimage = new Image(display, "./icons/sun.png");
		rank.redraw();

		final Label label5 = new Label(composite, SWT.NONE);
		label5.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label5.setLayoutData(new GridData());
		label5.setText("性    别:");

		sexCombo = new Combo(composite, SWT.NONE);
		sexCombo.setItems(new String[] { "男", "女" });
		sexCombo.setText("男");

		final Label label6 = new Label(composite, SWT.NONE);
		label6.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label6.setText("姓名:");

		nameText = new Text(composite, SWT.BORDER);
		nameText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		final Label label7 = new Label(composite, SWT.NONE);
		label7.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label7.setText("年龄:");

		oleText = new Text(composite, SWT.BORDER);
		oleText.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		final Label label8 = new Label(composite, SWT.NONE);
		label8.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label8.setText("毕业院校:");

		schoolText = new Text(composite, SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		schoolText.setLayoutData(gridData);

		final Label label9 = new Label(composite, SWT.NONE);
		label9.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label9.setText("生肖:");

		animalCombo = new Combo(composite, SWT.NONE);
		animalCombo.setItems(new String[] { "猴", "鸡", "狗" });
		animalCombo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		animalCombo.setText("鸡");

		final Label label10 = new Label(composite, SWT.NONE);
		label10.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label10.setText("职    业:");

		jobCombo = new Combo(composite, SWT.BORDER);
		jobCombo.setItems(new String[] { "计算机业", "学生" });
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		jobCombo.setLayoutData(gridData);
		jobCombo.setText("计算机业");

		final Label label11 = new Label(composite, SWT.NONE);
		label11.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label11.setText("星座:");

		constellationCombo = new Combo(composite, SWT.NONE);
		constellationCombo.setItems(new String[] { "牧羊座", "金牛座" });
		constellationCombo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		constellationCombo.setText("牧羊座");

		final Label label28 = new Label(composite, SWT.NONE);
		label28.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label28.setText("个人主页:");

		frontPageText = new Text(composite, SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		frontPageText.setLayoutData(gridData);

		final Label label29 = new Label(composite, SWT.NONE);
		label29.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label29.setText("血型:");

		bloodCombo = new Combo(composite, SWT.NONE);
		bloodCombo.setItems(new String[] { "A型", "B型", "O型", "AB型", "其他" });
		bloodCombo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		bloodCombo.setText("O型");

		final Label introLabel = new Label(composite, SWT.NONE);
		introLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		introLabel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		introLabel.setText("个人说明:");

		introText = new Text(composite, SWT.WRAP | SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_VERTICAL);
		gridData.horizontalSpan = 5;
		introText.setLayoutData(gridData);

		composite_1 = new Composite(rightComp, SWT.NONE);
		composite_1.setLayout(new FillLayout());

		final Label label = new Label(composite_1, SWT.NONE);
		label.setText("联系方式面板");
		stackLayout.topControl = composite;

		final Composite buttonComp = new Composite(shell, SWT.NONE);
		buttonComp.setBackground(SWTResourceManager.getColor(107, 172, 231));
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		buttonComp.setLayoutData(gridData);
		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 15;
		buttonComp.setLayout(rowLayout);

		final Button button = new Button(buttonComp, SWT.NONE);
		button.setForeground(SWTResourceManager.getColor(0, 255, 255));
		button.setBackground(SWTResourceManager.getColor(182, 222, 255));
		button.setText("   确定   ");

		final Button button_1 = new Button(buttonComp, SWT.NONE);
		button_1.setText("   取消   ");

		final Button button_2 = new Button(buttonComp, SWT.NONE);
		button_2.setText("   应用   ");
		sashForm.setWeights(new int[] { 1, 4 });
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}