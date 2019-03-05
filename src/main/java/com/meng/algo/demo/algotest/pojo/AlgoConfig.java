package com.meng.algo.demo.algotest.pojo;

import java.util.HashMap;
import java.util.Map;
import org.msgpack.annotation.Message;

/**
 * 算法映射实体类
 * @author IBM CHEN ZHI KAN
 * @create 2018/10/27
 */
@Message
public class AlgoConfig {
  private String snNum;
  private Map<String,String> algosMap=new HashMap<>();

  public Map<String, String> getAlgosMap() {
    return algosMap;
  }

  public void setAlgosMap(Map<String, String> algosMap) {
    this.algosMap = algosMap;
  }

  public String getSnNum() {
    return snNum;
  }

  public void setSnNum(String snNum) {
    this.snNum = snNum;
  }
}
