#include "ThostFtdcTraderApi.h"
#include "thosttraderapi.h"

class CTraderSpi : public CThostFtdcTraderSpi
{
public:
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

	///登录请求响应
	virtual void OnRspUserLogin(CThostFtdcRspUserLoginField *pRspUserLogin, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///登出请求响应
	virtual void OnRspUserLogout(CThostFtdcUserLogoutField *pUserLogout, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///报单录入请求响应
	virtual void OnRspOrderInsert(CThostFtdcInputOrderField *pInputOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///预埋单录入请求响应
	virtual void OnRspParkedOrderInsert(CThostFtdcParkedOrderField *pParkedOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///预埋撤单录入请求响应
	virtual void OnRspParkedOrderAction(CThostFtdcParkedOrderActionField *pParkedOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///报单操作请求响应
	virtual void OnRspOrderAction(CThostFtdcInputOrderActionField *pInputOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///查询最大报单数量响应
	virtual void OnRspQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField *pQueryMaxOrderVolume, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///删除预埋单响应
	virtual void OnRspRemoveParkedOrder(CThostFtdcRemoveParkedOrderField *pRemoveParkedOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///删除预埋撤单响应
	virtual void OnRspRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField *pRemoveParkedOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询报单响应
	virtual void OnRspQryOrder(CThostFtdcOrderField *pOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询成交响应
	virtual void OnRspQryTrade(CThostFtdcTradeField *pTrade, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询投资者持仓响应
	virtual void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField *pInvestorPosition, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询资金账户响应
	virtual void OnRspQryTradingAccount(CThostFtdcTradingAccountField *pTradingAccount, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询投资者响应
	virtual void OnRspQryInvestor(CThostFtdcInvestorField *pInvestor, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询交易编码响应
	virtual void OnRspQryTradingCode(CThostFtdcTradingCodeField *pTradingCode, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询合约保证金率响应
	virtual void OnRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField *pInstrumentMarginRate, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询合约手续费率响应
	virtual void OnRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField *pInstrumentCommissionRate, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询交易所响应
	virtual void OnRspQryExchange(CThostFtdcExchangeField *pExchange, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询合约响应
	virtual void OnRspQryInstrument(CThostFtdcInstrumentField *pInstrument, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询行情响应
	virtual void OnRspQryDepthMarketData(CThostFtdcDepthMarketDataField *pDepthMarketData, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询投资者持仓明细响应
	virtual void OnRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField *pInvestorPositionDetail, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询客户通知响应
	virtual void OnRspQryNotice(CThostFtdcNoticeField *pNotice, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询投资者持仓明细响应
	virtual void OnRspQryInvestorPositionCombineDetail(CThostFtdcInvestorPositionCombineDetailField *pInvestorPositionCombineDetail, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///错误应答
	virtual void OnRspError(CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///报单通知
	virtual void OnRtnOrder(CThostFtdcOrderField *pOrder);

	///成交通知
	virtual void OnRtnTrade(CThostFtdcTradeField *pTrade);

	///报单录入错误回报
	virtual void OnErrRtnOrderInsert(CThostFtdcInputOrderField *pInputOrder, CThostFtdcRspInfoField *pRspInfo);

	///报单操作错误回报
	virtual void OnErrRtnOrderAction(CThostFtdcOrderActionField *pOrderAction, CThostFtdcRspInfoField *pRspInfo);

	///合约交易状态通知
	virtual void OnRtnInstrumentStatus(CThostFtdcInstrumentStatusField *pInstrumentStatus);

	///交易通知
	virtual void OnRtnTradingNotice(CThostFtdcTradingNoticeInfoField *pTradingNoticeInfo);

	///提示条件单校验错误
	virtual void OnRtnErrorConditionalOrder(CThostFtdcErrorConditionalOrderField *pErrorConditionalOrder);

	///请求查询预埋单响应
	virtual void OnRspQryParkedOrder(CThostFtdcParkedOrderField *pParkedOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询预埋撤单响应
	virtual void OnRspQryParkedOrderAction(CThostFtdcParkedOrderActionField *pParkedOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

	///请求查询交易通知响应
	virtual void OnRspQryTradingNotice(CThostFtdcTradingNoticeField *pTradingNotice, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

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

	///报单录入请求响应回调
	OnRspOrderInsertfp onRspOrderInsertfp;

	///预埋单录入请求响应回调
	OnRspParkedOrderInsertfp onRspParkedOrderInsertfp;

	///预埋撤单录入请求响应回调
	OnRspParkedOrderActionfp onRspParkedOrderActionfp;

	///报单操作请求响应回调
	OnRspOrderActionfp onRspOrderActionfp;

	///查询最大报单数量响应回调
	OnRspQueryMaxOrderVolumefp onRspQueryMaxOrderVolumefp;

	///删除预埋单响应回调
	OnRspRemoveParkedOrderfp onRspRemoveParkedOrderfp;

	///删除预埋撤单响应回调
	OnRspRemoveParkedOrderActionfp onRspRemoveParkedOrderActionfp;

	///请求查询报单响应回调
	OnRspQryOrderfp onRspQryOrderfp;

	///请求查询成交响应回调
	OnRspQryTradefp onRspQryTradefp;

	///请求查询投资者持仓响应回调
	OnRspQryInvestorPositionfp onRspQryInvestorPositionfp;

	///请求查询资金账户响应回调
	OnRspQryTradingAccountfp onRspQryTradingAccountfp;

	///请求查询投资者响应回调
	OnRspQryInvestorfp onRspQryInvestorfp;

	///请求查询交易编码响应回调
	OnRspQryTradingCodefp onRspQryTradingCodefp;

	///请求查询合约保证金率响应回调
	OnRspQryInstrumentMarginRatefp onRspQryInstrumentMarginRatefp;

	///请求查询合约手续费率响应回调
	OnRspQryInstrumentCommissionRatefp onRspQryInstrumentCommissionRatefp;

	///请求查询交易所响应回调
	OnRspQryExchangefp onRspQryExchangefp;

	///请求查询合约响应回调
	OnRspQryInstrumentfp onRspQryInstrumentfp;

	///请求查询行情响应回调
	OnRspQryDepthMarketDatafp onRspQryDepthMarketDatafp;

	///请求查询投资者持仓明细响应回调
	OnRspQryInvestorPositionDetailfp onRspQryInvestorPositionDetailfp;

	///请求查询客户通知响应回调
	OnRspQryNoticefp onRspQryNoticefp;

	///请求查询投资者持仓明细响应回调
	OnRspQryInvestorPositionCombineDetailfp onRspQryInvestorPositionCombineDetailfp;

	//错误应答回调
	OnRspErrorfp onRspErrorfp;

	///报单通知
	OnRtnOrderfp onRtnOrderfp;

	///成交通知
	OnRtnTradefp onRtnTradefp;

	///报单录入错误回报
	OnErrRtnOrderInsertfp onErrRtnOrderInsertfp;

	///报单操作错误回报
	OnErrRtnOrderActionfp onErrRtnOrderActionfp;

	///合约交易状态通知
	OnRtnInstrumentStatusfp onRtnInstrumentStatusfp;

	///交易通知
	OnRtnTradingNoticefp onRtnTradingNoticefp;

	///提示条件单校验错误
	OnRtnErrorConditionalOrderfp onRtnErrorConditionalOrderfp;

	///请求查询预埋单响应回调
	OnRspQryParkedOrderfp onRspQryParkedOrderfp;

	///请求查询预埋撤单响应回调
	OnRspQryParkedOrderActionfp onRspQryParkedOrderActionfp;

	///请求查询交易通知响应回调
	OnRspQryTradingNoticefp onRspQryTradingNoticefp;
};

