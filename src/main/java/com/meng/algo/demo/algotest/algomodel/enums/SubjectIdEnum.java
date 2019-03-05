package com.meng.algo.demo.algotest.algomodel.enums;

public enum  SubjectIdEnum {
  DEF("0","通用"),
  EN("1","英语"),
  MATH("2","数学"),
  CN("3","语文"),
  PHY("4","物理"),
  CHE("5","化学"),
  BIO("6","生物"),
  ;
  String subjectId;
  String subjectName;

  public String getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(String subjectId) {
    this.subjectId = subjectId;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  SubjectIdEnum(String subjectId,String subjectName){
    this.subjectId=subjectId;
    this.subjectName=subjectName;
  }
}
