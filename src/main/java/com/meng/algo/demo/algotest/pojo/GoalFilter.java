package com.meng.algo.demo.algotest.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import org.msgpack.annotation.Message;

/**
 * @auther jjp
 * @date 2018/5/25 0025
 */
@Message
public class GoalFilter {
    private String filterId;//过滤器ID
    private String filterType;//过滤器类型：试题，知识点，视频，讲义
    private String filterItem;//过滤依据的列名：难度，权重等
    private String filterValue;//filterItem过滤对应的值
    private String filterRelation;//过滤关系 大于/等于/小于等
    private String resVersion;//系统版本

    public String getResVersion() {
        return resVersion;
    }

    public void setResVersion(String resVersion) {
        this.resVersion = resVersion;
    }

    @JSONField(name = "isInclude")
    private String isInclude;//是否包含符合该filter的对象

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterItem() {
        return filterItem;
    }

    public void setFilterItem(String filterItem) {
        this.filterItem = filterItem;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public String getFilterRelation() {
        return filterRelation;
    }

    public void setFilterRelation(String filterRelation) {
        this.filterRelation = filterRelation;
    }
    public String getIsInclude() {
        return isInclude;
    }

    public void setIsInclude(String isInclude) {
        this.isInclude = isInclude;
    }


}
