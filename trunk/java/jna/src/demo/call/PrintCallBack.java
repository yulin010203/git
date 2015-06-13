package demo.call;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-5-4
 */
public class PrintCallBack implements Callback {
	CallBack2 call;

	/**
	 * @param call
	 */
	public PrintCallBack(CallBack2 call) {
		this.call = call;
	}

	/**
	 * @param str
	 */
	public void print(String str) {
		call.print(str);
	}
}
