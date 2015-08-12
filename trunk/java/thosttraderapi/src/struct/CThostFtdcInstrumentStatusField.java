package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 指定的合约
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcInstrumentStatusField extends Structure {

	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "InstrumentID" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcInstrumentStatusField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcInstrumentStatusField implements Structure.ByValue {
	};
}
