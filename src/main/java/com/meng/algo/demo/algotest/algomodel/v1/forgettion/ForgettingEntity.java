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
 * @date 2017年12月5日 上午10:12:43	
 */
public class ForgettingEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 复习次数
	 */
	private Integer reviewNum;
	
	/**
	 * 上次做题时间
	 */
	private Long lastTime;
	
	/**
	 * 知识点
	 */
	private String node;

	/**
	 * reviewNum.
	 * @return  the reviewNum
	 */
	public Integer getReviewNum() {
		return reviewNum;
	}

	/**
	 * lastTime.
	 * @return  the lastTime
	 */
	public Long getLastTime() {
		return lastTime;
	}

	/**
	 * node.
	 * @return  the node
	 */
	public String getNode() {
		return node;
	}

	/**
	 * @param   reviewNum  the reviewNum to set
	 */
	public void setReviewNum(Integer reviewNum) {
		this.reviewNum = reviewNum;
	}

	/**
	 * @param   lastTime  the lastTime to set
	 */
	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}

	/**
	 * @param   node  the node to set
	 */
	public void setNode(String node) {
		this.node = node;
	}

}
