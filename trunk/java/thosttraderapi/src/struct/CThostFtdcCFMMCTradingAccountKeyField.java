package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 客户端认证信息
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcCFMMCTradingAccountKeyField extends Structure {
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 经纪公司统一编码
	 */
	public byte[] ParticipantID = new byte[11];
	/**
	 * 投资者帐号
	 */
	public byte[] AccountID = new byte[13];
	/**
	 * 密钥编号
	 */
	public int KeyID;
	/**
	 * 动态密钥
	 */
	public byte[] CurrentKey = new byte[21];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "ParticipantID", "AccountID", "KeyID", "CurrentKey" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcCFMMCTradingAccountKeyField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcCFMMCTradingAccountKeyField implements Structure.ByValue {
	}
}
