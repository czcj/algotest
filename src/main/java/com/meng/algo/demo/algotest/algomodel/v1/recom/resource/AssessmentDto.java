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
package com.meng.algo.demo.algotest.algomodel.v1.recom.resource;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhang
 * @date 2017年10月31日 上午10:19:05
 */
public class AssessmentDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8014231893058480069L;

	/**
	 * 题目ID集合
	 */
	private List<String> questions;

	public AssessmentDto() {

	}

	public AssessmentDto(List<String> questions) {
		this.questions = questions;
	}

	/**
	 * questions.
	 * 
	 * @return the questions
	 */
	public List<String> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

}
