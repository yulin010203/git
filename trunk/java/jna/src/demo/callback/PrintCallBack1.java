package demo.callback;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-5-4
 */
public interface PrintCallBack1 extends Callback {
	/**
	 * @param str
	 * @param str1
	 */
	public void print(String str, String str1);
}
