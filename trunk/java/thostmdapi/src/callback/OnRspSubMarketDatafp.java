package callback;

import com.sun.jna.Callback;

import struct.CThostFtdcRspInfoField;
import struct.CThostFtdcSpecificInstrumentField;

/**
 * 行情订阅
 * 
 * @author 陈霖 2015-5-5
 */
public class OnRspSubMarketDatafp implements Callback {

	private ICallBack call;

	/**
	 * @param call
	 */
	public OnRspSubMarketDatafp(ICallBack call) {
		this.call = call;
	}

	/**
	 * @param pSpecificInstrument
	 * @param pRspInfo
	 * @param nRequestID
	 * @param bIsLast
	 */
	public void onRspSubMarketData(CThostFtdcSpecificInstrumentField.ByReference pSpecificInstrument, CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast) {
		call.onRspSubMarketData(pSpecificInstrument, pRspInfo, nRequestID, bIsLast);
	}
}
