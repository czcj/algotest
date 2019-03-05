package com.meng.algo.demo.algotest.pojo;

import java.io.Serializable;
import org.msgpack.annotation.Message;


/**
 * 
 * 
 * @author robinwb
 * @email wangbiao@classba.cn
 * @date 2017-10-28 11:23:57
 */
@Message
public class RuleTrigger implements Serializable {
	private static final long serialVersionUID = 1L;

  private String triggerId;//主键
  private String triggerType;//activity_type类型编码(详见MDS字典表)

  public String getTriggerId() {
    return triggerId;
	}

  public void setTriggerId(String triggerId) {
    this.triggerId = triggerId;
  }

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}
}
