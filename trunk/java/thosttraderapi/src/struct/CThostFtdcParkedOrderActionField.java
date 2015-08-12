package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcParkedOrderActionField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者帐号
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 报单操作引用
	 */
	public int OrderActionRef;
	/**
	 * 报单引用
	 */
	public byte[] OrderRef = new byte[13];
	/**
	 * 请求编号
	 */
	public int RequestID;
	/**
	 * 前置编号
	 */
	public int FrontID;
	/**
	 * 会话编号
	 */
	public int SessionID;
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 报单编号
	 */
	public byte[] OrderSysID = new byte[21];
	/**
	 * 操作标志
	 */
	public byte ActionFlag;
	/**
	 * 价格
	 */
	public double LimitPrice;
	/**
	 * 数量变化
	 */
	public int VolumeChange;
	/**
	 * 用户代码
	 */
	public byte[] UserID = new byte[16];
	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];
	/**
	 * 预埋撤单单编号
	 */
	public byte[] ParkedOrderActionID = new byte[13];
	/**
	 * 用户类型
	 */
	public byte UserType;
	/**
	 * 预埋撤单状态
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

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "InvestorID", "OrderActionRef", "OrderRef", "RequestID", "FrontID", "SessionID", "ExchangeID", "OrderSysID", "ActionFlag", "LimitPrice",
				"VolumeChange", "UserID", "InstrumentID", "ParkedOrderActionID", "UserType", "Status", "ErrorID", "ErrorMsg" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcParkedOrderActionField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcParkedOrderActionField implements Structure.ByValue {
	}
}
