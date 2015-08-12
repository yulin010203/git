package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 客户端认证请求
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcRspTransferField extends Structure {
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
	 * 客户类型
	 */
	public byte CustType;
	/**
	 * 银行帐号
	 */
	public byte[] BankAccount = new byte[41];
	/**
	 * 银行密码
	 */
	public byte[] BankPassWord = new byte[41];
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
	 * 期货公司流水号
	 */
	public int FutureSerial;
	/**
	 * 用户标识
	 */
	public byte[] UserID = new byte[16];
	/**
	 * 验证客户证件号码标志
	 */
	public byte VerifyCertNoFlag;
	/**
	 * 币种代码
	 */
	public byte[] CurrencyID = new byte[4];
	/**
	 * 交易金额
	 */
	public double TradeAmount;
	/**
	 * 期货可取金额
	 */
	public double FutureFetchAmount;
	/**
	 * 费用支付标志
	 */
	public double FeePayFlag;
	/**
	 * 应收客户费用
	 */
	public double CustFee;
	/**
	 * 应收期货公司费用
	 */
	public double BrokerFee;
	/**
	 * 发送方给接收方的消息
	 */
	public byte[] Message = new byte[129];
	/**
	 * 摘要
	 */
	public byte[] Digest = new byte[36];
	/**
	 * 银行帐号类型
	 */
	public byte BankAccType;
	/**
	 * 渠道标志
	 */
	public byte[] DeviceID = new byte[3];
	/**
	 * 期货单位帐号类型
	 */
	public byte BankSecuAccType;
	/**
	 * 期货公司银行编码
	 */
	public byte[] BrokerIDByBank = new byte[33];
	/**
	 * 期货单位帐号
	 */
	public byte[] BankSecuAcc = new byte[41];
	/**
	 * 银行密码标志
	 */
	public byte BankPwdFlag;
	/**
	 * 期货资金密码核对标志
	 */
	public byte SecuPwdFlag;
	/**
	 * 交易柜员
	 */
	public byte[] OperNo = new byte[17];
	/**
	 * 请求编号
	 */
	public int RequestID;
	/**
	 * 交易ID
	 */
	public int TID;
	/**
	 * 转账交易状态
	 */
	public byte TransferStatus;
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
				"SessionID", "CustomerName", "IdCardType", "IdentifiedCardNo", "CustType", "BankAccount", "BankPassWord", "AccountID", "Password", "InstallID", "FutureSerial", "UserID",
				"VerifyCertNoFlag", "CurrencyID", "TradeAmount", "FutureFetchAmount", "FeePayFlag", "CustFee", "BrokerFee", "Message", "Digest", "BankAccType", "DeviceID", "BankSecuAccType",
				"BrokerIDByBank", "BankSecuAcc", "BankPwdFlag", "SecuPwdFlag", "OperNo", "RequestID", "TID", "TransferStatus", "ErrorID", "ErrorMsg" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcRspTransferField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcRspTransferField implements Structure.ByValue {
	}
}
