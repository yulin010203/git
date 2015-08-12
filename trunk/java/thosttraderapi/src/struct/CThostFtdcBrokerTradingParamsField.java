package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 经纪公司交易参数
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcBrokerTradingParamsField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];
	/**
	 * 持仓处理算法编号
	 */
	public byte HandlePositionAlgoID;
	/**
	 * 寻找保证金率算法编号
	 */
	public byte FindMarginRateAlgoID;
	/**
	 * 资金处理算法编号
	 */
	public byte HandleTradingAccountAlgoID;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "ExchangeID", "InstrumentID", "HandlePositionAlgoID", "FindMarginRateAlgoID", "HandleTradingAccountAlgoID" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcBrokerTradingParamsField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcBrokerTradingParamsField implements Structure.ByValue {
	};
}
