package com.math.util;

import java.util.Arrays;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

/**
 * R函数库(为调用方便，封装R库的部分函数库)
 * 
 * @author 钟城 2011-8-1
 * @exclude
 */
public class RUtil {
	private static Rengine re = null;
	static {
		try {
			// 为了让程序能用nohup正常启动，添加参数
			re = new Rengine(new String[] { "--vanilla" }, false, null);
		} catch (Throwable e) {
		}
	}

	private RUtil() {
	}

	/**
	 * 计算分位数(概率取0,0.25,0.5,0.75,1)，返回通过每个概率计算后的分位数的值所组成的数组。(为避免多线程同时计算分位数时容易死锁( R的BUG)，此方法为同步方法)
	 * 
	 * @param arr
	 *            被计算的double数组
	 * @return 分位数
	 * @exclude
	 */
	public static synchronized double[] quantile(double[] arr) {
		try {
			re.assign("tt", arr);
			REXP x = re.eval("quantile(tt)");
			return x.asDoubleArray();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			re.end();
		}
	}

	/**
	 * 计算分位数，返回通过指定概率计算后的分位数的值。(为避免多线程同时计算分位数时容易死锁(R的BUG)，此方法为同步方法)
	 * 
	 * @param arr
	 *            被计算的double数组
	 * @param probs
	 *            概率
	 * @return 分位数
	 * @exclude
	 */
	public static synchronized double quantile(double[] arr, double probs) {
		try {
			re.assign("tt", arr);
			re.assign("probs", new double[] { probs });
			REXP x = re.eval("quantile(tt,probs)");
			return x.asDouble();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			re.end();
		}
	}

	public static synchronized double[][] matrix(int start, int end, int row, int col) {
		try {
			re.assign("start", new int[] { start });
			re.assign("end", new int[] { end });
			re.assign("row", new int[] { row });
			re.assign("col", new int[] { col });
			REXP result = re.eval("matrix(start:end,nrow=row,ncol=col)%*%matrix(start:end,nrow=col,ncol=row)");
			return result.asDoubleMatrix();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			re.end();
		}
	}

	public static void main(String[] args) {
		double[][] a = matrix(1, 12, 3, 4);
		System.out.println(a);
	}
}
