package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 资金账户口令变更域
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcTradingNoticeField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者范围
	 */
	public byte InvestorRange;
	/**
	 * 投资者代码
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 序列系列号
	 */
	public short SequenceSeries;
	/**
	 * 用户代码
	 */
	public byte[] UserID = new byte[16];
	/**
	 * 发送时间
	 */
	public byte[] SendTime = new byte[9];
	/**
	 * 序列号
	 */
	public int SequenceNo;
	/**
	 * 消息正文
	 */
	public byte[] FieldContent = new byte[501];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "InvestorRange", "InvestorID", "SequenceSeries", "UserID", "SendTime", "SequenceNo", "FieldContent" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcTradingNoticeField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcTradingNoticeField implements Structure.ByValue {
	};
}
