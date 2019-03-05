package com.meng.algo.demo.algotest.pojo;


import java.io.Serializable;
import org.msgpack.annotation.Message;


/**
 * activity主表
 *
 * @author robinwb
 * @email wangbiao@classba.cn
 * @date 2017-10-28 10:53:11
 */
@Message
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String activityId;//动作ID
    private String status;//动作状态 查看重构基本数据整理文档
    private String itemId;//资源ID
    private String itemType;//资源类型
    private String activityType;//动作类型 查看重构基本数据整理文档
    private String seqNo;//序号
    private Integer duration;//时长

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (this.status == null) {
            this.status = status;
        }
        if (!this.status.equals("NO_DEALED_SKIP") || !this.status.equals("MASTER_SKIP")) {
            this.status = status;
        }
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
