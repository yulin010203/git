package test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.sun.jna.Callback;

import demo.Clibrary;
import demo.callback.CallBack;
import demo.callback.PrintCallBack;
import demo.callback.PrintCallBack1;
import demo.callback.PrintCallBack2;

public class Test {

	public static void main(String[] args) {
		String str = "Hello World!";
		CallBack call = new CallBack();
		// Clibrary.INSTANCE.registerFun(call);
//		Method m1 = getCallbackMethod(getClass((PrintCallBack)call));
//		Method m2 = getCallbackMethod(getClass((PrintCallBack1)call));
//		Method m3 = getCallbackMethod(getClass((PrintCallBack2)call));
		Clibrary.INSTANCE.registerFun(call, call, call);
		Clibrary.INSTANCE.output(str, str, str);
		// System.out.println(Clibrary.INSTANCE.add(1, 7));
	}
	
	private static Class getClass(PrintCallBack callback){
		return callback.getClass();
	}
	private static Class getClass(PrintCallBack1 callback){
		return callback.getClass();
	}
	private static Class getClass(PrintCallBack2 callback){
		return callback.getClass();
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
}
