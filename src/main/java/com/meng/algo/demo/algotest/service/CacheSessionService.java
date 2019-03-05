package com.meng.algo.demo.algotest.service;

import com.meng.algo.demo.algotest.pojo.CacheSession;
import java.util.ArrayList;

public interface CacheSessionService {

  CacheSession getCacheSessionByLoNum(CacheSession cacheSession,CacheSession cacheSession1,Integer loNum);
  void setLineModelData(ArrayList<String> xData, ArrayList<Long> sData1, ArrayList<Long> sData2);

  void setredisLineModelData(ArrayList<String> xData, ArrayList<Long> sData);
}
