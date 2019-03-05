package com.meng.algo.demo.algotest.echartModel;

import java.util.List;

public class Series{

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  String name;
  List<Long> data;
  String type;

  public List<Long> getData() {
    return data;
  }

  public void setData(List<Long> data) {
    this.data = data;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}