package test;

import com.sun.jna.NativeLibrary;

import demo1.CLibrary;
import demo1.CallBack;
import demo1.CallBack1;

public class Test1 {
	private static CallBack callback =  new CallBack();
	private static CallBack1 callback1 =  new CallBack1();

	public static void main(String[] args) throws InterruptedException {
		NativeLibrary.addSearchPath("jna", System.getProperty("user.dir") + "\\lib");
//		CLibrary.INSTANCE.regesiter(callback);
		CLibrary.INSTANCE.regesiter1(callback1);
		CLibrary.INSTANCE.subData();
		Thread.sleep(100000);
	}
}
