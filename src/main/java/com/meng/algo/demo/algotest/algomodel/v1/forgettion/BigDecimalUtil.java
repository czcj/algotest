/*
 * Copyright (C) 2016 Shanghai yixue soft Co., Ltd
 *
 * All copyrights reserved by Shanghai yixue.
 * Any copying, transferring or any other usage is prohibited.
 * Or else, Shanghai yixue possesses the right to require legal 
 * responsibilities from the violator.
 * All third-party contributions are distributed under license by
 * Shanghai yixue soft Co., Ltd.
 */
package com.meng.algo.demo.algotest.algomodel.v1.forgettion;

import java.math.BigDecimal;

/**
 * @author zhang
 * @date 2017年12月7日 下午3:26:44	
 */
public class BigDecimalUtil {
	private BigDecimalUtil(){
		
	}
	private static final int ROUND_HALF_UP = 10;   
	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时
	 * @param v1 被除数
	 * @param v2 除数
	 * 两个参数的商
	 */
	public static double div(double v1,double v2) {
		return div(v1, v2, ROUND_HALF_UP);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		
		double result = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal b = BigDecimal.valueOf(result);
		return b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
}
