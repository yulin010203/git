package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 响应信息
 * 
 * @author 陈霖 2015-5-4
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
		return Arrays.asList(new String[] { "BrokerID", "UserID" });
	}

	/**
	 * 指针
	 */
	public class ByReference extends CThostFtdcRspInfoField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public class ByValue extends CThostFtdcRspInfoField implements Structure.ByValue {
	}
}
