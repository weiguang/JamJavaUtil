package com.okayjam.util;

import java.math.BigDecimal;

/**
 * @author Chen Weiguang <chen2621978@gmail.com>
 */
public class DataUtil {

   static final private int []  SCALA = {1, 10 ,100, 1000, 10000, 100000, 100000, 1000000, 10000000, 100000000, 1000000000};

	/**
	 * 保留n位小数
	 * @param data 输入数值
	 * @param n 保留的小数点位数
	 * @return 返回新的浮点数
	 */
	public static double reservedDecimal(double data,int n) {
        return new BigDecimal(data).setScale(n, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	/**
	 * 耗时短， 最多保留10位小数，适合数值小的数据,大数会溢出
	 * @param d 输入数值
	 * @param n 保留的小数点位数
	 * @return 返回新的浮点数
	 */
	public static double reservedDecimal1(double d, int n) {
		int a = SCALA[n];
		return (double)Math.round(d * a) / a;
	}


	/**
	 * 小端输出模式(计算机处理常用的方式)
	 * @param i 需要转换的数字
	 * @return 字节数组
	 */
	public static byte[] int2BytesSmall(int i) {
		return new byte[] {
				(byte) (i & 0xFF),
				(byte) ((i >> 8) & 0xFF),
				(byte) ((i >> 16) & 0xFF),
				(byte) ((i >> 24) & 0xFF)
		};
	}

	/**
	 * 大端输出模式(符合人阅读)
	 * @param i  需要转换的数字
	 * @return 字节数组
	 */
	public static byte[] int2BytesBig(int i) {
		byte[] bytes = new byte[4];
		int offset = 0;
		bytes[offset + 0] = (byte) (i >>> 24);
		bytes[offset + 1] = (byte) (i >>> 16);
		bytes[offset + 2] = (byte) (i >>> 8);
		bytes[offset + 3] = (byte) i;
		return bytes;
	}
	
}
