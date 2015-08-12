package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcErrorConditionalOrderField extends Structure {

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
	 * 报单价格条件
	 */
	public byte OrderPriceType;
	/**
	 * 买卖方向
	 */
	public byte Direction;
	/**
	 * 组合开平标志
	 */
	public byte[] CombOffsetFlag = new byte[5];
	/**
	 * 组合投机套保标志
	 */
	public byte[] CombHedgeFlag = new byte[5];
	/**
	 * 价格
	 */
	public double LimitPrice;
	/**
	 * 数量
	 */
	public int VolumeTotalOriginal;
	/**
	 * 有效期类型
	 */
	public byte TimeCondition;
	/**
	 * GTD日期
	 */
	public byte[] GTDDate = new byte[9];
	/**
	 * 成交量类型
	 */
	public byte VolumeCondition;
	/**
	 * 最小成交量
	 */
	public int MinVolume;
	/**
	 * 触发条件
	 */
	public byte ContingentCondition;
	/**
	 * 止损价
	 */
	public double StopPrice;
	/**
	 * 强平原因
	 */
	public byte ForceCloseReason;
	/**
	 * 自动挂起标志
	 */
	public int IsAutoSuspend;
	/**
	 * 业务单元
	 */
	public byte[] BusinessUnit = new byte[21];
	/**
	 * 请求编号
	 */
	public int RequestID;
	/**
	 * 本地报单编号
	 */
	public byte[] OrderLocalID = new byte[13];
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 会员代码
	 */
	public byte[] ParticipantID = new byte[11];
	/**
	 * 客户代码
	 */
	public byte[] ClientID = new byte[11];
	/**
	 * 合约在交易所的代码
	 */
	public byte[] ExchangeInstID = new byte[31];
	/**
	 * 交易所交易员代码
	 */
	public byte[] TraderID = new byte[21];
	/**
	 * 安装编号
	 */
	public int InstallID;
	/**
	 * 报单提交状态
	 */
	public byte OrderSubmitStatus;
	/**
	 * 报单提示序号
	 */
	public int NotifySequence;
	/**
	 * 交易日
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 结算编号
	 */
	public int SettlementID;
	/**
	 * 报单编号
	 */
	public byte[] OrderSysID = new byte[21];
	/**
	 * 报单来源
	 */
	public byte OrderSource;
	/**
	 * 报单状态
	 */
	public byte OrderStatus;
	/**
	 * 报单类型
	 */
	public byte OrderType;
	/**
	 * 今成交数量
	 */
	public int VolumeTraded;
	/**
	 * 剩余数量
	 */
	public int VolumeTotal;
	/**
	 * 报单日期
	 */
	public byte[] InsertDate = new byte[9];
	/**
	 * 委托时间
	 */
	public byte[] InsertTime = new byte[9];
	/**
	 * 激活时间
	 */
	public byte[] ActiveTime = new byte[9];
	/**
	 * 挂起时间
	 */
	public byte[] SuspendTime = new byte[9];
	/**
	 * 最后修改时间
	 */
	public byte[] UpdateTime = new byte[9];
	/**
	 * 撤销时间
	 */
	public byte[] CancelTime = new byte[9];
	/**
	 * 最后修改交易所交易员代码
	 */
	public byte[] ActiveTraderID = new byte[21];
	/**
	 * 结算会员编号
	 */
	public byte[] ClearingPartID = new byte[11];
	/**
	 * 序号
	 */
	public int SequenceNo;
	/**
	 * 前置编号
	 */
	public int FrontID;
	/**
	 * 会话编号
	 */
	public int SessionID;
	/**
	 * 用户端产品信息
	 */
	public byte[] UserProductInfo = new byte[11];
	/**
	 * 状态信息
	 */
	public byte[] StatusMsg = new byte[81];
	/**
	 * 用户强评标志
	 */
	public int UserForceClose;
	/**
	 * 操作用户代码
	 */
	public byte[] ActiveUserID = new byte[16];
	/**
	 * 经纪公司报单编号
	 */
	public int BrokerOrderSeq;
	/**
	 * 相关报单
	 */
	public byte[] RelativeOrderSysID = new byte[21];
	/**
	 * 郑商所成交数量
	 */
	public int ZCETotalTradedVolume;
	/**
	 * 错误代码
	 */
	public int ErrorID;
	/**
	 * 错误信息
	 */
	public byte[] ErrorMsg = new byte[81];
	/**
	 * 互换单标志
	 */
	public int IsSwapOrder;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "InvestorID", "InstrumentID", "OrderRef", "UserID", "OrderPriceType", "Direction", "CombOffsetFlag", "CombHedgeFlag", "LimitPrice",
				"VolumeTotalOriginal", "TimeCondition", "GTDDate", "VolumeCondition", "MinVolume", "ContingentCondition", "StopPrice", "ForceCloseReason", "IsAutoSuspend", "BusinessUnit",
				"RequestID", "OrderLocalID", "ExchangeID", "ParticipantID", "ClientID", "ExchangeInstID", "TraderID", "InstallID", "OrderSubmitStatus", "NotifySequence", "TradingDay", "SettlementID",
				"OrderSysID", "OrderSource", "OrderStatus", "OrderType", "VolumeTraded", "VolumeTotal", "InsertDate", "InsertTime", "ActiveTime", "SuspendTime", "UpdateTime", "CancelTime",
				"ActiveTraderID", "ClearingPartID", "SequenceNo", "FrontID", "SessionID", "UserProductInfo", "StatusMsg", "UserForceClose", "ActiveUserID", "BrokerOrderSeq", "RelativeOrderSysID",
				"ZCETotalTradedVolume", "ErrorID", "ErrorMsg", "IsSwapOrder" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcErrorConditionalOrderField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcErrorConditionalOrderField implements Structure.ByValue {
	}
}
