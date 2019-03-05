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
 * @date 2017年11月30日 下午6:10:12	
 */
public final class ForgettingCurve {
	
	private volatile static ForgettingCurve forgettingCurve;
	public ForgettingCurve(){}

	/**
	 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
	 * @return
	 */
	public static ForgettingCurve getSingleton() {
		return new ForgettingCurve();
	}
	
	/**
	 * 遗忘曲线随时间的变化，记忆的丢失率
	 * @param t 时间小时
	 * @param u 
	 * @param a
	 * @return
	 */
	private static Double retention(Double t, Double u, Double a){
		//Math.pow(x,y) : x的y次方
	    Double p = u/(u+Math.pow(Math.log10(t*60),a));

		BigDecimal b = new BigDecimal(p);

		Double forgetRate = b.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
		
		return  forgetRate;
	}
	/**
	 * 根据复习的次数不一样 可以得
	 * 到不同的学习记忆参数 
	 * @param isDisBeing 学科是否在配置文件里
	 * @param time 单位为小时 时间
	 * @param fewNumber 复习的次数
	 * @return
	 */
	public Forger getForgetRate(boolean isDisBeing,Double time,Integer fewNumber){
		Forger entry = new Forger();
		//文科理科？
		if(isDisBeing){
			if(fewNumber<=2){
				entry.setForgerValue(retention(time,4.0,0.6));
				entry.setpForger(0.63);
				entry.setStandardTime(10.0*24);
			}else if (2<fewNumber&& fewNumber<=5) {
				entry.setForgerValue(retention(time,20.0,0.1));
				entry.setpForger(0.94);
				entry.setStandardTime(60.0*24);
			}else if(5<fewNumber){
				entry.setForgerValue(1d);
				entry.setpForger(0.94);
				entry.setStandardTime(60.0*24);
			}
		}else{
			switch (fewNumber) {
				case 0:					
					entry.setForgerValue(retention(time,1.8,1.21));
					entry.setpForger(0.31);
					entry.setStandardTime(1.0*24);
					break;
				case 1:
					entry.setForgerValue(retention(time,2.5,0.9));
					entry.setpForger(0.44);
					entry.setStandardTime(3.0*24);
					break;
				case 2:
					entry.setForgerValue(retention(time,4.0,0.6));
					entry.setpForger(0.63);
					entry.setStandardTime(10.0*24);
					break;
				case 3:
					entry.setForgerValue(retention(time,8.0,0.4));
					entry.setpForger(0.81); 
					entry.setStandardTime(30.0*24);
					break;			
				case 4:
					entry.setForgerValue(retention(time,20.0,0.1));
					entry.setpForger(0.94);
					entry.setStandardTime(60.0*24);
					break;
				default:
					entry.setForgerValue(1d);
					entry.setpForger(0.94);
					entry.setStandardTime(60.0*24);
			}		
		}
		
		return entry;
	}
}
