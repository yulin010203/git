package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcInstrumentMarginRateField extends Structure {

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
	 * 投机套保标志
	 */
	public byte HedgeFlag;
	/**
	 * 多头保证金率
	 */
	public double LongMarginRatioByMoney;
	/**
	 * 多头保证金费
	 */
	public double LongMarginRatioByVolume;
	/**
	 * 空头保证金率
	 */
	public double ShortMarginRatioByMoney;
	/**
	 * 空头保证金费
	 */
	public double ShortMarginRatioByVolume;
	/**
	 * 是否相对交易所收取
	 */
	public int IsRelative;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "InstrumentID", "InvestorRange", "BrokerID", "InvestorID", "HedgeFlag", "LongMarginRatioByMoney", "LongMarginRatioByVolume", "ShortMarginRatioByMoney",
				"ShortMarginRatioByVolume", "IsRelative" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcInstrumentMarginRateField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcInstrumentMarginRateField implements Structure.ByValue {
	}
}
