package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 用户登出请求
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcUserLogoutField extends Structure {

	/**
	 * 经纪公司代码
	 */
	public byte[] BrokerID = new byte[11];
	/**
	 * 用户代码
	 */
	public byte[] UserID = new byte[16];

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "UserID" });
	}

	/**
	 * 指针
	 */
	public class ByReference extends CThostFtdcUserLogoutField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public class ByValue extends CThostFtdcUserLogoutField implements Structure.ByValue {
	}
}
