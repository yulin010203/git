#include "stdafx.h"
#include "thostmdapi.h"
#include "thostmdspi.h"
#include "Windows.h"

///连接成功
void CMdSpi::OnFrontConnected()
{
	if (onFrontConnectedfp != 0)
	{
		(*onFrontConnectedfp)();
	} else 
	{
		OutputDebugString("onFrontConnectedfp failure!\n");
	}
}

///连接失败
void CMdSpi::OnFrontDisconnected(int nReason)
{
	if (onFrontDisconnectedfp != 0)
	{
		OutputDebugString("onFrontDisconnectedfp success!\n");
		(*onFrontDisconnectedfp)(nReason);
	}else
	{
		OutputDebugString("onFrontDisconnectedfp failure!\n");
	}
}

///登录响应
void CMdSpi::OnRspUserLogin(CThostFtdcRspUserLoginField *pRspUserLogin, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspUserLoginfp != 0)
	{
		(*onRspUserLoginfp)(pRspUserLogin, pRspInfo, nRequestID, bIsLast);
	}
}

///登出响应
void CMdSpi::OnRspUserLogout(CThostFtdcUserLogoutField *pUserLogout, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspUserLogoutfp != 0)
	{
		(*onRspUserLogoutfp)(pUserLogout, pRspInfo, nRequestID, bIsLast);
	}
}

///错误应答
void CMdSpi::OnRspError(CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspErrorfp != 0)
	{
		(*onRspErrorfp)(pRspInfo, nRequestID, bIsLast);
	}
}

///订阅行情
void CMdSpi::OnRspSubMarketData(CThostFtdcSpecificInstrumentField *pSpecificInstrument, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspSubMarketDatafp != 0)
	{
		(*onRspSubMarketDatafp)(pSpecificInstrument, pRspInfo, nRequestID, bIsLast);
	}
}

///取消订阅行情
void CMdSpi::OnRspUnSubMarketData(CThostFtdcSpecificInstrumentField *pSpecificInstrument, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspUnSubMarketDatafp != 0)
	{
		(*onRspUnSubMarketDatafp)(pSpecificInstrument, pRspInfo, nRequestID, bIsLast);
	}
}

///深度行情
void CMdSpi::OnRtnDepthMarketData(CThostFtdcDepthMarketDataField *pDepthMarketData)
{
	if (onRtnDepthMarketDatafp != 0)
	{
		(*onRtnDepthMarketDatafp)(pDepthMarketData);
	}
}