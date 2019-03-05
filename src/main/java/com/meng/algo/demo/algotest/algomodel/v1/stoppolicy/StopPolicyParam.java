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
package com.meng.algo.demo.algotest.algomodel.v1.stoppolicy;


import java.util.List;

/**
 * 描述：BKTMode接口参数实体类
 * 作者：wrz
 */
public class StopPolicyParam {

	/**
	 *
	 */
	private static final long serialVersionUID = 76438689323022285L;

	/**
	 * 答案正误 不能为空
	 */
	//	private Integer answer;
	/**
	 * 学生作答结果集合(1-对，0-错)  多个作答结果必须按照实际做题顺序提供
	 */
	private List<Integer> answers;

	/**
	 * 最大题量 不能为空
	 */
	private Integer max_size;

	/**
	 * 能力值 不能为空
	 */
	private Double ability;
	/**
	 * 做题答案是否为增量方式提供True-增量,False-全量
	 * 默认为True
	 */
	private Boolean increment;

	/**
	 * 知识点编号 不能为空
	 */
	private String node_code;
	/**
	 * 科目编号 不能为空
	 */
	private String subject_code;

	public String getSubjectCode() {
		return subject_code;
	}

	public void setSubjectCode(String subject_code) {
		this.subject_code = subject_code;
	}

	public Double getAbility() {
		return ability;
	}

	public void setAbility(Double ability) {
		this.ability = ability;
	}

	public Integer getMax_size() {
		return max_size;
	}

	public void setMax_size(Integer max_size) {
		this.max_size = max_size;
	}

	public String getNode_code() {
		return node_code;
	}

	public void setNode_code(String node_code) {
//		StringUtil.dislodgeTrim(node_code);
		this.node_code = node_code;
	}

	//    public Integer getAnswer() {
	//        return answer;
	//    }
	//
	//    public void setAnswer(Integer answer) {
	//        this.answer = answer;
	//    }

	public List<Integer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Integer> answers) {
		this.answers = answers;
	}

	public Boolean isIncrement() {
		if (increment==null){
			increment=true;
		}
		return increment;
	}

	public void setIncrement(Boolean increment) {
		this.increment = increment;
	}
}
