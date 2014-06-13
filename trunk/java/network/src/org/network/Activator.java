package org.network;

import java.awt.EventQueue;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.network.ui.LoginDialog;

public class Activator {

	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Display display = new Display();
				Shell shell = new Shell(display);
				LoginDialog login = new LoginDialog(shell);
			}
		});
	}
}
