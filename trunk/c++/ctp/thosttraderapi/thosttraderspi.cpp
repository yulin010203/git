#include "stdafx.h"
#include "thosttraderspi.h"

///连接成功响应
void CTraderSpi::OnFrontConnected()
{
	if (onFrontConnectedfp)
	{
		(*onFrontConnectedfp)();
	}
}
///连接断开响应
void CTraderSpi::OnFrontDisconnected(int nReason)
{
	if (onFrontDisconnectedfp)
	{
		(*onFrontDisconnectedfp)(nReason);
	}
}

///登录请求响应
void CTraderSpi::OnRspUserLogin(CThostFtdcRspUserLoginField *pRspUserLogin, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspUserLoginfp)
	{
		(*onRspUserLoginfp)(pRspUserLogin, pRspInfo, nRequestID, bIsLast);
	}
}

///登出请求响应
void CTraderSpi::OnRspUserLogout(CThostFtdcUserLogoutField *pUserLogout, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspUserLogoutfp)
	{
		(*onRspUserLogoutfp)(pUserLogout, pRspInfo, nRequestID, bIsLast);
	}
}

///报单录入请求响应
void CTraderSpi::OnRspOrderInsert(CThostFtdcInputOrderField *pInputOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspOrderInsertfp)
	{
		(*onRspOrderInsertfp)(pInputOrder, pRspInfo, nRequestID, bIsLast);
	}
}

///预埋单录入请求响应
void CTraderSpi::OnRspParkedOrderInsert(CThostFtdcParkedOrderField *pParkedOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspParkedOrderInsertfp)
	{
		(*onRspParkedOrderInsertfp)(pParkedOrder, pRspInfo, nRequestID, bIsLast);
	}
}

///预埋撤单录入请求响应
void CTraderSpi::OnRspParkedOrderAction(CThostFtdcParkedOrderActionField *pParkedOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspParkedOrderActionfp)
	{
		(*onRspParkedOrderActionfp)(pParkedOrderAction, pRspInfo, nRequestID, bIsLast);
	}
}

///报单操作请求响应
void CTraderSpi::OnRspOrderAction(CThostFtdcInputOrderActionField *pInputOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspOrderActionfp)
	{
		(*onRspOrderActionfp)(pInputOrderAction, pRspInfo, nRequestID, bIsLast);
	}
}

///查询最大报单数量响应
void CTraderSpi::OnRspQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField *pQueryMaxOrderVolume, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQueryMaxOrderVolumefp)
	{
		(*onRspQueryMaxOrderVolumefp)(pQueryMaxOrderVolume, pRspInfo, nRequestID, bIsLast);
	}
}

///删除预埋单响应
void CTraderSpi::OnRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField *pRemoveParkedOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspRemoveParkedOrderfp)
	{
		(*onRspRemoveParkedOrderfp)(pRemoveParkedOrder, pRspInfo, nRequestID, bIsLast);
	}
}

///删除预埋撤单响应
void CTraderSpi::OnRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField *pRemoveParkedOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspRemoveParkedOrderActionfp)
	{
		(*onRspRemoveParkedOrderActionfp)(pRemoveParkedOrderAction, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询报单响应
void CTraderSpi::OnRspQryOrder(CThostFtdcOrderField *pOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryOrderfp)
	{
		(*onRspQryOrderfp)(pOrder, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询成交响应
void CTraderSpi::OnRspQryTrade(CThostFtdcTradeField *pTrade, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryTradefp)
	{
		(*onRspQryTradefp)(pTrade, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询投资者持仓响应
void CTraderSpi::OnRspQryInvestorPosition(CThostFtdcInvestorPositionField *pInvestorPosition, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryInvestorPositionfp)
	{
		(*onRspQryInvestorPositionfp)(pInvestorPosition, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询资金账户响应
void CTraderSpi::OnRspQryTradingAccount(CThostFtdcTradingAccountField *pTradingAccount, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryTradingAccountfp)
	{
		(*onRspQryTradingAccountfp)(pTradingAccount, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询投资者响应
void CTraderSpi::OnRspQryInvestor(CThostFtdcInvestorField *pInvestor, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryInvestorfp)
	{
		(*onRspQryInvestorfp)(pInvestor, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询交易编码响应
void CTraderSpi::OnRspQryTradingCode(CThostFtdcTradingCodeField *pTradingCode, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryTradingCodefp)
	{
		(*onRspQryTradingCodefp)(pTradingCode, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询合约保证金率响应
void CTraderSpi::OnRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField *pInstrumentMarginRate, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryInstrumentMarginRatefp)
	{
		(*onRspQryInstrumentMarginRatefp)(pInstrumentMarginRate, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询合约手续费率响应
void CTraderSpi::OnRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField *pInstrumentCommissionRate, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryInstrumentCommissionRatefp)
	{
		(*onRspQryInstrumentCommissionRatefp)(pInstrumentCommissionRate, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询交易所响应
void CTraderSpi::OnRspQryExchange(CThostFtdcExchangeField *pExchange, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryExchangefp)
	{
		(*onRspQryExchangefp)(pExchange, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询合约响应
void CTraderSpi::OnRspQryInstrument(CThostFtdcInstrumentField *pInstrument, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryInstrumentfp)
	{
		(*onRspQryInstrumentfp)(pInstrument, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询行情响应
void CTraderSpi::OnRspQryDepthMarketData(CThostFtdcDepthMarketDataField *pDepthMarketData, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryDepthMarketDatafp)
	{
		(*onRspQryDepthMarketDatafp)(pDepthMarketData, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询投资者持仓明细响应
void CTraderSpi::OnRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField *pInvestorPositionDetail, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryInvestorPositionDetailfp)
	{
		(*onRspQryInvestorPositionDetailfp)(pInvestorPositionDetail, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询客户通知响应
void CTraderSpi::OnRspQryNotice(CThostFtdcNoticeField *pNotice, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryNoticefp)
	{
		(*onRspQryNoticefp)(pNotice, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询投资者持仓明细响应
void CTraderSpi::OnRspQryInvestorPositionCombineDetail(CThostFtdcInvestorPositionCombineDetailField *pInvestorPositionCombineDetail, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryInvestorPositionCombineDetailfp)
	{
		(*onRspQryInvestorPositionCombineDetailfp)(pInvestorPositionCombineDetail, pRspInfo, nRequestID, bIsLast);
	}
}

///错误应答
void CTraderSpi::OnRspError(CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspErrorfp)
	{
		(*onRspErrorfp)(pRspInfo, nRequestID, bIsLast);
	}
}

///报单通知
void CTraderSpi::OnRtnOrder(CThostFtdcOrderField *pOrder)
{
	if (onRtnOrderfp)
	{
		(*onRtnOrderfp)(pOrder);
	}
}

///成交通知
void CTraderSpi::OnRtnTrade(CThostFtdcTradeField *pTrade)
{
	if (onRtnTradefp)
	{
		(*onRtnTradefp)(pTrade);
	}
}

///报单录入错误回报
void CTraderSpi::OnErrRtnOrderInsert(CThostFtdcInputOrderField *pInputOrder, CThostFtdcRspInfoField *pRspInfo)
{
	if (onErrRtnOrderInsertfp)
	{
		(*onErrRtnOrderInsertfp)(pInputOrder, pRspInfo);
	}
}

///报单操作错误回报
void CTraderSpi::OnErrRtnOrderAction(CThostFtdcOrderActionField *pOrderAction, CThostFtdcRspInfoField *pRspInfo)
{
	if (onErrRtnOrderActionfp)
	{
		(*onErrRtnOrderActionfp)(pOrderAction, pRspInfo);
	}
}

///合约交易状态通知
void CTraderSpi::OnRtnInstrumentStatus(CThostFtdcInstrumentStatusField *pInstrumentStatus)
{
	if (onRtnInstrumentStatusfp)
	{
		(*onRtnInstrumentStatusfp)(pInstrumentStatus);
	}
}

///交易通知
void CTraderSpi::OnRtnTradingNotice(CThostFtdcTradingNoticeInfoField *pTradingNoticeInfo)
{
	if (onRtnTradingNoticefp)
	{
		(*onRtnTradingNoticefp)(pTradingNoticeInfo);
	}
}

///提示条件单校验错误
void CTraderSpi::OnRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField *pErrorConditionalOrder)
{
	if (onRtnErrorConditionalOrderfp)
	{
		(*onRtnErrorConditionalOrderfp)(pErrorConditionalOrder);
	}
}

///请求查询预埋单响应
void CTraderSpi::OnRspQryParkedOrder(CThostFtdcParkedOrderField *pParkedOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryParkedOrderfp)
	{
		(*onRspQryParkedOrderfp)(pParkedOrder, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询预埋撤单响应
void CTraderSpi::OnRspQryParkedOrderAction(CThostFtdcParkedOrderActionField *pParkedOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryParkedOrderActionfp)
	{
		(*onRspQryParkedOrderActionfp)(pParkedOrderAction, pRspInfo, nRequestID, bIsLast);
	}
}

///请求查询交易通知响应
void CTraderSpi::OnRspQryTradingNotice(CThostFtdcTradingNoticeField *pTradingNotice, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast)
{
	if (onRspQryTradingNoticefp)
	{
		(*onRspQryTradingNoticefp)(pTradingNotice, pRspInfo, nRequestID, bIsLast);
	}
}