package callback;

import com.sun.jna.Callback;

import struct.CThostFtdcRspInfoField;
import struct.CThostFtdcSpecificInstrumentField;

/**
 * 取消订阅
 * 
 * @author 陈霖 2015-5-5
 */
public class OnRspUnSubMarketDatafp implements Callback {

	private ICallBack call;

	/**
	 * @param call
	 */
	public OnRspUnSubMarketDatafp(ICallBack call) {
		this.call = call;
	}

	/**
	 * @param pSpecificInstrument
	 * @param pRspInfo
	 * @param nRequestID
	 * @param bIsLast
	 */
	public void onRspUnSubMarketData(CThostFtdcSpecificInstrumentField.ByReference pSpecificInstrument, CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast) {
		call.onRspUnSubMarketData(pSpecificInstrument, pRspInfo, nRequestID, bIsLast);
	}
}
