package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 资金账户口令变更域
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcSettlementInfoField extends Structure {

	/**
	 * 交易日
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 结算编号
	 */
	public int SettlementID;
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者帐号
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 序号
	 */
	public int SequenceNo;
	/**
	 * 消息正文
	 */
	public byte[] Content = new byte[501];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "TradingDay", "SettlementID", "BrokerID", "InvestorID", "SequenceNo", "Content" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcSettlementInfoField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcSettlementInfoField implements Structure.ByValue {
	};
}
