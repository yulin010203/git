package com.javamsg.ui;

import static com.javamsg.CommandDefinition.IPMSG_FILE_DIR;
import static com.javamsg.CommandDefinition.IPMSG_FILE_REGULAR;
import static com.javamsg.CommonConstant.DIRECTORY;
import static com.javamsg.CommonConstant.MAX_ATTACHMENTS_LENGTH;
import static com.javamsg.CommonConstant.REGULAR_FILE;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.javamsg.Client;
import com.javamsg.beans.FileBean;
import com.javamsg.beans.SystemData;
import com.javamsg.beans.UserBean;
import com.javamsg.net.TcpReceiver;
import com.javamsg.net.TcpSender;
import com.javamsg.util.MyTools;

public class ChatWindow {
	private static final SystemData data = SystemData.instance();
	private static final Logger logger = Logger.getRootLogger();

	// one chat window, one user
	private UserBean user;
	private Client client;
	private TcpSender tcpSender;
	private TcpReceiver tcpReceiver;

	private FileBean[] fileAttrList;

	// private String[] fileNameList = new String[MAX_COUNT];
	// private String[] fileDirList = new String[MAX_COUNT];
	// attachments attribute {file1, type}{file2, type}{file3, type}
	private String[][] attachmentList = new String[MAX_ATTACHMENTS_LENGTH][2];
	private int attachmentCount = 0; // file + dir

	private Display display;
	private Shell shell;
	private StyledText readOnlyTextEditor; // this editor is read only, show
											// message
	private StyledText typedTextEditor; // this editor is for user input message
	private StyledText userInfoTextEditor; // this editor is for display user
											// info
	private Table fileListTable;
	// image extensions, just common extensions, if there are more, just add
	// them here
	private String[] imageExtensions = { "jpg", "jpge", "bmp", "gif", "icon" };

	public ChatWindow(Display display, UserBean user, Client client) {
		this.display = display;
		this.user = user;
		this.client = client;
		buildWindow();
	}

	public Shell open() {
		shell.setSize(600, 511);
		shell.open();
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				e.doit = close();
			}
		});
		return shell;
	}

	public boolean setFocus() {
		return shell.setFocus();
	}

	public void appendMessage(String message) {
		readOnlyTextEditor.append(message);
	}

	public void receiveAttachment(FileBean[] attachments) {
		// Show a Directory Dialog, attachments will be save to this directory
		// user choose different directory
		// for each attach, like FeiQ
		// reject others
		DirectoryDialog dirDialog = new DirectoryDialog(shell);
		// attachments will be saved to this directory
		String dirName = dirDialog.open();
		for (int i = 0; i < attachments.length; i++) {
			// build save path name
			attachments[i].setPathName(dirName + File.separator + attachments[i].getFileName());

		}
		if (dirName != null && !"".equals(dirName.trim())) {
			tcpReceiver = new TcpReceiver(user.getIpAddress(), attachments);
			Thread t = new Thread(tcpReceiver);
			t.start();
		}
	}

	/**
	 * build chat window
	 */
	private void buildWindow() {
		shell = new Shell(display);
		shell.setText(user.getNickName() + "");

		// create a menu bar
		createMenuBar();
		// create a tool bar, use for emotion icon
		// createToolBar();

		// create a popup menu
		createPopupMenu();
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		shell.setLayout(layout);
		createLeftPart();
		createRightPart();
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

		MenuItem addFileItem = new MenuItem(fileMenu, SWT.NONE);
		addFileItem.setText("Add File");
		addFileItem.setAccelerator(SWT.MOD1 + 'F');
		addFileItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// show a file dialog, let user choose a regular file to send
				FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
				String fileName = fileDialog.open();
				if (fileName != null && !"".equals(fileName.trim())) {
					// loop, find a blank row to insert the file info
					for (int i = 0; i < MAX_ATTACHMENTS_LENGTH; i++) {
						if (attachmentList[i][0] == null || "".equals(attachmentList[i][0].trim())) {
							// add file name
							attachmentList[i][0] = fileName;
							// add file type
							attachmentList[i][1] = REGULAR_FILE;
							attachmentCount++;
							TableItem fileRow;
							if (fileListTable.getItems().length <= i) {
								fileRow = new TableItem(fileListTable, SWT.NONE);
							} else {
								fileRow = fileListTable.getItem(i);
							}

							// TableItem fileRow = new TableItem(fileListTable,
							// SWT.NONE, i);
							fileRow.setText(0, fileName);
							fileRow.setText(1, REGULAR_FILE);
							return;
						}
					}
					// notify user max size of file list is MAX_COUNT
					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
					warningBox.setText("Warning!");
					warningBox.setMessage("Max size of file list is " + MAX_ATTACHMENTS_LENGTH);
					warningBox.open();
				}
			}
		});
		MenuItem addDirectoryItem = new MenuItem(fileMenu, SWT.NONE);
		addDirectoryItem.setText("Add Directory");
		addDirectoryItem.setAccelerator(SWT.MOD1 + 'D');
		addDirectoryItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// show a file dialog, let user choose a directory to send
				DirectoryDialog dirDialog = new DirectoryDialog(shell);
				String dirName = dirDialog.open();
				if (dirName != null && !"".equals(dirName.trim())) {
					for (int i = 0; i < MAX_ATTACHMENTS_LENGTH; i++) {
						if (attachmentList[i][0] == null || "".equals(attachmentList[i][0].trim())) {
							// set file name
							attachmentList[i][0] = dirName;
							// set file type
							attachmentList[i][1] = DIRECTORY;
							attachmentCount++;
							TableItem fileRow = fileListTable.getItem(i);
							// TableItem fileRow = new TableItem(fileListTable,
							// SWT.NONE, MAX_COUNT + i);
							fileRow.setText(0, dirName);
							fileRow.setText(1, DIRECTORY);
							return;
						}
					}
					// notify user max size of file list is MAX_COUNT
					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
					warningBox.setText("Warning!");
					warningBox.setMessage("Max size of Dir list is " + MAX_ATTACHMENTS_LENGTH);
					warningBox.open();
				}
			}
		});
		new MenuItem(fileMenu, SWT.SEPARATOR);
		MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
		exitItem.setText("Close");
		exitItem.setAccelerator(SWT.ESC);
		exitItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (close()) {
					shell.close();
				}
			}
		});
	}

	private void createToolsMenu(Menu menuBar) {
		MenuItem toolsItem = new MenuItem(menuBar, SWT.CASCADE);
		toolsItem.setText("Tools");
		Menu toolsMenu = new Menu(shell, SWT.DROP_DOWN);
		toolsItem.setMenu(toolsMenu);

		MenuItem refreshItem = new MenuItem(toolsMenu, SWT.NONE);
		refreshItem.setText("Insert Image");
		refreshItem.setAccelerator(SWT.MOD1 + 'I');
		refreshItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// show a file dialog, let user choose a image to send
				FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
				//
				// fileDialog.setFilterExtensions(imageExtensions);
				String fileName = fileDialog.open();
				if (fileName != null) {
					Image image = new Image(display, fileName);
					int offset = typedTextEditor.getCaretOffset();
					typedTextEditor.replaceTextRange(offset, 0, "\uFFFC"); //$NON-NLS-1$
					StyleRange style = new StyleRange();
					Rectangle rect = image.getBounds();
					style.metrics = new GlyphMetrics(rect.height, 0, rect.width);
					style.data = image;
					int[] ranges = { offset, 1 };
					StyleRange[] styles = { style };
					typedTextEditor.setStyleRanges(0, 0, ranges, styles);
				}
			}
		});
		MenuItem preferenceItem = new MenuItem(toolsMenu, SWT.NONE);
		preferenceItem.setText("Flush Record");
		preferenceItem.setAccelerator(SWT.MOD1 + 'R');
		preferenceItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// flush message record
				readOnlyTextEditor.setText("");
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
		aboutItem.setAccelerator(SWT.MOD1 + 'A');
		aboutItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AboutDialog dialog = new AboutDialog(shell);
				dialog.showAboutDialog();
			}
		});
	}

	private void createPopupMenu() {
	}

	/**
	 * create left part, left part contains two text editors and two buttons
	 */
	private void createLeftPart() {
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);

		Group sendMessageRegion = new Group(composite, SWT.NONE);
		sendMessageRegion.setText("Send Message");
		GridLayout sendMessageRegionLayout = new GridLayout();
		sendMessageRegionLayout.numColumns = 3;
		sendMessageRegion.setLayout(sendMessageRegionLayout);

		// create a styled text editor
		readOnlyTextEditor = new StyledText(sendMessageRegion, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);
		GridData gd_readOnlyTextEditor = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
		gd_readOnlyTextEditor.heightHint = 257;
		gd_readOnlyTextEditor.widthHint = 352;
		readOnlyTextEditor.setLayoutData(gd_readOnlyTextEditor);
		
		Button btnNewButton = new Button(sendMessageRegion, SWT.NONE);
		btnNewButton.setImage(new Image(display, ""));
		new Label(sendMessageRegion, SWT.NONE);
		new Label(sendMessageRegion, SWT.NONE);
		
		
		GridData textEditorData = new GridData();
		textEditorData.horizontalAlignment = SWT.FILL;
		textEditorData.horizontalSpan = 3;
		textEditorData.verticalAlignment = SWT.FILL;
		textEditorData.grabExcessVerticalSpace = true;
		textEditorData.grabExcessHorizontalSpace = true;
		textEditorData.minimumHeight = 80;
		typedTextEditor = new StyledText(sendMessageRegion, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		typedTextEditor.setLayoutData(textEditorData);
		typedTextEditor.setSize(100, 100);
		typedTextEditor.setFocus();
		Label inputInfoMessage = new Label(sendMessageRegion, SWT.NONE);
		inputInfoMessage.setText("Input Message");
		GridData labelGridData = new GridData();
		labelGridData.grabExcessVerticalSpace = false;
		labelGridData.grabExcessHorizontalSpace = true;
		inputInfoMessage.setLayoutData(labelGridData);
		final Button okButton = new Button(sendMessageRegion, SWT.PUSH);
		okButton.setText("Send");
		okButton.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.MOD1 + SWT.CR) {
					okButton.setSelection(true);
				}
			}
		});
		okButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 1) get message from typedTextEditor,
				String message = typedTextEditor.getText();
				// message is empty and no attachment
				if ("".equals(message) && attachmentCount <= 0) {
					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
					warningBox.setText("Warning!");
					warningBox.setMessage("You can't send empty message!");
					warningBox.open();
					return;
				}
				// 2) append this message to read only text editor
				if (!"".equals(message)) {
					Date now = new Date();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss ");
					String displayMessage = data.getNickName() + "  " + df.format(now) + "\n" + message + "\n";
					readOnlyTextEditor.append(displayMessage);
					readOnlyTextEditor.update();
				}
				typedTextEditor.setText("");
				typedTextEditor.update();
				if (attachmentCount > 0) {
					fileAttrList = new FileBean[MAX_ATTACHMENTS_LENGTH];
					attachmentCount = 0;
					// get attribute of the attachments
					for (int i = 0; i < MAX_ATTACHMENTS_LENGTH; i++) {
						if (attachmentList[i][0] == null || "".equals(attachmentList[i][0])) {
							// skip this attachment
							continue;
						}
						logger.info("File name : " + attachmentList[i][0]);
						File file = new File(attachmentList[i][0]);
						if (!file.exists() || !file.canRead()) {
							// this file can't access
							logger.error("File : " + attachmentList[i][0] + "can't access!");
							return;
						}
						// calculate the effective file count
						attachmentCount++;
						fileAttrList[i] = new FileBean();
						// set file number
						fileAttrList[i].setFileNo(i);
						logger.info("\t File Number : " + fileAttrList[i].getFileNo());
						// set file name
						fileAttrList[i].setFileName(file.getName());
						logger.info("\t File Name : " + fileAttrList[i].getFileName());

						// set last modified time
						fileAttrList[i].setLastModifyTime(file.lastModified());
						logger.info("\t Last Modified Time : " + fileAttrList[i].getLastModifyTime());
						// set path name, use absolute path
						fileAttrList[i].setPathName(file.getAbsolutePath());
						logger.info("\t File Absolute Path : " + fileAttrList[i].getPathName());
						// set file size, if file is directory, calculate its
						// size recursively
						fileAttrList[i].setFileSize(MyTools.calculateFileLength(file));
						logger.info("\t File Length : " + fileAttrList[i].getFileSize());
						if (file.isFile()) {
							// regular file
							fileAttrList[i].setFileAttr(IPMSG_FILE_REGULAR);
							logger.info("\t File Type : Regular File");
						} else if (file.isDirectory()) {
							// directory
							fileAttrList[i].setFileAttr(IPMSG_FILE_DIR);
							logger.info("\t File Type : Directory");
						}
					}
					if (attachmentCount > 0) {
						// receiver's connection
						tcpSender = new TcpSender(fileAttrList);
						Thread t = new Thread(tcpSender);
						t.start();
						// there are some effective attachments
						// send udp packet, tell receiver I will send attachment
						// to you
						if (!client.sendMessageWithAttachments(message, fileAttrList, attachmentCount, user.getIpAddress())) {
							// send failed
							readOnlyTextEditor.append("Your friend may be off-line!\n");
							tcpSender.stop();
							tcpSender.close();
						}
					} else {
						// there are some invalid attachments, send failed
						MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						errorBox.setText("Error!");
						errorBox.setMessage("Some Attachment Can't Access!");
						errorBox.open();
						return;
					}
					// there are some attachments

					return;
				}
				// 3) send this message to the user
				if (!client.sendMessage(message, user.getIpAddress())) {
					// send failed
					readOnlyTextEditor.append("Your friend may be off-line!\n");
				}
			}
		});
		// close button
		Button cancelButton = new Button(sendMessageRegion, SWT.PUSH);
		cancelButton.setText("Close");
		cancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (close()) {
					shell.close();
				}
			}
		});
		sendMessageRegion.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));

	}

	/**
	 * create right part, right part contains one text editor using for display
	 * user information
	 */
	private void createRightPart() {
		Group userInfoRegion = new Group(shell, SWT.NONE);
		userInfoRegion.setText("User Info");
		userInfoRegion.setLayout(new GridLayout());
		userInfoRegion.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true));
		userInfoTextEditor = new StyledText(userInfoRegion, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		userInfoTextEditor.setBackground(new Color(display, 255, 248, 220)); // #FFF8DC
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.heightHint = 288;
		gridData.minimumWidth = 150;
		userInfoTextEditor.setLayoutData(gridData);
		userInfoTextEditor.setWordWrap(true);
		StringBuffer buffer = new StringBuffer();
		buffer.append("version: ").append(user.getVersion()).append("\n");
		buffer.append("NickName: ").append(user.getNickName()).append("\n");
		buffer.append("User: ").append(user.getSender()).append("\n");
		buffer.append("Host: ").append(user.getHost()).append("\n");
		buffer.append("IP : ").append(user.getIpAddress()).append("\n");
		buffer.append("OS: ").append(user.getOperatingSystemVersion()).append("\n");
		buffer.append("Encoding: ").append(user.getEncoding()).append("\n");
		// justify font size , I think small font is better...so just keep the
		// default size
		// FontData fontData = userInfoTextEditor.getFont().getFontData()[0];
		// fontData.setHeight(fontData.getHeight());
		// userInfoTextEditor.setFont(new Font(display, fontData));
		userInfoTextEditor.setText(buffer.toString());

		fileListTable = new Table(userInfoRegion, SWT.VIRTUAL | SWT.BORDER | SWT.MULTI);
		// file name column
		TableColumn fileNameColumn = new TableColumn(fileListTable, SWT.NONE);
		fileNameColumn.setText("File Name");
		fileNameColumn.setWidth(150);
		// file type column, regular file or directory
		TableColumn fileTypeColumn = new TableColumn(fileListTable, SWT.NONE);
		fileTypeColumn.setText("File Type");
		fileTypeColumn.setWidth(0);
		// set attachment's max size
		fileListTable.setItemCount(MAX_ATTACHMENTS_LENGTH);
		fileListTable.setLinesVisible(true);
		fileListTable.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));
		Menu file_popup_menu = new Menu(shell, SWT.POP_UP);
		fileListTable.setMenu(file_popup_menu);
		MenuItem deleteItem = new MenuItem(file_popup_menu, SWT.PUSH);
		deleteItem.setText("Delete Selection");
		deleteItem.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int[] selectIndices = fileListTable.getSelectionIndices();
				if (selectIndices.length <= 0) {
					return;
				}
				fileListTable.remove(selectIndices);
				attachmentCount = attachmentCount - selectIndices.length;
				for (int i = 0; i < selectIndices.length; i++) {
					attachmentList[selectIndices[i]][0] = "";
					attachmentList[selectIndices[i]][1] = "";
				}
			}
		});
	}

	/**
	 * close this window
	 */
	public boolean close() {
		if ((tcpSender != null && tcpSender.isWorking()) || (tcpReceiver != null && tcpReceiver.isWorking())) {
			MessageBox warningBox = new MessageBox(shell, SWT.OK | SWT.CANCEL);
			warningBox.setText("Confirm!");
			warningBox.setMessage("There are some attachments not finish");
			if (warningBox.open() == SWT.CANCEL) {
				return false;
			}
		}
		if (tcpSender != null) {
			tcpSender.stop();
			tcpSender.close();
		}
		if (tcpReceiver != null) {
			tcpReceiver.stop();
			tcpReceiver.close();
		}
		return true;
	}

	/**
	 * just for test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UserBean user = new UserBean("1", "mario", "supermario", "192.168.1.234", 2425);
		user.setNickName("soyang");
		Display display = new Display();
		Client c = new Client();
		ChatWindow window = new ChatWindow(display, user, c);
		Shell shell = window.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}