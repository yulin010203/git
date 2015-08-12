package struct;

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
		return Arrays.asList(new String[] { "SequenceSeries", "SequenceNo" });
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
