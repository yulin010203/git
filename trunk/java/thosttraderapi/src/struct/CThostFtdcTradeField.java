package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcTradeField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者帐号
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];
	/**
	 * 报单引用
	 */
	public byte[] OrderRef = new byte[13];
	/**
	 * 用户代码
	 */
	public byte[] UserID = new byte[16];
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 成交编号
	 */
	public byte[] TradeID = new byte[21];
	/**
	 * 买卖方向
	 */
	public byte Direction;

	/**
	 * 报单编号
	 */
	public byte[] OrderSysID = new byte[21];
	/**
	 * 会员代码
	 */
	public byte[] ParticipantID = new byte[11];
	/**
	 * 客户代码
	 */
	public byte[] ClientID = new byte[11];
	/**
	 * 交易角色
	 */
	public byte TradingRole;
	/**
	 * 合约在交易所的代码
	 */
	public byte[] ExchangeInstID = new byte[31];
	/**
	 * 开平标志
	 */
	public byte OffsetFlag;
	/**
	 * 投机套保标志
	 */
	public byte HedgeFlag;
	/**
	 * 价格
	 */
	public double Price;
	/**
	 * 数量
	 */
	public int Volume;
	/**
	 * 成交时期
	 */
	public byte[] TradeDate = new byte[9];
	/**
	 * 成交时间
	 */
	public byte[] TradeTime = new byte[9];
	/**
	 * 成交类型
	 */
	public byte[] TradeType = new byte[9];
	/**
	 * 成交价来源
	 */
	public byte PriceSource;
	/**
	 * 交易所交易员代码
	 */
	public byte[] TraderID = new byte[21];
	/**
	 * 本地报单编号
	 */
	public byte[] OrderLocalID = new byte[13];
	/**
	 * 结算会员编号
	 */
	public byte[] ClearingPartID = new byte[11];
	/**
	 * 业务单元
	 */
	public byte[] BusinessUnit = new byte[21];
	/**
	 * 序号
	 */
	public int SequenceNo;
	/**
	 * 交易日
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 结算编号
	 */
	public int SettlementID;
	/**
	 * 经纪公司报单编号
	 */
	public int BrokerOrderSeq;
	/**
	 * 成交来源
	 */
	public byte TradeSource;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "InvestorID", "InstrumentID", "OrderRef", "UserID", "ExchangeID", "TradeID", "Direction", "OrderSysID", "ParticipantID", "ClientID",
				"TradingRole", "ExchangeInstID", "OffsetFlag", "HedgeFlag", "Price", "Volume", "TradeDate", "TradeTime", "TradeType", "PriceSource", "TraderID", "OrderLocalID", "ClearingPartID",
				"BusinessUnit", "SequenceNo", "TradingDay", "SettlementID", "BrokerOrderSeq", "TradeSource" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcTradeField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcTradeField implements Structure.ByValue {
	}
}
