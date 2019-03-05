package com.meng.algo.demo.algotest.algomodel.enums;

public enum ModelVersion {
  //计算能力值模型
  ABI_V1("ABI_V1","计算能力值V1"),
  ABI_V2("ABI_V2","计算能力值V2(时间维度)"),
  KST_V1("KST_V1","测试阶段推知识点V1"),
  NLI_V1("NLI_V1","学习阶段推知识点V1"),
  ASSE_V1("ASSE_V1","推题算法V1"),
  ASSE_V2("ASSE_V2","推题算法V2"),
  STOP_V1("STOP_V1","BKT稳定算法V1"),
  FOR_V1("FOR_V1","记忆曲线算法V1"),
  TRANS_V1("TRANS_V1","能力值传递V1"),
  //计算知识点掌握程度
  MAS_V1("MAS_V1","计算知识点掌握程度"),
  //没有做算法配置走默认版本
  DEFAULT("DEF","默认版本"),
  ;
  String modelVersion;
  String desc;

  public String getModelVersion() {
    return modelVersion;
  }

  public void setModelVersion(String modelVersion) {
    this.modelVersion = modelVersion;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  ModelVersion(String modelVersion,String desc){
    this.modelVersion=modelVersion;
    this.desc=desc;
  }
}