package util;

/**
 * byte数组工具
 * 
 * @author 陈霖 2015-5-5
 */
public class ByteUtil {

	/**
	 * @param bytes
	 * @return
	 */
	public static int getArrayLen(byte[] bytes) {
		int r = 0;
		for (byte b : bytes) {
			if (b == 0)
				break;
			r++;

		}
		return r;
	}

	/**
	 * @param bytes
	 * @return
	 */
	public static byte[] getArrays(byte[] bytes) {
		byte[] bb = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == 0) {
				break;
			}
			bb[i] = bytes[i];
		}
		return bb;
	}
	
	/**
	 * @param bytes
	 * @return
	 */
	public static String arraysToString(byte[] bytes){
		int len = getArrayLen(bytes);
		return new String(bytes, 0, len);
	}

	/**
	 * @param dst
	 * @param src
	 */
	public static void memset(byte[] dst, byte[] src) {
		System.arraycopy(src, 0, dst, 0, src.length);
	}

	/**
	 * @param dst
	 * @param src
	 */
	public static void memset(byte[] dst, String src) {
		byte[] bytes = src.getBytes();
		System.arraycopy(bytes, 0, dst, 0, bytes.length);
	}

}
