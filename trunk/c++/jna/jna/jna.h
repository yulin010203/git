#if !defined(JNA_API_H)
#define JNA_API_H

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "stdio.h"

#if defined(WIN32)
#define JNA_API extern "C" __declspec(dllexport)
#define JNA_API_IMPORT __declspec(dllimport)
#else
#define JNA_API extern "C"
#endif

typedef int CallBackIntType;
typedef double CallBackDoubleType;
typedef char CallBackNameType[10];
typedef char CallBackDateType[9];
typedef char CallBackTimeType[9];
struct CallBackData
{
	CallBackIntType id;

	CallBackNameType name;

	CallBackDateType date;

	CallBackTimeType time;

	CallBackIntType millis;
	
	CallBackDoubleType price;

	CallBackDoubleType price1;

	CallBackDoubleType price2;

	CallBackDoubleType price3;

	CallBackDoubleType price4;
};

///回调函数
typedef void (*Callback)(const char *str);

///结构体指针回调
typedef void (*Callback1)(CallBackData *data);

///注册回调事件
JNA_API void regesiter(Callback callback);

///注册结构体指针回调
JNA_API void regesiter1(Callback1 callback);

///订阅数据
JNA_API void subData();

///取消订阅数据
JNA_API void unsubData();

#endif
