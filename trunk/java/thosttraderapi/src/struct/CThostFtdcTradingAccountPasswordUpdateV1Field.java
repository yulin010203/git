package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 资金账户口令变更域
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcTradingAccountPasswordUpdateV1Field extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者帐号
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 原来的口令
	 */
	public byte[] OldPassword = new byte[41];
	/**
	 * 新的口令
	 */
	public byte[] NewPassword = new byte[41];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "InvestorID", "OldPassword", "NewPassword" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcTradingAccountPasswordUpdateV1Field implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcTradingAccountPasswordUpdateV1Field implements Structure.ByValue {
	};
}
