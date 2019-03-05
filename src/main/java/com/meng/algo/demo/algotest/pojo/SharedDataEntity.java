package com.meng.algo.demo.algotest.pojo;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.msgpack.annotation.Message;

/**
 * Created by robinwb
 * Date:2017/11/10 ${DATETIME}.
 */
@Message
public class SharedDataEntity {


    private Set<String> masteredSet;
    private Set<String> notMasteredSet;
    private Set<String> noDealedSet;

    private Map<String, ShareData> shareDataMap;


    public Set<String> getNoDealedSet() {
        if (noDealedSet == null) {
            return new HashSet<>();
        }
        return noDealedSet;
    }

    public void setNoDealedSet(Set<String> noDealedSet) {
        this.noDealedSet = noDealedSet;
    }

    public Map<String, ShareData> getShareDataMap() {
        if (shareDataMap == null) {
            return new HashMap<String, ShareData>();
        }
        return shareDataMap;
    }

    public void setShareDataMap(Map<String, ShareData> shareDataMap) {
        if (this.shareDataMap == null) {
            this.shareDataMap = new HashMap<String, ShareData>();
        }

        this.shareDataMap = shareDataMap;
    }

    public Set<String> getMasteredSet() {
        if (masteredSet == null) {
            return new HashSet<>();
        }
        return masteredSet;
    }

    public void setMasteredSet(Set<String> masteredSet) {
        this.masteredSet = masteredSet;
    }

    public Set<String> getNotMasteredSet() {
        if (notMasteredSet == null) {
            return new HashSet<>();
        }
        return notMasteredSet;
    }

    public void setNotMasteredSet(Set<String> notMasteredSet) {
        this.notMasteredSet = notMasteredSet;
    }


    public static void main(String[] args) {
        String test = "{\n" +
                "    \"masteredSet\": [],\n" +
                "    \"noDealedSet\": [],\n" +
                "  }";

        SharedDataEntity q = JSON.parseObject(test, SharedDataEntity.class);
        ShareData shareData = new ShareData();

        Map map = new HashMap();
        Set set = new HashSet();
        set.add("3");
        shareData.setNoDealedSet(set);
        map.put("1", shareData);
        q.setShareDataMap(map);
        System.out.println(JSON.toJSONString(q));
    }
}
