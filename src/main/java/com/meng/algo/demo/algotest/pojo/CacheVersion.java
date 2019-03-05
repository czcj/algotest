package com.meng.algo.demo.algotest.pojo;

import org.msgpack.annotation.Message;

@Message
public class CacheVersion {
    //系统版本
    public  String systemVersion;
    //知识点版本
    public  String loVersion;
    //题目版本
    public  String resVersion;

    public CacheVersion() {
        this.systemVersion = SysVersionEnum.DEFAULT_VERSION.getValue();
        this.loVersion = SysVersionEnum.DEFAULT_VERSION.getValue();
        this.resVersion = SysVersionEnum.DEFAULT_VERSION.getValue();
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getLoVersion() {
        return loVersion;
    }

    public void setLoVersion(String loVersion) {
        this.loVersion = loVersion;
    }

    public String getResVersion() {
        return resVersion;
    }

    public void setResVersion(String resVersion) {
        this.resVersion = resVersion;
    }
}
