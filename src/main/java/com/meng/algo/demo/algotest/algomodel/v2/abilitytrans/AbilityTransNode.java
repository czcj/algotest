package com.meng.algo.demo.algotest.algomodel.v2.abilitytrans;

public class AbilityTransNode {
  //知识点编号
  String node;
  //传递层数
  String transLevel;
  //传递能力值
  String transAbility;
  //计算能力值
  String ability;
  //能力值是传递获得还是做题计算获得
  String reason;
  //能力值传递于哪个知识点code
  String transNode;

  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public String getTransLevel() {
    return transLevel;
  }

  public void setTransLevel(String transLevel) {
    this.transLevel = transLevel;
  }

  public String getTransAbility() {
    return transAbility;
  }

  public void setTransAbility(String transAbility) {
    this.transAbility = transAbility;
  }

  public String getAbility() {
    return ability;
  }

  public void setAbility(String ability) {
    this.ability = ability;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getTransNode() {
    return transNode;
  }

  public void setTransNode(String transNode) {
    this.transNode = transNode;
  }
}
