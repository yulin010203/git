package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcInvestorPositionCombineDetailField extends Structure {

	/**
	 * 交易日
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 开仓日期
	 */
	public byte[] OpenDate = new byte[9];
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 结算编号
	 */
	public int SettlementID;
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];

	/**
	 * 投资者代码
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 组合编号
	 */
	public byte[] ComTradeID = new byte[21];
	/**
	 * 撮合编号
	 */
	public byte[] TradeID = new byte[21];
	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];
	/**
	 * 投机套保标志
	 */
	public byte HedgeFlag;
	/**
	 * 买卖
	 */
	public byte Direction;
	/**
	 * 持仓量
	 */
	public int TotalAmt;
	/**
	 * 投资者保证金
	 */
	public double Margin;
	/**
	 * 交易所保证金
	 */
	public double ExchMargin;
	/**
	 * 保证金率
	 */
	public double MarginRateByMoney;
	/**
	 * 保证金率(按手数)
	 */
	public double MarginRateByVolume;
	/**
	 * 单腿编号
	 */
	public int LegID;
	/**
	 * 单腿乘数
	 */
	public int LegMultiple;
	/**
	 * 组合持仓合约编码
	 */
	public byte[] CombInstrumentID = new byte[31];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "TradingDay", "OpenDate", "ExchangeID", "SettlementID", "BrokerID", "InvestorID", "ComTradeID", "TradeID", "InstrumentID", "HedgeFlag", "Direction",
				"TotalAmt", "Margin", "ExchMargin", "MarginRateByMoney", "MarginRateByVolume", "LegID", "LegMultiple", "CombInstrumentID" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcInvestorPositionCombineDetailField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcInvestorPositionCombineDetailField implements Structure.ByValue {
	}
}
