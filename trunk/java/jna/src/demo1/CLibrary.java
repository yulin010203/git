package demo1;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author 陈霖 2015-6-13
 */
public interface CLibrary extends Library {

	CLibrary INSTANCE = (CLibrary) Native.loadLibrary("jna", CLibrary.class);

	/**
	 * 注册回调事件
	 * 
	 * @param callback
	 *            回调事件
	 */
	void regesiter(Callback callback);
	
	/**
	 * @param callback
	 */
	void regesiter1(Callback callback);

	/**
	 * 订阅数据
	 */
	void subData();

	/**
	 * 取消订阅数据
	 */
	void unsubData();
}
