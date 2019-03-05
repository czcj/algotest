package com.meng.algo.demo.algotest.pojo;


import org.msgpack.annotation.Message;

/**
 * @author IBM CHEN ZHI KAN
 * @create 2017/10/24
 */
@Message
public class RecomNextActivity {

    private String itemId;//资源ID
    private String itemType; //资源对象类型
    private String activityType;//动作类型
    private String ncode;//资源所属知识点编码Code
    private String codeType;//推题类型（squence或者goal推题）
    private String goalType;
    private String sectionLevel;
    private boolean isUse = false;//当前activity是被消费，true被消费 false没有被消费，默认没有

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public String getSectionLevel() {
        return sectionLevel;
    }

    public void setSectionLevel(String sectionLevel) {
        this.sectionLevel = sectionLevel;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public RecomNextActivity() {
    }

    public RecomNextActivity(String activityType) {
        this.activityType = activityType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }


    public String getNcode() {
        return ncode;
    }

    public void setNcode(String ncode) {
        this.ncode = ncode;
    }
}
