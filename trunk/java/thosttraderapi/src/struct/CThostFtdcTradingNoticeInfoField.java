package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 资金账户口令变更域
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcTradingNoticeInfoField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者代码
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 发送时间
	 */
	public byte[] SendTime = new byte[9];
	/**
	 * 消息正文
	 */
	public byte[] FieldContent = new byte[501];
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
		return Arrays.asList(new String[] { "BrokerID", "InvestorID", "SendTime", "FieldContent", "SequenceSeries", "SequenceNo" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcTradingNoticeInfoField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcTradingNoticeInfoField implements Structure.ByValue {
	};
}
