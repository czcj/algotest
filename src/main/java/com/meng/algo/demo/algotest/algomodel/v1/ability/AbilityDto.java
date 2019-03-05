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
 * @date 2017年3月17日 下午2:26:56
 */
public class AbilityDto  implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -5701665260470744419L;

	public AbilityDto(){
		
	}
	
	public AbilityDto(String ability,List<Double> likelihood,double abilityprob){
		this.ability = ability;
		this.likelihood = likelihood;
		this.abilityprob = abilityprob;
	}
	
	/**
	 * 能力值
	 */
	private String ability;
	/**
	 * 算法版本
	 */
	private String version;
	
	/**
	 *  该学生目前的能力估计对应的最大似然函数概率值(99个)
	 */
	private List<Double> likelihood;
	
	/**
	 * 整体评估能力可能性值
	 */
	private double abilityprob;

	/**
	 * ability.
	 * @return  the ability
	 */
	public String getAbility() {
		return ability;
	}

	/**
	 * likelihood.
	 * @return  the likelihood
	 */
	public List<Double> getLikelihood() {
		return likelihood;
	}

	/**
	 * abilityprob.
	 * @return  the abilityprob
	 */
	public double getAbilityprob() {
		return abilityprob;
	}

	/**
	 * @param   ability  the ability to set
	 */
	public void setAbility(String ability) {
		this.ability = ability;
	}

	/**
	 * @param   likelihood  the likelihood to set
	 */
	public void setLikelihood(List<Double> likelihood) {
		this.likelihood = likelihood;
	}

	/**
	 * @param   abilityprob  the abilityprob to set
	 */
	public void setAbilityprob(double abilityprob) {
		this.abilityprob = abilityprob;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
