package struct;

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
		return Arrays.asList(new String[] { "BrokerID", "UserID" });
	}

	/**
	 * 指针
	 */
	public class ByReference extends CThostFtdcExchangeField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public class ByValue extends CThostFtdcExchangeField implements Structure.ByValue {
	}
}
