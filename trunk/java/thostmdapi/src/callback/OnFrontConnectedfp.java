package callback;

import com.sun.jna.Callback;

/**
 * 连接成功
 * 
 * @author 陈霖 2015-5-5
 */
public class OnFrontConnectedfp implements Callback {

	private ICallBack call;

	/**
	 * @param call
	 */
	public OnFrontConnectedfp(ICallBack call) {
		this.call = call;
	}

	/**
	 * 连接成功
	 */
	public void onFrontConnectedfp() {
		call.onFrontConnected();
	}
}
