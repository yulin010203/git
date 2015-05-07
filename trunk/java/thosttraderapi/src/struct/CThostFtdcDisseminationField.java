package struct;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 信息分发
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcDisseminationField extends Structure {

	/**
	 * 序列系列号
	 */
	public short SequenceSeries;
	/**
	 * 序列号
	 */
	public int SequenceNo;

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
	public static class ByReference extends CThostFtdcDisseminationField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcDisseminationField implements Structure.ByValue {
	}
}
