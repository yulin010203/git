package demo1;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-6-13
 */
public class CallBack implements Callback {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss.SSS");

	/**
	 * @param str
	 */
	public void print(String str) {
		// 1011|IF1506|100|20150613|12:51:09|72
		String[] arr = str.split("\\|");
		MD md = new MD();
		int index = 1;
		md.setName(arr[index++]);
		md.setId(Integer.parseInt(arr[index++]));
		md.setDate(arr[index++]);
		md.setTime(arr[index++]);
		md.setMillis(Integer.parseInt(arr[index++]));
		md.setPrice(Double.parseDouble(arr[index++]));
		md.setPrice1(Double.parseDouble(arr[index++]));
		md.setPrice2(Double.parseDouble(arr[index++]));
		md.setPrice3(Double.parseDouble(arr[index++]));
		md.setPrice4(Double.parseDouble(arr[index++]));
		System.out.println(md);
		System.out.println("date:" + sdf.format(new Date()));
	}

	/**
	 * @param arr
	 * @param separator
	 * @return
	 */
	private static String join(Object[] arr, String separator) {
		if (arr == null || arr.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder(arr.length * 10);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) {
				sb.append("");
			} else {
				sb.append(arr[i]);
			}
			if (i != arr.length - 1) {// 最后一个
				sb.append(separator);
			}
		}
		return sb.toString();
	}
}
