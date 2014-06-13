package demo;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/* 
 JTree的构造函数:
 JTree()
 JTree(Hashtable value)
 JTree(Object[] value)//只有这个构造函数可以创建多个根结点
 JTree(TreeModel newModel)
 JTree(TreeNode root)
 JTree(TreeNode root, boolean asksAllowsChildren)
 JTree(Vector value)

 */
public class JTreeDemo {

	private static String plaf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public static void main(String[] args) {

		// 构造函数：JTree()
		JTree example1 = new JTree();

		// 构造函数：JTree(Object[] value)
		Object[] letters = { " a ", " b ", " c ", " d ", " e " };
		JTree example2 = new JTree(letters);

		// 构造函数：JTree(TreeNode root)(TreeNode空)
		// 用空结点创建树
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(); // 定义树结点
		JTree example3 = new JTree(node1); // 用此树结点做参数调用 JTree的构造函数创建含有一个根结点的树

		// 构造函数：JTree(TreeNode root)(同上,只是TreeNode非空)
		// 用一个根结点创建树
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(" Color ");
		JTree example4 = new JTree(node2); // 结点不可以颜色,默认为白面黑字
		example4.setBackground(Color.lightGray);

		// 构造函数：JTree(TreeNode root, boolean
		// asksAllowsChildren)(同上,只是TreeNode又有不同)
		// 使用DefaultMutableTreeNode类先用一个根结点创建树，设置为可添加孩子结点,再添加孩子结点
		DefaultMutableTreeNode color = new DefaultMutableTreeNode(" Color ", true);
		DefaultMutableTreeNode gray = new DefaultMutableTreeNode(" Gray ");
		color.add(gray);
		color.add(new DefaultMutableTreeNode(" Red "));
		gray.add(new DefaultMutableTreeNode(" Lightgray "));
		gray.add(new DefaultMutableTreeNode(" Darkgray "));
		color.add(new DefaultMutableTreeNode(" Green "));
		JTree example5 = new JTree(color);

		// 构造函数：JTree(TreeNode root)(同上,只是TreeNode非空)
		// 通过逐个添加结点创建树
		DefaultMutableTreeNode biology = new DefaultMutableTreeNode(" Biology ");
		DefaultMutableTreeNode animal = new DefaultMutableTreeNode(" Animal ");
		DefaultMutableTreeNode mammal = new DefaultMutableTreeNode(" Mammal ");
		DefaultMutableTreeNode horse = new DefaultMutableTreeNode(" Horse ");
		mammal.add(horse);
		animal.add(mammal);
		biology.add(animal);
		JTree example6 = new JTree(biology);
		horse.isLeaf();
		horse.isRoot();

		// 构造函数:JTree(TreeModel newModel)
		// 用DefaultMutableTreeNodel类定义一个结点再用这个结点做参数定义一个用DefaultTreeMode
		// 创建一个树的模型,再用JTree的构造函数创建一个树

		DefaultMutableTreeNode root = new DefaultMutableTreeNode(" Root1 ");
		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode(" Child1 ");
		DefaultMutableTreeNode child11 = new DefaultMutableTreeNode(" Child11 ");
		DefaultMutableTreeNode child111 = new DefaultMutableTreeNode(" Child111 ");
		root.add(child1);
		child1.add(child11);
		child11.add(child111);

		DefaultTreeModel model = new DefaultTreeModel(root);

		JTree example7 = new JTree(model);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setPreferredSize(new Dimension(700, 400));
		panel.add(new JScrollPane(example1)); // JTree必须放在JScrollPane上
		panel.add(new JScrollPane(example2));
		panel.add(new JScrollPane(example3));
		panel.add(new JScrollPane(example4));
		panel.add(new JScrollPane(example5));
		panel.add(new JScrollPane(example6));
		panel.add(new JScrollPane(example7));

		JFrame frame = new JFrame(" JTreeDemo ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		try {
			UIManager.setLookAndFeel(plaf);
			SwingUtilities.updateComponentTreeUI(frame);
//			SwingUtilities.updateComponentTreeUI(panel);
//			SwingUtilities.updateComponentTreeUI(example1);
//			SwingUtilities.updateComponentTreeUI(example2);
//			SwingUtilities.updateComponentTreeUI(example3);
//			SwingUtilities.updateComponentTreeUI(example4);
//			SwingUtilities.updateComponentTreeUI(example5);
//			SwingUtilities.updateComponentTreeUI(example6);
//			SwingUtilities.updateComponentTreeUI(example7);
		} catch (Exception e) {
		}
		frame.pack();
		frame.show();
	}
}