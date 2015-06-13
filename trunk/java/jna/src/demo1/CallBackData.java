package demo1;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * @author 陈霖 2015-6-13
 */
public class CallBackData extends Structure {

	/**
	 * id
	 */
	public int id;

	/**
	 * name
	 */
	public byte[] name = new byte[10];
//	public String name;

	/**
	 * 日期
	 */
	public byte[] date = new byte[9];
//	public String date;

	/**
	 * 时间
	 */
	public byte[] time = new byte[9];
//	public String time;

	/**
	 * 毫秒
	 */
	public int millis;

	public double price;
	public double price1;
	public double price2;
	public double price3;
	public double price4;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "id", "name", "date", "time", "millis", "price", "price1", "price2", "price3", "price4" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CallBackData implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CallBackData implements Structure.ByValue {
	}

}
