package com.javamsg.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

import com.javamsg.Client;
import com.javamsg.CommonConstant;
import com.javamsg.IEventReceiver;
import com.javamsg.Observer;
import com.javamsg.beans.FileBean;
import com.javamsg.beans.UserBean;
import com.javamsg.beans.UserList;

public class MainWindow implements Observer, IEventReceiver {

	private static final Logger logger = Logger.getRootLogger();

	private Display display;
	private Shell shell;
	private Table userListTable;

	// private ChatWindow chatWindow;
	private Map<String, ChatWindow> chatWindows = new HashMap<String, ChatWindow>();

	private UserList userList = UserList.instance();

	private Client client;

	private static final String[] columnNames = { "icon", "nickName", "ipAddress", "userName", "hostName", };

	public MainWindow() {
		init();
	}

	public void update() {
		fillUserListTable();
	}

	public void receiveMessage(final UserBean user, final String message) {
		if (user == null) {
			return;
		}
		final Display display = Display.getDefault();
		if (!display.isDisposed()) {
			Runnable runnable = new Runnable() {
				public void run() {
					appendMessage(user, message);
				}
			};
			display.syncExec(runnable); // when update SWT UI, this method call
										// is required
		}
	}

	/**
	 * receive attachment
	 */
	public void receiveAttachment(final UserBean user, final String message, final FileBean[] files) {
		if (user == null || files == null) {
			return;
		}
		final Display display = Display.getDefault();
		if (!display.isDisposed()) {
			Runnable runnable = new Runnable() {
				public void run() {
					appendMessageWithAttachments(user, message, files);

				}
			};
			display.syncExec(runnable); // when update SWT UI, this method call
										// is required
		}
	}

	public void register() {
		userList.registerObserver(this);
		UserBean user1 = new UserBean("1", "mario", "supermario", "192.168.1.234", 2425);
		user1.setNickName("soyang");
		UserBean user2 = new UserBean("1", "yanni", "yuyan", "192.168.1.12", 2425);
		user2.setNickName("yanni");
		userList.addUser(user1);
		userList.addUser(user2);
	}

	public Shell open(Display _display) {
		display = _display;
		shell = new Shell(_display);
		shell.setText("JavaMSG");
		shell.setImage(new Image(display, CommonConstant.SYSTEM_ICON));
		shell.setLayout(new FillLayout());
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				e.doit = exitJavaMsg();
			}
		});
		// create a menu bar
		createMenuBar();
		createTray();

		userListTable = new Table(shell, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
		// userListTable.setHeaderVisible(true);
		// userListTable.setLinesVisible(true);
		userListTable.setMenu(createPopupMenu());
		userListTable.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// send message to this user
				int index = userListTable.getSelectionIndex();
				if (index != -1) {
					String ip = userListTable.getItem(index).getText(2);
					if (chatWindows.containsKey(ip)) {
						// this chat window is open
						// let it get focus
						ChatWindow window = chatWindows.get(ip);
						window.setFocus();
						return;
					}
					UserBean user = userList.getUserByIpAddress(ip);
					if (user == null) {
						return;
					}
					logger.debug("user nick name is " + user.getNickName());
					ChatWindow chatWindow = new ChatWindow(display, user, client);
					chatWindow.open();
					chatWindows.put(ip, chatWindow);
				}
			}
		});

		// ICON column
		TableColumn iconColumn = new TableColumn(userListTable, SWT.NONE);
		iconColumn.setText(columnNames[0]);
		iconColumn.setWidth(50);
		// nickName
		TableColumn nickNameColumn = new TableColumn(userListTable, SWT.NONE);
		nickNameColumn.setText(columnNames[1]);
		nickNameColumn.setWidth(70);
		// ipAddress
		TableColumn ipAddressColumn = new TableColumn(userListTable, SWT.NONE);
		ipAddressColumn.setText(columnNames[2]);
		ipAddressColumn.setWidth(120);
		// userName
		TableColumn userNameColumn = new TableColumn(userListTable, SWT.NONE);
		userNameColumn.setText(columnNames[3]);
		userNameColumn.setWidth(0);
		// hostName
		TableColumn hostNameColumn = new TableColumn(userListTable, SWT.NONE);
		hostNameColumn.setText(columnNames[4]);
		hostNameColumn.setWidth(0);

		// fillUserListTable();
		// ---------------------------------------------
		shell.setSize(userListTable.computeSize(SWT.DEFAULT, SWT.DEFAULT).x, 500);
		shell.setLocation(900, 100);
		shell.open();
		return shell;
	}

	private void createTray() {
		final Tray tray = display.getSystemTray();
		final TrayItem trayItem = new TrayItem(tray, SWT.NONE);

		// 程序启动时，窗口是显示的，所以系统栏图标隐藏
		trayItem.setVisible(false);
		trayItem.setToolTipText(shell.getText());

		trayItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				trayDisplay(shell, tray);
			}
		});

		createTrayMenuBar(tray, trayItem);
		trayItem.setImage(shell.getImage());
		// 注册窗口事件监听器
		shell.addShellListener(new ShellAdapter() {

			// 点击窗口最小化按钮时，窗口隐藏，系统栏显示图标
			public void shellIconified(ShellEvent e) {
				trayDisplay(shell, tray);
			}

			// 点击窗口关闭按钮时，并不终止程序，而时隐藏窗口，同时系统栏显示图标
			public void shellClosed(ShellEvent e) {
			}
		});
	}

	private void createTrayMenuBar(final Tray tray, TrayItem trayItem) {
		final Menu trayMenu = new Menu(shell, SWT.POP_UP);
		MenuItem showMenuItem = new MenuItem(trayMenu, SWT.PUSH);
		showMenuItem.setText("显示窗口(&s)");

		// 显示窗口，并隐藏系统栏中的图标
		showMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				trayDisplay(shell, tray);
			}
		});

		trayMenu.setDefaultItem(showMenuItem);

		new MenuItem(trayMenu, SWT.SEPARATOR);

		// 系统栏中的退出菜单，程序只能通过这个菜单退出
		MenuItem exitMenuItem = new MenuItem(trayMenu, SWT.PUSH);
		exitMenuItem.setText("退出程序(&x)");

		exitMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				client.stopReceiving();
				shell.close();
			}
		});
		trayItem.addMenuDetectListener(new MenuDetectListener() {
			public void menuDetected(MenuDetectEvent e) {
				trayMenu.setVisible(true);
			}
		});
	}

	private void trayDisplay(Shell shell, Tray tray) {
		try {
			shell.setVisible(!shell.isVisible());
			tray.getItem(0).setVisible(!shell.isVisible());
			if (shell.getVisible()) {
				shell.setMinimized(false);
				shell.setActive();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean exitJavaMsg() {
		// show a dialog with OK_CANCLE
		MessageBox confirmBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK | SWT.CANCEL);
		confirmBox.setText("Confirm");
		confirmBox.setMessage("Are your sure to exit?");
		if (SWT.OK == confirmBox.open()) {
			if (client != null) {
				client.close();
			}
			return true;
		}
		return false;
	}

	private Menu createMenuBar() {
		Menu menuBar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuBar);

		createFileMenu(menuBar);
		createToolsMenu(menuBar);
		createHelpMenu(menuBar);

		return menuBar;
	}

	private void createFileMenu(Menu menuBar) {
		MenuItem fileItem = new MenuItem(menuBar, SWT.CASCADE);
		fileItem.setText("File");
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(fileMenu);

		MenuItem detectItem = new MenuItem(fileMenu, SWT.NONE);
		detectItem.setText("Detect");
		detectItem.setAccelerator(SWT.MOD1 + 'D');
		detectItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DetectDialog detectDialog = new DetectDialog(display, client);
				detectDialog.setLocation(shell.getLocation().x + 10, shell.getLocation().y + 80);
				detectDialog.open();
			}
		});
		MenuItem findItem = new MenuItem(fileMenu, SWT.NONE);
		findItem.setText("Find");
		findItem.setAccelerator(SWT.MOD1 + 'F');
		findItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			}
		});
		new MenuItem(fileMenu, SWT.SEPARATOR);
		MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
		exitItem.setText("Exit");
		exitItem.setAccelerator(SWT.MOD1 + 'E');
		exitItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				client.stopReceiving();
				shell.close();
			}
		});
	}

	private void createToolsMenu(Menu menuBar) {
		MenuItem toolsItem = new MenuItem(menuBar, SWT.CASCADE);
		toolsItem.setText("Tools");
		Menu toolsMenu = new Menu(shell, SWT.DROP_DOWN);
		toolsItem.setMenu(toolsMenu);

		MenuItem refreshItem = new MenuItem(toolsMenu, SWT.NONE);
		refreshItem.setText("Refresh");
		refreshItem.setAccelerator(SWT.F5);
		refreshItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// Refresh the user list
				client.entry();
			}
		});
		MenuItem preferenceItem = new MenuItem(toolsMenu, SWT.NONE);
		preferenceItem.setText("Preference");
		preferenceItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			}
		});

	}

	private void createHelpMenu(Menu menuBar) {
		MenuItem helpItem = new MenuItem(menuBar, SWT.CASCADE);
		helpItem.setText("Help");
		Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
		helpItem.setMenu(helpMenu);

		MenuItem aboutItem = new MenuItem(helpMenu, SWT.NONE);
		aboutItem.setText("About");
		aboutItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AboutDialog dialog = new AboutDialog(shell);
				dialog.showAboutDialog();
			}
		});
	}

	private Menu createPopupMenu() {
		Menu popupMenu = new Menu(shell, SWT.POP_UP);
		userListTable.setMenu(popupMenu);
		MenuItem sendMessageItem = new MenuItem(popupMenu, SWT.PUSH);
		sendMessageItem.setText("Send Message");
		sendMessageItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// send message to this user
				int index = userListTable.getSelectionIndex();
				if (index != -1) {
					String ip = userListTable.getItem(index).getText(2);
					if (chatWindows.containsKey(ip)) {
						// this chat window is open
						// let it get focus
						ChatWindow window = chatWindows.get(ip);
						window.setFocus();
						return;
					}
					UserBean user = userList.getUserByIpAddress(ip);
					if (user == null) {
						return;
					}
					ChatWindow chatWindow = new ChatWindow(display, user, client);
					chatWindow.open();
					chatWindows.put(ip, chatWindow);
				}
			}
		});
		MenuItem modifyInformationItem = new MenuItem(popupMenu, SWT.PUSH);
		modifyInformationItem.setText("Modify Info");
		modifyInformationItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			}
		});
		MenuItem deleteItem = new MenuItem(popupMenu, SWT.PUSH);
		deleteItem.setText("Delete");
		deleteItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int index = userListTable.getSelectionIndex();
				if (index == -1) {
					// no user selected
					return;
				}
				// Delete this user, before delete show a confirm dialog
				MessageBox confirmBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK | SWT.CANCEL);
				confirmBox.setText("Confirm");
				confirmBox.setMessage("Are your sure to delete this user?");
				if (SWT.OK == confirmBox.open()) {
					String ip = userListTable.getItem(index).getText(2);
					UserBean user = userList.getUserByIpAddress(ip);
					userList.removeUser(user);
				}
			}
		});

		return popupMenu;
	}

	private void fillUserListTable() {
		final Display display = Display.getDefault();
		if (!display.isDisposed()) {
			Runnable runnable = new Runnable() {
				public void run() {
					// userListTable.clearAll();
					userListTable.removeAll();
					for (int i = 0; i < userList.getUserCount(); i++) {
						TableItem item = new TableItem(userListTable, SWT.NONE);
						UserBean user = userList.getUserByIndex(i);
						// item.setImage(0, new Image(display,
						// "D:\\icon-qq.png"));
						// item.setImage(0, new Image(display,
						// "/home/mario/icon-qq.png"));
						// item.setImage(0, new Image(display,
						// "resource/icons/qq.jpg"));
						item.setImage(shell.getImage());
						item.setText(1, user.getNickName() + "");
						item.setText(2, user.getIpAddress());
						item.setText(3, user.getSender());
						item.setText(4, user.getHost());
					}
				}
			};
			display.syncExec(runnable); // when update SWT UI, this method call
										// is required
		}
	}

	/**
	 * append message in the chat window
	 * 
	 * @param user
	 * @param message
	 */
	private ChatWindow appendMessage(UserBean user, String message) {
		Display display = Display.getDefault();
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss ");
		String displayDate = df.format(now);
		String nickName = user.getNickName();
		nickName = nickName.substring(0, nickName.length() - 2);
		String displayMessage = nickName + "   " + displayDate + "\n" + message + "\n";
		ChatWindow window = chatWindows.get(user.getIpAddress());
		if (window == null) {
			// this window is not open
			window = new ChatWindow(display, user, client);
			window.open();
			chatWindows.put(user.getIpAddress(), window);
		}
		window.setFocus();
		window.appendMessage(displayMessage);
		return window;
	}

	private void appendMessageWithAttachments(UserBean user, String message, FileBean[] attachments) {
		ChatWindow window = appendMessage(user, message);
		window.receiveAttachment(attachments);

	}

	private void init() {

		client = new Client();
		client.setEventReceiver(this);

	}

	public void startWork() {
		client.startListeningUdpCommand();
		client.entry();
	}

	public static void main(String[] args) {

		MainWindow window = new MainWindow();
		Display display = new Display();
		Shell shell = window.open(display);
		window.register();
		window.startWork();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
