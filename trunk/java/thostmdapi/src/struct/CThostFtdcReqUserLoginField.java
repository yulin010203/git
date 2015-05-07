package struct;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 用户登录请求
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcReqUserLoginField extends Structure {

	/**
	 * 交易日
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 用户代码
	 */
	public byte[] UserID = new byte[16];
	/**
	 * 密码
	 */
	public byte[] Password = new byte[41];
	/**
	 * 用户端产品信息
	 */
	public byte[] UserProductInfo = new byte[11];
	/**
	 * 接口端产品信息
	 */
	public byte[] InterfaceProductInfo = new byte[11];
	/**
	 * 协议信息
	 */
	public byte[] ProtocolInfo = new byte[11];
	/**
	 * Mac地址
	 */
	public byte[] MacAddress = new byte[21];
	/**
	 * 动态密码
	 */
	public byte[] OneTimePassword = new byte[41];
	/**
	 * 终端IP地址
	 */
	public byte[] ClientIPAddress = new byte[16];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "TradingDay", "BrokerID", "UserID", "Password", "UserProductInfo", "InterfaceProductInfo", "ProtocolInfo", "MacAddress", "OneTimePassword",
				"ClientIPAddress" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcReqUserLoginField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcReqUserLoginField implements Structure.ByValue {
	};
}
