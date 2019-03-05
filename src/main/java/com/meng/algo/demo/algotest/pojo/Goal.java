package com.meng.algo.demo.algotest.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.msgpack.annotation.Message;


/**
 * goal主表
 *
 * @author robinwb
 * @email wangbiao@classba.cn
 * @date 2017-10-28 11:23:57
 */
@Message
public class Goal implements Serializable {
    private static final long serialVersionUID = 1L;
    private String goalId;//目标ID
    private String goalType;//目标类型  查看重构基本数据整理文档
    private String defaultTargetType;//题目数量/能力值/正答率/做题结果序列等
    private String defaultCondOper;//大于/等于/小于/包含等
    private String defaultTargetScore;//目标分数
    private String maxRecomSize;//最大可推荐Activity数量
    private String metricsEnabled;//是否可测量，1可以，0不可以
    private String duration;//参考时长，0表示无
    private String loMapId; //知识图谱ID

    private String status;//目标状态 查看重构基本数据整理文档
    private List<Target> targets;//目标对应的target
    private Map<String, Set<String>> loMap;//知识图谱
    private List<LO> loList;//知识图谱对应的知识点

    public String getGoalId() {
        return goalId;
    }

    public void setGoalId(String goalId) {
        this.goalId = goalId;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public String getDefaultTargetType() {
        return defaultTargetType;
    }

    public void setDefaultTargetType(String defaultTargetType) {
        this.defaultTargetType = defaultTargetType;
    }

    public String getDefaultCondOper() {
        return defaultCondOper;
    }

    public void setDefaultCondOper(String defaultCondOper) {
        this.defaultCondOper = defaultCondOper;
    }

    public String getDefaultTargetScore() {
        return defaultTargetScore;
    }

    public void setDefaultTargetScore(String defaultTargetScore) {
        this.defaultTargetScore = defaultTargetScore;
    }

    public String getMaxRecomSize() {
        return maxRecomSize;
    }

    public void setMaxRecomSize(String maxRecomSize) {
        this.maxRecomSize = maxRecomSize;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMetricsEnabled() {
        return metricsEnabled;
    }

    public void setMetricsEnabled(String metricsEnabled) {
        this.metricsEnabled = metricsEnabled;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public Map<String, Set<String>> getLoMap() {
        return loMap;
    }

    public void setLoMap(Map<String, Set<String>> loMap) {
        this.loMap = loMap;
    }

    public List<LO> getLoList() {
        return loList;
    }

    public void setLoList(List<LO> loList) {
        this.loList = loList;
    }

    public String getLoMapId() {
        return loMapId;
    }

    public void setLoMapId(String loMapId) {
        this.loMapId = loMapId;
    }

    public Goal() {
    }


}
