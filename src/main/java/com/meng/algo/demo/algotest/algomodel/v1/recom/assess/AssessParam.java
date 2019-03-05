package com.meng.algo.demo.algotest.algomodel.v1.recom.assess;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 描述：先行测试知识点测评的状态数据实体类
 * 作者：王儒作
 * 创建时间：2017年10月31日11:28:11
 */
public class AssessParam implements Serializable {

  /**
   * 生成唯一序列
   */
  private static final long serialVersionUID = 76438682323937485L;


  //剩余（可选知识点集合）
  private List<String> restKNodes;

  //候选知识点
  private List<String> kNodesCandidate;

  //当前图谱下没有后置知识点的集合
  private List<String> updatedEndKNode;

  //当前图谱下所有前置知识点，key:所有知识点，value:知识点对应的直接前置知识点
  private Map<String, List<String>> hmUpdatedPreKMap;

  //当前图谱下所有后置知识点，key:所有知识点，value:知识点对应的直接后置知识点
  private Map<String, List<String>> hmUpdatedPostKMap;


  public List<String> getRestKNodes() {
    return restKNodes;
  }

  public void setRestKNodes(List<String> restKNodes) {
    this.restKNodes = restKNodes;
  }

  public List<String> getkNodesCandidate() {
    return kNodesCandidate;
  }

  public void setkNodesCandidate(List<String> kNodesCandidate) {
    this.kNodesCandidate = kNodesCandidate;
  }


  public List<String> getUpdatedEndKNode() {
    return updatedEndKNode;
  }

  public void setUpdatedEndKNode(List<String> updatedEndKNode) {
    this.updatedEndKNode = updatedEndKNode;
  }

  public Map<String, List<String>> getHmUpdatedPreKMap() {
    return hmUpdatedPreKMap;
  }

  public void setHmUpdatedPreKMap(Map<String, List<String>> hmUpdatedPreKMap) {
    this.hmUpdatedPreKMap = hmUpdatedPreKMap;
  }

  public Map<String, List<String>> getHmUpdatedPostKMap() {
    return hmUpdatedPostKMap;
  }

  public void setHmUpdatedPostKMap(Map<String, List<String>> hmUpdatedPostKMap) {
    this.hmUpdatedPostKMap = hmUpdatedPostKMap;
  }

}
