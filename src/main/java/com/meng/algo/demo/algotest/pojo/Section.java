package com.meng.algo.demo.algotest.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.msgpack.annotation.Message;


/**
 * section主表
 *
 * @author robinwb
 * @email wangbiao@classba.cn
 * @date 2017-10-27 10:52:33
 */
@Message
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sectionId;//阶段Id
    private String sessionId;//缓存ID
    private String rootSessionId;//根阶段的缓存ID
    private String parentSectionId;//父section的id
    private String parentSessionId;//父sectionSessionId
    private String project;//科目枚举 查看重构基本数据整理文档
    private String courseTemplateId;//课程ID
    private String sectionType;//(enum(ASSESSMENT))
    private String duration;//评估时长
    private String model;//l(enum(STATIC/DYNAMIC))（动态section/静态section 默认静态）
    private String released;//(enum(ALL/PARTIAL)) 是否完全发布
    private String version;
    private String status;//阶段状态 查看重构基本数据整理文档
    private String name;//阶段描述
    private String sModule;//   学习模块(先测,边学边练,综合测试)
    private String kModule;//    知识点模块(语法,听力,阅读，虚词，实词等)
    private String groupId;
    private String stageCode;//    学段
    private String sectionLevel;
    private Map<String, String> sModuleSessionIdMap;//学习模块对应sessionId,初始化为空集合
    private String classSectionId;//所属课次的sectionId
    private String taskSectionId;//所属任务卡的sectionid

  public String getClassSectionId() {
    return classSectionId;
  }

  public void setClassSectionId(String classSectionId) {
    this.classSectionId = classSectionId;
  }

  public String getTaskSectionId() {
    return taskSectionId;
  }

  public void setTaskSectionId(String taskSectionId) {
    this.taskSectionId = taskSectionId;
  }

  public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSectionLevel() {
        return sectionLevel;
    }

    public void setSectionLevel(String sectionLevel) {
        this.sectionLevel = sectionLevel;
    }

    public Map<String, String> getsModuleSessionIdMap() {
        if (this.sModuleSessionIdMap == null) {
            return new HashMap<>();
        }
        return sModuleSessionIdMap;
    }

    public void setsModuleSessionIdMap(Map<String, String> sModuleSessionIdMap) {
        this.sModuleSessionIdMap = sModuleSessionIdMap;
    }

    public String getsModule() {
        return sModule;
    }

    public void setsModule(String sModule) {
        this.sModule = sModule;
    }

    public String getkModule() {
        return kModule;
    }

    public void setkModule(String kModule) {
        this.kModule = kModule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRootSessionId() {
        return rootSessionId;
    }

    public void setRootSessionId(String rootSessionId) {
        this.rootSessionId = rootSessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (this.status == null) {
            this.status = status;
        }
        if ("NO_DEALED_SKIP".equals(this.status) || "MASTER_SKIP".equals(this.status)) {
        } else {
            this.status = status;
        }
    }

    public String getParentSessionId() {
        return parentSessionId;
    }

    public void setParentSessionId(String parentSessionId) {
        this.parentSessionId = parentSessionId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getParentSectionId() {
        return parentSectionId;
    }

    public void setParentSectionId(String parentSectionId) {
        this.parentSectionId = parentSectionId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCourseTemplateId() {
        return courseTemplateId;
    }

    public void setCourseTemplateId(String courseTemplateId) {
        this.courseTemplateId = courseTemplateId;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }
}
