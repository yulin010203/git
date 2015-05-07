package callback;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-5-5
 */
public class OnFrontDisconnectedfp implements Callback {

	private ICallBack call;

	/**
	 * @param call
	 */
	public OnFrontDisconnectedfp(ICallBack call) {
		this.call = call;
	}

	/**
	 * @param nReason
	 */
	public void onFrontDisconnected(int nReason){
		call.onFrontDisconnected(nReason);
	}
}
