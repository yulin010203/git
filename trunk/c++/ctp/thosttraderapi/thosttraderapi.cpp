// traderapi.cpp : 定义 DLL 应用程序的导出函数。
//

#include "stdafx.h"
#include "thosttraderapi.h"
#include "thosttraderspi.h"

///交易回调事件
CTraderSpi *traderspi;

///交易源api
CThostFtdcTraderApi *traderapi;

/////////////////////////////////////定义调用接口函数///////////////////////
///创建TraderApi
///@param pszFlowPath 存贮订阅信息文件的目录，默认为当前目录
///@return 创建出的UserApi
//modify for udp marketdata
TRADER_API void CreateFtdcTraderApi(const char *pszFlowPath, const bool bIsUsingUdp)
{
	traderapi = CThostFtdcTraderApi::CreateFtdcTraderApi(pszFlowPath, bIsUsingUdp);
	traderspi = new CTraderSpi();
}

///删除接口对象本身
///@remark 不再使用本接口对象时,调用该函数删除接口对象
TRADER_API void Release()
{
	traderapi->Release();
}

///初始化
///@remark 初始化运行环境,只有调用后,接口才开始工作
TRADER_API void Init()
{
	traderapi->Init();
}

///等待接口线程结束运行
///@return 线程退出代码
TRADER_API int Join()
{
	return traderapi->Join();
}

///获取当前交易日
///@retrun 获取到的交易日
///@remark 只有登录成功后,才能得到正确的交易日
TRADER_API const char *GetTradingDay()
{
	return traderapi->GetTradingDay();
}

///注册前置机网络地址
///@param pszFrontAddress：前置机网络地址。
///@remark 网络地址的格式为：“protocol://ipaddress:port”，如：”tcp://127.0.0.1:17001”。 
///@remark “tcp”代表传输协议，“127.0.0.1”代表服务器地址。”17001”代表服务器端口号。
TRADER_API void RegisterFront(char *pszFrontAddress)
{
	traderapi->RegisterFront(pszFrontAddress);
}

///注册名字服务器网络地址
///@param pszNsAddress：名字服务器网络地址。
///@remark 网络地址的格式为：“protocol://ipaddress:port”，如：”tcp://127.0.0.1:12001”。 
///@remark “tcp”代表传输协议，“127.0.0.1”代表服务器地址。”12001”代表服务器端口号。
///@remark RegisterNameServer优先于RegisterFront
TRADER_API void RegisterNameServer(char *pszNsAddress)
{
	traderapi->RegisterNameServer(pszNsAddress);
}

///注册回调接口
TRADER_API void RegisterSpifp(OnFrontConnectedfp onFrontConnectedfp, OnFrontDisconnectedfp onFrontDisconnectedfp, OnRspUserLoginfp onRspUserLoginfp, OnRspUserLogoutfp onRspUserLogoutfp, OnRspOrderInsertfp onRspOrderInsertfp, OnRspParkedOrderInsertfp onRspParkedOrderInsertfp, OnRspParkedOrderActionfp onRspParkedOrderActionfp, OnRspOrderActionfp onRspOrderActionfp, OnRspQueryMaxOrderVolumefp onRspQueryMaxOrderVolumefp, OnRspRemoveParkedOrderfp onRspRemoveParkedOrderfp, OnRspRemoveParkedOrderActionfp onRspRemoveParkedOrderActionfp, OnRspQryOrderfp onRspQryOrderfp, OnRspQryTradefp onRspQryTradefp, OnRspQryInvestorPositionfp onRspQryInvestorPositionfp, OnRspQryTradingAccountfp onRspQryTradingAccountfp, OnRspQryInvestorfp onRspQryInvestorfp, OnRspQryTradingCodefp onRspQryTradingCodefp, OnRspQryInstrumentMarginRatefp onRspQryInstrumentMarginRatefp, OnRspQryInstrumentCommissionRatefp onRspQryInstrumentCommissionRatefp, OnRspQryExchangefp onRspQryExchangefp, OnRspQryInstrumentfp onRspQryInstrumentfp, OnRspQryDepthMarketDatafp onRspQryDepthMarketDatafp, OnRspQryInvestorPositionDetailfp onRspQryInvestorPositionDetailfp, OnRspQryNoticefp onRspQryNoticefp, OnRspQryInvestorPositionCombineDetailfp onRspQryInvestorPositionCombineDetailfp, OnRspErrorfp onRspErrorfp, OnRtnOrderfp onRtnOrderfp, OnRtnTradefp onRtnTradefp, OnErrRtnOrderInsertfp onErrRtnOrderInsertfp, OnErrRtnOrderActionfp onErrRtnOrderActionfp, OnRtnInstrumentStatusfp onRtnInstrumentStatusfp, OnRtnTradingNoticefp onRtnTradingNoticefp, OnRtnErrorConditionalOrderfp onRtnErrorConditionalOrderfp, OnRspQryParkedOrderfp onRspQryParkedOrderfp, OnRspQryParkedOrderActionfp onRspQryParkedOrderActionfp, OnRspQryTradingNoticefp onRspQryTradingNoticefp)
{

}
///订阅私有流。
///@param nResumeType 私有流重传方式  
///        THOST_TERT_RESTART:从本交易日开始重传
///        THOST_TERT_RESUME:从上次收到的续传
///        THOST_TERT_QUICK:只传送登录后私有流的内容
///@remark 该方法要在Init方法前调用。若不调用则不会收到私有流的数据。
TRADER_API void SubscribePrivateTopic(THOST_TE_RESUME_TYPE nResumeType)
{
	traderapi->SubscribePrivateTopic(nResumeType);
}

///订阅公共流。
///@param nResumeType 公共流重传方式  
///        THOST_TERT_RESTART:从本交易日开始重传
///        THOST_TERT_RESUME:从上次收到的续传
///        THOST_TERT_QUICK:只传送登录后公共流的内容
///@remark 该方法要在Init方法前调用。若不调用则不会收到公共流的数据。
TRADER_API void SubscribePublicTopic(THOST_TE_RESUME_TYPE nResumeType)
{
	traderapi->SubscribePublicTopic(nResumeType);
}

///用户登录请求
TRADER_API int ReqUserLogin(CThostFtdcReqUserLoginField *pReqUserLoginField, int nRequestID)
{
	return traderapi->ReqUserLogin(pReqUserLoginField, nRequestID);
}

///登出请求
TRADER_API int ReqUserLogout(CThostFtdcUserLogoutField *pUserLogout, int nRequestID)
{
	return traderapi->ReqUserLogout(pUserLogout, nRequestID);
}

///报单录入请求
TRADER_API int ReqOrderInsert(CThostFtdcInputOrderField *pInputOrder, int nRequestID)
{
	return traderapi->ReqOrderInsert(pInputOrder, )
}

///预埋单录入请求
TRADER_API int ReqParkedOrderInsert(CThostFtdcParkedOrderField *pParkedOrder, int nRequestID)
{
	return traderapi->ReqParkedOrderInsert(pParkedOrder, nRequestID);
}

///预埋撤单录入请求
TRADER_API int ReqParkedOrderAction(CThostFtdcParkedOrderActionField *pParkedOrderAction, int nRequestID)
{
	return traderapi->ReqParkedOrderAction(pParkedOrderAction, nRequestID);
}

///报单操作请求
TRADER_API int ReqOrderAction(CThostFtdcInputOrderActionField *pInputOrderAction, int nRequestID)
{
	return traderapi->ReqOrderAction(pInputOrderAction, nRequestID);
}

///查询最大报单数量请求
TRADER_API int ReqQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField *pQueryMaxOrderVolume, int nRequestID)
{
	return traderapi->ReqQueryMaxOrderVolume(pQueryMaxOrderVolume, nRequestID);
}

///请求删除预埋单
TRADER_API int ReqRemoveParkedOrder(CThostFtdcRemoveParkedOrderField *pRemoveParkedOrder, int nRequestID)
{
	return traderapi->ReqRemoveParkedOrder(pRemoveParkedOrder, nRequestID);
}

///请求删除预埋撤单
TRADER_API int ReqRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField *pRemoveParkedOrderAction, int nRequestID)
{
	return traderapi->ReqRemoveParkedOrderAction(pRemoveParkedOrderAction, nRequestID);
}

///请求查询报单
TRADER_API int ReqQryOrder(CThostFtdcQryOrderField *pQryOrder, int nRequestID)
{
	return traderapi->ReqQryOrder(pQryOrder, nRequestID);
}

///请求查询成交
TRADER_API int ReqQryTrade(CThostFtdcQryTradeField *pQryTrade, int nRequestID)
{
	return traderapi->ReqQryTrade(pQryTrade, nRequestID);
}

///请求查询投资者持仓
TRADER_API int ReqQryInvestorPosition(CThostFtdcQryInvestorPositionField *pQryInvestorPosition, int nRequestID)
{
	return traderapi->ReqQryInvestorPosition(pQryInvestorPosition, nRequestID);
}

///请求查询资金账户
TRADER_API int ReqQryTradingAccount(CThostFtdcQryTradingAccountField *pQryTradingAccount, int nRequestID)
{
	return traderapi->ReqQryTradingAccount(pQryTradingAccount, nRequestID);
}

///请求查询投资者
TRADER_API int ReqQryInvestor(CThostFtdcQryInvestorField *pQryInvestor, int nRequestID)
{
	return traderapi->ReqQryInvestor(pQryInvestor, nRequestID);
}

///请求查询交易编码
TRADER_API int ReqQryTradingCode(CThostFtdcQryTradingCodeField *pQryTradingCode, int nRequestID)
{
	return traderapi->ReqQryTradingCode(pQryTradingCode, nRequestID);
}

///请求查询合约保证金率
TRADER_API int ReqQryInstrumentMarginRate(CThostFtdcQryInstrumentMarginRateField *pQryInstrumentMarginRate, int nRequestID)
{
	return traderapi->ReqQryInstrumentMarginRate(pQryInstrumentMarginRate, nRequestID);
}

///请求查询合约手续费率
TRADER_API int ReqQryInstrumentCommissionRate(CThostFtdcQryInstrumentCommissionRateField *pQryInstrumentCommissionRate, int nRequestID)
{
	return traderapi->ReqQryInstrumentCommissionRate(pQryInstrumentCommissionRate, nRequestID);
}

///请求查询交易所
TRADER_API int ReqQryExchange(CThostFtdcQryExchangeField *pQryExchange, int nRequestID)
{
	return traderapi->ReqQryExchange(pQryExchange, nRequestID);
}

///请求查询合约
TRADER_API int ReqQryInstrument(CThostFtdcQryInstrumentField *pQryInstrument, int nRequestID)
{
	return traderapi->ReqQryInstrument(pQryInstrument, nRequestID);
}

///请求查询行情
TRADER_API int ReqQryDepthMarketData(CThostFtdcQryDepthMarketDataField *pQryDepthMarketData, int nRequestID)
{
	return traderapi->ReqQryDepthMarketData(pQryDepthMarketData, nRequestID);
}

///请求查询投资者持仓明细
TRADER_API int ReqQryInvestorPositionDetail(CThostFtdcQryInvestorPositionDetailField *pQryInvestorPositionDetail, int nRequestID)
{
	return traderapi->ReqQryInvestorPositionDetail(pQryInvestorPositionDetail, nRequestID);
}

///请求查询客户通知
TRADER_API int ReqQryNotice(CThostFtdcQryNoticeField *pQryNotice, int nRequestID)
{
	return traderapi->ReqQryNotice(pQryNotice, nRequestID);
}

///请求查询结算信息确认
TRADER_API int ReqQrySettlementInfoConfirm(CThostFtdcQrySettlementInfoConfirmField *pQrySettlementInfoConfirm, int nRequestID)
{
	return traderapi->ReqQrySettlementInfoConfirm(pQrySettlementInfoConfirm, nRequestID);
}

///请求查询投资者持仓明细
TRADER_API int ReqQryInvestorPositionCombineDetail(CThostFtdcQryInvestorPositionCombineDetailField *pQryInvestorPositionCombineDetail, int nRequestID)
{
	return traderapi->ReqQryInvestorPositionCombineDetail(pQryInvestorPositionCombineDetail, nRequestID);
}

///请求查询预埋单
TRADER_API int ReqQryParkedOrder(CThostFtdcQryParkedOrderField *pQryParkedOrder, int nRequestID)
{
	return traderapi->ReqQryParkedOrder(pQryParkedOrder, nRequestID);
}

///请求查询预埋撤单
TRADER_API int ReqQryParkedOrderAction(CThostFtdcQryParkedOrderActionField *pQryParkedOrderAction, int nRequestID)
{
	return traderapi->ReqQryParkedOrderAction(pQryParkedOrderAction, nRequestID);
}

///请求查询交易通知
TRADER_API int ReqQryTradingNotice(CThostFtdcQryTradingNoticeField *pQryTradingNotice, int nRequestID)
{
	return traderapi->ReqQryTradingNotice(pQryTradingNotice, nRequestID);
}
