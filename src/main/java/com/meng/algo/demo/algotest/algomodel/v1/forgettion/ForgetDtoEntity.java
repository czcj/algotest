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
 * @date 2017年12月5日 上午10:45:32	
 */
public class ForgetDtoEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6816941452931614332L;
	/**
	 * 是否为复习 true是复习 false不复习
	 */
	private boolean isReview;
	
	/**
	 * 标准遗忘率
	 */
	private Double retention;
	
	/**
	 * 知识点
	 */
	private String node;

	/**
	 * isReview.
	 * @return  the isReview
	 */
	public boolean isReview() {
		return isReview;
	}
	/**
	 * @param   isReview  the isReview to set
	 */
	public void setReview(boolean isReview) {
		this.isReview = isReview;
	}
	/**
	 * retention.
	 * @return  the retention
	 */
	public Double getRetention() {
		return retention;
	}
	/**
	 * node.
	 * @return  the node
	 */
	public String getNode() {
		return node;
	}
	/**
	 * @param   retention  the retention to set
	 */
	public void setRetention(Double retention) {
		this.retention = retention;
	}
	/**
	 * @param   node  the node to set
	 */
	public void setNode(String node) {
		this.node = node;
	}

	
}
