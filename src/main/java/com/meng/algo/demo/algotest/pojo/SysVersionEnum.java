package com.meng.algo.demo.algotest.pojo;

import org.msgpack.annotation.Message;

@Message
public enum SysVersionEnum {
  SYSTEM_VERSION("v1","systemVersion"),
  RES_VERSION("v2","resVersion"),
  LO_VERSION("v1","loVersion"),
  DEFAULT_VERSION("v1","default");
  private String value;
  private String name;

  SysVersionEnum(String value,String name) {
   setValue(value);
   setName(name);
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
