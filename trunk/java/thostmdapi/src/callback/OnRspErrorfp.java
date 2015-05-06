package callback;

import com.sun.jna.Callback;

import struct.CThostFtdcRspInfoField;

/**
 * 错误响应
 * 
 * @author 陈霖 2015-5-5
 */
public class OnRspErrorfp implements Callback {

	private ICallBack call;

	/**
	 * @param call
	 */
	public OnRspErrorfp(ICallBack call) {
		this.call = call;
	}

	/**
	 * @param pRspInfo
	 * @param nRequestID
	 * @param bIsLast
	 */
	public void onRspError(CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast) {
		call.onRspError(pRspInfo, nRequestID, bIsLast);
	}
}
