#ifndef _DLL_MD_API_H
#define _DLL_MD_API_H
#define MD_API_EXPORTS
#ifdef MD_API_EXPORTS
#define MD_API extern "C" __declspec(dllexport)
#else
#define MD_API  __declspec(dllimport)
#endif
#include "ThostFtdcUserApiStruct.h"
#include "ThostFtdcMdApi.h"
//#include "mdspi.h"

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

///心跳超时警告。当长时间未收到报文时，该方法被调用。
///@param nTimeLapse 距离上次接收报文的时间
//typedef void (*OnHeartBeatWarningfp)(int nTimeLapse);

///登录请求响应
typedef void (*OnRspUserLoginfp)(CThostFtdcRspUserLoginField *pRspUserLogin, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///登出请求响应
typedef void (*OnRspUserLogoutfp)(CThostFtdcUserLogoutField *pUserLogout, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///错误应答
typedef void (*OnRspErrorfp)(CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///订阅行情应答
typedef void (*OnRspSubMarketDatafp)(CThostFtdcSpecificInstrumentField *pSpecificInstrument, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///取消订阅行情应答
typedef void (*OnRspUnSubMarketDatafp)(CThostFtdcSpecificInstrumentField *pSpecificInstrument, CThostFtdcRspInfoField *pRspInfo, int nRequestID, bool bIsLast);

///深度行情通知
typedef void (*OnRtnDepthMarketDatafp)(CThostFtdcDepthMarketDataField *pDepthMarketData);

///创建MdApi
///@param pszFlowPath 存贮订阅信息文件的目录，默认为当前目录
///@return 创建出的UserApi
///modify for udp marketdata
//static CThostFtdcMdApi *CreateFtdcMdApi(const char *pszFlowPath = "", const bool bIsUsingUdp=false);
MD_API void CreateFtdcMdApi(const char *pszFlowPath, const bool bIsUsingUdp);

///删除接口对象本身
///@remark 不再使用本接口对象时,调用该函数删除接口对象
MD_API void Release();

///初始化
///@remark 初始化运行环境,只有调用后,接口才开始工作
MD_API void Init();

///等待接口线程结束运行
///@return 线程退出代码
MD_API int Join();

///获取当前交易日
///@retrun 获取到的交易日
///@remark 只有登录成功后,才能得到正确的交易日
MD_API const char *GetTradingDay();

///注册前置机网络地址
///@param pszFrontAddress：前置机网络地址。
///@remark 网络地址的格式为：“protocol://ipaddress:port”，如：”tcp://127.0.0.1:17001”。 
///@remark “tcp”代表传输协议，“127.0.0.1”代表服务器地址。”17001”代表服务器端口号。
MD_API void RegisterFront(char *pszFrontAddress);

///注册名字服务器网络地址
///@param pszNsAddress：名字服务器网络地址。
///@remark 网络地址的格式为：“protocol://ipaddress:port”，如：”tcp://127.0.0.1:12001”。 
///@remark “tcp”代表传输协议，“127.0.0.1”代表服务器地址。”12001”代表服务器端口号。
///@remark RegisterNameServer优先于RegisterFront
MD_API void RegisterNameServer(char *pszNsAddress);

///注册回调接口
///@param pSpi 派生自回调接口类的实例
//MD_API void RegisterSpi(CThostFtdcMdSpi *pSpi);
MD_API void RegisterSpifp(OnFrontConnectedfp onFrontConnectedfp, OnFrontDisconnectedfp onFrontDisconnectedfp, OnRspUserLoginfp onRspUserLoginfp, OnRspUserLogoutfp onRspUserLogoutfp, OnRspErrorfp onRspErrorfp, OnRspSubMarketDatafp onRspSubMarketDatafp, OnRspUnSubMarketDatafp onRspUnSubMarketDatafp, OnRtnDepthMarketDatafp onRtnDepthMarketDatafp);

///订阅行情。
///@param ppInstrumentID 合约ID  
///@param nCount 要订阅/退订行情的合约个数
///@remark 
MD_API int SubscribeMarketData(char *ppInstrumentID[], int nCount);

///退订行情。
///@param ppInstrumentID 合约ID  
///@param nCount 要订阅/退订行情的合约个数
///@remark 
MD_API int UnSubscribeMarketData(char *ppInstrumentID[], int nCount);

///用户登录请求
MD_API int ReqUserLogin(CThostFtdcReqUserLoginField *pReqUserLoginField, int nRequestID);

///登出请求
MD_API int ReqUserLogout(CThostFtdcUserLogoutField *pUserLogout, int nRequestID);

///行情回调事件
//CMdSpi *mdspi;

///行情api
//CThostFtdcMdApi *mdapi;
#endif