package com.meng.algo.demo.algotest.algomodel.v1.recom.resource;

public class NodeString {

  //题目ID
  private String key;
  //题目推出概率
  private Double value;

  public Double getDiff() {
    return diff;
  }

  public void setDiff(Double diff) {
    this.diff = diff;
  }

  //题目难度
  public Double diff;

  public NodeString() {

  }

  public NodeString(String key, Double value,Double diff) {
    this.key = key;
    this.value = value;
    this.diff = diff;
  }
  public NodeString(String key, Double value) {
    this.key = key;
    this.value = value;
  }

  /**
   * key.
   *
   * @return the key
   */
  public String getKey() {
    return key;
  }

  /**
   * value.
   *
   * @return the value
   */
  public Double getValue() {
    return value;
  }

  /**
   * @param key the key to set
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * @param value the value to set
   */
  public void setValue(Double value) {
    this.value = value;
  }
}
