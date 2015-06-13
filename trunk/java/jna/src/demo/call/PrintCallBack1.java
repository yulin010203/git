package demo.call;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-5-4
 */
public class PrintCallBack1 implements Callback {
	CallBack2 call;

	/**
	 * @param call
	 */
	public PrintCallBack1(CallBack2 call) {
		this.call = call;
	}

	/**
	 * @param str
	 * @param str1
	 */
	public void print(String str, String str1) {
		call.print(str, str1);
	}
}
