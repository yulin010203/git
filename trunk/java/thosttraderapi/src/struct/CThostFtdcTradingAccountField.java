package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcTradingAccountField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者帐号
	 */
	public byte[] AccountID = new byte[13];
	/**
	 * 上次质押金额
	 */
	public double PreMortgage;
	/**
	 * 上次信用额度
	 */
	public double PreCredit;
	/**
	 * 上次存款额
	 */
	public double PreDeposit;
	/**
	 * 上次结算准备金
	 */
	public double PreBalance;
	/**
	 * 上次占用的保证金
	 */
	public double PreMargin;
	/**
	 * 利息基数
	 */
	public double InterestBase;
	/**
	 * 利息收入
	 */
	public double Interest;
	/**
	 * 入金金额
	 */
	public double Deposit;
	/**
	 * 出金金额
	 */
	public double Withdraw;
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
	 * 当前保证金总额
	 */
	public double CurrMargin;
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
	 * 期货结算准备金
	 */
	public double Balance;
	/**
	 * 可用资金
	 */
	public double Available;
	/**
	 * 可取资金
	 */
	public double WithdrawQuota;
	/**
	 * 基本准备金
	 */
	public double Reserve;
	/**
	 * 交易日
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 结算编号
	 */
	public int SettlementID;
	/**
	 * 信用额度
	 */
	public double Credit;
	/**
	 * 质押金额
	 */
	public double Mortgage;
	/**
	 * 交易所保证金
	 */
	public double ExchangeMargin;
	/**
	 * 投资者交割保证金
	 */
	public double DeliveryMargin;
	/**
	 * 交易所交割保证金
	 */
	public double ExchangeDeliveryMargin;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "AccountID", "PreMortgage", "PreCredit", "PreDeposit", "PreBalance", "PreMargin", "InterestBase", "Interest", "Deposit", "Withdraw",
				"FrozenMargin", "FrozenCash", "FrozenCommission", "CurrMargin", "CashIn", "Commission", "CloseProfit", "PositionProfit", "Balance", "Available", "WithdrawQuota", "Reserve",
				"TradingDay", "SettlementID", "Credit", "Mortgage", "ExchangeMargin", "DeliveryMargin", "ExchangeDeliveryMargin" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcTradingAccountField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcTradingAccountField implements Structure.ByValue {
	}
}
