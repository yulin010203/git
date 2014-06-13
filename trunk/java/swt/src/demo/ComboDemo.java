package demo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class ComboDemo
{
 private Group group1;
 private Group group2;
 
 private GridLayout gridLayout;
 private RowLayout rowlayout;
 
 public ComboDemo(Composite parent)
 {
  gridLayout = new GridLayout();
  gridLayout.numColumns = 2;
  
  parent.setLayout(gridLayout);
  
  rowlayout  = new RowLayout(SWT.HORIZONTAL);
  
  group1 = new Group(parent, SWT.NONE);
  group1.setText("组合框1");
  group1.setLayout(rowlayout);
  group1.setLayoutData(new GridData(GridData.FILL_BOTH));
  createCombo(group1);
  
  group2 = new Group(parent, SWT.SIMPLE);
  group2.setText("组合框2");
  group2.setLayout(rowlayout);
  group2.setLayoutData(new GridData(GridData.FILL_BOTH));
  createSimpleCombo(group2);
 }
 
 private void createCombo(Composite parent)
 {
  final Combo combo = new Combo(parent, SWT.NONE);
  combo.setItems(new String[] { "选项1", "选项2", "选项3", "选项4" , "选项5", "选项6"});
  //注册键盘事件
  combo.addKeyListener(new KeyAdapter()
  {
   public void keyPressed(KeyEvent e)
   {
    // 如果单击了向左的箭头按键，则选中上一个选项
    if (e.keyCode == SWT.ARROW_LEFT)
     combo.select(combo.getSelectionIndex() - 1);
    // 如果单击了向右的箭头按键，则选中下一个选项
    else if (e.keyCode == SWT.ARROW_RIGHT)
     combo.select(combo.getSelectionIndex() + 1);
   }
  });
 }
 
 private void createSimpleCombo(Composite parent)
 {
  final Combo combo = new Combo(parent, SWT.SIMPLE);
  combo.setItems(new String[] { "选项1", "选项2", "选项3", "选项4" , "选项5", "选项6"});
  //注册键盘事件
  combo.addKeyListener(new KeyAdapter()
  {
   public void keyPressed(KeyEvent e)
   {
    // 如果单击了向左的箭头按键，则选中上一个选项
    if (e.keyCode == SWT.ARROW_LEFT)
     combo.select(combo.getSelectionIndex() - 1);
    // 如果单击了向右的箭头按键，则选中下一个选项
    else if (e.keyCode == SWT.ARROW_RIGHT)
     combo.select(combo.getSelectionIndex() + 1);
   }
  });
 }
}
 