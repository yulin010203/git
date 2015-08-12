package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 资金账户口令变更域
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcTradingCodeField extends Structure {

	/**
	 * 投资者帐号
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 客户代码
	 */
	public byte[] ClientID = new byte[11];
	/**
	 * 是否活跃
	 */
	public int IsActive;
	/**
	 * 交易编码类型
	 */
	public byte ClientIDType;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "InvestorID", "BrokerID", "ExchangeID", "ClientID", "IsActive", "ClientIDType" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcTradingCodeField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcTradingCodeField implements Structure.ByValue {
	};
}
