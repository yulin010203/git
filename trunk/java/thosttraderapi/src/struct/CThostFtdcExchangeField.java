package struct;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 交易所
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcExchangeField extends Structure {

	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 交易所名称
	 */
	public byte[] ExchangeName = new byte[31];
	/**
	 * 交易所属性
	 */
	public byte ExchangeProperty;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		Field[] fields = this.getClass().getDeclaredFields();
		List<String> names = new ArrayList<String>(fields.length);
		for (int i = 0; i < fields.length; i++) {
			names.add(fields[i].getName());
		}
		return names;
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcExchangeField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcExchangeField implements Structure.ByValue {
	}
}
