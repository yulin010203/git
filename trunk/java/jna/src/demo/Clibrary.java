package demo;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;

import demo.call.DataField;
import demo.callback.PrintCallBack;
import demo.callback.PrintCallBack1;
import demo.callback.PrintCallBack2;

/**
 * @author 陈霖 2015-5-2
 */
public interface Clibrary extends Library {

	/**
	 * 
	 */
	public static String PATH = System.getProperty("user.dir");

	/**
	 * 实例
	 */
	Clibrary INSTANCE = (Clibrary) Native.loadLibrary(PATH + "\\lib\\jna", Clibrary.class);

	// Clibrary INSTANCE = (Clibrary) Native.loadLibrary("jna", Clibrary.class);

	/**
	 * @param call
	 * @param call1
	 * @param call2
	 */
	// public void registerFun(PrintCallBack call, PrintCallBack1 call1, PrintCallBack2 call2);
	public void registerFun(Callback call, Callback call1, Callback call2);

	/**
	 * @param call
	 */
	// public void registerFun(Callback call);
	public void registerFun1(Callback call);

	/**
	 * @param str
	 * @param str1
	 * @param str2
	 */
	public void output(String str, String str1, String str2);

	/**
	 * @param data
	 */
	public void output1(DataField.ByReference data);

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	public int add(int a, int b);
}
