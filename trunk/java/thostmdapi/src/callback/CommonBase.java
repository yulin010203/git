package callback;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-5-5
 */
public class CommonBase implements Callback {

	/**
	 * 回调接口
	 */
	protected ICallBack call;

	/**
	 * @param call
	 */
	protected CommonBase(ICallBack call) {
		this.call = call;
	}
}
