package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 资金账户口令变更域
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcSettlementInfoConfirmField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者帐号
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 确认日期
	 */
	public byte[] ConfirmDate = new byte[9];
	/**
	 * 确认时间
	 */
	public byte[] ConfirmTime = new byte[9];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "InvestorID", "ConfirmDate", "ConfirmTime" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcSettlementInfoConfirmField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcSettlementInfoConfirmField implements Structure.ByValue {
	};
}
