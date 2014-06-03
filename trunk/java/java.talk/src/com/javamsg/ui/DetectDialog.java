package com.javamsg.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.javamsg.Client;
import com.javamsg.beans.UserBean;

public class DetectDialog {
        private Display display;
        private Shell shell;
        
        private Text text ;
        
        private Client client;
        
        public DetectDialog(Display _display, Client _client){
                this.display = _display ;
                this.client = _client ;
                buildWindow();
        
        }
        
        public Shell open(){
                
                shell.setSize(200, 110);
                // shell.setMaximized(false);
                // shell.setModified(false);
                shell.open();
                return shell;
        }
        
        public void setLocation(int x, int y){
                shell.setLocation(x, y);
        }
        private void buildWindow(){

                shell = new Shell(display, SWT.CLOSE);
                shell.setText("Detect Dialog");
                GridLayout shellLayout = new GridLayout();
                shellLayout.numColumns = 1;
                shell.setLayout(shellLayout);
                
                Composite topRegion = new Composite(shell, SWT.NONE);
                topRegion.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
                GridLayout layout= new GridLayout();
                layout.numColumns = 2;
                topRegion.setLayout(layout);
                Label label = new Label(topRegion, SWT.LEFT);
                label.setText("IP Address ");   
                text = new Text(topRegion, SWT.BORDER);
                GridData gridData = new GridData();
                gridData.minimumWidth = 150 ;
                gridData.horizontalAlignment = SWT.FILL ;
                gridData.grabExcessHorizontalSpace = true ;
                gridData.horizontalAlignment = SWT.RIGHT;
                text.setLayoutData(gridData);
                // text.setSize(100, 100);
                
                Composite bottomRegion = new Composite(shell, SWT.NONE);
                bottomRegion.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false));
                GridLayout bottomLayout = new GridLayout();
                bottomLayout.numColumns = 2;
                bottomRegion.setLayout(bottomLayout);
                
                Button okButton = new Button(bottomRegion, SWT.PUSH);
                okButton.setText("Confirm");
                okButton.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e) {
                                String ip = text.getText();
                                if(!isValidIp(ip)){
                                        MessageBox box = new MessageBox(shell, SWT.OK | SWT.ICON_WARNING);
                                        box.setText("Warning!");
                                        box.setMessage("IP is invalid!");
                                        box.open();
                                        shell.close();
                                        return;
                                }
                                client.sendDetectPacket(ip);
                        }
                });
                
                Button cancelButton = new Button(bottomRegion, SWT.PUSH);
                cancelButton.setText("Cancel");
                cancelButton.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e) {
                                shell.close();
                        }
                });
                // okButton.setSize(cancelButton.getSize());
                text.setFocus();
                shell.setDefaultButton(okButton);
                
        }
        /**
         * determine ip address is valid
         * @param ipAddress
         * @return
         */
        private boolean isValidIp(String ipAddress)   
        {   
               String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";    
               Pattern pattern = Pattern.compile(ip);    
               Matcher matcher = pattern.matcher(ipAddress);    
               return matcher.matches();    
        }  
        /**
         * just for test
         * @param args
         */
        public static void main(String[] args){
                Display display = new Display();
                Client c = new Client();
                DetectDialog d = new DetectDialog(display, c);
                Shell shell  = d.open();
                while(!shell.isDisposed()){
                        if(!display.readAndDispatch())
                                display.sleep();
                }
                display.dispose();
        }
}