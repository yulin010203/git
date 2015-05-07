package callback;

import com.sun.jna.Callback;

import struct.CThostFtdcRspInfoField;
import struct.CThostFtdcRspUserLoginField;

/**
 * 用户登录响应
 * 
 * @author 陈霖 2015-5-5
 */
public class OnRspUserLoginfp implements Callback {

	private ICallBack call;

	/**
	 * @param call
	 */
	public OnRspUserLoginfp(ICallBack call) {
		this.call = call;
	}

	/**
	 * @param pRspUserLogin
	 * @param pRspInfo
	 * @param nRequestID
	 * @param bIsLast
	 */
	public void onRspUserLogin(CThostFtdcRspUserLoginField.ByReference pRspUserLogin, CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast) {
		call.onRspUserLogin(pRspUserLogin, pRspInfo, nRequestID, bIsLast);
	}
}
