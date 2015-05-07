package callback;

import struct.CThostFtdcDepthMarketDataField;
import struct.CThostFtdcRspInfoField;
import struct.CThostFtdcRspUserLoginField;
import struct.CThostFtdcSpecificInstrumentField;
import struct.CThostFtdcUserLogoutField;

/**
 * @author 陈霖 2015-5-5
 */
public interface ICallBack {

	/**
	 * 连接成功
	 */
	public void onFrontConnected();

	/**
	 * 连接断开
	 * 
	 * @param nReason
	 *            错误原因(0x1001 网络读失败, 0x1002 网络写失败, 0x2001 接收心跳超时, 0x2002 发送心跳失败, 0x2003 收到错误报文)
	 */
	public void onFrontDisconnected(int nReason);

	/**
	 * 登录请求响应
	 * 
	 * @param pRspUserLogin
	 *            用户登录响应
	 * @param pRspInfo
	 *            响应信息
	 * @param nRequestID
	 *            请求ID
	 * @param bIsLast
	 *            结束符
	 */
	public void onRspUserLogin(CThostFtdcRspUserLoginField.ByReference pRspUserLogin, CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast);

	/**
	 * 登出请求响应
	 * 
	 * @param pUserLogout
	 *            用户登出响应
	 * @param pRspInfo
	 *            响应信息
	 * @param nRequestID
	 *            请求ID
	 * @param bIsLast
	 *            结束符
	 */
	public void onRspUserLogout(CThostFtdcUserLogoutField.ByReference pUserLogout, CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast);

	/**
	 * 错误应答
	 * 
	 * @param pRspInfo
	 *            错误响应
	 * @param nRequestID
	 *            请求ID
	 * @param bIsLast
	 *            结束符
	 */
	public void onRspError(CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast);

	/**
	 * 订阅行情应答
	 * 
	 * @param pSpecificInstrument
	 *            合约
	 * @param pRspInfo
	 *            响应信息
	 * @param nRequestID
	 *            请求ID
	 * @param bIsLast
	 *            结束符
	 */
	public void onRspSubMarketData(CThostFtdcSpecificInstrumentField.ByReference pSpecificInstrument, CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast);

	/**
	 * 取消订阅行情应答
	 * 
	 * @param pSpecificInstrument
	 *            合约
	 * @param pRspInfo
	 *            响应信息
	 * @param nRequestID
	 *            请求ID
	 * @param bIsLast
	 *            结束符
	 */
	public void onRspUnSubMarketData(CThostFtdcSpecificInstrumentField.ByReference pSpecificInstrument, CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast);

	/**
	 * 深度行情通知
	 * 
	 * @param pDepthMarketData
	 *            深度行情
	 */
	public void onRtnDepthMarketData(CThostFtdcDepthMarketDataField.ByReference pDepthMarketData);
}
