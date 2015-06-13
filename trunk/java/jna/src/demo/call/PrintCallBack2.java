package demo.call;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-5-4
 */
public class PrintCallBack2 implements Callback {
	CallBack2 call;

	/**
	 * @param call
	 */
	public PrintCallBack2(CallBack2 call) {
		this.call = call;
	}

	/**
	 * @param str
	 * @param str1
	 * @param str2
	 */
	public void print(String str, String str1, String str2) {
		call.print(str, str1, str2);
	}
}
