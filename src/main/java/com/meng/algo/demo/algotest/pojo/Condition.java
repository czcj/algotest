package com.meng.algo.demo.algotest.pojo;

import org.msgpack.annotation.Message;

/**
 * @author IBM CHEN ZHI KAN
 * @create 2017/11/8
 */
@Message
public class Condition {

  private String conId;//条件ID
  private String nextCondId;//下一个关联conditionId
  private String variable;//变量定义
  private String logicalOper;//逻辑运算符(AND/OR)
  private String operatorSign;//条件的判断运算符 > 、>=、<、<=，见操作符运算映射表
  private String value;//variable变量对应值
  private String seqNo;//condition的序号-多个condition之间的先后顺序
  private String priority;//优先级 代表的是哪个condition计算权重高

  public String getOperatorSign() {
    return operatorSign;
  }

  public void setOperatorSign(String operatorSign) {
    this.operatorSign = operatorSign;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getVariable() {
    return variable;
  }

  public void setVariable(String variable) {
    this.variable = variable;
  }

  public String getLogicalOper() {
    return logicalOper;
  }

  public void setLogicalOper(String logicalOper) {
    this.logicalOper = logicalOper;
  }

  public String getNextCondId() {
    return nextCondId;
  }

  public void setNextCondId(String nextCondId) {
    this.nextCondId = nextCondId;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getConId() {
    return conId;
  }

  public void setConId(String conId) {
    this.conId = conId;
  }

  public String getSeqNo() {
    return seqNo;
  }

  public void setSeqNo(String seqNo) {
    this.seqNo = seqNo;
  }
}
