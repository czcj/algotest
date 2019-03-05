package com.meng.algo.demo.algotest.algomodel.enums;

public enum  AlgoModelEnum {

  //计算能力值模型
  ABILITY_ALGO("ABILITY_ALGO","计算能力值"),
  //先行测试推知识点
  RECOM_ASSESMENT_LO_ALGO("RECOM_ASSESMENT_LO_ALGO","测试推知识点"),
  //学习阶段推知识点
  RECOM_STUDY_LO_ALGO("RECOM_STUDY_LO_ALGO","学习阶段推知识点"),
  //推题
  RECOM_ITEM_LO_ALGO("RECOM_ITEM_LO_ALGO","题目推荐模型"),
  //记忆曲线
  FORGETTION_ALGO("FORGET_ALGO","记忆曲线模型"),
  //学习稳定性
  STOPPOLICY_ALGO("STOP_ALGO","稳定性模型"),
  //能力值传递
  ABILITY_TRANS_ALGO("ABILITY_TRANS_ALGO","能力值传递"),
  //计算知识点掌握程度
  MASTERY_ABI_ALGO("MASTERY_ABI_ALGO","计算知识点掌握程度"),
  //没有做算法配置走默认算法
  DEFAULT("DEFAULT","默认算法"),
  ;
  String modelName;
  String desc;

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  AlgoModelEnum(String modelName,String desc){
    this.modelName=modelName;
    this.desc=desc;
  }
}
