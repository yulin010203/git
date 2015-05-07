package callback;

import com.sun.jna.Callback;

import struct.CThostFtdcDepthMarketDataField;

/**
 * 深度行情
 * 
 * @author 陈霖 2015-5-5
 */
public class OnRtnDepthMarketDatafp implements Callback{

	private ICallBack call;
	/**
	 * @param call
	 */
	public OnRtnDepthMarketDatafp(ICallBack call) {
		this.call = call;
	}

	/**
	 * @param pDepthMarketData
	 */
	public void onRtnDepthMarketData(CThostFtdcDepthMarketDataField.ByReference pDepthMarketData) {
		call.onRtnDepthMarketData(pDepthMarketData);
	}
}
