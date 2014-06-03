package com.javamsg.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class PreferenceDialog {
        private final static String TITLE = "Preference Dialog";
        
        private final static String DEFAULT_ICON = "icon-qq.png";
        
        private final static String[] menuString = {"Person Info", "System Setting"};
        
        private Shell shell;
        private Display display;
        
        private Image avatarIcon;
        public PreferenceDialog(Shell shell, Display display){
        	this.shell = shell;
        	this.display = display;
        	buildWindow();
        }
        private void buildWindow(){
                GridLayout layout = new GridLayout();
                layout.numColumns = 2;
                shell.setLayout(layout);
                createLeftPart();
                createRightPart();
                createBottomPart();
        }
        
        private void createLeftPart(){
                Composite composite = new Composite(shell, SWT.NONE);
                GridData compositeGridData = new GridData(SWT.LEFT, SWT.FILL, false, true);
                compositeGridData.minimumWidth = 200;
                composite.setLayoutData(compositeGridData);
                GridLayout layout= new GridLayout();
                layout.numColumns = 1;
                composite.setLayout(layout);
                // top for image label
                Button imageButton = new Button(composite, SWT.PUSH);
                GridData imageData = new GridData(SWT.FILL, SWT.TOP, true, false);
                imageData.minimumHeight = 100 ;
                imageButton.setLayoutData(imageData);
                avatarIcon = new Image(display, DEFAULT_ICON );
                imageButton.setImage(avatarIcon);
                imageButton.setToolTipText("Change Icon...");
                imageButton.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e) {
                        }
                });
                Button changeImageButton = new Button(composite, SWT.PUSH);
                changeImageButton.setText("Change Icon");
                // bottom for setting menu
                List menuList = new List(composite, SWT.SINGLE | SWT.BORDER);
                menuList.setItems(menuString);
                menuList.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e) {
                        }
                });
                
        }
        private void createRightPart(){
                Composite composite = new Composite(shell, SWT.NONE);
                GridData compositeGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
                composite.setLayoutData(compositeGridData);
                GridLayout layout= new GridLayout();
                layout.numColumns = 1;
                composite.setLayout(layout);
                OptionComposite defaultComposite = new PersonInfoComposite();
                defaultComposite.buildComposite(composite);
        }
        private void createBottomPart(){
                Composite composite = new Composite(shell, SWT.NONE);
                GridData gridData = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
                composite.setLayoutData(gridData);
                GridLayout layout = new GridLayout();
                layout.numColumns = 4;
                
                Label infoLabel = new Label(composite, SWT.NONE);
                infoLabel.setText(" ");
                GridData labelGridData = new GridData();
                labelGridData.grabExcessVerticalSpace = false;
                labelGridData.grabExcessHorizontalSpace = true;
                infoLabel.setLayoutData(labelGridData);
                
                Button okButton = new Button(composite, SWT.PUSH);
                okButton.setText("OK");
                okButton.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e) {
                        }
                });
                Button cancelButton = new Button(composite, SWT.PUSH);
                cancelButton.setText("Cancel");
                cancelButton.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e) {
                        }
                });
                Button applyButton = new Button(composite, SWT.PUSH);
                applyButton.setText("Apply");
                applyButton.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e) {
                        }
                });
        }
        
        
        public interface OptionComposite{
                public void buildComposite(Composite composite);
        }
        
        private class PersonInfoComposite implements OptionComposite{

                @Override
                public void buildComposite(Composite composite) {
                }
        }
        private class SystemSettingComposite implements OptionComposite{

                @Override
                public void buildComposite(Composite composite) {
                }
        }
        
        public static void main(String[] args) {
			PreferenceDialog pd = new PreferenceDialog(new Shell(), new Display());
		}
}