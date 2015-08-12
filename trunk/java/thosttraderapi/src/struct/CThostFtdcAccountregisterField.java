package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 客户开销户信息表
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcAccountregisterField extends Structure {
	/**
	 * 交易日期
	 */
	public byte[] TradeDay = new byte[9];
	/**
	 * 银行编码
	 */
	public byte[] BankID = new byte[4];
	/**
	 * 银行分支机构编码
	 */
	public byte[] BankBranchID = new byte[5];
	/**
	 * 银行帐号
	 */
	public byte[] BankAccount = new byte[41];
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 期货公司分支机构编码
	 */
	public byte[] BrokerBranchID = new byte[31];
	/**
	 * 投资者帐号
	 */
	public byte[] AccountID = new byte[13];
	/**
	 * 证件类型
	 */
	public byte IdCardType;
	/**
	 * 证件号码
	 */
	public byte[] IdentifiedCardNo = new byte[51];
	/**
	 * 客户姓名
	 */
	public byte[] CustomerName = new byte[51];
	/**
	 * 币种代码
	 */
	public byte[] CurrencyID = new byte[4];
	/**
	 * 开销户类别
	 */
	public byte OpenOrDestroy;
	/**
	 * 签约日期
	 */
	public byte[] RegDate = new byte[9];
	/**
	 * 解约日期
	 */
	public byte[] OutDate = new byte[9];
	/**
	 * 交易ID
	 */
	public int TID;
	/**
	 * 客户类型
	 */
	public byte CustType;
	/**
	 * 银行帐号类型
	 */
	public byte BankAccType;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "TradeDay", "BankID", "BankBranchID", "BankAccount", "BrokerID", "BrokerBranchID", "AccountID", "IdCardType", "IdentifiedCardNo", "CustomerName",
				"CurrencyID", "OpenOrDestroy", "RegDate", "OutDate", "TID", "CustType", "BankAccType" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcAccountregisterField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcAccountregisterField implements Structure.ByValue {
	}
}
