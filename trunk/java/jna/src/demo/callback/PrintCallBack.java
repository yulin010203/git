package demo.callback;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-5-4
 */
public interface PrintCallBack extends Callback {
	/**
	 * @param str
	 */
	public void print(String str);
}
