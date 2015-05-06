#include "stdio.h"
#include "ThostFtdcMdApi.h"
#include "mdapi.h"

class CMdSpi : public CThostFtdcMdSpi
{
public:
	CMdSpi(){};
	~CMdSpi(){};
	///当客户端与交易后台建立起通信连接时（还未登录前），该方法被调用。
	virtual void OnFrontConnected();

	///当客户端与交易后台通信连接断开时，该方法被调用。当发生这个情况后，API会自动重新连接，客户端可不做处理。
	///@param nReason 错误原因
	///        0x1001 网络读失败
	///        0x1002 网络写失败
	///        0x2001 接收心跳超时
	///        0x2002 发送心跳失败
	///        0x2003 收到错误报文
	virtual void OnFrontDisconnected(int nReason);

	///心跳超时警告。当长时间未收到报文时，该方法被调用。
	///@param nTimeLapse 距离上次接收报文的时间
	//virtual void OnHeartBeatWarning(int nTimeLapse);

	///登录请求响应
	virtual void OnRspUserLogin(CThostFtdcRspUserLoginField *pRspUserLogin, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///登出请求响应
	virtual void OnRspUserLogout(CThostFtdcUserLogoutField *pUserLogout, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///错误应答
	virtual void OnRspError(CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///订阅行情应答
	virtual void OnRspSubMarketData(CThostFtdcSpecificInstrumentField *pSpecificInstrument, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///取消订阅行情应答
	virtual void OnRspUnSubMarketData(CThostFtdcSpecificInstrumentField *pSpecificInstrument, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///深度行情通知
	virtual void OnRtnDepthMarketData(CThostFtdcDepthMarketDataField *pDepthMarketData);

public:
	///连接成功回调
	OnFrontConnectedfp onFrontConnectedfp;

	///连接失败回调
	OnFrontDisconnectedfp onFrontDisconnectedfp;

	///心跳警告回调
	//OnHeartBeatWarningfp onHeartBeatWarningfp;

	///登录响应回调
	OnRspUserLoginfp onRspUserLoginfp;

	///登出响应回调
	OnRspUserLogoutfp onRspUserLogoutfp;

	//错误应答回调
	OnRspErrorfp onRspErrorfp;

	///行情订阅回调
	OnRspSubMarketDatafp onRspSubMarketDatafp;

	///行情取消订阅回调
	OnRspUnSubMarketDatafp onRspUnSubMarketDatafp;

	//深度行情回调
	OnRtnDepthMarketDatafp onRtnDepthMarketDatafp;
};