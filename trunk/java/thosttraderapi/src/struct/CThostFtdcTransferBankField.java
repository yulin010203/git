package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 资金账户口令变更域
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcTransferBankField extends Structure {

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
	/**
	 * 是否活跃
	 */
	public int IsActive;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BankID", "BankBrchID", "BankName", "IsActive" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcTransferBankField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcTransferBankField implements Structure.ByValue {
	};
}
