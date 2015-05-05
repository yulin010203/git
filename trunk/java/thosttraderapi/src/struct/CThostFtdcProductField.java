package struct;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 产品
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcProductField extends Structure {

	/**
	 * 产品代码
	 */
	public byte[] ProductID = new byte[31];
	/**
	 * 产品名称
	 */
	public byte[] ProductName = new byte[21];
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 产品类型
	 */
	public byte ProductClass;
	/**
	 * 合约数量乘数
	 */
	public int VolumeMultiple;
	/**
	 * 最小变动价位
	 */
	public double PriceTick;
	/**
	 * 市价单最大下单量
	 */
	public int MaxMarketOrderVolume;
	/**
	 * 市价单最小下单量
	 */
	public int MinMarketOrderVolume;
	/**
	 * 限价单最大下单量
	 */
	public int MaxLimitOrderVolume;
	/**
	 * 限价单最小下单量
	 */
	public int MinLimitOrderVolume;
	/**
	 * 持仓类型
	 */
	public byte PositionType;
	/**
	 * 持仓日期类型
	 */
	public byte PositionDateType;
	/**
	 * 平仓处理类型
	 */
	public byte CloseDealType;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "BrokerID", "UserID" });
	}

	/**
	 * 指针
	 */
	public class ByReference extends CThostFtdcProductField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public class ByValue extends CThostFtdcProductField implements Structure.ByValue {
	}
}
