package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 客户端认证请求
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcChangeAccountField extends Structure {
	/**
	 * 交易代码
	 */
	public byte[] TradeCode = new byte[7];
	/**
	 * 银行编码
	 */
	public byte[] BankID = new byte[4];
	/**
	 * 银行分支机构编码
	 */
	public byte[] BankBranchID = new byte[5];
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 期商分支机构代码
	 */
	public byte[] BrokerBranchID = new byte[31];
	/**
	 * 交易发起方日期
	 */
	public byte[] TradeDate = new byte[9];
	/**
	 * 交易时间
	 */
	public byte[] TradeTime = new byte[9];
	/**
	 * 银行流水号
	 */
	public byte[] BankSerial = new byte[13];
	/**
	 * 交易日期
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 平台流水号
	 */
	public int PlateSerial;
	/**
	 * 最后分片标志
	 */
	public byte LastFragment;
	/**
	 * 会话编号
	 */
	public int SessionID;
	/**
	 * 客户姓名
	 */
	public byte[] CustomerName = new byte[51];
	/**
	 * 证件类型
	 */
	public byte IdCardType;
	/**
	 * 证件号码
	 */
	public byte[] IdentifiedCardNo = new byte[51];
	/**
	 * 性别
	 */
	public byte Gender;
	/**
	 * 国家代码
	 */
	public byte[] CountryCode = new byte[21];
	/**
	 * 客户类型
	 */
	public byte CustType;
	/**
	 * 地址
	 */
	public byte[] Address = new byte[101];
	/**
	 * 邮编
	 */
	public byte[] ZipCode = new byte[7];
	/**
	 * 电话号码
	 */
	public byte[] Telephone = new byte[41];
	/**
	 * 手机
	 */
	public byte[] MobilePhone = new byte[21];
	/**
	 * 传真
	 */
	public byte[] Fax = new byte[41];
	/**
	 * 电子邮件
	 */
	public byte[] EMail = new byte[41];
	/**
	 * 资金账户状态
	 */
	public byte MoneyAccountStatus;
	/**
	 * 银行帐号
	 */
	public byte[] BankAccount = new byte[41];
	/**
	 * 银行密码
	 */
	public byte[] BankPassWord = new byte[41];
	/**
	 * 新银行帐号
	 */
	public byte[] NewBankAccount = new byte[41];
	/**
	 * 新银行密码
	 */
	public byte[] NewBankPassWord = new byte[41];
	/**
	 * 投资者帐号
	 */
	public byte[] AccountID = new byte[13];
	/**
	 * 期货密码
	 */
	public byte[] Password = new byte[41];
	/**
	 * 安装编号
	 */
	public int InstallID;

	/**
	 * 验证客户证件号码标志
	 */
	public byte VerifyCertNoFlag;
	/**
	 * 币种代码
	 */
	public byte[] CurrencyID = new byte[4];
	/**
	 * 摘要
	 */
	public byte[] Digest = new byte[36];
	/**
	 * 银行帐号类型
	 */
	public byte BankAccType;
	/**
	 * 期货公司银行编码
	 */
	public byte[] BrokerIDByBank = new byte[33];
	/**
	 * 银行密码标志
	 */
	public byte BankPwdFlag;
	/**
	 * 期货资金密码核对标志
	 */
	public byte SecuPwdFlag;
	/**
	 * 交易ID
	 */
	public int TID;
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
		return Arrays.asList(new String[] { "TradeCode", "BankID", "BankBranchID", "BrokerID", "BrokerBranchID", "TradeDate", "TradeTime", "BankSerial", "TradingDay", "PlateSerial", "LastFragment",
				"SessionID", "CustomerName", "IdCardType", "IdentifiedCardNo", "Gender", "CountryCode", "CustType", "Address", "ZipCode", "Telephone", "MobilePhone", "Fax", "EMail",
				"MoneyAccountStatus", "BankAccount", "BankPassWord", "NewBankAccount", "NewBankPassWord", "AccountID", "Password", "InstallID", "VerifyCertNoFlag", "CurrencyID", "Digest",
				"BankAccType", "BrokerIDByBank", "BankPwdFlag", "SecuPwdFlag", "TID", "ErrorID", "ErrorMsg" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcChangeAccountField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcChangeAccountField implements Structure.ByValue {
	}
}
