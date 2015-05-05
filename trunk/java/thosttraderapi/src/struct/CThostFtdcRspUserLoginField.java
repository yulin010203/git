package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 用户登录应答
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcRspUserLoginField extends Structure {
	/**
	 * 交易日
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 登录成功时间
	 */
	public byte[] LoginTime = new byte[9];
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 用户代码
	 */
	public byte[] UserID = new byte[16];
	/**
	 * 交易系统名称
	 */
	public byte[] SystemName = new byte[41];
	/**
	 * 前置编号
	 */
	public int FrontID;
	/**
	 * 会话编号
	 */
	public int SessionID;
	/**
	 * 最大报单引用
	 */
	public byte[] MaxOrderRef = new byte[13];
	/**
	 * 上期所时间
	 */
	public byte[] SHFETime = new byte[9];
	/**
	 * 大商所时间
	 */
	public byte[] DCETime = new byte[9];
	/**
	 * 郑商所时间
	 */
	public byte[] CZCETime = new byte[9];
	/**
	 * 中金所时间
	 */
	public byte[] FFEXTime = new byte[9];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "TradingDay", "BrokerID", "UserID", "Password", "UserProductInfo", "InterfaceProductInfo", "ProtocolInfo", "MacAddress", "OneTimePassword",
				"ClientIPAddress" });
	}

	/**
	 * 指针
	 */
	public class ByReference extends CThostFtdcRspUserLoginField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public class ByValue extends CThostFtdcRspUserLoginField implements Structure.ByValue {
	}
}
