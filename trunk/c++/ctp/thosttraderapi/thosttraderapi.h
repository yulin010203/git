#ifndef _DLL_TRADER_API_H
#define _DLL_TRADER_API_H
#define TRADER_API_EXPORTS
#ifdef TRADER_API_EXPORTS
#define TRADER_API extern "C" __declspec(dllexport)
#else
#define TRADER_API  __declspec(dllimport)
#endif
#include "ThostFtdcUserApiStruct.h"

///////////////////////////////定义回调函数指针////////////////////////////////////////////
///当客户端与交易后台建立起通信连接时（还未登录前），该方法被调用。
typedef void (*OnFrontConnectedfp)();

///当客户端与交易后台通信连接断开时，该方法被调用。当发生这个情况后，API会自动重新连接，客户端可不做处理。
///@param nReason 错误原因
///        0x1001 网络读失败
///        0x1002 网络写失败
///        0x2001 接收心跳超时
///        0x2002 发送心跳失败
///        0x2003 收到错误报文
typedef void (*OnFrontDisconnectedfp)(int nReason);

///登录请求响应
typedef void (*OnRspUserLoginfp)(CThostFtdcRspUserLoginField *pRspUserLogin, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///登出请求响应
typedef void (*OnRspUserLogoutfp)(CThostFtdcUserLogoutField *pUserLogout, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///报单录入请求响应
typedef void (*OnRspOrderInsertfp)(CThostFtdcInputOrderField *pInputOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///预埋单录入请求响应
typedef void (*OnRspParkedOrderInsertfp)(CThostFtdcParkedOrderField *pParkedOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///预埋撤单录入请求响应
typedef void (*OnRspParkedOrderActionfp)(CThostFtdcParkedOrderActionField *pParkedOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///报单操作请求响应
typedef void (*OnRspOrderActionfp)(CThostFtdcInputOrderActionField *pInputOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///查询最大报单数量响应
typedef void (*OnRspQueryMaxOrderVolumefp)(CThostFtdcQueryMaxOrderVolumeField *pQueryMaxOrderVolume, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///删除预埋单响应
typedef void (*OnRspRemoveParkedOrderfp)(CThostFtdcRemoveParkedOrderField *pRemoveParkedOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///删除预埋撤单响应
typedef void (*OnRspRemoveParkedOrderActionfp)(CThostFtdcRemoveParkedOrderActionField *pRemoveParkedOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询报单响应
typedef void (*OnRspQryOrderfp)(CThostFtdcOrderField *pOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询成交响应
typedef void (*OnRspQryTradefp)(CThostFtdcTradeField *pTrade, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询投资者持仓响应
typedef void (*OnRspQryInvestorPositionfp)(CThostFtdcInvestorPositionField *pInvestorPosition, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询资金账户响应
typedef void (*OnRspQryTradingAccountfp)(CThostFtdcTradingAccountField *pTradingAccount, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询投资者响应
typedef void (*OnRspQryInvestorfp)(CThostFtdcInvestorField *pInvestor, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询交易编码响应
typedef void (*OnRspQryTradingCodefp)(CThostFtdcTradingCodeField *pTradingCode, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询合约保证金率响应
typedef void (*OnRspQryInstrumentMarginRatefp)(CThostFtdcInstrumentMarginRateField *pInstrumentMarginRate, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询合约手续费率响应
typedef void (*OnRspQryInstrumentCommissionRatefp)(CThostFtdcInstrumentCommissionRateField *pInstrumentCommissionRate, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询交易所响应
typedef void (*OnRspQryExchangefp)(CThostFtdcExchangeField *pExchange, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询合约响应
typedef void (*OnRspQryInstrumentfp)(CThostFtdcInstrumentField *pInstrument, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询行情响应
typedef void (*OnRspQryDepthMarketDatafp)(CThostFtdcDepthMarketDataField *pDepthMarketData, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询投资者持仓明细响应
typedef void (*OnRspQryInvestorPositionDetailfp)(CThostFtdcInvestorPositionDetailField *pInvestorPositionDetail, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询客户通知响应
typedef void (*OnRspQryNoticefp)(CThostFtdcNoticeField *pNotice, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询投资者持仓明细响应
typedef void (*OnRspQryInvestorPositionCombineDetailfp)(CThostFtdcInvestorPositionCombineDetailField *pInvestorPositionCombineDetail, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///错误应答
typedef void (*OnRspErrorfp)(CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///报单通知
typedef void (*OnRtnOrderfp)(CThostFtdcOrderField *pOrder);

///成交通知
typedef void (*OnRtnTradefp)(CThostFtdcTradeField *pTrade);

///报单录入错误回报
typedef void (*OnErrRtnOrderInsertfp)(CThostFtdcInputOrderField *pInputOrder, CThostFtdcRspInfoField *pRspInfo);

///报单操作错误回报
typedef void (*OnErrRtnOrderActionfp)(CThostFtdcOrderActionField *pOrderAction, CThostFtdcRspInfoField *pRspInfo);

///合约交易状态通知
typedef void (*OnRtnInstrumentStatusfp)(CThostFtdcInstrumentStatusField *pInstrumentStatus);

///交易通知
typedef void (*OnRtnTradingNoticefp)(CThostFtdcTradingNoticeInfoField *pTradingNoticeInfo);

///提示条件单校验错误
typedef void (*OnRtnErrorConditionalOrderfp)(CThostFtdcErrorConditionalOrderField *pErrorConditionalOrder);

///请求查询预埋单响应
typedef void (*OnRspQryParkedOrderfp)(CThostFtdcParkedOrderField *pParkedOrder, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询预埋撤单响应
typedef void (*OnRspQryParkedOrderActionfp)(CThostFtdcParkedOrderActionField *pParkedOrderAction, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///请求查询交易通知响应
typedef void (*OnRspQryTradingNoticefp)(CThostFtdcTradingNoticeField *pTradingNotice, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

/////////////////////////////////////定义调用接口函数///////////////////////
///创建TraderApi
///@param pszFlowPath 存贮订阅信息文件的目录，默认为当前目录
///@return 创建出的UserApi
//modify for udp marketdata
TRADER_API void CreateFtdcTraderApi(const char *pszFlowPath, const bool bIsUsingUdp);

///删除接口对象本身
///@remark 不再使用本接口对象时,调用该函数删除接口对象
TRADER_API void Release();

///初始化
///@remark 初始化运行环境,只有调用后,接口才开始工作
TRADER_API void Init();

///等待接口线程结束运行
///@return 线程退出代码
TRADER_API int Join();

///获取当前交易日
///@retrun 获取到的交易日
///@remark 只有登录成功后,才能得到正确的交易日
TRADER_API const char *GetTradingDay();

///注册前置机网络地址
///@param pszFrontAddress：前置机网络地址。
///@remark 网络地址的格式为：“protocol://ipaddress:port”，如：”tcp://127.0.0.1:17001”。 
///@remark “tcp”代表传输协议，“127.0.0.1”代表服务器地址。”17001”代表服务器端口号。
TRADER_API void RegisterFront(char *pszFrontAddress);

///注册名字服务器网络地址
///@param pszNsAddress：名字服务器网络地址。
///@remark 网络地址的格式为：“protocol://ipaddress:port”，如：”tcp://127.0.0.1:12001”。 
///@remark “tcp”代表传输协议，“127.0.0.1”代表服务器地址。”12001”代表服务器端口号。
///@remark RegisterNameServer优先于RegisterFront
TRADER_API void RegisterNameServer(char *pszNsAddress);

///注册回调接口
///@param pSpi 派生自回调接口类的实例
TRADER_API void RegisterSpifp(
	///连接成功回调
	OnFrontConnectedfp onFrontConnectedfp,

	///连接失败回调
	OnFrontDisconnectedfp onFrontDisconnectedfp,

	///心跳警告回调
	//OnHeartBeatWarningfp onHeartBeatWarningfp,

	///登录响应回调
	OnRspUserLoginfp onRspUserLoginfp,

	///登出响应回调
	OnRspUserLogoutfp onRspUserLogoutfp,

	///报单录入请求响应回调
	OnRspOrderInsertfp onRspOrderInsertfp,

	///预埋单录入请求响应回调
	OnRspParkedOrderInsertfp onRspParkedOrderInsertfp,

	///预埋撤单录入请求响应回调
	OnRspParkedOrderActionfp onRspParkedOrderActionfp,

	///报单操作请求响应回调
	OnRspOrderActionfp onRspOrderActionfp,

	///查询最大报单数量响应回调
	OnRspQueryMaxOrderVolumefp onRspQueryMaxOrderVolumefp,

	///删除预埋单响应回调
	OnRspRemoveParkedOrderfp onRspRemoveParkedOrderfp,

	///删除预埋撤单响应回调
	OnRspRemoveParkedOrderActionfp onRspRemoveParkedOrderActionfp,

	///请求查询报单响应回调
	OnRspQryOrderfp onRspQryOrderfp,

	///请求查询成交响应回调
	OnRspQryTradefp onRspQryTradefp,

	///请求查询投资者持仓响应回调
	OnRspQryInvestorPositionfp onRspQryInvestorPositionfp,

	///请求查询资金账户响应回调
	OnRspQryTradingAccountfp onRspQryTradingAccountfp,

	///请求查询投资者响应回调
	OnRspQryInvestorfp onRspQryInvestorfp,

	///请求查询交易编码响应回调
	OnRspQryTradingCodefp onRspQryTradingCodefp,

	///请求查询合约保证金率响应回调
	OnRspQryInstrumentMarginRatefp onRspQryInstrumentMarginRatefp,

	///请求查询合约手续费率响应回调
	OnRspQryInstrumentCommissionRatefp onRspQryInstrumentCommissionRatefp,

	///请求查询交易所响应回调
	OnRspQryExchangefp onRspQryExchangefp,

	///请求查询合约响应回调
	OnRspQryInstrumentfp onRspQryInstrumentfp,

	///请求查询行情响应回调
	OnRspQryDepthMarketDatafp onRspQryDepthMarketDatafp,

	///请求查询投资者持仓明细响应回调
	OnRspQryInvestorPositionDetailfp onRspQryInvestorPositionDetailfp,

	///请求查询客户通知响应回调
	OnRspQryNoticefp onRspQryNoticefp,

	///请求查询投资者持仓明细响应回调
	OnRspQryInvestorPositionCombineDetailfp onRspQryInvestorPositionCombineDetailfp,

	//错误应答回调
	OnRspErrorfp onRspErrorfp,

	///报单通知
	OnRtnOrderfp onRtnOrderfp,

	///成交通知
	OnRtnTradefp onRtnTradefp,

	///报单录入错误回报
	OnErrRtnOrderInsertfp onErrRtnOrderInsertfp,

	///报单操作错误回报
	OnErrRtnOrderActionfp onErrRtnOrderActionfp,

	///合约交易状态通知
	OnRtnInstrumentStatusfp onRtnInstrumentStatusfp,

	///交易通知
	OnRtnTradingNoticefp onRtnTradingNoticefp,

	///提示条件单校验错误
	OnRtnErrorConditionalOrderfp onRtnErrorConditionalOrderfp,

	///请求查询预埋单响应回调
	OnRspQryParkedOrderfp onRspQryParkedOrderfp,

	///请求查询预埋撤单响应回调
	OnRspQryParkedOrderActionfp onRspQryParkedOrderActionfp,

	///请求查询交易通知响应回调
	OnRspQryTradingNoticefp onRspQryTradingNoticefp);

///订阅私有流。
///@param nResumeType 私有流重传方式  
///        THOST_TERT_RESTART:从本交易日开始重传
///        THOST_TERT_RESUME:从上次收到的续传
///        THOST_TERT_QUICK:只传送登录后私有流的内容
///@remark 该方法要在Init方法前调用。若不调用则不会收到私有流的数据。
TRADER_API void SubscribePrivateTopic(THOST_TE_RESUME_TYPE nResumeType);

///订阅公共流。
///@param nResumeType 公共流重传方式  
///        THOST_TERT_RESTART:从本交易日开始重传
///        THOST_TERT_RESUME:从上次收到的续传
///        THOST_TERT_QUICK:只传送登录后公共流的内容
///@remark 该方法要在Init方法前调用。若不调用则不会收到公共流的数据。
TRADER_API void SubscribePublicTopic(THOST_TE_RESUME_TYPE nResumeType);

///用户登录请求
TRADER_API int ReqUserLogin(CThostFtdcReqUserLoginField *pReqUserLoginField, int nRequestID);

///登出请求
TRADER_API int ReqUserLogout(CThostFtdcUserLogoutField *pUserLogout, int nRequestID);

///报单录入请求
TRADER_API int ReqOrderInsert(CThostFtdcInputOrderField *pInputOrder, int nRequestID);

///预埋单录入请求
TRADER_API int ReqParkedOrderInsert(CThostFtdcParkedOrderField *pParkedOrder, int nRequestID);

///预埋撤单录入请求
TRADER_API int ReqParkedOrderAction(CThostFtdcParkedOrderActionField *pParkedOrderAction, int nRequestID);

///报单操作请求
TRADER_API int ReqOrderAction(CThostFtdcInputOrderActionField *pInputOrderAction, int nRequestID);

///查询最大报单数量请求
TRADER_API int ReqQueryMaxOrderVolume(CThostFtdcQueryMaxOrderVolumeField *pQueryMaxOrderVolume, int nRequestID);

///请求删除预埋单
TRADER_API int ReqRemoveParkedOrder(CThostFtdcRemoveParkedOrderField *pRemoveParkedOrder, int nRequestID);

///请求删除预埋撤单
TRADER_API int ReqRemoveParkedOrderAction(CThostFtdcRemoveParkedOrderActionField *pRemoveParkedOrderAction, int nRequestID);

///请求查询报单
TRADER_API int ReqQryOrder(CThostFtdcQryOrderField *pQryOrder, int nRequestID);

///请求查询成交
TRADER_API int ReqQryTrade(CThostFtdcQryTradeField *pQryTrade, int nRequestID);

///请求查询投资者持仓
TRADER_API int ReqQryInvestorPosition(CThostFtdcQryInvestorPositionField *pQryInvestorPosition, int nRequestID);

///请求查询资金账户
TRADER_API int ReqQryTradingAccount(CThostFtdcQryTradingAccountField *pQryTradingAccount, int nRequestID);

///请求查询投资者
TRADER_API int ReqQryInvestor(CThostFtdcQryInvestorField *pQryInvestor, int nRequestID);

///请求查询交易编码
TRADER_API int ReqQryTradingCode(CThostFtdcQryTradingCodeField *pQryTradingCode, int nRequestID);

///请求查询合约保证金率
TRADER_API int ReqQryInstrumentMarginRate(CThostFtdcQryInstrumentMarginRateField *pQryInstrumentMarginRate, int nRequestID);

///请求查询合约手续费率
TRADER_API int ReqQryInstrumentCommissionRate(CThostFtdcQryInstrumentCommissionRateField *pQryInstrumentCommissionRate, int nRequestID);

///请求查询交易所
TRADER_API int ReqQryExchange(CThostFtdcQryExchangeField *pQryExchange, int nRequestID);

///请求查询合约
TRADER_API int ReqQryInstrument(CThostFtdcQryInstrumentField *pQryInstrument, int nRequestID);

///请求查询行情
TRADER_API int ReqQryDepthMarketData(CThostFtdcQryDepthMarketDataField *pQryDepthMarketData, int nRequestID);

///请求查询投资者持仓明细
TRADER_API int ReqQryInvestorPositionDetail(CThostFtdcQryInvestorPositionDetailField *pQryInvestorPositionDetail, int nRequestID);

///请求查询客户通知
TRADER_API int ReqQryNotice(CThostFtdcQryNoticeField *pQryNotice, int nRequestID);

///请求查询结算信息确认
TRADER_API int ReqQrySettlementInfoConfirm(CThostFtdcQrySettlementInfoConfirmField *pQrySettlementInfoConfirm, int nRequestID);

///请求查询投资者持仓明细
TRADER_API int ReqQryInvestorPositionCombineDetail(CThostFtdcQryInvestorPositionCombineDetailField *pQryInvestorPositionCombineDetail, int nRequestID);

///请求查询预埋单
TRADER_API int ReqQryParkedOrder(CThostFtdcQryParkedOrderField *pQryParkedOrder, int nRequestID);

///请求查询预埋撤单
TRADER_API int ReqQryParkedOrderAction(CThostFtdcQryParkedOrderActionField *pQryParkedOrderAction, int nRequestID);

///请求查询交易通知
TRADER_API int ReqQryTradingNotice(CThostFtdcQryTradingNoticeField *pQryTradingNotice, int nRequestID);

#endif