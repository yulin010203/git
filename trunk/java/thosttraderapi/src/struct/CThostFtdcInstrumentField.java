package struct;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 合约
 * 
 * @author 陈霖 2015-5-4
 */
public class CThostFtdcInstrumentField extends Structure {

	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 合约名称
	 */
	public byte[] InstrumentName = new byte[21];
	/**
	 * 合约在交易所的代码
	 */
	public byte[] ExchangeInstID = new byte[31];
	/**
	 * 产品代码
	 */
	public byte[] ProductID = new byte[31];
	/**
	 * 产品类型
	 */
	public byte ProductClass;
	/**
	 * 交割年份
	 */
	public int DeliveryYear;
	/**
	 * 交割月
	 */
	public int DeliveryMonth;
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
	 * 合约数量乘数
	 */
	public int VolumeMultiple;
	/**
	 * 最小变动价位
	 */
	public double PriceTick;
	/**
	 * 创建日
	 */
	public byte[] CreateDate = new byte[9];
	/**
	 * 上市日
	 */
	public byte[] OpenDate = new byte[9];
	/**
	 * 到期日
	 */
	public byte[] ExpireDate = new byte[9];
	/**
	 * 开始交割日
	 */
	public byte[] StartDelivDate = new byte[9];
	/**
	 * 结束交割日
	 */
	public byte[] EndDelivDate = new byte[9];
	/**
	 * 合约生命周期状态
	 */
	public byte InstLifePhase;
	/**
	 * 当前是否交易
	 */
	public int IsTrading;
	/**
	 * 持仓类型
	 */
	public byte PositionType;
	/**
	 * 持仓日期类型
	 */
	public byte PositionDateType;
	/**
	 * 多头保证金率
	 */
	public byte LongMarginRatio;
	/**
	 * 空头保证金率
	 */
	public byte ShortMarginRatio;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		Field[] fields = this.getClass().getDeclaredFields();
		List<String> names = new ArrayList<String>(fields.length);
		for (int i = 0; i < fields.length; i++) {
			names.add(fields[i].getName());
		}
		return names;
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcInstrumentField implements Structure.ByReference {
	}

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcInstrumentField implements Structure.ByValue {
	}
}
