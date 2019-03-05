package com.meng.algo.demo.algotest.pojo;

import java.util.List;
import java.util.Map;
import org.msgpack.annotation.Message;

/**
 * @author IBM CHEN ZHI KAN
 * @create 2017/11/8
 */
@Message
public class LO {

    private String loMapId;
    private String status;
    private String ability;//每一个知识点的能力值
    private String resultQueue;//所有题做题记录，已处理的知识结果 （初始化为空 做对为1做错为0 字符串拼接）
    private String assessResult;
    private String studyResult;
    private String interactiveResult;
    private String takeques; //题冒题
    private String reason;//知识点掌握状态的原因
    private String loId;
    private String loCode;
    private String loDifficulty;//每一个知识点的难度
    private String loWeight;//考频
    private List<String> resultdiffQueue;//所有题做题的难度记录
    private String version;
    public  String loVersion ;
    private Map<String,ItemProcess> itemProcessMap;

    public Map<String, ItemProcess> getItemProcessMap() {
        return itemProcessMap;
    }

    public void setItemProcessMap(Map<String, ItemProcess> itemProcessMap) {
        this.itemProcessMap = itemProcessMap;
    }

    public List<String> getResultdiffQueue() {
        return resultdiffQueue;
    }

    public void setResultdiffQueue(List<String> resultdiffQueue) {
        this.resultdiffQueue = resultdiffQueue;
    }

    public String getLoVersion() {
        return loVersion;
    }

    public void setLoVersion(String loVersion) {
        this.loVersion = loVersion;
    }

    //传递能力值参数
    //传递层数
    String transLevel;
    //传递能力值
    String transAbility;
    //能力值是传递获得还是做题计算获得
    String abilityReason;
    //能力值传递于哪个知识点code
    String transNode;

    public String getTransLevel() {
        return transLevel;
    }

    public void setTransLevel(String transLevel) {
        this.transLevel = transLevel;
    }

    public String getTransAbility() {
        return transAbility;
    }

    public void setTransAbility(String transAbility) {
        this.transAbility = transAbility;
    }

    public String getAbilityReason() {
        return abilityReason;
    }

    public void setAbilityReason(String abilityReason) {
        this.abilityReason = abilityReason;
    }

    public String getTransNode() {
        return transNode;
    }

    public void setTransNode(String transNode) {
        this.transNode = transNode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTakeques() {
        return takeques;
    }

    public void setTakeques(String takeques) {
        if (this.takeques == null) {
            this.takeques = "";
        }
        this.takeques = this.takeques + takeques;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLoWeight() {
        return loWeight;
    }

    public void setLoWeight(String loWeight) {
        this.loWeight = loWeight;
    }

    public String getLoMapId() {
        return loMapId;
    }

    public void setLoMapId(String loMapId) {
        this.loMapId = loMapId;
    }

    public String getLoDifficulty() {
        return loDifficulty;
    }

    public void setLoDifficulty(String loDifficulty) {
        this.loDifficulty = loDifficulty;
    }

    public String getLoCode() {
        return loCode;
    }

    public void setLoCode(String loCode) {
        this.loCode = loCode;
    }

    public String getLoId() {
        return loId;
    }

    public void setLoId(String loId) {
        this.loId = loId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getResultQueue()
    {
        if (this.resultQueue == null) {
            this.resultQueue = "";
        }
        return resultQueue;
    }

    public void setResultQueue(String resultQueue) {
        if (this.resultQueue == null) {
            this.resultQueue = "";
        }
        this.resultQueue = this.resultQueue + resultQueue;
    }

    public String getAssessResult() {
        if (this.assessResult == null) {
            this.assessResult = "";
        }
        return assessResult;
    }

    public void setAssessResult(String assessResult) {

        if (this.assessResult == null) {
            this.assessResult = "";
        }
        this.assessResult = this.assessResult + assessResult;
    }

    public String getStudyResult() {
        if (studyResult == null) {
            this.studyResult = "";
        }
        return studyResult;
    }

    public void setStudyResult(String studyResult) {

        if (this.studyResult == null) {
            this.studyResult = "";
        }
        this.studyResult = this.studyResult + studyResult;
    }

    public String getInteractiveResult() {
        if (interactiveResult == null) {
            this.interactiveResult = "";
        }
        return interactiveResult;
    }

    public void setInteractiveResult(String interactiveResult) {

        if (this.interactiveResult == null) {
            this.interactiveResult = "";
        }
        this.interactiveResult = this.interactiveResult + interactiveResult;
    }
}
