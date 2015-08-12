package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 客户端认证请求
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcTransferSerialField extends Structure {
	/**
	 * 平台流水号
	 */
	public int PlateSerial;
	/**
	 * 交易发起方日期
	 */
	public byte[] TradeDate = new byte[9];
	/**
	 * 交易日期
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 交易时间
	 */
	public byte[] TradeTime = new byte[9];
	/**
	 * 交易代码
	 */
	public byte[] TradeCode = new byte[7];
	/**
	 * 会话编号
	 */
	public int SessionID;
	/**
	 * 银行编码
	 */
	public byte[] BankID = new byte[4];
	/**
	 * 银行分支机构编码
	 */
	public byte[] BankBranchID = new byte[5];
	/**
	 * 银行帐号类型
	 */
	public byte BankAccType;
	/**
	 * 银行帐号
	 */
	public byte[] BankAccount = new byte[41];
	/**
	 * 银行流水号
	 */
	public byte[] BankSerial = new byte[13];
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 期商分支机构代码
	 */
	public byte[] BrokerBranchID = new byte[31];
	/**
	 * 期货公司帐号类型
	 */
	public byte FutureAccType;
	/**
	 * 投资者帐号
	 */
	public byte[] AccountID = new byte[13];
	/**
	 * 投资者代码
	 */
	public byte[] InvestorID = new byte[13];
	/**
	 * 期货公司流水号
	 */
	public int FutureSerial;
	/**
	 * 证件类型
	 */
	public byte IdCardType;
	/**
	 * 证件号码
	 */
	public byte[] IdentifiedCardNo = new byte[51];
	/**
	 * 币种代码
	 */
	public byte[] CurrencyID = new byte[4];
	/**
	 * 交易金额
	 */
	public double TradeAmount;
	/**
	 * 应收客户费用
	 */
	public double CustFee;
	/**
	 * 应收期货公司费用
	 */
	public double BrokerFee;
	/**
	 * 有效标志
	 */
	public byte AvailabilityFlag;
	/**
	 * 操作员
	 */
	public byte[] OperatorCode = new byte[17];
	/**
	 * 新银行帐号
	 */
	public byte[] BankNewAccount = new byte[41];
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
		return Arrays.asList(new String[] { "PlateSerial", "TradeDate", "TradingDay", "TradeTime", "TradeCode", "SessionID", "BankID", "BankBranchID", "BankAccType", "BankAccount", "BankSerial",
				"BrokerID", "BrokerBranchID", "FutureAccType", "AccountID", "InvestorID", "FutureSerial", "IdCardType", "IdentifiedCardNo", "CurrencyID", "TradeAmount", "CustFee", "BrokerFee",
				"AvailabilityFlag", "OperatorCode", "BankNewAccount", "ErrorID", "ErrorMsg" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcTransferSerialField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcTransferSerialField implements Structure.ByValue {
	}
}
