package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 客户端认证信息
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcAuthenticationInfoField extends Structure {
	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 用户代码
	 */
	public byte[] UserID = new byte[16];
	/**
	 * 用户端产品信息
	 */
	public byte[] UserProductInfo = new byte[11];
	/**
	 * 认证信息
	 */
	public byte[] AuthInfo = new byte[129];
	/**
	 * 是否为认证结果
	 */
	public int IsResult;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "UserID", "UserProductInfo", "AuthInfo", "IsResult" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcAuthenticationInfoField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcAuthenticationInfoField implements Structure.ByValue {
	}
}
