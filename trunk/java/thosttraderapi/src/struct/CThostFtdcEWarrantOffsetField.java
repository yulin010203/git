package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 客户端认证信息
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcEWarrantOffsetField extends Structure {
	/**
	 * 交易日期
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者代码
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];
	/**
	 * 买卖方向
	 */
	public byte Direction;
	/**
	 * 投机套保标志
	 */
	public byte HedgeFlag;
	/**
	 * 数量
	 */
	public int Volume;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "TradingDay", "BrokerID", "InvestorID", "ExchangeID", "InstrumentID", "Direction", "HedgeFlag", "Volume" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcEWarrantOffsetField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcEWarrantOffsetField implements Structure.ByValue {
	}
}
