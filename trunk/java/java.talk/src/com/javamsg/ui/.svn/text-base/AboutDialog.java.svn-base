package com.javamsg.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class AboutDialog {
        
        private Shell shell;
        private final static String TITLE = "About" ;
        private final static String MESSAGE = "This is a About Dialog";
        
        public AboutDialog(Shell shell){
                this.shell = shell;
        }
        
        public void showAboutDialog(){
                MessageBox box = new MessageBox(shell, SWT.NONE);
                box.setText(TITLE);
                box.setMessage(MESSAGE);
                box.open();     
        }
        /**
         * 
         * @param args
         */
        public static void main(String[] args){
                Display display = new Display();
                Shell shell = new Shell(display);
                AboutDialog dialog = new AboutDialog(shell);
                shell.setSize(500, 500);
                shell.open();
                dialog.showAboutDialog();
        }
}