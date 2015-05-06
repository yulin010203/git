package library;

import struct.CThostFtdcReqUserLoginField;
import struct.CThostFtdcUserLogoutField;
import callback.OnFrontConnectedfp;
import callback.OnFrontDisconnectedfp;
import callback.OnRspErrorfp;
import callback.OnRspSubMarketDatafp;
import callback.OnRspUnSubMarketDatafp;
import callback.OnRspUserLoginfp;
import callback.OnRspUserLogoutfp;
import callback.OnRtnDepthMarketDatafp;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author 陈霖 2015-5-5
 */
public interface Clibrary extends Library {
	
	public static String PATH = System.getProperty("user.dir");

	/**
	 * 实例
	 */
//	Clibrary INSTANCE = (Clibrary) Native.loadLibrary(PATH + "\\lib\\mdapi", Clibrary.class);
	Clibrary INSTANCE = (Clibrary) Native.loadLibrary("mdapi", Clibrary.class);

	/**
	 * 创建MdApi
	 * 
	 * @param pszFlowPath
	 *            存贮订阅信息文件的目录，默认为当前目录
	 * @param bIsUsingUdp
	 *            是否UDP
	 */
	public void CreateFtdcMdApi(String pszFlowPath, boolean bIsUsingUdp);

	/**
	 * 删除接口对象本身
	 * 
	 * @remark 不再使用本接口对象时,调用该函数删除接口对象
	 */
	public void Release();

	/**
	 * 初始化
	 * 
	 * @remark 初始化运行环境,只有调用后,接口才开始工作
	 */

	public void Init();

	/**
	 * 等待接口线程结束运行
	 * 
	 * @return 线程退出代码
	 */
	public int Join();

	/**
	 * 获取当前交易日
	 * 
	 * @return 获取到的交易日
	 * @remark 只有登录成功后,才能得到正确的交易日
	 */
	public String GetTradingDay();

	/**
	 * 注册前置机网络地址
	 * 
	 * @param pszFrontAddress
	 *            前置机网络地址。
	 * @remark 网络地址的格式为：“protocol://ipaddress:port”，如：”tcp://127.0.0.1:17001”。
	 * @remark “tcp”代表传输协议，“127.0.0.1”代表服务器地址。”17001”代表服务器端口号。
	 */
	public void RegisterFront(String pszFrontAddress);

	/**
	 * 注册名字服务器网络地址
	 * 
	 * @param pszNsAddress
	 *            名字服务器网络地址。
	 * @remark 网络地址的格式为：“protocol://ipaddress:port”，如：”tcp://127.0.0.1:12001”。
	 * @remark “tcp”代表传输协议，“127.0.0.1”代表服务器地址。”12001”代表服务器端口号。
	 * @remark RegisterNameServer优先于RegisterFront
	 */
	public void RegisterNameServer(String pszNsAddress);

	/**
	 * 注册回调接口
	 * 
	 * @param onFrontConnectedfp
	 *            连接成功
	 * @param onFrontDisconnectedfp
	 *            连接断开
	 * @param onRspUserLoginfp
	 *            登录响应
	 * @param onRspUserLogoutfp
	 *            登出响应
	 * @param onRspErrorfp
	 *            错误响应
	 * @param onRspSubMarketDatafp
	 *            订阅行情
	 * @param onRspUnSubMarketDatafp
	 *            取消订阅
	 * @param onRtnDepthMarketDatafp
	 *            深度行情
	 */
	public void RegisterSpifp(Callback onFrontConnectedfp, Callback onFrontDisconnectedfp, Callback onRspUserLoginfp, Callback onRspUserLogoutfp,
			Callback onRspErrorfp, Callback onRspSubMarketDatafp, Callback onRspUnSubMarketDatafp, Callback onRtnDepthMarketDatafp);

	/**
	 * 订阅行情。
	 * 
	 * @param ppInstrumentID
	 *            合约ID
	 * @param nCount
	 *            要订阅/退订行情的合约个数
	 * @return int
	 */
	public int SubscribeMarketData(String ppInstrumentID[], int nCount);

	/**
	 * 退订行情。
	 * 
	 * @param ppInstrumentID
	 *            合约ID
	 * @param nCount
	 *            要订阅/退订行情的合约个数
	 * @return int
	 */
	public int UnSubscribeMarketData(String[] ppInstrumentID, int nCount);

	/**
	 * 用户登录请求
	 * 
	 * @param pReqUserLoginField
	 * @param nRequestID
	 * @return int
	 */
	public int ReqUserLogin(CThostFtdcReqUserLoginField.ByReference pReqUserLoginField, int nRequestID);

	/**
	 * 登出请求
	 * 
	 * @param pUserLogout
	 * @param nRequestID
	 * @return int
	 */
	public int ReqUserLogout(CThostFtdcUserLogoutField.ByReference pUserLogout, int nRequestID);
}
