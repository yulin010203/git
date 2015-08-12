package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcParkedOrderField extends Structure {

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
	 * 用户强评标志
	 */
	public int UserForceClose;
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 预埋报单编号
	 */
	public byte[] ParkedOrderID = new byte[13];
	/**
	 * 用户类型
	 */
	public byte UserType;
	/**
	 * 预埋单状态
	 */
	public byte Status;
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
				"RequestID", "UserForceClose", "ExchangeID", "ParkedOrderID", "UserType", "Status", "ErrorID", "ErrorMsg", "IsSwapOrder" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcParkedOrderField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcParkedOrderField implements Structure.ByValue {
	}
}
