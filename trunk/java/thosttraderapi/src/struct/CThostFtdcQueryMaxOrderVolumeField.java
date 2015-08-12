package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcQueryMaxOrderVolumeField extends Structure {

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
	 * 买卖方向
	 */
	public byte Direction;
	/**
	 * 开平标志
	 */
	public byte OffsetFlag;
	/**
	 * 投机套保标志
	 */
	public byte HedgeFlag;
	/**
	 * 最大允许报单数量
	 */
	public int MaxVolume;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "InvestorID", "InstrumentID", "Direction", "OffsetFlag", "HedgeFlag", "MaxVolume" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcQueryMaxOrderVolumeField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcQueryMaxOrderVolumeField implements Structure.ByValue {
	}
}
