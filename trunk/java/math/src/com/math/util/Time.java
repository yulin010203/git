package com.math.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间对象
 * 
 * @author 钟城 2011-4-21
 */
public class Time extends java.sql.Time {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6292108952476547268L;
	/**
	 * 小时(日期加减时用来标志需要加减的部分)
	 * 
	 * @see #add(int, int)
	 */
	public final static int HOUR = Calendar.HOUR;
	/**
	 * 分(日期加减时用来标志需要加减的部分)
	 * 
	 * @see #add(int, int)
	 */
	public final static int MINUTE = Calendar.MINUTE;
	/**
	 * 秒(日期加减时用来标志需要加减的部分)
	 * 
	 * @see #add(int, int)
	 */
	public final static int SECOND = Calendar.SECOND;
	/**
	 * 毫秒(日期加减时用来标志需要加减的部分)
	 * 
	 * @see #add(int, int)
	 */
	public final static int MILLISECOND = Calendar.MILLISECOND;

	/**
	 * 一天等于多少毫秒
	 * 
	 * @exclude
	 */
	public static int MILLIS_A_DAY = 1000 * 3600 * 24;
	/**
	 * 一小时等于多少毫秒
	 * 
	 * @exclude
	 */
	public static int MILLIS_A_HOUR = 1000 * 3600;
	/**
	 * 16小时等于多少毫秒
	 * 
	 * @exclude
	 */
	public static int MILLIS_16_HOURS = 1000 * 3600 * 16;

	/**
	 * 无参构造器(创建的对象为当前时间)
	 */
	public Time() {
		this(new Date().getTime());
	}

	/**
	 * 有参构造器：(创建的对象为date对象所对应的时间)
	 * 
	 * @param date
	 *            日期
	 */
	public Time(Date date) {
		this(date.getTime());
	}

	/**
	 * 有参构造器：(创建的对象为毫秒时间所对应的时间)
	 * 
	 * @param time
	 *            毫秒时间
	 */
	public Time(long time) {
		// 把年月日设置成1970,1,1，然后把毫秒设置为0
		super(calc(time));
	}

	/**
	 * 
	 * @param time
	 *            毫秒
	 * @return 1970 1,1 00:00:00-23:59:59 的秒数 注意：00:00:00-8:00:00 秒数为负的
	 * 
	 */
	protected static long calc(long time) {
		// 此方法只在东八区有效 其它时区可能有问题
		// 把年月日设置成1970,1,1
		// long newTime = time % MILLIS_A_DAY - time % 1000;
		long newTime = time % MILLIS_A_DAY;
		// 时间从8:00开始计时 1970,1,1 0-8时对应的时间毫秒数应为负
		// 如果newTime>MILLIS_16_HOURS 即减去一天的毫秒数即可转为负值
		if (newTime >= MILLIS_16_HOURS) {
			return newTime - MILLIS_A_DAY;
		} else if (newTime < -MILLIS_16_HOURS / 2) {
			return newTime + MILLIS_A_DAY;
		} else {
			return newTime;
		}
	}

	/**
	 * 把HH:mm:ss格式的字符串转换成时间对象，示例如下：<br>
	 * Time time = Time.valueOf("13:30:00");//13:30:00表示下午1点半
	 * 
	 * @param str
	 *            时间字符串
	 * @return 时间对象
	 */
	public static Time valueOf(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			Date date = sdf.parse(str);
			return new Time(date);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 把HHmmss格式的字符串转换成时间对象，示例如下：<br>
	 * Time time = Time.valueOfHHmmss("133000");//133000表示下午1点半
	 * 
	 * @param str
	 *            时间字符串
	 * @return 时间对象
	 * @exclude
	 */
	public static Time valueOfHHmmss(String str) {
		if (str.length() == 5) {// 兼容90516的格式
			str = "0" + str;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		try {
			Date date = sdf.parse(str);
			return new Time(date);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 日期加减，示例如下：<br>
	 * 1).在当前时间上加1小时：<br>
	 * Time now = new Time();<br>
	 * now.add(Time.HOUR, 1);<br>
	 * 2).在当前时间上减10分钟：<br>
	 * Time now = new Time();<br>
	 * now.add(Time.MINUTE, -10);<br>
	 * 
	 * @param field
	 *            日期字段(表示需要在小时上加减还是在分钟上加减)
	 * @param amount
	 *            为时间添加的偏移量，如果大于0则表示加，小于0则表示减
	 */
	public void add(int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(this);
		c.add(field, amount);
		this.setTime(c.getTimeInMillis());
	}

	/**
	 * 判断此对象的时间是否大于特定日期中的时间(即忽略日期对象中的年月日)
	 * 
	 * @param when
	 *            特定日期
	 * @return true或false
	 */
	@Override
	public boolean after(Date when) {
		assertNotNull(when);
		long a = calc(this.getTime());
		long b = calc(when.getTime());
		return a > b;
	}

	/**
	 * 判断此对象的时间是否大于特定时间
	 * 
	 * @param when
	 *            特定时间
	 * @return true或false
	 */
	public boolean after(Time when) {
		return after((Date) when);
	}

	/**
	 * 判断此对象的时间是否小于特定日期中的时间(即忽略日期对象中的年月日)
	 * 
	 * @param when
	 *            特定日期
	 * @return true或false
	 */
	@Override
	public boolean before(Date when) {
		assertNotNull(when);
		long a = calc(this.getTime());
		long b = calc(when.getTime());
		return a < b;
	}

	/**
	 * 判断此对象的时间是否小于特定时间
	 * 
	 * @param when
	 *            特定时间
	 * @return true或false
	 */
	public boolean before(Time when) {
		return before((Date) when);
	}

	/**
	 * 判断此对象的时间是否大于等于特定日期中的时间(即忽略日期对象中的年月日)
	 * 
	 * @param when
	 *            特定日期
	 * @return true或false
	 */
	public boolean afterOrEquals(Date when) {
		assertNotNull(when);
		long a = calc(this.getTime());
		long b = calc(when.getTime());
		return a >= b;
	}

	/**
	 * 判断此对象的时间是否大于等于特定时间
	 * 
	 * @param when
	 *            特定时间
	 * @return true或false
	 */
	public boolean afterOrEquals(Time when) {
		return afterOrEquals((Date) when);
	}

	/**
	 * 判断此对象的时间是否小于等于特定日期中的时间(即忽略日期对象中的年月日)
	 * 
	 * @param when
	 *            特定日期
	 * @return true或false
	 */
	public boolean beforeOrEquals(Date when) {
		assertNotNull(when);
		long a = calc(this.getTime());
		long b = calc(when.getTime());
		return a <= b;
	}

	/**
	 * 判断此对象的时间是否小于等于特定时间
	 * 
	 * @param when
	 *            特定时间
	 * @return true或false
	 */
	public boolean beforeOrEquals(Time when) {
		return beforeOrEquals((Date) when);
	}

	/**
	 * 判断此对象的时间是否在某两个特定日期时间段内（大于等于开始，小于等于结束，忽略年月日）
	 * 
	 * @param start
	 *            起始日期
	 * @param end
	 *            结束日期
	 * @return true或false
	 */
	public boolean between(Date start, Date end) {
		assertNotNull(start);
		assertNotNull(end);
		long im = calc(this.getTime());
		long a = calc(start.getTime());
		long b = calc(end.getTime());
		return im >= a && im <= b;
	}

	/**
	 * 判断此对象的时间是否在某两个特定时间段内（大于等于开始，小于等于结束）
	 * 
	 * @param start
	 *            起始时间
	 * @param end
	 *            结束时间
	 * @return true或false
	 */
	public boolean between(Time start, Time end) {
		return between((Date) start, (Date) end);
	}

	/**
	 * 判断此对象的时间是否在某个时间的置信区间内[time-10, time+10]<br>
	 * 举例:between(time, 1000),time=10:00:00;表示判断某个时间处于[09:59:59,10:00:01]区间内<br>
	 * 
	 * @param time
	 *            比较时间
	 * @param sep
	 *            置信区间
	 * @return boolean
	 */
	public boolean between(Time time, long sep) {
		long a = calc(time.getTime()) - sep;
		long b = calc(time.getTime()) + sep;
		long im = calc(this.getTime());
		return im >= a && im <= b;
	}

	/**
	 * 判断特定时间是否在某两个特定时间段内（大于等于开始，小于等于结束）
	 * 
	 * @param start
	 *            起始时间
	 * @param end
	 *            结束时间
	 * @param when
	 *            特定时间
	 * @return true或false
	 */
	public static boolean between(Time start, Time end, Time when) {
		assertNotNull(start);
		assertNotNull(end);
		assertNotNull(when);
		// start<end(9:00-15:00),仅当start<=when<=end时返回true
		if (start.before(end)) {
			return when.afterOrEquals(start) && when.beforeOrEquals(end);
		} else {
			// start>=end时(18:00-7:00),当when>=start或when<=end时返回true
			return when.afterOrEquals(start) || when.beforeOrEquals(end);
		}
	}

	/**
	 * 判断特定时间是否在某个时间的置信区间内[time-10, time+10]<br>
	 * 举例:between(time, 1000),time=10:00:00;表示判断某个时间处于[09:59:59,10:00:01]区间内<br>
	 * 
	 * @param time
	 *            比较时间
	 * @param when
	 *            特定时间
	 * @param sep
	 *            置信区间
	 * @return boolean
	 */
	public static boolean between(Time time, Time when, long sep) {
		long a = calc(time.getTime()) - sep;
		long b = calc(time.getTime()) + sep;
		long im = calc(when.getTime());
		return im >= a && im <= b;
	}

	/**
	 * 判断此对象的时间是否等于特定时间
	 * 
	 * @param when
	 *            特定时间
	 * @return true或false
	 */
	@Override
	public boolean equals(Object when) {
		assertNotNull(when);
		if (!(when instanceof Date)) {
			return false;
		}
		Date when_ = (Date) when;
		long a = calc(this.getTime());
		long b = calc(when_.getTime());
		return a == b;
	}

	/**
	 * @exclude
	 */
	@Override
	public int hashCode() {
		return new Long(getTime()).hashCode();
	}

	/**
	 * 返回当前时间
	 * 
	 * @return 当前时间
	 */
	public static Time now() {
		return new Time();
	}

	/**
	 * 克隆一个新的时间对象，新的时间与原对象的时间是一样的
	 * 
	 * @return 新的时间对象
	 */
	@Override
	public Time clone() {
		return (Time) super.clone();
	}

	/**
	 * 断言对象是否为空
	 * 
	 * @param obj
	 */
	private static void assertNotNull(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("必须的参数不能为null");
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 * @exclude
	 */
	public static void main(String[] args) throws Exception {
		Time time = valueOf("16:03:16");
		Time time2 = valueOf("16:03:16");
		System.out.println(time == time2);
		System.out.println(time.afterOrEquals(time2));
		System.out.println(time.after(time2));

		Time time3 = valueOfHHmmss("160316");
		System.out.println(time3.between(time, time2));
		System.out.println(time3);
	}

}
