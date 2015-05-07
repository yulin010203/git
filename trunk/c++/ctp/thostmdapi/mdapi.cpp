// mdapi.cpp : 定义 DLL 应用程序的导出函数。
//

#include "stdafx.h"
#include "mdapi.h"
#include "mdspi.h"
#include "Windows.h"

///行情回调事件
CMdSpi *mdspi;

///行情api
CThostFtdcMdApi *mdapi;

///创建MdApi
///@param pszFlowPath 存贮订阅信息文件的目录，默认为当前目录
///@return 创建出的UserApi
///modify for udp marketdata
//static CThostFtdcMdApi *CreateFtdcMdApi(const char *pszFlowPath = "", const bool bIsUsingUdp=false);
MD_API void CreateFtdcMdApi(const char *pszFlowPath, const bool bIsUsingUdp)
{
	mdapi = CThostFtdcMdApi::CreateFtdcMdApi(pszFlowPath, bIsUsingUdp);
	mdspi = new CMdSpi();
}

///删除接口对象本身
///@remark 不再使用本接口对象时,调用该函数删除接口对象
MD_API void Release()
{
	mdapi->Release();
}

///初始化
///@remark 初始化运行环境,只有调用后,接口才开始工作
MD_API void Init()
{
	mdapi->Init();
}

///等待接口线程结束运行
///@return 线程退出代码
MD_API int Join()
{
	return mdapi->Join();
}

///获取当前交易日
///@retrun 获取到的交易日
///@remark 只有登录成功后,才能得到正确的交易日
MD_API const char *GetTradingDay()
{
	return mdapi->GetTradingDay();
}

///注册前置机网络地址
///@param pszFrontAddress：前置机网络地址。
///@remark 网络地址的格式为：“protocol://ipaddress:port”，如：”tcp://127.0.0.1:17001”。 
///@remark “tcp”代表传输协议，“127.0.0.1”代表服务器地址。”17001”代表服务器端口号。
MD_API void RegisterFront(char *pszFrontAddress)
{
	mdapi->RegisterFront(pszFrontAddress);
}

///注册名字服务器网络地址
///@param pszNsAddress：名字服务器网络地址。
///@remark 网络地址的格式为：“protocol://ipaddress:port”，如：”tcp://127.0.0.1:12001”。 
///@remark “tcp”代表传输协议，“127.0.0.1”代表服务器地址。”12001”代表服务器端口号。
///@remark RegisterNameServer优先于RegisterFront
MD_API void RegisterNameServer(char *pszNsAddress)
{
	mdapi->RegisterNameServer(pszNsAddress);
}

///注册回调接口
///@param pSpi 派生自回调接口类的实例
//MD_API void RegisterSpi(CThostFtdcMdSpi *pSpi) = 0;
MD_API void RegisterSpifp(OnFrontConnectedfp onFrontConnectedfp, OnFrontDisconnectedfp onFrontDisconnectedfp, OnRspUserLoginfp onRspUserLoginfp, OnRspUserLogoutfp onRspUserLogoutfp, OnRspErrorfp onRspErrorfp, OnRspSubMarketDatafp onRspSubMarketDatafp, OnRspUnSubMarketDatafp onRspUnSubMarketDatafp, OnRtnDepthMarketDatafp onRtnDepthMarketDatafp)
{
	mdspi->onFrontConnectedfp = onFrontConnectedfp;
	mdspi->onFrontDisconnectedfp = onFrontDisconnectedfp;
	mdspi->onRspUserLoginfp = onRspUserLoginfp;
	mdspi->onRspUserLogoutfp = onRspUserLogoutfp;
	mdspi->onRspErrorfp = onRspErrorfp;
	mdspi->onRspSubMarketDatafp = onRspSubMarketDatafp;
	mdspi->onRspUnSubMarketDatafp = onRspUnSubMarketDatafp;
	mdspi->onRtnDepthMarketDatafp = onRtnDepthMarketDatafp;
	mdapi->RegisterSpi(mdspi);
}

///订阅行情。
///@param ppInstrumentID 合约ID  
///@param nCount 要订阅/退订行情的合约个数
///@remark 
MD_API int SubscribeMarketData(char *ppInstrumentID[], int nCount)
{
	return mdapi->SubscribeMarketData(ppInstrumentID, nCount);
}

///退订行情。
///@param ppInstrumentID 合约ID  
///@param nCount 要订阅/退订行情的合约个数
///@remark 
MD_API int UnSubscribeMarketData(char *ppInstrumentID[], int nCount)
{
	return mdapi->UnSubscribeMarketData(ppInstrumentID, nCount);
}

///用户登录请求
MD_API int ReqUserLogin(CThostFtdcReqUserLoginField *pReqUserLoginField, int nRequestID)
{
	char buf[512];
	sprintf(buf, "login:%s|%s|%s|%d",pReqUserLoginField->BrokerID,pReqUserLoginField->UserID, pReqUserLoginField->Password, nRequestID);
	OutputDebugString(buf);
	return mdapi->ReqUserLogin(pReqUserLoginField, nRequestID);
}

///登出请求
MD_API int ReqUserLogout(CThostFtdcUserLogoutField *pUserLogout, int nRequestID)
{
	return mdapi->ReqUserLogout(pUserLogout, nRequestID);
}
