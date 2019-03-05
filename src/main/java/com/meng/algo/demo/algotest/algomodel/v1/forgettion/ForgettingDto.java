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
import java.util.List;

/**
 * @author zhang
 * @date 2017年12月4日 上午10:25:07	
 */
public class ForgettingDto implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -5265759884011304953L;
	
	/**
	 * 批量返回遗忘曲线list
	 */
	private List<ForgetDtoEntity> nodes;

	/**
	 * nodes.
	 * @return  the nodes
	 */
	public List<ForgetDtoEntity> getNodes() {
		return nodes;
	}

	/**
	 * @param   nodes  the nodes to set
	 */
	public void setNodes(List<ForgetDtoEntity> nodes) {
		this.nodes = nodes;
	}

	
}
