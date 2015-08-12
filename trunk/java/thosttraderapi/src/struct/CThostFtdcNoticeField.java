package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 资金账户口令变更域
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcNoticeField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 消息正文
	 */
	public byte[] Content = new byte[501];
	/**
	 * 经纪公司通知内容序列号
	 */
	public byte[] SequenceLabel = new byte[2];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "Content", "SequenceLabel" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcNoticeField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcNoticeField implements Structure.ByValue {
	};
}
