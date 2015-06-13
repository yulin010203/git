package demo.callback;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-5-4
 */
public interface PrintCallBack2 extends Callback {
	/**
	 * @param str
	 * @param str1
	 * @param str2
	 */
	public void print(String str, String str1, String str2);
}
