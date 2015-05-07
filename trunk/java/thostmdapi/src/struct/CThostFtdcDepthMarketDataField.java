package struct;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * 指定的合约
 * 
 * @author 陈霖 2015-5-5
 */
public class CThostFtdcDepthMarketDataField extends Structure {

	/**
	 * 交易日
	 */
	public byte[] TradingDay = new byte[9];
	/**
	 * 合约代码
	 */
	public byte[] InstrumentID = new byte[31];
	/**
	 * 交易所代码
	 */
	public byte[] ExchangeID = new byte[9];
	/**
	 * 合约在交易所的代码
	 */
	public byte[] ExchangeInstID = new byte[31];
	/**
	 * 最新价
	 */
	public double LastPrice;
	/**
	 * 上次结算价
	 */
	public double PreSettlementPrice;
	/**
	 * 昨收盘
	 */
	public double PreClosePrice;
	/**
	 * 昨持仓量
	 */
	public double PreOpenInterest;
	/**
	 * 今开盘
	 */
	public double OpenPrice;
	/**
	 * 最高价
	 */
	public double HighestPrice;
	/**
	 * 最低价
	 */
	public double LowestPrice;
	/**
	 * 数量
	 */
	public int Volume;
	/**
	 * 成交金额
	 */
	public double Turnover;
	/**
	 * 持仓量
	 */
	public double OpenInterest;
	/**
	 * 今收盘
	 */
	public double ClosePrice;
	/**
	 * 本次结算价
	 */
	public double SettlementPrice;
	/**
	 * 涨停板价
	 */
	public double UpperLimitPrice;
	/**
	 * 跌停板价
	 */
	public double LowerLimitPrice;
	/**
	 * 昨虚实度
	 */
	public double PreDelta;
	/**
	 * 今虚实度
	 */
	public double CurrDelta;
	/**
	 * 最后修改时间
	 */
	public byte[] UpdateTime = new byte[9];
	/**
	 * 最后修改毫秒
	 */
	public int UpdateMillisec;
	/**
	 * 申买价一
	 */
	public double BidPrice1;
	/**
	 * 申买量一
	 */
	public int BidVolume1;
	/**
	 * 申卖价一
	 */
	public double AskPrice1;
	/**
	 * 申卖量一
	 */
	public int AskVolume1;
	/**
	 * 申买价二
	 */
	public double BidPrice2;
	/**
	 * 申买量二
	 */
	public int BidVolume2;
	/**
	 * 申卖价二
	 */
	public double AskPrice2;
	/**
	 * 申卖量二
	 */
	public int AskVolume2;
	/**
	 * 申买价三
	 */
	public double BidPrice3;
	/**
	 * 申买量三
	 */
	public int BidVolume3;
	/**
	 * 申卖价三
	 */
	public double AskPrice3;
	/**
	 * 申卖量三
	 */
	public int AskVolume3;
	/**
	 * 申买价四
	 */
	public double BidPrice4;
	/**
	 * 申买量四
	 */
	public int BidVolume4;
	/**
	 * 申卖价四
	 */
	public double AskPrice4;
	/**
	 * 申卖量四
	 */
	public int AskVolume4;
	/**
	 * 申买价五
	 */
	public double BidPrice5;
	/**
	 * 申买量五
	 */
	public int BidVolume5;
	/**
	 * 申卖价五
	 */
	public double AskPrice5;
	/**
	 * 申卖量五
	 */
	public int AskVolume5;
	/**
	 * 当日均价
	 */
	public double AveragePrice;

	@Override
	@SuppressWarnings("rawtypes")
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "TradingDay", "InstrumentID", "ExchangeID", "ExchangeInstID", "LastPrice", "PreSettlementPrice", "PreClosePrice", "PreOpenInterest", "OpenPrice",
				"HighestPrice", "LowestPrice", "Volume", "Turnover", "OpenInterest", "ClosePrice", "SettlementPrice", "UpperLimitPrice", "LowerLimitPrice", "PreDelta", "CurrDelta", "UpdateTime",
				"UpdateMillisec", "BidPrice1", "BidVolume1", "AskPrice1", "AskVolume1", "BidPrice2", "BidVolume2", "AskPrice2", "AskVolume2", "BidPrice3", "BidVolume3", "AskPrice3", "AskVolume3",
				"BidPrice4", "BidVolume4", "AskPrice4", "AskVolume4", "BidPrice5", "BidVolume5", "AskPrice5", "AskVolume5", "AveragePrice" });
	}

	/**
	 * 指针
	 */
	public static class ByReference extends CThostFtdcDepthMarketDataField implements Structure.ByReference {
	};

	/**
	 * 值
	 */
	public static class ByValue extends CThostFtdcDepthMarketDataField implements Structure.ByValue {
	};
}
