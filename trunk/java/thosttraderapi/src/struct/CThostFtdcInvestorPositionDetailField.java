package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcInvestorPositionDetailField extends Structure {

	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者帐号
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 持仓多空方向
	 */
	public byte PosiDirection;
	/**
	 * 投机套保标志
	 */
	public byte HedgeFlag;
	/**
	 * 持仓日期
	 */
	public byte PositionDate;
	/**
	 * 上日持仓
	 */
	public int YdPosition;
	/**
	 * 今日持仓
	 */
	public int Position;
	/**
	 * 多头冻结
	 */
	public int LongFrozen;
	/**
	 * 空头冻结
	 */
	public int ShortFrozen;
	/**
	 * 开仓冻结金额
	 */
	public double LongFrozenAmount;
	/**
	 * 开仓冻结金额
	 */
	public double ShortFrozenAmount;
	/**
	 * 开仓量
	 */
	public int OpenVolume;
	/**
	 * 平仓量
	 */
	public int CloseVolume;
	/**
	 * 开仓金额
	 */
	public double OpenAmount;
	/**
	 * 平仓金额
	 */
	public double CloseAmount;
	/**
	 * 持仓成本
	 */
	public double PositionCost;
	/**
	 * 上次占用的保证金
	 */
	public double PreMargin;
	/**
	 * 占用的保证金
	 */
	public double UseMargin;
	/**
	 * 冻结的保证金
	 */
	public double FrozenMargin;
	/**
	 * 冻结的资金
	 */
	public double FrozenCash;
	/**
	 * 冻结的手续费
	 */
	public double FrozenCommission;
	/**
	 * 资金差额
	 */
	public double CashIn;
	/**
	 * 手续费
	 */
	public double Commission;
	/**
	 * 平仓盈亏
	 */
	public double CloseProfit;
	/**
	 * 持仓盈亏
	 */
	public double PositionProfit;
	/**
	 * 上次结算价
	 */
	public double PreSettlementPrice;
	/**
	 * 本次结算价
	 */
	public double SettlementPrice;
	/**
	 * 交易日
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 结算编号
	 */
	public int SettlementID;
	/**
	 * 开仓成本
	 */
	public double OpenCost;
	/**
	 * 交易所保证金
	 */
	public double ExchangeMargin;
	/**
	 * 组合成交形成的持仓
	 */
	public int CombPosition;
	/**
	 * 组合多头冻结
	 */
	public int CombLongFrozen;
	/**
	 * 组合空头冻结
	 */
	public int CombShortFrozen;
	/**
	 * 逐日盯市平仓盈亏
	 */
	public double CloseProfitByDate;
	/**
	 * 逐笔对冲平仓盈亏
	 */
	public double CloseProfitByTrade;
	/**
	 * 今日持仓
	 */
	public int TodayPosition;
	/**
	 * 保证金率
	 */
	public double MarginRateByMoney;
	/**
	 * 保证金率(按手数)
	 */
	public double MarginRateByVolume;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "InstrumentID", "BrokerID", "InvestorID", "PosiDirection", "HedgeFlag", "PositionDate", "YdPosition", "Position", "LongFrozen", "ShortFrozen",
				"LongFrozenAmount", "ShortFrozenAmount", "OpenVolume", "CloseVolume", "OpenAmount", "CloseAmount", "PositionCost", "PreMargin", "UseMargin", "FrozenMargin", "FrozenCash",
				"FrozenCommission", "CashIn", "Commission", "CloseProfit", "PositionProfit", "PreSettlementPrice", "SettlementPrice", "TradingDay", "SettlementID", "OpenCost", "ExchangeMargin",
				"CombPosition", "CombLongFrozen", "CombShortFrozen", "CloseProfitByDate", "CloseProfitByTrade", "TodayPosition", "MarginRateByMoney", "MarginRateByVolume" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcInvestorPositionDetailField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcInvestorPositionDetailField implements Structure.ByValue {
	}
}
