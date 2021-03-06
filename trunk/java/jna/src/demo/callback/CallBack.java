package demo.callback;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-5-2
 */
public class CallBack implements PrintCallBack, PrintCallBack1, PrintCallBack2 {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss.SSS");

	/**
	 * @param str
	 */
	public void print(String str) {
		System.out.println(sdf.format(new Date()) + "|" + str);
	}

	/**
	 * @param str
	 * @param str1
	 */
	public void print(String str, String str1) {
		System.out.println("print1:" + str + ":" + str1);
	}

	/**
	 * @param str
	 * @param str1
	 * @param str2
	 */
	public void print(String str, String str1, String str2) {
		System.out.println("print2:" + str + ":" + str1 + ":" + str2);
	}
}
