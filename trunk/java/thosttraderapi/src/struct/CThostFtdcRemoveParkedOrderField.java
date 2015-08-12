package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 资金账户口令变更域
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcRemoveParkedOrderField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者帐号
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 预埋报单编号
	 */
	public byte[] ParkedOrderIDnew = new byte[13];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "InvestorID", "ParkedOrderIDnew" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcRemoveParkedOrderField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcRemoveParkedOrderField implements Structure.ByValue {
	};
}
