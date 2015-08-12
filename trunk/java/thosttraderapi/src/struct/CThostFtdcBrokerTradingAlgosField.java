package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 经纪公司交易算法
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcBrokerTradingAlgosField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者代码
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 保证金价格类型
	 */
	public byte MarginPriceType;
	/**
	 * 盈亏算法
	 */
	public byte Algorithm;
	/**
	 * 可用是否包含平仓盈利
	 */
	public byte AvailIncludeCloseProfit;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "InvestorID", "MarginPriceType", "Algorithm", "AvailIncludeCloseProfit" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcBrokerTradingAlgosField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcBrokerTradingAlgosField implements Structure.ByValue {
	};
}
