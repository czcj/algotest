package com.meng.algo.demo.algotest.pojo;


import java.io.Serializable;
import java.util.List;
import org.msgpack.annotation.Message;


/**
 * sequence主表
 *
 * @author robinwb
 * @email wangbiao@classba.cn
 * @date 2017-10-28 11:23:57
 */
@Message
public class Sequence implements Serializable {
    private static final long serialVersionUID = 1L;


    private String sequenceId;//动作顺序的ID
    private String status;//动作顺序状态
    private String sequenceType;//动作顺序类型
    private List<Activity> acts;//具体的动作

    public String getSequenceType() {
        return sequenceType;
    }

    public void setSequenceType(String sequenceType) {
        this.sequenceType = sequenceType;
    }

    public List<Activity> getActs() {
        return acts;
    }

    public void setActs(List<Activity> acts) {
        this.acts = acts;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
