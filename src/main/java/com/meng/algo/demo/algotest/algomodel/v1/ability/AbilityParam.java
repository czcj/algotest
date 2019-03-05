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
package com.meng.algo.demo.algotest.algomodel.v1.ability;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhang
 * @date 2017年10月31日 上午11:35:37	
 */
public class AbilityParam implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2120213739413538112L;
	public AbilityParam(){
		
	}
	public AbilityParam(List<Double> difficulty,List<Double> score,List<Double> likelihood,List<Double> type){
		this.difficulty = difficulty;
		this.score = score;
		this.likelihood = likelihood;
		this.type = type;
	}
	
	public AbilityParam(List<Double> difficulty,List<Double> score,List<Double> likelihood,List<Double> type,Integer totalLevel){
		this.difficulty = difficulty;
		this.score = score;
		this.likelihood = likelihood;
		this.type = type;
		this.totalLevel = totalLevel;
	}
	/**
	 * 学生做过的题目的难度
	 */
	private List<Double> difficulty;
	
	/**
	 * 学生做过的题目对应的得分
	 */
	private List<Double> score;
	
	/**
	 * 该学生目前的能力估计对应的最大似然函数概率值(99个)，< likelihood> 
	 */
	private List<Double> likelihood;
	
	/**
	 * 能力评估类型(1-assessment,2-practice)，< type >
	 */
	private List<Double> type;
	
	/**
	 * 能力值级别数量 默认3个级别
	 */
	private Integer totalLevel;
	/**
	 * difficulty.
	 * @return  the difficulty
	 */
	public List<Double> getDifficulty() {
		return difficulty;
	}

	/**
	 * score.
	 * @return  the score
	 */
	public List<Double> getScore() {
		return score;
	}

	/**
	 * likelihood.
	 * @return  the likelihood
	 */
	public List<Double> getLikelihood() {
		return likelihood;
	}

	/**
	 * type.
	 * @return  the type
	 */
	public List<Double> getType() {
		return type;
	}

	/**
	 * totalLevel.
	 * @return  the totalLevel
	 */
	public Integer getTotalLevel() {
		return totalLevel;
	}

	/**
	 * @param   difficulty  the difficulty to set
	 */
	public void setDifficulty(List<Double> difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @param   score  the score to set
	 */
	public void setScore(List<Double> score) {
		this.score = score;
	}

	/**
	 * @param   likelihood  the likelihood to set
	 */
	public void setLikelihood(List<Double> likelihood) {
		this.likelihood = likelihood;
	}

	/**
	 * @param   type  the type to set
	 */
	public void setType(List<Double> type) {
		this.type = type;
	}

	/**
	 * @param   totalLevel  the totalLevel to set
	 */
	public void setTotalLevel(Integer totalLevel) {
		this.totalLevel = totalLevel;
	}

	
	
}
