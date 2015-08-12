package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcInstrumentCommissionRateField extends Structure {

	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];
	/**
	 * 投资者范围
	 */
	public byte InvestorRange;
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者代码
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 开仓手续费率
	 */
	public double OpenRatioByMoney;
	/**
	 * 开仓手续费
	 */
	public double OpenRatioByVolume;
	/**
	 * 平仓手续费率
	 */
	public double CloseRatioByMoney;
	/**
	 * 平仓手续费
	 */
	public double CloseRatioByVolume;
	/**
	 * 平今手续费率
	 */
	public double CloseTodayRatioByMoney;
	/**
	 * 平今手续费
	 */
	public double CloseTodayRatioByVolume;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "InstrumentID", "InvestorRange", "BrokerID", "InvestorID", "OpenRatioByMoney", "OpenRatioByVolume", "CloseRatioByMoney", "CloseRatioByVolume",
				"CloseTodayRatioByMoney", "CloseTodayRatioByVolume" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcInstrumentCommissionRateField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcInstrumentCommissionRateField implements Structure.ByValue {
	}
}
