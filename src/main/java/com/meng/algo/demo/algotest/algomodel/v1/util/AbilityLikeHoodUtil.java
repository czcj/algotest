package com.meng.algo.demo.algotest.algomodel.v1.util;

import java.util.Collections;
import java.util.List;

public class AbilityLikeHoodUtil {

  /**
   * 根据likelihood得到能力值
   * @param likelihood
   * @return
   */
  public Double getAbilityByLikeliHood(List<Double> likelihood){
    Double max = Collections.max(likelihood);
    int index = likelihood.indexOf(max);
    double ability = (index + 1) * 0.01;
    return ability;
  }
}
