import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import struct.CThostFtdcReqUserLoginField;
import util.ByteUtil;

import com.sun.jna.Callback;
import com.sun.jna.NativeLibrary;

import library.Clibrary;
import callback.ICallBack;
import callback.OnFrontConnectedfp;
import callback.OnFrontDisconnectedfp;
import callback.OnRspErrorfp;
import callback.OnRspSubMarketDatafp;
import callback.OnRspUnSubMarketDatafp;
import callback.OnRspUserLoginfp;
import callback.OnRspUserLogoutfp;
import callback.OnRtnDepthMarketDatafp;

public class Test implements ICallBack {

	public static void main(String[] args) throws InterruptedException {
		Test t = new Test();
		String dir = System.getProperty("user.dir");
		NativeLibrary.addSearchPath("mdapi", dir + "/lib");
		Clibrary.INSTANCE.CreateFtdcMdApi("", false);
		Clibrary.INSTANCE.RegisterSpifp(new OnFrontConnectedfp(t), new OnFrontDisconnectedfp(t), new OnRspUserLoginfp(t), new OnRspUserLogoutfp(t), new OnRspErrorfp(t), new OnRspSubMarketDatafp(t),
				new OnRspUnSubMarketDatafp(t), new OnRtnDepthMarketDatafp(t));
		Clibrary.INSTANCE.RegisterFront("tcp://180.168.212.79:31213");// 41003397/270015
		Clibrary.INSTANCE.Init();
		Thread.sleep(1000);
		System.out.println(Clibrary.INSTANCE.GetTradingDay());
		CThostFtdcReqUserLoginField.ByReference login = new CThostFtdcReqUserLoginField.ByReference();
		ByteUtil.memset(login.BrokerID, "8000");
//		byte[] brokerId = "8000".getBytes(); 
//		System.arraycopy(brokerId, 0, login.BrokerID, 0, brokerId.length);
		ByteUtil.memset(login.UserID, "41003397");
//		byte[] accountId = "41003397".getBytes(); 
//		System.arraycopy(accountId, 0, login.UserID, 0, accountId.length);
		ByteUtil.memset(login.Password, "270015");
//		byte[] password = "270015".getBytes(); 
//		System.arraycopy(password, 0, login.Password, 0, password.length);
		Clibrary.INSTANCE.ReqUserLogin(login, 12134);
		String[] arr = new String[] { "IF1505", "IF1506" };
		Clibrary.INSTANCE.SubscribeMarketData(arr, 2);
		Thread.sleep(100000);

	}

	private static Method getCallbackMethod(Class cls) {
		// Look at only public methods defined by the Callback class
		Class[] interfaces = cls.getInterfaces();
		Method[] pubMethods = cls.getDeclaredMethods();
		Method[] classMethods = cls.getMethods();
		Set pmethods = new HashSet(Arrays.asList(pubMethods));
		pmethods.retainAll(Arrays.asList(classMethods));

		// Remove Object methods disallowed as callback method names
		for (Iterator i = pmethods.iterator(); i.hasNext();) {
			Method m = (Method) i.next();
			if (Callback.FORBIDDEN_NAMES.contains(m.getName())) {
				i.remove();
			}
		}
		Method[] methods = (Method[]) pmethods.toArray(new Method[pmethods.size()]);
		if (methods.length == 1) {
			// return checkMethod(methods[0]);
			return methods[0];
		}
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			if (Callback.METHOD_NAME.equals(m.getName())) {
				// return checkMethod(m);
				return m;
			}
		}
		String msg = "Callback must implement a single public method, " + "or one public method named '" + Callback.METHOD_NAME + "'";
		throw new IllegalArgumentException(msg);
	}

	@Override
	public void onFrontConnected() {
		System.out.println("connected success");
	}

	@Override
	public void onFrontDisconnected(int nReason) {
		System.out.println("connected failure");
	}

	@Override
	public void onRspUserLogin(struct.CThostFtdcRspUserLoginField.ByReference pRspUserLogin, struct.CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast) {
		System.out.println("login success");
	}

	@Override
	public void onRspUserLogout(struct.CThostFtdcUserLogoutField.ByReference pUserLogout, struct.CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast) {
	}

	@Override
	public void onRspError(struct.CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast) {
	}

	@Override
	public void onRspSubMarketData(struct.CThostFtdcSpecificInstrumentField.ByReference pSpecificInstrument, struct.CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast) {
	}

	@Override
	public void onRspUnSubMarketData(struct.CThostFtdcSpecificInstrumentField.ByReference pSpecificInstrument, struct.CThostFtdcRspInfoField.ByReference pRspInfo, int nRequestID, boolean bIsLast) {
		System.out.println("sub market");
	}

	@Override
	public void onRtnDepthMarketData(struct.CThostFtdcDepthMarketDataField.ByReference pDepthMarketData) {
		System.out.println(ByteUtil.arraysToString(pDepthMarketData.InstrumentID));
	}
}
