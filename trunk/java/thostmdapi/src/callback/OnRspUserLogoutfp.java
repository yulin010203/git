package callback;

import com.sun.jna.Callback;

import struct.CThostFtdcRspInfoField;
import struct.CThostFtdcUserLogoutField;

/**
 * 用户登出响应
 * 
 * @author 陈霖 2015-5-5
 */
public class OnRspUserLogoutfp implements Callback {

	private ICallBack call;

	/**
	 * @param call
	 */
	public OnRspUserLogoutfp(ICallBack call) {
		this.call = call;
	}

	/**
	 * @param pUserLogout
	 * @param pRspInfo
	 * @param nRequestID
	 * @param bIsLast
	 */
	public void onRspUserLogout(CThostFtdcUserLogoutField.ByReference pUserLogout, CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast) {
		call.onRspUserLogout(pUserLogout, pRspInfo, nRequestID, bIsLast);
	}
}
