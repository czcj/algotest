package com.meng.algo.demo.algotest.pojo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.msgpack.annotation.Message;

/**
 * @author IBM CHEN ZHI KAN
 * @create 2017/11/8
 */
@Message
public class LearnMapCounter {

  private Set<String> candidateNodes;//可选的知识点（goal下所有知识点的key）
  private Set<String> masteryNodes;//掌握的的知识点(初始化时为空)
  private Set<String> noItemsNodes;//没题的知识点（goal下所有知识点的key）
  private Set<String> notMasteredNodes;//未掌握的知识点（学习过的知识点，但是未掌握）
  private Set<String> noMasteredNodes;//未掌握知识点（初始化为当前goal的所有知识点）
  private Set<String> noDealedNodes;//不需要做的知识点状态
  private Set<String> recommendedSet;//学过的知识点
  private Set<String> curLearnedNodeList;//当前轮次已学习知识点
  private Set<String> curExerciseNodeList;//本轮次需要测试的知识点
  private Set<String> curMasterNodeList;//当前轮次已掌握的知识点
  private Map<String, Target> curExerciseNodeMap;//当前轮次需要练习的目标

  public LearnMapCounter() {

  }

  public Set<String> getNoMasteredNodes() {
    if (noMasteredNodes == null) {
      return new TreeSet<String>();
    }
    return noMasteredNodes;
  }

  public void setNoMasteredNodes(Set<String> noMasteredNodes) {
    if (this.noMasteredNodes == null || noMasteredNodes.size() == 0) {
      this.noMasteredNodes = noMasteredNodes;
    } else {
      this.noMasteredNodes.addAll(noMasteredNodes);
    }
    this.noMasteredNodes = noMasteredNodes;
  }

  public Set<String> getNoDealedNodes() {
    if (noDealedNodes == null) {
      return new TreeSet<String>();
    }
    return noDealedNodes;
  }

  public void setNoDealedNodes(Set<String> noDealedNodes) {

    if (this.noDealedNodes == null || noDealedNodes.size() == 0) {
      this.noDealedNodes = noDealedNodes;
    } else {
      this.noDealedNodes.addAll(noDealedNodes);
    }
    this.noDealedNodes = noDealedNodes;
  }

  /**
   * 更新没有题的知识点
   */
  public void updateNoItemNode(String loCode) {
    this.getNoItemsNodes().add(loCode);
  }

  public Set<String> getNoItemsNodes() {
    if (noItemsNodes == null) {
      return new TreeSet<String>();
    }
    return noItemsNodes;
  }

  public void setNoItemsNodes(Set<String> noItemsNodes) {
    if (this.noItemsNodes == null || noItemsNodes.size() == 0) {
      this.noItemsNodes = noItemsNodes;
    } else {
      this.noItemsNodes.addAll(noItemsNodes);
    }
  }

  public Map<String, Target> getCurExerciseNodeMap() {
    if (curExerciseNodeMap == null) {
      return new TreeMap<String, Target>();
    }
    return curExerciseNodeMap;
  }

  //判断本轮次知识点是否都学完了
  public Boolean allExerciseLOIsClose() {
    if (curExerciseNodeMap == null) {
      return true;
    }
    Iterator<String> loKey = curExerciseNodeMap.keySet().iterator();
    while (loKey.hasNext()) {
      String loCode = loKey.next();
      Target currentLOTarget = curExerciseNodeMap.get(loCode);
      if (currentLOTarget != null && !currentLOTarget.getStatus().equals("COMPLETED")) {
        return false;
      }
    }
    return true;
  }

  public void setCurExerciseNodeMap(
      Map<String, Target> curExerciseNodeMap) {
    this.curExerciseNodeMap = curExerciseNodeMap;
  }

//  public String recommendNextExerciseLO(String currentLOCode) {
//    Target target = curExerciseNodeMap.get(currentLOCode);
//    if (target != null && target.getStatus().equals("INPROCESS")) {
//
//      return currentLOCode;
//    }
//    Iterator<String> loKey = curExerciseNodeMap.keySet().iterator();
//    while (loKey.hasNext()) {
//      String loCode = loKey.next();
//      Target currentLOTarget = curExerciseNodeMap.get(loCode);
//      if (currentLOTarget != null && !currentLOTarget.getStatus().equals("COMPLETED")) {
//        currentLOTarget.setStatus("INPROCESS");
//        return loCode;
//      }
//    }
//    throw IRSException.getException(IRSExceptionEnum.RECOMMEND_LO_IS_MISSING);
//  }
  /**
   * 推荐下一个练习知识点
   * 判断当前是否需要继续推送练习题
   * 如果需要判断targetType
   * 否则关闭当前target，推送下一个target
   * 如果没有target可以继续推荐，则抛出异常
   *
   * 解决了当前知识点错几题推几题的需求
   */
//  public String recommendNextExerciseLO(String currentLOCode) {
//      Target target = curExerciseNodeMap.get(currentLOCode);
//      target.setStatus("COMPLETED");
//      curExerciseNodeMap.put(currentLOCode,target);
//      Iterator<String> loKey = curExerciseNodeMap.keySet().iterator();
//      while (loKey.hasNext()) {
//        String loCode = loKey.next();
//        Target currentLOTarget = curExerciseNodeMap.get(loCode);
//        if (currentLOTarget != null && !currentLOTarget.getStatus().equals("COMPLETED")) {
//          currentLOTarget.setStatus("INPROCESS");
//          return loCode;
//        }
//      }
//
//      throw IRSException.getException(IRSExceptionEnum.RECOMMEND_LO_IS_MISSING);
//    }else{
//      //没学完继续推
//      return recommendNextExerciseLOByTargetType(currentLOCode);
//    }
//
//  }

  /**
   * 判断是否还需要继续推送练习题
   * 判断当前知识点已经做了的练习题题数是否小于做错的题数
   * 小于则代表继续推送练习题
   * 大于则代表当前练习题已经不需要在做了
//   * @param currentLOCode
   * @return true代表学完了，false代表没学完
   */
//  public boolean isExerciseCompleted(String currentLOCode) {
//    Target target = curExerciseNodeMap.get(currentLOCode);
//    if (target.getStatus().equals("COMPLETED")) {
//      return true;
//    }
//    return target.getFinishExerciseNum() >= target.getMistakeItems().size();
//  }

  public Set<String> getCurExerciseNodeList() {
    if (curExerciseNodeList == null) {
      return new TreeSet<String>();
    }
    return curExerciseNodeList;
  }

  public void setCurExerciseNodeList(Set<String> curExerciseNodeList) {
    this.curExerciseNodeList = curExerciseNodeList;
  }

  public Set<String> getCurLearnedNodeList() {
    if (curLearnedNodeList == null) {
      return new TreeSet<String>();
    }
    return curLearnedNodeList;
  }

  public void setCurLearnedNodeList(Set<String> curLearnedNodeList) {
    this.curLearnedNodeList = curLearnedNodeList;
  }

  public Set<String> getCurMasterNodeList() {
    if (curMasterNodeList == null) {
      return new TreeSet<String>();
    }
    return curMasterNodeList;
  }

  public void setCurMasterNodeList(Set<String> curMasterNodeList) {
    if (this.curMasterNodeList == null) {
      this.curMasterNodeList = curMasterNodeList;
    } else {
      this.curMasterNodeList.addAll(curMasterNodeList);
    }
  }

  public LearnMapCounter(Set<String> candidateNodes,
      Set<String> masteryNodes, Set<String> notMasteredNodes,
      Set<String> recommendedSet,Set<String> noMasteredNodes) {
    this.candidateNodes = candidateNodes;
    this.masteryNodes = masteryNodes;
    this.notMasteredNodes = notMasteredNodes;
    this.recommendedSet = recommendedSet;
    this.noMasteredNodes = noMasteredNodes;
  }

  public Set<String> getRecommendedSet() {
    if (recommendedSet == null) {
      this.recommendedSet = new HashSet<>();
    }
    return this.recommendedSet;
  }

  public void setRecommendedSet(Set<String> recommendedSet) {
    if (this.recommendedSet == null) {
      this.recommendedSet = new HashSet<>();
    }
    this.recommendedSet.addAll(recommendedSet);
  }

  public Set<String> getCandidateNodes() {
    return candidateNodes;
  }

  public void setCandidateNodes(Set<String> candidateNodes) {
    if (this.candidateNodes == null) {
      this.candidateNodes = new HashSet<String>();
    }
    this.candidateNodes.addAll(candidateNodes);
  }

  public Set<String> getMasteryNodes() {
    if (masteryNodes == null) {
      masteryNodes = new TreeSet<String>();
    }
    return masteryNodes;
  }

  public void setMasteryNodes(Set<String> masteryNodes) {
    if (masteryNodes == null || masteryNodes.size() == 0) {
      return;
    }
    if (this.masteryNodes == null) {
      this.masteryNodes = new TreeSet<String>();
    }
    this.masteryNodes.addAll(masteryNodes);
  }

  public Set<String> getNotMasteredNodes() {
    if (this.notMasteredNodes == null) {
      this.notMasteredNodes = new TreeSet<String>();
    }
    return this.notMasteredNodes;
  }

  public void setNotMasteredNodes(Set<String> notMasteredNodes) {
    if (notMasteredNodes == null || notMasteredNodes.size() == 0) {
      return;
    }
    if (this.notMasteredNodes == null) {
      this.notMasteredNodes = new HashSet<String>();
    }
    this.notMasteredNodes.addAll(notMasteredNodes);
  }


}
