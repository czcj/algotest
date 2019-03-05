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
package com.meng.algo.demo.algotest.algomodel.v1.recom.learnning;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 描述：学习推荐状态数据实体类
 * 作者：王儒作
 * 创建时间：2017年11月1日11:30:21
 */
public class NlixParam implements Serializable {

  /**
   * 生成唯一序列
   */
  private static final long serialVersionUID = 76438619323937485L;

  /**
   * 所有薄弱知识点集合(状态数据)
   */
  private List<String> weak_nodes;

  /**
   * 学过的知识点集合(状态数据)
   */
  private List<String> learned_nodes;

  /**
   * 直接前置
   */
  private Map<String, List<String>> hmPreKMap;

  /**
   * 直接后置
   */
  private Map<String, List<String>> hmPostKMap;

  /**
   * 所有知识点对应的学习次数
   */
  private Map<String, Integer> nodesWithCounts;

  public List<String> getWeak_nodes() {
    return weak_nodes;
  }

  public void setWeak_nodes(List<String> weak_nodes) {
    this.weak_nodes = weak_nodes;
  }

  public List<String> getLearned_nodes() {
    return learned_nodes;
  }

  public void setLearned_nodes(List<String> learned_nodes) {
    this.learned_nodes = learned_nodes;
  }

  public Map<String, List<String>> getHmPreKMap() {
    return hmPreKMap;
  }

  public void setHmPreKMap(Map<String, List<String>> hmPreKMap) {
    this.hmPreKMap = hmPreKMap;
  }

  public Map<String, List<String>> getHmPostKMap() {
    return hmPostKMap;
  }

  public void setHmPostKMap(Map<String, List<String>> hmPostKMap) {
    this.hmPostKMap = hmPostKMap;
  }

  public Map<String, Integer> getNodesWithCounts() {
    return nodesWithCounts;
  }

  public void setNodesWithCounts(Map<String, Integer> nodesWithCounts) {
    this.nodesWithCounts = nodesWithCounts;
  }

}
