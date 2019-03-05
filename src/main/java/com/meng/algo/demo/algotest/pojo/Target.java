package com.meng.algo.demo.algotest.pojo;

import org.msgpack.annotation.Message;

/**
 * @author IBM CHEN ZHI KAN
 * @create 2017/10/29
 */
@Message
public class Target {
  private String status;//目标状态 查看重构基本数据整理文档
  private String loCode;//唯一的知识点code
  private String duration;//评估时长
  private String targetScore;//targetType变量对应的值
  private String condOper;//目标条件运算符 >/</=等
  private String targetType;//目标类型 查看重构基本数据整理文档

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getLoCode() {
    return loCode;
  }

  public void setLoCode(String loCode) {
    this.loCode = loCode;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getTargetScore() {
    return targetScore;
  }

  public void setTargetScore(String targetScore) {
    this.targetScore = targetScore;
  }

  public String getCondOper() {
    return condOper;
  }

  public void setCondOper(String condOper) {
    this.condOper = condOper;
  }

  public String getTargetType() {
    return targetType;
  }

  public void setTargetType(String targetType) {
    this.targetType = targetType;
  }
}
