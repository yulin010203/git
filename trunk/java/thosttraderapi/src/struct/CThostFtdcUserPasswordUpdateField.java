package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 用户口令变更
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcUserPasswordUpdateField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 用户代码
	 */
	public byte[] UserID = new byte[16];
	/**
	 * 原来的口令
	 */
	public byte[] OldPassword = new byte[41];
	/**
	 * 新的口令
	 */
	public byte[] NewPassword = new byte[41];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "UserID", "OldPassword", "NewPassword" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcUserPasswordUpdateField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcUserPasswordUpdateField implements Structure.ByValue {
	};
}
