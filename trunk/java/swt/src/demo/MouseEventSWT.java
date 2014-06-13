package demo;

import javax.swing.JToggleButton;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MouseEventSWT {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("MouseEvent");
		shell.setSize(300, 150);
		shell.setLocation(300, 150);
//		final Button button = new Button(shell, SWT.NONE);
//		button.setSize(250, 80);
//		button.setLocation(25, 5);
//		button.addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseUp(MouseEvent e) {
//				switch (e.button) {
//				case 1:
//					System.out.println("up left");
//					break;
//				case 2:
//					System.out.println("up center");
//					break;
//				case 3:
//					System.out.println("up right");
//					break;
//				}
//			}
//
//			@Override
//			public void mouseDown(MouseEvent e) {
//				switch (e.button) {
//				case 1:
//					System.out.println("down left");
//					break;
//				case 2:
//					System.out.println("down center");
//					break;
//				case 3:
//					System.out.println("down right");
//					break;
//				}
//			}
//
//			@Override
//			public void mouseDoubleClick(MouseEvent e) {
//				switch (e.button) {
//				case 1:
//					System.out.println("click left");
//					break;
//				case 2:
//					System.out.println("click center");
//					break;
//				case 3:
//					System.out.println("click right");
//					break;
//				}
//			}
//		});
//		button.addMouseMoveListener(new MouseMoveListener() {
//
//			@Override
//			public void mouseMove(MouseEvent e) {
//				// System.out.println(e.x + " " + e.y);
//			}
//		});
//		button.addMouseTrackListener(new MouseTrackListener() {
//
//			@Override
//			public void mouseHover(MouseEvent e) {
//				button.setToolTipText("hover");
//			}
//
//			@Override
//			public void mouseExit(MouseEvent e) {
//				System.out.println("exit");
//			}
//
//			@Override
//			public void mouseEnter(MouseEvent e) {
//				System.out.println("enter");
//			}
//		});
//		JToggleButton 
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
