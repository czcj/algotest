package com.meng.algo.demo.algotest.pojo;

import org.msgpack.annotation.Message;

/**
 * @author IBM CHEN ZHI KAN
 * @create 2018/6/16
 */
@Message
public class HistoryRepeatSession {
  private Long createDate;
  private String sessionId;
  private Integer sortNum;

  public Long getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Long createDate) {
    this.createDate = createDate;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public Integer getSortNum() {
    return sortNum;
  }

  public void setSortNum(Integer sortNum) {
    this.sortNum = sortNum;
  }
}
