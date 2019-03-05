package com.meng.algo.demo.algotest.pojo;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.msgpack.annotation.Message;

/**
 * @author IBM CHEN ZHI KAN
 * @create 2017/10/29
 */

@Message
public class CacheSession {
    public static final String ROOT_SECTION_ID = "0";
    public static final String ROOT_PARENT_SESSION_ID = "-1";
    private String userRegId;//用户Id
    private String courseInstanceId;//课程实例id
    private String currentLoCode;//当前学习的知识点
    private String currentItemProcess;//当前流程
    private RecomNextActivity currentActivity;//当前动作
    private Map<String, String> curmetricMap; //当前LO对应的指标 比如能力值、题量、正答率等
    private Section section;//阶段
    private Goal goal;//目标
    private List<GoalFilter> goalFilter;//目标过滤器 用来过滤资源
    private Map<String, LO> los;//当前section下goal中所有知识点
    private Map<String, LO> initLos;//当前section下goal中所有知识点
    private Map<String, Map<String, List<String>>> loMap;//知识图谱前置和后置关系
    private Map<String, Map<String, List<String>>> updatedLoMap;//更新后的知识图谱前置和后置关系
    private Map<String, Sequence> sequences;//动作顺序 分为动作顺序和计算动作之后的动作顺序（endActs-暂时不用）
    private List<Rule> rules;//规则
    private Map<String, Target> targets;//用户记录和计算参数
    private Set<String> excludedItems = new HashSet<>(); //推荐过的题目，初始化为空集合
    private LearnMapCounter learnMapCounter;//知识点状态统计
    private SharedDataEntity sharedData;//共享数据
    private boolean isRepeat = false;//当前缓存是否是可重复进入缓存
    private Map<String, String> resVersionMap;//key:itemId value:题对应的版本号
    private Map<String, String> resLoMap;//资源id：知识点
    private RepeatSession repeatSession;//记载可重复进入的section信息缓存
    private String storageStatus;//使用状态
    private Long createDate;//缓存创建时间
    private Long updateDate;//缓存修改时间
    private static String sectionVersion = "v1";
    private AlgoConfig algoConfig;
    public CacheVersion cacheVersion;
    private Map<String,ItemProcess> itemProcessMap;

    public String getCurrentItemProcess() {
        return currentItemProcess;
    }

    public void setCurrentItemProcess(String currentItemProcess) {
        this.currentItemProcess = currentItemProcess;
    }

    public Map<String, ItemProcess> getItemProcessMap() {
        if (this.itemProcessMap == null) {
            return new HashMap<String, ItemProcess>();
        }
        return itemProcessMap;
    }

    public void setItemProcessMap(Map<String, ItemProcess> itemProcessMap) {
        this.itemProcessMap = itemProcessMap;
    }

    public CacheVersion getCacheVersion() {
        return cacheVersion;
    }

    public void setCacheVersion(CacheVersion cacheVersion) {
        this.cacheVersion = cacheVersion;
    }



    public CacheSession() {
    }

    public AlgoConfig getAlgoConfig() {
        return algoConfig;
    }

    public void setAlgoConfig(AlgoConfig algoConfig) {
        this.algoConfig = algoConfig;
    }

    public static String getSectionVersion() {
        return sectionVersion;
    }

    public static void setSectionVersion(String sectionVersion) {
        CacheSession.sectionVersion = sectionVersion;
    }

    public RepeatSession getRepeatSession() {
        return repeatSession;
    }

    public void setRepeatSession(RepeatSession repeatSession) {
        this.repeatSession = repeatSession;
    }

    public Map<String, String> getResLoMap() {
        if (this.resLoMap == null) {
            return new HashMap<>();
        }
        return resLoMap;
    }

    public void setResLoMap(Map<String, String> resLoMap) {
        this.resLoMap = resLoMap;
    }

    public Map<String, String> getResVersionMap() {
        if (this.resVersionMap == null) {
            return new HashMap<>();
        }
        return resVersionMap;
    }

    public void setResVersionMap(Map<String, String> resVersionMap) {
        this.resVersionMap = resVersionMap;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }


    public Map<String, Sequence> getSequences() {
        return sequences;
    }

    public void setSequences(
            Map<String, Sequence> sequences) {
        this.sequences = sequences;
    }

    public List<GoalFilter> getGoalFilter() {
        return goalFilter;
    }

    public void setGoalFilter(List<GoalFilter> goalFilter) {
        this.goalFilter = goalFilter;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public String getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    public static String getRootSectionId() {
        return ROOT_SECTION_ID;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public static String getRootParentSessionId() {
        return ROOT_PARENT_SESSION_ID;
    }

    public Boolean currentTargetLOIsClose() {
        Iterator<String> loKey = targets.keySet().iterator();
        while (loKey.hasNext()) {
            String loCode = loKey.next();
            Target currentLOTarget = targets.get(loCode);
            if (currentLOTarget != null && currentLOTarget.getStatus().equals("INPROCESS")) {
                return false;
            }
        }
        return true;
    }

    public String getCourseInstanceId() {
        return courseInstanceId;
    }

    public void setCourseInstanceId(String courseInstanceId) {
        this.courseInstanceId = courseInstanceId;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }


    public String getCurrentLoCode() {
        return currentLoCode;
    }

    public void setCurrentLoCode(String currentLoCode) {
        this.currentLoCode = currentLoCode;
    }

    public LearnMapCounter getLearnMapCounter() {
        return learnMapCounter;
    }


    public Map<String, Target> getTargets() {
        return targets;
    }

    public void setTargets(Map<String, Target> targets) {
        this.targets = targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = convertListMapByTargets(targets);
    }

    public Map<String, LO> getLos() {
        return los;
    }

    public void setLos(List<LO> los) {
        this.los = convertListMapByLOs(los);
    }

    public void setLos(Map<String, LO> los) {
        this.los = los;
    }


    public Map<String, LO> getInitLos() {
        return initLos;
    }

    public void setInitLos(List<LO> los) {
        this.initLos = convertListMapByLOs(los);
    }

    public void setInitLos(Map<String, LO> initLos) {
        this.initLos = initLos;
    }

    public void setLearnMapCounter(LearnMapCounter learnMapCounter) {
        this.learnMapCounter = learnMapCounter;
    }

    public void setLearnMapCounter(List<LO> loList) {
        Set<String> masteredNodes = new TreeSet<String>();
        Set<String> notMasteredNodes = new TreeSet<String>();
        Set<String> recommendedList = new TreeSet<String>();
        Set<String> locodes = queryKeyByLoCode(loList);
        LearnMapCounter lmc = new LearnMapCounter(locodes, masteredNodes, notMasteredNodes,
                recommendedList, locodes);
        this.learnMapCounter = lmc;
    }

    public Set<String> queryKeyByLoCode(List<LO> loList) {
        if (loList == null) {
            return new HashSet<String>();
        }
        Set<String> los = new TreeSet<>();
        for (LO lo : loList) {
            los.add(lo.getLoCode());
        }
        return los;
    }

    /**
     * 断言当前section所有target是否都完成了 完成了返回true 否则返回false
     */
    public boolean allTargetIsClose() {
        return true;
    }

    public RecomNextActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(RecomNextActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public Section getSection() {
        if (section == null) {
            return new Section();
        }
        return section;
    }

    public void setCmsSection(Section section) {
        this.section = section;
    }

    public Map<String, Map<String, List<String>>> getLoMap() {
        return loMap;
    }

    public void setLoMap(
            Map<String, Map<String, List<String>>> loMap) {
        this.loMap = loMap;
    }

    public String getUserRegId() {
        return userRegId;
    }

    public void setUserRegId(String userRegId) {
        this.userRegId = userRegId;
    }


    public Map<String, String> getCurmetricMap() {
        return curmetricMap;
    }

    public void setCurmetricMap(Map<String, String> curmetricMap) {
        this.curmetricMap = curmetricMap;
    }

    public Set<String> getExcludedItems() {
        return excludedItems;
    }

    public void setExcludedItems(Set<String> excludedItems) {
        this.excludedItems = excludedItems;
    }

    public void addExcludedItem(String itemId) {
        this.excludedItems.add(itemId);
    }

    public Map<String, Target> convertListMapByTargets(List<Target> targets) {
        Map<String, Target> targetMap = new HashMap<>();
        if (null != targets) {
            for (Target target : targets) {
                targetMap.put(target.getLoCode(), target);
            }
        }
        return targetMap;
    }

    public Map<String, LO> convertListMapByLOs(List<LO> los) {
        Map<String, LO> losMap = new HashMap<>();
        if (null != los) {
            for (LO lo : los) {
                losMap.put(lo.getLoCode(), lo);
            }
        }
        return losMap;
    }

    public Map<String, Map<String, List<String>>> getUpdatedLoMap() {
        return updatedLoMap;
    }

    public void setUpdatedLoMap(
            Map<String, Map<String, List<String>>> updatedLoMap) {
        this.updatedLoMap = updatedLoMap;
    }

    public SharedDataEntity getSharedData() {
        if (sharedData == null) {
            sharedData = new SharedDataEntity();
        }
        return sharedData;
    }

    public void setSharedData(SharedDataEntity sharedData) {
        this.sharedData = sharedData;
    }

    public void closeTarget(String loCode) {
        Target target = targets.get(loCode);
        targets.put(loCode, target);
    }


    /**
     * 判断当前section是否具有本轮次推练习题规则
     *
     * @return 如果有返回true 否则返回false
     */
    public boolean hasExerciseStage() {

//    for (Rule rule : this.section.getRules()) {
//      for (RuleAction currentAction : rule.getActions()) {
//        if (currentAction.getVariable().equals(RuleActionEnum.SETMAINPAUSED.getCode())) {
//          return true;
//        }
//      }
//    }
        return false;
    }
}
