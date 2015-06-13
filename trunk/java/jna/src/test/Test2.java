package test;

import java.util.Arrays;

import demo.Clibrary;
import demo.call.CallBack2;
import demo.call.DataField;
import demo.call.PrintCallBack;
import demo.call.PrintCallBack1;
import demo.call.PrintCallBack2;
import demo.call.StructCallBack;

public class Test2 {

	public static void main(String[] args) {
		// CallBack2 call = new CallBack2();
		// Clibrary.INSTANCE.registerFun(new PrintCallBack(call), new PrintCallBack1(call), new PrintCallBack2(call));
		Clibrary.INSTANCE.registerFun1(new StructCallBack());
		String str = "Hello World!";
		// System.out.println(Arrays.asList(str.getBytes()));
		// Clibrary.INSTANCE.output(str, str, str);
		DataField.ByReference data = new DataField.ByReference();
		byte[] bytes = str.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			data.str[i] = bytes[i];
		}
		// data.writeField("str");
		// data.str = str.getBytes();
		data.str[str.getBytes().length] = 0;
		Clibrary.INSTANCE.output1(data);
	}
}
