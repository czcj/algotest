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

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * @author zhang
 * @date 2017年12月4日 上午9:58:38	
 */
public class ForgettingParam {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 记忆曲线 批量参数
	 */
	private List<ForgettingEntity> nodes;
	/**
	 * 文科与理科true理科，false文科
	 */
	private Boolean isDisBeing;


	public Boolean getDisBeing() {
		return isDisBeing;
	}

	public void setDisBeing(Boolean disBeing) {
		isDisBeing = disBeing;
	}
	/**
	 * nodes.
	 * @return  the nodes
	 */
	public List<ForgettingEntity> getNodes() {
		return nodes;
	}

	/**
	 * @param   nodes  the nodes to set
	 */
	public void setNodes(List<ForgettingEntity> nodes) {
		this.nodes = nodes;
	}

	


}
