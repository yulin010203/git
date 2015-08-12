package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 资金账户口令变更域
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcContractBankField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 银行代码
	 */
	public byte[] BankID = new byte[4];
	/**
	 * 银行分中心代码
	 */
	public byte[] BankBrchID = new byte[5];
	/**
	 * 银行名称
	 */
	public byte[] BankName = new byte[101];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "BankID", "BankBrchID", "BankName" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcContractBankField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcContractBankField implements Structure.ByValue {
	};
}
