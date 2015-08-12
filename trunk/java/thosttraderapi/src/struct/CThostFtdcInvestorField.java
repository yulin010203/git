package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcInvestorField extends Structure {

	/**
	 * 投资者帐号
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 投资者分组代码
	 */
	public byte[] InvestorGroupID = new byte[13];
	/**
	 * 投资者名称
	 */
	public byte[] InvestorName = new byte[81];
	/**
	 * 证件类型
	 */
	public byte IdentifiedCardType;
	/**
	 * 证件号码
	 */
	public byte[] IdentifiedCardNo = new byte[51];
	/**
	 * 是否活跃
	 */
	public int IsActive;
	/**
	 * 联系电话
	 */
	public byte[] Telephone = new byte[41];
	/**
	 * 通讯地址
	 */
	public byte[] Address = new byte[101];
	/**
	 * 开户日期
	 */
	public byte[] OpenDate = new byte[9];
	/**
	 * 手机
	 */
	public byte[] Mobile = new byte[41];
	/**
	 * 手续费率模板代码
	 */
	public byte[] CommModelID = new byte[13];
	/**
	 * 保证金率模板代码
	 */
	public byte[] MarginModelID = new byte[13];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "InvestorID", "BrokerID", "InvestorGroupID", "InvestorName", "IdentifiedCardType", "IdentifiedCardNo", "IsActive", "Telephone", "Address", "OpenDate",
				"Mobile", "CommModelID", "MarginModelID" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcInvestorField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcInvestorField implements Structure.ByValue {
	}
}
