package demo.call;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.sun.jna.Callback;

/**
 * @author 陈霖 2015-5-4
 */
public class StructCallBack implements Callback {

	/**
	 * @param data
	 */
	public void output(DataField.ByReference data) {
//		System.out.println(Arrays.asList(data.str));
//		try {
		int len = 0;
		for(byte b: data.str)
		{
			if(b==0)
				break;
			++len;
		}
		System.out.println("");
			System.out.println(new String(data.str,0, len));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
	}
}
