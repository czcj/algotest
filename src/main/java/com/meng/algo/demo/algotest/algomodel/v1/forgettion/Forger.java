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

import java.io.Serializable;

/**
 * @author zhang
 * @date 2017年12月4日 下午4:42:43	
 */
public class Forger implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 4054073673676061686L;

	/**
	 * 实际遗忘率
	 */
	private Double forgerValue; 
	
	/**
	 * 标准遗忘率
	 */
	private Double pForger;
	
	/**
	 * 标准遗忘时间
	 */
	private Double standardTime; 


	/**
	 * forgerValue.
	 * @return  the forgerValue
	 */
	@SuppressWarnings("unused")
	public Double getForgerValue() {
		return forgerValue;
	}

	/**
	 * pForger.
	 * @return  the pForger
	 */
	@SuppressWarnings("unused")
	public Double getpForger() {
		return pForger;
	}

	/**
	 * @param   forgerValue  the forgerValue to set
	 */
	public void setForgerValue(Double forgerValue) {
		this.forgerValue = forgerValue;
	}

	/**
	 * @param   pForger  the pForger to set
	 */
	public void setpForger(Double pForger) {
		this.pForger = pForger;
	}

	/**
	 * standardTime.
	 * @return  the standardTime
	 */
	public Double getStandardTime() {
		return standardTime;
	}

	/**
	 * @param   standardTime  the standardTime to set
	 */
	public void setStandardTime(Double standardTime) {
		this.standardTime = standardTime;
	}

}
