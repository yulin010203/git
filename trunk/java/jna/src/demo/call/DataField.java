package demo.call;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * @author 陈霖 2015-5-4
 */
public class DataField extends Structure {
	public static class ByReference extends DataField implements Structure.ByReference {
	}

	public static class ByValue extends DataField implements Structure.ByValue {
	}

	public byte[] str = new byte[64];
//	public byte[] str;

	@Override
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "str" });
	}

}
