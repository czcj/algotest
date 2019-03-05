package com.meng.algo.demo.algotest.pojo;

import java.io.Serializable;
import org.msgpack.annotation.Message;


/**
 * @author robinwb
 * @email wangbiao@classba.cn
 * @date 2017-10-28 10:49:48
 */
@Message
public class RuleAction implements Serializable {
    private static final long serialVersionUID = 1L;

  private String actionId;//唯一ID
  private String actionType;//查看重构基本数据整理文档
  private String variable;//变量定义
  private String value;//值
  private String operatorSign;
  private String nextActionId;


  public String getNextActionId() {
    return nextActionId;
  }

  public void setNextActionId(String nextActionId) {
    this.nextActionId = nextActionId;
  }

  /**
     * 设置：
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * 获取：
     */
    public String getActionType() {
        return actionType;
    }

  public String getActionId() {
        return actionId;
    }

  public void setActionId(String actionId) {
    this.actionId = actionId;
  }

    /**
     * 设置：
     */
    public void setVariable(String variable) {
        this.variable = variable;
    }

    /**
     * 获取：
     */
    public String getVariable() {
        return variable;
    }

    /**
     * 设置：
     */
    public void setOperatorSign(String operatorSign) {
        this.operatorSign = operatorSign;
    }

    /**
     * 获取：
     */
    public String getOperatorSign() {
        return operatorSign;
    }

    /**
     * 设置：
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取：
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "RuleAction{" +
                "actionId=" + actionId +
                ", variable='" + variable + '\'' +
                ", operatorSign='" + operatorSign + '\'' +
                ", value='" + value + '\'' +
                ", actionType='" + actionType + '\'' +
                '}';
    }
}
