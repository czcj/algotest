package com.meng.algo.demo.algotest.pojo;

import java.util.ArrayList;
import java.util.List;
import org.msgpack.annotation.Message;

/**
 * @author IBM CHEN ZHI KAN
 *
 * @create 2018/6/16
 */
@Message
public class RepeatSession {
  private String currentSessionId;
  private String beforeSessionId;
  private String status;
  private List<HistoryRepeatSession> historyRepeatSessions=new ArrayList<>();

  public String getCurrentSessionId() {
    return currentSessionId;
  }

  public void setCurrentSessionId(String currentSessionId) {
    this.currentSessionId = currentSessionId;
  }

  public String getBeforeSessionId() {
    return beforeSessionId;
  }

  public void setBeforeSessionId(String beforeSessionId) {
    this.beforeSessionId = beforeSessionId;
  }

  public List<HistoryRepeatSession> getHistoryRepeatSessions() {
    return historyRepeatSessions;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setHistoryRepeatSessions(
      List<HistoryRepeatSession> historyRepeatSessions) {
    this.historyRepeatSessions = historyRepeatSessions;
  }
}
