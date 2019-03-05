package com.meng.algo.demo.algotest.service;

import java.util.ArrayList;

public interface RedisTestService {

  void getWriteData(ArrayList<String> xdatas, ArrayList<Long> sdatas, Integer size);


  void getReadData(ArrayList<String> xdatas, ArrayList<Long> sdatas, Integer size);

  void getUpdateData(ArrayList<String> xData, ArrayList<Long> sData, Integer size);
  public void getArrWriteData(ArrayList<String> xData, ArrayList<Long> sData,Integer size);
  public void getArrReadData(ArrayList<String> xData, ArrayList<Long> sData,Integer size);
  public void getArrUpdateData(ArrayList<String> xData, ArrayList<Long> sData,Integer size);

  String getValueByKey(String key);

  void setTestCacheSession(String cacheSession);

  void getTestCacheSession();
}
