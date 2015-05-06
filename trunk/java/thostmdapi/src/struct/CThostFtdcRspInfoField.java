package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 响应信息
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcRspInfoField extends Structure {

	/**
	 * 错误代码
	 */
	public int ErrorID;
	/**
	 * 错误信息
	 */
	public byte[] ErrorMsg = new byte[81];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "ErrorID", "ErrorMsg" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcRspInfoField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcRspInfoField implements Structure.ByValue {
	};
}
