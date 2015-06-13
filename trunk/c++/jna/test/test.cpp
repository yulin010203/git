// test.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include "jna.h"
#include "stdio.h"
#include "Windows.h"
#include <iostream>
using namespace std;

void print(const char *str)
{
	cout << str << endl;
}

void print1(CallBackData *data)
{
	char tmp[64];
	sprintf(tmp, "1011|%s|%d|%s|%s|%d", data->name, data->id, data->date, data->time, data->millis);
	cout << tmp << endl;
}

int _tmain(int argc, _TCHAR* argv[])
{
	//regesiter(&print);
	regesiter1(&print1);
	subData();
	Sleep(10000);
	unsubData();
	return 0;
}

