package com.math.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.math.stat.regression.SimpleRegression;

/**
 * 数学函数，包括求公倍数、公约数、分位数、判断上穿、下穿等
 * 
 * @author 钟城 2011-8-1
 */
public class MathUtil {

	/**
	 * 合约的最小变动价位精确到小数点后X位
	 * 
	 * @exclude
	 */
	private static final int SCALE = 8;

	private MathUtil() {
	}

	/**
	 * 获取double类型的小数点位数<br>
	 * 整数:0<br>
	 * 0.2:1<br>
	 * 0.25:2<br>
	 * 0.125:3<br>
	 * 1.125:3<br>
	 * 
	 * @param input
	 * <br>
	 * @return
	 * @exclude
	 */
	public static int scale(double input) {
		if ((int) input == input) {// 整数
			return 0;
		}
		String str = Double.toString(input);
		while (str.endsWith("0")) {
			str = str.substring(0, str.length() - 1);
		}
		// 一定要用BigDecimal(String)构造函数，否则传入0.002(国债)就会变成0.00200000000000000004163336342344337026588618755340576171875
		String s = new BigDecimal(str).toPlainString();
		int scale;
		if (s.endsWith("0")) {
			scale = s.substring(s.indexOf(".") + 1, s.lastIndexOf("0")).length();
		} else {
			scale = s.substring(s.indexOf(".") + 1, s.length()).length();
		}
		return scale;
	}

	/**
	 * 求方差VARIANCE(X，N)，即各个数与平均数之差的平方的平均数。<br>
	 * 算法如下：<br>
	 * 若X1，X2，X3......Xn的平均数为M，则方差S^2=[(X1-M)^2+(X2-M)^2+.......+(Xn-M)^2]/N<br>
	 * 
	 * @param x
	 *            目标数组(长度必须>=n+1)
	 * @param n
	 *            取值长度
	 * @return VARIANCE(X，N)的值
	 */
	public static double variance(double[] x, int n) {
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += x[i];
		}
		double avg = sum / n;
		sum = 0;
		for (int i = 0; i < n; i++) {
			sum += (x[i] - avg) * ((x[i] - avg));
		}
		return sum / n;
	}

	/**
	 * 求标准差standardDev(X，N)，即方差的平方根。<br>
	 * 算法如下：<br>
	 * 若X1，X2，X3......Xn的平均数为M，则标准差S=sqrt([(X1-M)^2+(X2-M)^2+.......+(Xn-M)^2]/N )<br>
	 * 
	 * @param x
	 *            目标数组(长度必须>=n+1)
	 * @param n
	 *            取值长度
	 * @return VARIANCE(X，N)的值
	 */
	public static double standardDev(double[] x, int n) {
		return Math.sqrt(variance(x, n));
	}

	/**
	 * 根据二维数据计算一元线型回归的斜率
	 * 
	 * @param data
	 *            目标二维数组
	 * @return 斜率
	 */
	public static double slope(double[][] data) {
		SimpleRegression reg = new SimpleRegression();
		reg.addData(data);
		return reg.getSlope();
	}

	/**
	 * 求double数组的所有元素和
	 * 
	 * @param arr
	 *            目标数组
	 * @return 和
	 */
	public static double sum(double arr[]) {
		int length = (arr == null ? 0 : arr.length);
		return sum(arr, length);
	}

	/**
	 * 对double数组的前length元素求和
	 * 
	 * @param arr
	 *            目标数组
	 * @param length
	 *            取多少个元素
	 * @return 和
	 */
	public static double sum(double arr[], int length) {
		double result = 0;
		if (arr != null) {
			for (int i = 0; i < arr.length && i < length; i++) {
				result += arr[i];
			}
		}
		return round(result);
	}

	/**
	 * 求一个double数组中的最大值
	 * 
	 * @param arr
	 *            目标数组
	 * @return 最大值
	 */
	public static double max(double... arr) {
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
		}

		return max;
	}

	/**
	 * 求一个int数组中的最大值
	 * 
	 * @param arr
	 *            目标数组
	 * @return 最大值
	 */
	public static int max(int... arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
		}

		return max;
	}

	/**
	 * 求一个double数组中的最小值
	 * 
	 * @param arr
	 *            目标数组
	 * @return 最小值
	 */
	public static double min(double... arr) {
		double min = Double.POSITIVE_INFINITY;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min)
				min = arr[i];
		}
		return min;
	}

	/**
	 * 求一个int数组中的最小值
	 * 
	 * @param arr
	 *            目标数组
	 * @return 最小值
	 */
	public static int min(int... arr) {
		int min = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min)
				min = arr[i];
		}
		return min;
	}

	/**
	 * 求int整数的绝对值
	 * 
	 * @param i
	 *            目标数
	 * @return 绝对值
	 */
	public static double abs(int i) {
		return Math.abs(i);
	}

	/**
	 * 求long整数的绝对值
	 * 
	 * @param l
	 *            目标数
	 * @return 绝对值
	 */
	public static double abs(long l) {
		return Math.abs(l);
	}

	/**
	 * 求double的绝对值
	 * 
	 * @param d
	 *            目标数
	 * @return 绝对值
	 */
	public static double abs(double d) {
		return Math.abs(d);
	}

	/**
	 * 求int整数的负绝对值
	 * 
	 * @param i
	 *            目标数
	 * @return 绝对值
	 */
	public static double neg(int i) {
		return Math.abs(i) * -1;
	}

	/**
	 * 求long整数的负绝对值
	 * 
	 * @param l
	 *            目标数
	 * @return 绝对值
	 */
	public static double neg(long l) {
		return Math.abs(l) * -1;
	}

	/**
	 * 求double的负绝对值
	 * 
	 * @param d
	 *            目标数
	 * @return 绝对值
	 */
	public static double neg(double d) {
		return Math.abs(d) * -1;
	}

	/**
	 * 提供精确的加法运算。<br>
	 * 如：MathUtil.add(0.05, 0.01) = 0.06
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。<br>
	 * 如：MathUtil.sub(1.0, 0.42) = 0.58
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。<br>
	 * 如：MathUtil.mul(4.015, 100) = 401.5
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。<br>
	 * 如：MathUtil.div(123.3, 100) = 1.233
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, 10);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。<br>
	 * 如：MathUtil.div(123.3, 100, 4) = 1.233
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 按一组数的最小公倍数，把目标数向上规置到最近的一个符合最小公倍数的整数倍的数<br>
	 * 如：数组值为4、6，求得最小公倍数为12，如果目标数为30，那么向上规整之后得到的结果为36<br>
	 * 
	 * @param original
	 *            原始值
	 * @param arr
	 *            需要求最小公倍数的数组
	 * @return 规置后的数
	 */
	public static double higher(double original, double... arr) {
		double mul = lcm(arr, SCALE);// 先求两个合约的最小变动价位最小公倍数
		if (rem(original, mul, SCALE) == 0) {// 求余数必须用这个方法
			return round(original, SCALE);
		}
		double absOriginal = Math.abs(original);
		double result = 0;
		if (original > 0) {
			result = absOriginal - rem(absOriginal, mul, SCALE) + mul;
		} else {
			result = -(absOriginal - rem(absOriginal, mul, SCALE));
		}
		return round(result, SCALE);// 结果要保留digit位小数
	}

	/**
	 * 按一组数的最小公倍数，把目标数向下规置到最近的一个符合最小公倍数的整数倍的数<br>
	 * 如：数组值为4、6，求得最小公倍数为12，如果目标数为30，那么向下规整之后得到的结果为24<br>
	 * 
	 * @param original
	 *            原始值
	 * @param arr
	 *            需要求最小公倍数的数组
	 * @return 规置后的数
	 */
	public static double lower(double original, double... arr) {
		double mul = lcm(arr, SCALE);// 先求两个合约的最小变动价位最小公倍数
		if (rem(original, mul, SCALE) == 0) {// 求余数必须用这个方法
			return round(original, SCALE);
		}
		double absOriginal = Math.abs(original);
		double result = 0;
		if (original > 0) {
			result = absOriginal - rem(absOriginal, mul, SCALE);
		} else {
			result = -(absOriginal - rem(absOriginal, mul, SCALE) + mul);
		}
		return round(result, SCALE);// 结果要保留digit位小数
	}

	/**
	 * 四舍五入取整
	 * 
	 * @param input
	 *            目标数
	 * @return 取整后的数
	 */
	public static long roundInt(double input) {
		return Math.round(input);
	}

	/**
	 * 将double按2位小数点位数保留(四舍五入)<br>
	 * 注意:由于double精度的限制，小数点前后位数和超过15位时将不再保证取值的精确性<br>
	 * 
	 * @param input
	 *            目标数 目标数
	 * @return 保留后的数
	 */
	public static double round2(double input) {
		return round(input, 2);
	}

	/**
	 * 将double按指定小数点位数保留(四舍五入)<br>
	 * 注意:由于double精度的限制，小数点前后位数和超过15位时将不再保证取值的精确性<br>
	 * 
	 * @param input
	 *            目标数
	 * @param scale
	 *            希望保留小数点的位数
	 * @return 保留后的数
	 */
	public static double round(double input, int scale) {
		// double multiple = Math.pow(10, digit);
		// double result = (long) Math.round((input * multiple));
		// return result / multiple;
		// BigDecimal bd = new BigDecimal(input);
		// return bd.setScale(scale, RoundingMode.HALF_UP).doubleValue();
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero.");
		}
		BigDecimal b = new BigDecimal(Double.toString(input));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * 将double按默认小数点位数(系统默认8位)保留(四舍五入)<br>
	 * 注意:由于double精度的限制，小数点前后位数和超过15位时将不再保证取值的精确性<br>
	 * 
	 * @param input
	 *            目标数
	 * @return 保留后的数
	 */
	public static double round(double input) {
		return round(input, SCALE);
	}

	/**
	 * 最N个整数的最大公约数
	 * 
	 * @param arr
	 *            目标数组
	 * @return 最大公约数
	 */
	public static int gcd(int[] arr) {
		int result = arr[0];
		for (int i = 1; i < arr.length; i++) {
			result = gcd(result, arr[i]);
		}
		return result;
	}

	/**
	 * 最两个整数的最大公约数
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @return 最大公约数
	 */
	public static int gcd(int a, int b) {
		int temp = -1;
		if (a < b) {
			temp = a;
			a = b;
			b = temp;
		}
		while (b != 0) {
			a = a % b;
			temp = a;
			a = b;
			b = temp;
		}
		return a;
	}

	/**
	 * 求N个整数最小公倍数
	 * 
	 * @param arr
	 *            目标数组
	 * @return 最小公倍数
	 */
	public static int lcm(int[] arr) {
		int result = arr[0];
		for (int i = 1; i < arr.length; i++) {
			result = lcm(result, arr[i]);
		}
		return result;
	}

	/**
	 * 求两个整数的最小公倍数(欧几里德辗转相除算法)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @return 最小公倍数
	 */
	public static int lcm(int a, int b) {
		int i = a, j = b, s;
		// 先求a、b的最大公约数j (欧几里德辗转相除算法)
		for (; j != 0 && (s = i % j) != 0; i = j, j = s) {
		}
		return (j == 0 ? 0 : (a * b / j)); // 得到最小公倍数 (=他们的乘积除以最大公约数)
	}

	/**
	 * 求N个浮点数的最小公倍数<br>
	 * 浮点数的最小公倍数必须知道乘数是多少，如要求0.3和0.07的最小公倍数，需要先把0.3*100=30,0.07*100=7，<br>
	 * 然后求30和7的最小公倍数为210， 然后再210/100=2.1，最终求得最小公倍数为2.1，digit即是2，并且是自然数。<br>
	 * 
	 * @param arr
	 *            数组
	 * @param digit
	 *            计算时扩大及缩小的倍数(10的多少倍，如果为2将会扩大100倍)
	 * @return 最小公倍数
	 */
	public static double lcm(double[] arr, int digit) {
		// double result = arr[0];
		// for (int i = 1; i < arr.length; i++) {
		// result = lcm(result, arr[i], digit);
		// }
		// return result;
		BigDecimal result = new BigDecimal(Double.toString(arr[0])).setScale(digit, BigDecimal.ROUND_HALF_UP);
		for (int i = 1; i < arr.length; i++) {
			result = lcm(result, new BigDecimal(Double.toString(arr[i])).setScale(digit, BigDecimal.ROUND_HALF_UP), digit);
		}
		return result.setScale(digit, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 求两个浮点数的最小公倍数<br>
	 * 浮点数的最小公倍数必须知道乘数是多少，如要求0.3和0.07的最小公倍数，需要先把0.3*100=30,0.07*100=7，<br>
	 * 然后求30和7的最小公倍数为210， 然后再210/100=2.1，最终求得最小公倍数为2.1，digit即是2，并且是自然数。<br>
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @param digit
	 *            计算时扩大及缩小的倍数(10的多少倍，如果为2将会扩大100倍)
	 * @return 最小公倍数
	 */
	public static double lcm(double a, double b, int digit) {
		if (digit <= 0) {
			throw new IllegalArgumentException("求最小公倍数失败，小数位数digit必须是自然数：" + digit);
		}
		// double expand = Math.pow(10, digit);// 根据位数求10的倍数，如2位数->100
		// int a0 = new Double(round(a * expand, digit)).intValue();
		// int b0 = new Double(round(b * expand, digit)).intValue();
		// double pub = lcm(a0, b0);
		// return round(pub / expand, digit);
		final BigDecimal numberA = new BigDecimal(Double.toString(a)).setScale(digit, RoundingMode.HALF_UP);
		final BigDecimal numberB = new BigDecimal(Double.toString(b)).setScale(digit, RoundingMode.HALF_UP);
		final BigDecimal result = numberA.multiply(numberB).divide(gcd(numberA, numberB));
		return result.setScale(digit, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * 求两个BigDecimal数的最小公倍数
	 * 
	 * @param a
	 * @param b
	 * @param digit
	 * @return 两个数的最小公倍数
	 */
	private static BigDecimal lcm(final BigDecimal a, final BigDecimal b, final int digit) {
		final BigDecimal result = a.multiply(b).divide(gcd(a, b));
		return result.setScale(digit, RoundingMode.HALF_UP);
	}

	/**
	 * 求两个BigDecimal数的最大公约数
	 * 
	 * @param a
	 * @param b
	 * @return 两个数的最大公约数
	 */
	private static BigDecimal gcd(BigDecimal a, BigDecimal b) {
		BigDecimal temp = new BigDecimal("1.0");
		if (0 == a.compareTo(b)) {
			return a;
		} else if (0 > a.compareTo(b)) {
			temp = a;
			a = b;
			b = temp;
		}
		while (0 != BigDecimal.ZERO.compareTo(b)) {
			a = a.remainder(b);
			temp = a;
			a = b;
			b = temp;
		}
		return a;
	}

	/**
	 * 求余数，类似于%运算符，不过支持浮点数<br>
	 * 如：rem(12.3,0.2,2)=0.1<br>
	 * 如：rem(12.3,0.5,2)=0.3<br>
	 * 
	 * @param base
	 *            被除数
	 * @param divisor
	 *            除数
	 * @param digit
	 *            精确到小数点后的位数
	 * @return 余数
	 */
	public static double rem(double base, double divisor, int digit) {
		// double multiple = Math.pow(10, digit);// 乘数
		// base = round(base, digit);
		// divisor = round(divisor, digit);
		// double result = round(base * multiple, digit) % round(divisor *
		// multiple, digit);
		// return round(result / multiple, digit);
		BigDecimal bgBase = new BigDecimal(Double.toString(base)).setScale(digit, RoundingMode.HALF_UP);
		BigDecimal bgDivisor = new BigDecimal(Double.toString(divisor)).setScale(digit, RoundingMode.HALF_UP);
		return bgBase.remainder(bgDivisor).setScale(digit, RoundingMode.HALF_UP).doubleValue();
	}

	// /**
	// * 求余数，类似于%运算符，不过支持浮点数<br>
	// * 如：mod(12.3,0.2,2)=0.1<br>
	// * 如：mod(12.3,0.5,2)=0.3<br>
	// *
	// * @param base
	// * 被除数
	// * @param divisor
	// * 除数
	// * @param digit
	// * 精确到小数点后的位数
	// * @return 余数<br>
	// * 此方法已被 {@link #rem(double, double, int)}替代
	// */
	// @Deprecated
	// public static double mod(double base, double divisor, int digit) {
	// return rem(base, divisor, digit);
	// }

	/**
	 * 判断a==b(先按系统默认位数保留，然后再比)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @return true或false
	 */
	public static boolean equals(double a, double b) {
		a = round(a, SCALE);
		b = round(b, SCALE);
		return a == b;
	}

	/**
	 * 判断a&lt;b(先按系统默认位数保留，然后再比)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @return true或false
	 */
	public static boolean less(double a, double b) {
		a = round(a, SCALE);
		b = round(b, SCALE);
		return a < b;
	}

	/**
	 * 判断a<=b(先按系统默认位数保留，然后再比)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @return true或false
	 */
	public static boolean lessOrEquals(double a, double b) {
		a = round(a, SCALE);
		b = round(b, SCALE);
		return a <= b;
	}

	/**
	 * 判断a>b(先按系统默认位数保留，然后再比)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @return true或false
	 */
	public static boolean greater(double a, double b) {
		a = round(a, SCALE);
		b = round(b, SCALE);
		return a > b;
	}

	/**
	 * 判断a>=b(先按系统默认位数保留，然后再比)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @return true或false
	 */
	public static boolean greaterOrEquals(double a, double b) {
		a = round(a, SCALE);
		b = round(b, SCALE);
		return a >= b;
	}

	/**
	 * 判断a==b(先按指定位数保留，然后再比)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @param scale
	 *            需要保留的小数位
	 * @return true或false
	 */
	public static boolean equals(double a, double b, int scale) {
		a = round(a, scale);
		b = round(b, scale);
		return a == b;
	}

	/**
	 * 判断a&lt;b(先按指定位数保留，然后再比)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @param scale
	 *            需要保留的小数位
	 * @return true或false
	 */
	public static boolean less(double a, double b, int scale) {
		a = round(a, scale);
		b = round(b, scale);
		return a < b;
	}

	/**
	 * 判断a<=b(先按指定位数保留，然后再比)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @param scale
	 *            需要保留的小数位
	 * @return true或false
	 */
	public static boolean lessOrEquals(double a, double b, int scale) {
		a = round(a, scale);
		b = round(b, scale);
		return a <= b;
	}

	/**
	 * 判断a>b(先按指定位数保留，然后再比)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @param scale
	 *            需要保留的小数位
	 * @return true或false
	 */
	public static boolean greater(double a, double b, int scale) {
		a = round(a, scale);
		b = round(b, scale);
		return a > b;
	}

	/**
	 * 判断a>=b(先按指定位数保留，然后再比)
	 * 
	 * @param a
	 *            目标数1
	 * @param b
	 *            目标数2
	 * @param scale
	 *            需要保留的小数位
	 * @return true或false
	 */
	public static boolean greaterOrEquals(double a, double b, int scale) {
		a = round(a, scale);
		b = round(b, scale);
		return a >= b;
	}

	/**
	 * 生成特定范围(>=min,<=max)的随机整数
	 * 
	 * @param min
	 *            随机数范围上限
	 * @param max
	 *            随机数范围下限
	 * @return 生成后的随机数
	 */
	public static int createRandom(int min, int max) {
		int seed = (max - min);
		double random = Math.random() * seed;
		random = Math.ceil(random);
		int intValue = new Double(random).intValue();
		return intValue + min;
	}

	/**
	 * 计算分位数(概率取0,0.25,0.5,0.75,1)，返回通过每个概率计算后的分位数的值所组成的数组<br>
	 * (为避免多线程同时计算分位数时容易死锁(R的BUG)，此方法为同步方法)<br>
	 * 
	 * @param arr
	 *            被计算的double数组
	 * @return 分位数
	 */
	public static double[] quantile(double[] arr) {
		return RUtil.quantile(arr);
	}

	/**
	 * 计算分位数，返回通过指定概率计算后的分位数的值<br>
	 * (为避免多线程同时计算分位数时容易死锁( R的BUG)，此方法为同步方法)<br>
	 * 
	 * @param arr
	 *            被计算的double数组概率
	 * @param probs
	 *            概率
	 * @return 分位数
	 */
	public static double quantile(double[] arr, double probs) {
		return RUtil.quantile(arr, probs);
	}

	/**
	 * 返回角的三角正弦。特殊情况如下：<br>
	 * <br>
	 * 
	 * 如果参数为 NaN 或无穷大，那么结果为 NaN。<br>
	 * 如果参数为 0，那么结果为 0，符号与参数符号相同。<br>
	 * <br>
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * @param a
	 *            以弧度表示的角。
	 * @return 参数的正弦。
	 */
	public static double sin(double a) {

		return Math.sin(a);
	}

	/**
	 * 返回角的三角余弦。特殊情况如下：<br>
	 * <br>
	 * 
	 * 如果参数为 NaN 或无穷大，那么结果为 NaN。<br>
	 * <br>
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * @param a
	 *            以弧度表示的角。
	 * @return 参数的余弦。
	 */
	public static double cos(double a) {
		return Math.cos(a);
	}

	/**
	 * 返回角的三角正切。特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN 或无穷大，那么结果为 NaN。<br>
	 * • 如果参数为 0，那么结果为 0，符号与参数符号相同。<br>
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * @param a
	 *            以弧度表示的角。
	 * @return 参数的正切。
	 */
	public static double tan(double a) {
		return Math.tan(a);
	}

	/**
	 * 返回一个值的反正弦；返回的角度范围在 -pi/2 到 pi/2 之间。特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN 或它的绝对值大于 1，那么结果为 NaN。<br>
	 * • 如果参数为 0，那么结果为 0，符号与参数符号相同。<br>
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * @param a
	 *            要返回其反正弦的值。
	 * @return 参数的反正弦。
	 */
	public static double asin(double a) {
		return Math.asin(a);
	}

	/**
	 * 返回一个值的反余弦；返回的角度范围在 0.0 到 pi 之间。特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN 或它的绝对值大于 1，那么结果为 NaN。<br>
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * @param a
	 *            要返回其反余弦的值。
	 * @return 参数的反余弦。
	 */
	public static double acos(double a) {
		return Math.acos(a);
	}

	/**
	 * 返回一个值的反正切；返回的角度范围在 -pi/2 到 pi/2 之间。特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN，那么结果为 NaN。<br>
	 * • 如果参数为 0，那么结果为 0，符号与参数符号相同。<br>
	 * 
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * @param a
	 *            要返回其反正切的值。
	 * @return 参数的反余弦。
	 */
	public static double atan(double a) {
		return Math.atan(a);
	}

	/**
	 * 将用角度表示的角转换为近似相等的用弧度表示的角。从角度到弧度的转换通常是不精确的。<br>
	 * 
	 * @param angdeg
	 *            用角度表示的角。<br>
	 * @return 角 angrad 用弧度表示的值。<br>
	 */
	public static double toRadians(double angdeg) {
		return Math.toRadians(angdeg);
	}

	/**
	 * 将用弧度表示的角转换为近似相等的用角度表示的角。从弧度到角度的转换通常是不精确的；用户不 应该期望 cos(toRadians(90.0)) 与 0.0 完全相等。<br>
	 * 
	 * @param angrad
	 *            用弧度表示的角。<br>
	 * @return 角 angrad 用角度表示的值。<br>
	 */
	public static double toDegrees(double angrad) {
		return Math.toDegrees(angrad);
	}

	/**
	 * 返回欧拉数 e 的 double 次幂的值。特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN，那么结果为 NaN。<br>
	 * • 如果参数为正无穷大，那么结果为正无穷大。<br>
	 * • 如果参数为负无穷大，那么结果为正 0。<br>
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * @param angrad
	 *            用弧度表示的角。
	 * @return 角 angrad 用角度表示的值。
	 */
	public static double exp(double angrad) {
		return Math.exp(angrad);
	}

	/**
	 * 返回 double 值的自然对数（底数是 e）。特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN 或小于 0，那么结果为 NaN。<br>
	 * • 如果参数为正无穷大，那么结果为正无穷大。<br>
	 * • 如果参数为正 0 或负 0，那么结果为负无穷大。<br>
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * @param a
	 *            一个值。
	 * @return ln a 的值，即 a 的自然对数。
	 */
	public static double log(double a) {
		return Math.log(a);
	}

	/**
	 * 返回 double 值的底数为 10 的对数。特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN 或小于 0，那么结果为 NaN。<br>
	 * • 如果参数为正无穷大，那么结果为正无穷大。<br>
	 * • 如果参数为正 0 或负 0，那么结果为负无穷大。<br>
	 * • 如果参数等于 10 n（n 为整数），那么结果为 n。<br>
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * @param a
	 *            一个值。
	 * @return a 的底数为 10 的对数。
	 */
	public static double log10(double a) {
		return Math.log10(a);
	}

	/**
	 * 返回正确舍入的 double 值的正平方根。特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN 或小于 0，那么结果为 NaN。<br>
	 * • 如果参数为正无穷大，那么结果为正无穷大。<br>
	 * • 如果参数为正 0 或负 0，那么结果与参数相同。否则，结果为最接近该参数值的实际数学平方根的 double 值。<br>
	 * 
	 * @param a
	 *            一个值。
	 * @return a 的正平方根。如果参数为 NaN 或小于 0，那么结果为 NaN。
	 */
	public static double sqrt(double a) {
		return Math.sqrt(a);
	}

	/**
	 * 返回 double 值的立方根。对于正的有限值 x，cbrt(-x) == -cbrt(x)；<br>
	 * 也就是说，负值的立方根是该值数值的负立方根。特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN，那么结果为 NaN。<br>
	 * • 如果参数为无穷大，那么结果为无穷大，符号与参数符号相同。<br>
	 * • 如果参数为 0，那么结果为 0，符号与参数符号相同。<br>
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。<br>
	 * 
	 * @param a
	 *            一个值。
	 * @return a 的立方根。
	 */
	public static double cbrt(double a) {
		return Math.cbrt(a);
	}

	/**
	 * 返回最小的（最接近负无穷大）double 值，该值大于等于参数，并等于某个整数。特殊情况如下：<br>
	 * 
	 * • 如果参数值已经等于某个整数，那么结果与该参数相同。<br>
	 * • 如果参数为 NaN、无穷大、正 0 或负 0，那么结果与参数相同。<br>
	 * • 如果参数值小于 0，但是大于 -1.0，那么结果为负 0。<br>
	 * 
	 * 注意，Math.ceil(x) 的值与 -Math.floor(-x) 的值完全相同。<br>
	 * 
	 * @param a
	 *            一个值。
	 * @return 最小（最接近负无穷大）浮点值，该值大于等于该参数，并等于某个整数。
	 */
	public static double ceil(double a) {
		return Math.ceil(a);
	}

	/**
	 * 返回最大的（最接近正无穷大）double 值，该值小于等于参数，并等于某个整数。特殊情况如下:<br>
	 * 
	 * • 如果参数值已经等于某个整数，那么结果与该参数相同。<br>
	 * • 如果参数为 NaN、无穷大、正 0 或负 0，那么结果与参数相同。<br>
	 * 
	 * @param a
	 *            一个值。
	 * @return 最大（最接近正无穷大）浮点值，该值小于等于该参数，并等于某个整数。
	 */
	public static double floor(double a) {
		return Math.floor(a);
	}

	/**
	 * 返回最接近参数并等于某一整数的 double 值。如果两个同为整数的 double 值都同样接近，那么结果取偶数。特殊情况如下：<br>
	 * 
	 * • 如果参数值已经是整数，那么结果与参数相同。<br>
	 * • 如果参数为 NaN、无穷大、正 0 或负 0，那么结果与参数相同。<br>
	 * 
	 * @param a
	 *            double 值。
	 * @return 最接近 a 的整数浮点值。
	 */
	public static double rint(double a) {
		return Math.rint(a);
	}

	/**
	 * 将矩形坐标 (x, y) 转换成极坐标 (r, theta)，返回所得角 theta。该方法通过计算 y/x 的反正切值来计算相角 theta，范围为从 -pi 到 pi。特殊情况如下：<br>
	 * 
	 * • 如果任一参数为 NaN，那么结果为 NaN。<br>
	 * • 如果第一个参数为正 0，第二个参数为正数；或者第一个参数为正的有限值，第二个参数为正无穷大，那么结果为正 0。<br>
	 * • 如果第一个参数为负 0，第二个参数为正数；或者第一个参数为负的有限值，第二个参数为正无穷大，那么结果为负 0。<br>
	 * • 如果第一个参数为正 0，第二个参数为负数；或者第一个参数为正的有限值，第二个参数为负无穷大，那么结果为最接近 pi 的 double 值。<br>
	 * • 如果第一个参数为负 0，第二个参数为负数；或者第一个参数为负的有限值，第二个参数为负无穷大，那么结果为最接近 pi 的 double 值。<br>
	 * • 如果第一个参数为正数，第二个参数为正 0 或负 0；或者第一个参数为正无穷大，第二个参数为有限值，那么结果为最接近 pi/2 的 double 值。<br>
	 * • 如果第一个参数为负数，第二个参数为正 0 或负 0；或者第一个参数为负无穷大，第二个参数为有限值，那么结果为最接近 -pi/2 的 double 值。<br>
	 * • 如果两个参数都为正无穷大，那么结果为最接近 pi/4 的 double 值。<br>
	 * • 如果第一个参数为正无穷大，第二个参数为负无穷大，那么结果为最接近 3*pi/4 的 double 值。<br>
	 * • 如果第一个参数为负无穷大，第二个参数为正无穷大，那么结果为最接近 -pi/4 的 double 值。<br>
	 * • 如果两个参数都为负无穷大，那么结果为最接近 -3*pi/4 的 double 值。<br>
	 * 
	 * 计算结果必须在准确结果的 2 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * 
	 * 
	 * @param y
	 *            纵坐标
	 * 
	 * @param x
	 *            横坐标
	 * 
	 * @return 与笛卡儿坐标中点 (x, y) 对应的极坐标中点 (r, theta) 的 theta 组件。
	 */
	public static double atan2(double y, double x) {
		return Math.atan2(x, y);
	}

	/**
	 * 返回第一个参数的第二个参数次幂的值。特殊情况如下：<br>
	 * 
	 * • 如果第二个参数为正 0 或负 0，那么结果为 1.0。<br>
	 * • 如果第二个参数为 1.0，那么结果与第一个参数相同。<br>
	 * • 如果第二个参数为 NaN，那么结果为 NaN。<br>
	 * • 如果第一个参数为 NaN，第二个参数非 0，那么结果为 NaN。<br>
	 * • 如果<br>
	 * o 第一个参数的绝对值大于 1，并且第二个参数为正无穷大，或者<br>
	 * o 第一个参数的绝对值小于 1，并且第二个参数为负无穷大，<br>
	 * 那么结果为正无穷大。<br>
	 * 
	 * • 如果<br>
	 * o 第一个参数的绝对值大于 1，并且第二个参数为负无穷大，或者<br>
	 * o 第一个参数的绝对值小于 1，并且第二个参数为正无穷大， <br>
	 * 那么结果为正 0。<br>
	 * 
	 * • 如果第一个参数的绝对值等于1，并且第二个参数为无穷大，那么结果为 NaN。 <br>
	 * • 如果<br>
	 * o 第一个参数为正 0，并且第二个参数大于 0，或者<br>
	 * o 第一个参数为正无穷大，并且第二个参数小于 0，<br>
	 * 那么结果为正 0。<br>
	 * • 如果<br>
	 * o 第一个参数为正 0，并且第二个参数小于 0，或者<br>
	 * o 第一个参数为正无穷大，并且第二个参数大于 0，<br>
	 * 那么结果为正无穷大。<br>
	 * • 如果<br>
	 * o 如果第一个参数为负 0，并且第二个参数大于 0 但不是有限的奇数整数，或者<br>
	 * o 第一个参数为负无穷大，并且第二个参数小于 0 但不是有限的奇数整数，<br>
	 * 那么结果为正 0。<br>
	 * • 如果<br>
	 * o 第一个参数为负 0，并且第二个参数为正的有限奇数整数，或者<br>
	 * o 第一个参数为负无穷大，并且第二个参数为负的有限奇数整数，<br>
	 * 那么结果为负 0。<br>
	 * • 如果<br>
	 * o 如果第一个参数为负 0，并且第二个参数小于 0 但不是有限的奇数整数，或者<br>
	 * o 第一个参数为负无穷大，并且第二个参数大于 0 但不是有限的奇数整数，<br>
	 * 那么结果为正无穷大。<br>
	 * • 如果<br>
	 * o 第一个参数为负 0，并且第二个参数为负的有限奇数整数，或者<br>
	 * o 第一个参数为负无穷大，并且第二个参数为正的有限奇数整数，<br>
	 * 
	 * 那么结果为负无穷大。<br>
	 * • 如果第一个参数为小于 0 的有限值，<br>
	 * o 如果第二个参数为有限的偶数整数，那么结果等于第一个参数绝对值的第二个参数次幂的结果。<br>
	 * o 如果第二个参数为有限的奇数整数，那么结果等于负的第一个参数绝对值的第二个参数次幂的结果。<br>
	 * o 如果第二个参数为有限的非整数值，那么结果为 NaN。<br>
	 * • 如果两个参数都为整数，并且结果恰好可以表示为一个 double 值，那么该结果恰好等于第一个参数的第二个参数次幂的算术结果。<br>
	 * （在前面的描述中，当且仅当浮点数为有限值并且是方法 ceil 的定点数，或者是方法 floor 的定点数时，才可以认为浮点值是整数。<br>
	 * 当且仅当将某个单参数方法应用到某个值的结果等于该值时，该值才是这个方法的定点值。）<br>
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 
	 * @param a
	 *            底数。
	 * @param b
	 *            指数。
	 * @return 以a为底数，b为指数的值。
	 */
	public static double pow(double a, double b) {
		return Math.pow(a, b);
	}

	/**
	 * 返回带正号的 double 值，该值大于等于 0.0 且小于 1.0。<br>
	 * 返回值是一个伪随机选择的数，在该范围内（近似）均匀分布。<br>
	 * 
	 * 此方法是完全同步的，可允许多个线程使用而不出现错误。但是，如果许多线程需要以极高的速率生成伪随机数， 那么这可能会减少每个线程对拥有自己伪随机数生成器的争用。<br>
	 * 
	 * @return 大于等于 0.0 且小于 1.0 的伪随机 double 值。
	 */
	public static double random() {
		return Math.random();
	}

	// /**
	// * 返回参数的 ulp 大小。double 值的 ulp 是此浮点值与下一个数值较大的 double 值之间的正距离。注意，对于非 NaN
	// x，ulp(-x) == ulp(x)。<br>
	// * 特殊情况如下：<br>
	// * • 如果参数为 NaN，那么结果为 NaN。<br>
	// * • 如果参数为正的或负的无穷大，那么结果为正无穷大。<br>
	// * • 如果参数为正 0 或负 0，那么结果为 Double.MIN_VALUE。<br>
	// * • 如果参数为 ±Double.MAX_VALUE，那么结果等于 2的971次幂。<br>
	// *
	// *
	// * 此方法是完全同步的，可允许多个线程使用而不出现错误。但是，如果许多线程需要以极高的速率生成伪随机数，<br>
	// * 那么这可能会减少每个线程对拥有自己伪随机数生成器的争用。<br>
	// * @param d
	// * 要返回 ulp 的浮点值。
	// * @return
	// * 参数的 ulp 大小。
	// */
	// public static double ulp(double d)
	// {
	// return Math.ulp(d);
	// }
	//
	// /**
	// * 返回参数的 ulp 大小。float 值的 ulp 是此浮点值与下一个数值较大的 float 值之间的正距离。注意，对于非 NaN
	// x，ulp(-x) == ulp(x)。<br>
	// * 特殊情况如下：<br>
	// * • 如果参数为 NaN，那么结果为 NaN。<br>
	// * • 如果参数为正的或负的无穷大，那么结果为正无穷大。<br>
	// * • 如果参数为正 0 或负 0，那么结果为Float.MIN_VALUE。<br>
	// * • 如果参数为 ±Float.MAX_VALUE，那么结果等于 2的104次幂。<br>
	// *
	// *
	// * 此方法是完全同步的，可允许多个线程使用而不出现错误。但是，如果许多线程需要以极高的速率生成伪随机数，<br>
	// * 那么这可能会减少每个线程对拥有自己伪随机数生成器的争用。
	// * @param f
	// * 要返回 ulp 的浮点值。
	// * @return
	// * 参数的 ulp 大小。
	// */
	// public static float ulp(float f)
	// {
	// return Math.ulp(f);
	// }

	/**
	 * 返回参数的符号函数；如果参数为 0，则返回 0；如果参数大于 0，则返回 1.0；如果参数小于 0，则返回 -1.0。<br>
	 * 特殊情况如下： <br>
	 * 
	 * • 如果参数为 NaN，那么结果为 NaN。<br>
	 * • 如果参数为正 0 或负 0，那么结果与参数相同。<br>
	 * 
	 * @param d
	 *            要返回符号函数的浮点值。
	 * @return 参数的符号函数。
	 */
	public static double signum(double d) {
		return Math.signum(d);
	}

	/**
	 * 返回参数的符号函数；如果参数为 0，则返回 0；如果参数大于 0，则返回 1.0；如果参数小于 0，则返回 -1.0。<br>
	 * 特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN，那么结果为 NaN。<br>
	 * • 如果参数为正 0 或负 0，那么结果与参数相同。<br>
	 * 
	 * @param f
	 *            要返回符号函数的浮点值。
	 * @return 参数的符号函数。
	 */
	public static float signum(float f) {
		return Math.signum(f);
	}

	/**
	 * 返回 double 值的双曲线正弦。x 双曲线正弦的定义是 (e^x - e^(-x))/2，其中 e 是欧拉数。<br>
	 * 
	 * 特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN，那么结果为 NaN。<br>
	 * • 如果参数为无穷大，那么结果为无穷大，符号与参数符号相同。<br>
	 * • 如果参数为 0，那么结果为 0，符号与参数符号相同。<br>
	 * 
	 * 计算结果必须在准确结果的 2.5 ulp 范围内。<br>
	 * 
	 * @param x
	 *            要返回其双曲线正弦的数字。
	 * @return x 的双曲线正弦。
	 */
	public static double sinh(double x) {
		return Math.sinh(x);
	}

	/**
	 * 返回 double 值的双曲线余弦。x 的双曲线余弦的定义是 (e^x + e^(-x))/2，其中 e 是欧拉数。<br>
	 * 
	 * 特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN，那么结果为 NaN。<br>
	 * • 如果参数为无穷大，那么结果为正无穷大。<br>
	 * • 如果参数为 0，那么结果为 1.0。<br>
	 * 
	 * 计算结果必须在准确结果的 2.5 ulp 范围内。<br>
	 * 
	 * @param x
	 *            要返回其双曲线余弦的数字。
	 * @return x 的双曲线余弦。
	 */
	public static double cosh(double x) {
		return Math.cosh(x);
	}

	/**
	 * 返回 double 值的双曲线正切。x 的双曲线正切的定义是<br>
	 * (e^x - e^(-x))/(ex + e^(-x))，即 sinh(x)/cosh(x)。注意，准确的 tanh 绝对值始终小于 1。<br>
	 * 
	 * 
	 * 特殊情况如下：<br>
	 * • 如果参数为 NaN，那么结果为 NaN。<br>
	 * • 如果参数为 0，那么结果为 0，符号与参数符号相同。<br>
	 * • 如果参数为正无穷大，那么结果为 +1.0。<br>
	 * • 如果参数为负无穷大，那么结果为 -1.0。<br>
	 * 
	 * 计算结果必须在准确结果的 2.5 ulp 范围内。<br>
	 * 任何有限输入值的 tanh 结果的绝对值必定小于等于 1。<br>
	 * 注意，一旦准确的 tanh 结果在极限值 ±1 的 1/2 ulp 内，则应该返回有正确符号的 ±1.0。<br>
	 * 
	 * @param x
	 *            要返回其双曲线正切的数字。
	 * @return x 的双曲线正切。
	 */
	public static double tanh(double x) {
		return Math.tanh(x);
	}

	/**
	 * 返回 sqrt(x^2 +y^2)，没有中间溢出或下溢。<br>
	 * 
	 * 特殊情况如下：<br>
	 * • 如果两个参数都为无穷大，那么结果为正无穷大。<br>
	 * • 如果两个参数都为 NaN 且都不是无穷大，那么结果为 NaN。<br>
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。如果一个参数保持常量，那么在另一个参数中，结果必须具有半单调性。<br>
	 * 
	 * @param x
	 *            一个值。
	 * @param y
	 *            一个值。
	 * @return 没有中间溢出或下溢的 sqrt(x^2 +y^2)。
	 */
	public static double hypot(double x, double y) {
		return Math.hypot(x, y);
	}

	/**
	 * 返回 e^x -1。注意，对于接近 0 的 x 值，expm1(x) + 1 的准确和比 exp(x) 更接近 ex 的真实结果。<br>
	 * 
	 * 特殊情况如下：<br>
	 * 
	 * • 如果参数为 NaN，那么结果为 NaN。<br>
	 * • 如果参数为正无穷大，那么结果为正无穷大。<br>
	 * • 如果参数为负无穷大，那么结果为 -1.0。<br>
	 * • 如果参数为 0，那么结果为 0，符号与参数符号相同。<br>
	 * 
	 * 计算结果必须在准确结果的 1 ulp 范围内。结果必须具有半单调性。<br>
	 * 任何有限输入值的 expm1 的结果一定大于或等于 -1.0。<br>
	 * 注意，一旦 e^x - 1 的准确结果在极限值 -1 的 1/2 ulp 范围内，则应该返回 -1.0。<br>
	 * 
	 * @param x
	 *            在 e^x -1 的计算中 e 的指数。
	 * @return 值 e^x - 1。
	 */
	public static double expm1(double x) {
		return Math.expm1(x);
	}

	/**
	 * 求阶乘
	 * 
	 * @param num
	 * @return num的阶乘
	 */
	public static int fact(int num) {
		if (num < 0)
			throw new IllegalArgumentException("参数不合法");
		int result = 1;
		while (num > 0)
			result *= num--;
		return result;
	}

	/**
	 * 计算组合数。
	 * 
	 * @param num
	 * @param num_chosen
	 * @return 组合数
	 */
	public static int combin(int num, int num_chosen) {
		if (num < 1 || num_chosen < 1 || num < num_chosen)
			throw new IllegalArgumentException("参数不合法");
		if (num_chosen * 2 > num)
			num_chosen = num - num_chosen;
		int num2 = num - num_chosen;
		int result = 1, result2 = 1;
		while (num > num2)
			result *= num--;
		while (num_chosen > 0)
			result2 *= num_chosen--;
		return result / result2;
	}

	/**
	 * 计算排列数。
	 * 
	 * @param num
	 * @param num_chosen
	 * @return 排列数
	 */
	public static int permutation(int num, int num_chosen) {
		if (num < 1 || num_chosen < 1 || num < num_chosen)
			throw new IllegalArgumentException("参数不合法");
		int nChosen = num - num_chosen;
		int result = 1;
		while (num > nChosen)
			result *= num--;
		return result;
	}

	/**
	 * 将参数num沿绝对值增大的方向，舍入为最接近参数significance的最小倍数。
	 * 
	 * @param num
	 * @param significance
	 * @return 如果 Number和 Significance符号不同，Ceiling返回无效值。
	 */
	public static double ceilingForNum(double num, double significance) {
		if (num * significance < 0 || significance == 0)
			throw new IllegalArgumentException("参数不合法");
		return Math.ceil(num / significance) * significance;
	}

	/**
	 * 将参数num沿绝对值减小的方向，舍入为最接近参数significance的最小倍数。
	 * 
	 * @param num
	 * @param significance
	 * @return 如果 Number和 Significance符号不同，Ceiling返回无效值。
	 */
	public static double floorForNum(double num, double significance) {
		if (num * significance < 0 || significance == 0)
			throw new IllegalArgumentException("参数不合法");
		return (int) (num / significance) * significance;
	}

	/**
	 * 将参数num沿绝对值增大的方向，舍入为最接近的偶数。
	 * 
	 * @param num
	 * @return 偶数
	 */
	public static double even(double num) {
		double significance = signum(num) * 2;
		return Math.ceil(num / significance) * significance;
	}

	/**
	 * 将参数num沿绝对值增大的方向，舍入为最接近的奇数。
	 * 
	 * @param num
	 * @return 奇数
	 */
	public static double odd(double num) {
		if (Math.ceil(num) % 2 == 0)
			return (Math.ceil(Math.abs(num)) + 1) * signum(num);
		return Math.ceil(Math.abs(num)) * signum(num);
	}

	/**
	 * 返回num的小数部分 <br>
	 * eg: <br>
	 * fracPart(2.49) = 0.49<br>
	 * fracPart(-8.93) = 0.07。
	 * 
	 * @param num
	 * @return [0, 1)之间的值
	 */
	public static double fracPart(double num) {
		int scale = (int) Math.pow(10, scale(num));
		return num * scale % scale / scale + (num < 0 ? 1 : 0);
	}

	/**
	 * 从小数点后第一个不为零的格式化
	 * 
	 * @param input
	 * @param dig
	 *            需要大于零，否则没意义
	 * @return
	 */
	public static String roundFromPoint(double input, int dig) {
		dig = dig <= 0 ? 2 : dig;
		BigDecimal inputValue = new BigDecimal(input);
		String inputString = inputValue.toPlainString();
		int pointIndex = inputString.indexOf(".");
		if (input > 1)
			return inputString.substring(0, pointIndex + 1 + dig);
		if (pointIndex == -1)
			return inputValue.toPlainString();
		int fromIndex = 0;

		while (pointIndex != inputString.length() - 1) {
			pointIndex++;
			String value = String.valueOf(inputString.charAt(pointIndex));
			if (!"0".equals(value)) {
				fromIndex = pointIndex;
				break;
			}

		}
		if (fromIndex + dig > inputString.length())
			return inputString;
		String value = inputString.substring(0, fromIndex + dig);
		return value;
	}

	/**
	 * @param argv
	 * @exclude
	 */
	public static void main(String[] argv) {
		// System.out.println(getSlope(new double[][] { { 1d, 1d }, { 3d, 1d }
		// }));
		// double[] first = new double[] { 1, 3, 5, 7 };
		// double[] second = new double[] { 0, 3, 6, 9 };
		// System.out.println(MathUtil.cross(first, second));
		// int[] arr = { 4, 6, 9, 7 };
		// System.out.println(lcm(arr));// 最小公倍数
		//
		// int[] arr2 = { 20 * 21, 35 * 18, 21 * 45 };
		// System.out.println(gcd(arr2));// 最大公约数
		//
		// double[] arr3 = { 0.1, 0.7, 1.3, 0.5 };
		// System.out.println(lcm(arr3, 2));// 最小公倍数

		// 安全四则运算
		System.out.println(0.05 + 0.01);
		System.out.println(MathUtil.add(0.05, 0.01));
		System.out.println(1.0 - 0.42);
		System.out.println(MathUtil.sub(1.0, 0.42));
		System.out.println(4.015 * 100);
		System.out.println(MathUtil.mul(4.015, 100));
		System.out.println(123.3 / 100);
		System.out.println(MathUtil.div(123.3, 100));

		System.out.println(roundFromPoint(0.00036, 0));

		System.out.println("5.036".indexOf("."));
	}

}
