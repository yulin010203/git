package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 指定的合约
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcSpecificInstrumentField extends Structure {

	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 合约在交易所的代码
	 */
	public byte[] ExchangeInstID = new byte[31];
	/**
	 * 结算组代码
	 */
	public byte[] SettlementGroupID = new byte[9];
	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];
	/**
	 * 合约交易状态
	 */
	public byte InstrumentStatus;
	/**
	 * 交易阶段编号
	 */
	public int TradingSegmentSN;
	/**
	 * 进入本状态时间
	 */
	public byte[] EnterTime = new byte[9];
	/**
	 * 进入本状态原因
	 */
	public byte EnterReason;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "ExchangeID", "ExchangeInstID", "SettlementGroupID", "InstrumentID", "InstrumentStatus", "TradingSegmentSN", "EnterTime", "EnterReason" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcSpecificInstrumentField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcSpecificInstrumentField implements Structure.ByValue {
	};
}
