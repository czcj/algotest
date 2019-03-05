package com.meng.algo.demo.algotest.algomodel.enums;

/**
 * 算法模型标识及版本标识
 * @author wangbiao
 * @date 2018-09-27
 */
public enum AlgoVersionEnum {

  //计算能力值模型原始版本
  ABILITY_ORIGINAL("ab_v1","计算能力值模型原始版本"),
  //计算能力值模型,接入时间维度
  ABILITY_TIME("ab_v2","计算能力值模型,接入时间维度"),
  KST_ORIGINAL("kst_v1","测评推荐(测试推荐)原始版本"),
  QUESTRECOM_ORIGINAL("qestrecom_v1","题目推荐模型原始版"),
  BKT_ORIGINAL("bkt_v1","学习稳定性评判模型(用于中断学习)原始版"),
  LEARNRECOM_ORIGINAL("nlix_v1","需要学习的知识点推荐模型原始版"),
  ;
  AlgoVersionEnum(String algoCode,String desc){
    this.algoCode=algoCode;
    this.desc=desc;
  }
  /**
   * 算法模型版本code
   */
  private String algoCode;
  /**
   * 算法模型版本描述
   */
  private String desc;

  public String getAlgoCode() {
    return algoCode;
  }

  public void setAlgoCode(String algoCode) {
    this.algoCode = algoCode;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
