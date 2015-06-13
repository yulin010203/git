package demo1;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-6-13
 */
public class CallBack1 implements Callback {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss.SSS");

	/**
	 * @param data
	 */
	public void print(CallBackData.ByReference data) {
		if (data == null) {
			System.out.println();
			System.out.println("date:" + sdf.format(new Date()));
			return;
		}
		MD md = new MD();
		md.setId(data.id);
		md.setName(getString(data.name));
//		md.setName(data.name);
		md.setDate(getString(data.date));
//		md.setDate(data.date);
		md.setTime(getString(data.time));
//		md.setTime(data.time);
		md.setMillis(data.millis);
		md.setPrice(data.price);
		md.setPrice1(data.price1);
		md.setPrice2(data.price2);
		md.setPrice3(data.price3);
		md.setPrice4(data.price4);
		System.out.println(md);
		System.out.println("date:" + sdf.format(new Date()));
	}

	/**
	 * @param bytes
	 * @return
	 */
	private static String getString(byte[] bytes) {
		int len = 0;
		for (byte b : bytes) {
			if (b == 0)
				break;
			len++;
		}
		return new String(bytes, 0, len);
	}
}
