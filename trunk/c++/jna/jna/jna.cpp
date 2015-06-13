// jna.cpp : 定义 DLL 应用程序的导出函数。
//

#include "stdafx.h"
#include "jna.h"
#include "Windows.h"

Callback fun;

Callback1 fun1;

bool start;

DWORD WINAPI run(LPVOID lpParameter)
{
	if (!fun)
	{
		return 0;
	}
	SYSTEMTIME sys;
	memset(&sys, 0, sizeof(sys));
	for (int i = 0; i < 10000; i++)
	{
		if (start)
		{
			GetLocalTime(&sys);
			char tmp[64];
			sprintf(tmp, "1011|IF1506|%d|%4d%02d%02d|%02d:%02d:%02d|%03d|%.3f|%.3f|%.3f|%.3f|%.3f", 100 + i, sys.wYear, sys.wMonth, sys.wDay, sys.wHour, sys.wMinute, sys.wSecond, sys.wMilliseconds, 0.01, 0.01, 0.01, 0.01, 0.01);
			(*fun)(tmp);
			Sleep(250);
		}
	}
	return 0;
}

DWORD WINAPI run1(LPVOID lpParameter)
{
	if (!fun1)
	{
		return 0;
	}
	SYSTEMTIME sys;
	memset(&sys, 0, sizeof(sys));
	for (int i = 0; i < 10000; i++)
	{
		if (start)
		{
			/*if (i % 5 == 1)
			{
			(*fun)(NULL);
			Sleep(250)
			continue;
			}*/
			GetLocalTime(&sys);
			CallBackData data;
			memset(&data, 0, sizeof(data));
			data.id = 100 + i;
			if (i % 5 != 2)
			{
				strcpy(data.name, "IF1506");
			}
			
			char date[9];
			sprintf(date, "%4d%02d%02d", sys.wYear, sys.wMonth, sys.wDay);
			strcpy(data.date, date);

			char time[9];
			sprintf(time, "%02d:%02d:%02d", sys.wHour, sys.wMinute, sys.wSecond);
			strcpy(data.time, time);
			data.millis = sys.wMilliseconds;
			data.price = 0.01;
			data.price1 = 0.01;
			data.price2 = 0.01;
			data.price3 = 0.01;
			data.price4 = 0.01;
			(*fun1)(&data);
			Sleep(250);
		}
	}
	return 0;
}


///注册回调事件
JNA_API void regesiter(Callback callback)
{
	fun = callback;
}

///注册回调事件
JNA_API void regesiter1(Callback1 callback)
{
	fun1 = callback;
}

///订阅数据
JNA_API void subData()
{
	if (fun1)
	{
		start = true;
		CreateThread(NULL, 0, &run1, NULL, 0, NULL);
		return;
	}
	
	if (fun)
	{
		start = true;
		CreateThread(NULL, 0, &run, NULL, 0, NULL);
	}
}

///取消订阅数据
JNA_API void unsubData()
{
	if (fun || fun1)
	{
		start = false;
	}
}