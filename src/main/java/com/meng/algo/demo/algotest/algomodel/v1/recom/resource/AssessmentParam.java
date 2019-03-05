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
 * @date 2017年10月30日 下午8:28:33
 */
public class AssessmentParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 学生前一次题目难度
	 */
	private Double preDiff;

	/**
	 * 学生答题前一次题目对错（1：对；0：错）
	 */
	private Integer preAnswer;

	/**
	 * 学生能力值
	 */
	private Double ability;

	/**
	 * 题目ID集合
	 */
	private List<String> question_ids;

	/**
	 * 题目难度值集合
	 */
	private List<Double> question_difficulties;
	
	/**
	 * 推题的数量
	 */
	private Integer assessment_size;

	/**
	 * 学生做过的题目的难度
	 */
	private Integer totalLevel;

	public AssessmentParam() {

	}

	public AssessmentParam(List<String> question_ids, List<Double> question_difficulties, int assessment_size, double ability) {
		this.question_ids = question_ids;
		this.question_difficulties = question_difficulties;
		this.assessment_size = assessment_size;
		this.ability = ability;
	}
	
	public AssessmentParam(List<String> question_ids, List<Double> question_difficulties, int assessment_size, double ability,
			Integer totalLevel) {
		this.question_ids = question_ids;
		this.question_difficulties = question_difficulties;
		this.assessment_size = assessment_size;
		this.ability = ability;
		this.totalLevel = totalLevel;
	}


	public Double getPreDiff() {
		return preDiff;
	}

	public void setPreDiff(Double preDiff) {
		this.preDiff = preDiff;
	}

	public Integer getPreAnswer() {
		return preAnswer;
	}

	public void setPreAnswer(Integer preAnswer) {
		this.preAnswer = preAnswer;
	}

	/**
	 * ability.
	 * @return  the ability
	 */
	public Double getAbility() {
		return ability;
	}

	/**
	 * question_ids.
	 * @return  the question_ids
	 */
	public List<String> getQuestion_ids() {
		return question_ids;
	}

	/**
	 * question_difficulties.
	 * @return  the question_difficulties
	 */
	public List<Double> getQuestion_difficulties() {
		return question_difficulties;
	}

	/**
	 * assessment_size.
	 * @return  the assessment_size
	 */
	public Integer getAssessment_size() {
		return assessment_size;
	}

	/**
	 * totalLevel.
	 * @return  the totalLevel
	 */
	public Integer getTotalLevel() {
		return totalLevel;
	}

	/**
	 * @param   ability  the ability to set
	 */
	public void setAbility(Double ability) {
		this.ability = ability;
	}

	/**
	 * @param   question_ids  the question_ids to set
	 */
	public void setQuestion_ids(List<String> question_ids) {
		this.question_ids = question_ids;
	}

	/**
	 * @param   question_difficulties  the question_difficulties to set
	 */
	public void setQuestion_difficulties(List<Double> question_difficulties) {
		this.question_difficulties = question_difficulties;
	}

	/**
	 * @param   assessment_size  the assessment_size to set
	 */
	public void setAssessment_size(Integer assessment_size) {
		this.assessment_size = assessment_size;
	}

	/**
	 * @param   totalLevel  the totalLevel to set
	 */
	public void setTotalLevel(Integer totalLevel) {
		this.totalLevel = totalLevel;
	}
}
