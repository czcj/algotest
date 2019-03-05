package com.meng.algo.demo.algotest.service.impl;

import com.alibaba.fastjson.JSON;
import com.meng.algo.demo.algotest.pojo.CacheSession;
import com.meng.algo.demo.algotest.pojo.LO;
import com.meng.algo.demo.algotest.pojo.Target;
import com.meng.algo.demo.algotest.service.CacheSessionService;
import com.meng.algo.demo.algotest.util.CacheSessionStr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheSessionServiceImpl implements CacheSessionService {

  @Autowired
  private RedisTestServiceImpl redisTestService;

  public CacheSession getCachesessionByStatic(){
    CacheSession cacheSession = JSON.parseObject(CacheSessionStr.cacheSession, CacheSession.class);
    return cacheSession;
  }
  @Override
  public CacheSession getCacheSessionByLoNum(CacheSession cacheSession,CacheSession cacheSession1,Integer loNum){
    Map<String, LO> los = cacheSession1.getLos();
    Map<String, LO> initLos = cacheSession1.getInitLos();
    Map<String, Target> targets = cacheSession1.getTargets();
    HashMap<String, LO> los1 = new HashMap<>();
    HashMap<String, LO> initLos1 = new HashMap<>();
    HashMap<String,Target> targets1 = new HashMap<>();
    ArrayList<String> strings = new ArrayList<>(los.keySet());
    ArrayList<String> string1s = new ArrayList<>(initLos.keySet());
    ArrayList<String> string2s = new ArrayList<>(targets.keySet());
    String losStr = JSON.toJSONString(los.get(strings.get(0)));
    String initLosStr = JSON.toJSONString(initLos.get(string1s.get(0)));
    String targetStr = JSON.toJSONString(targets.get(string2s.get(0)));
    for(int i=0;i<loNum;i++){
      los1.put(i+"",JSON.parseObject(losStr,LO.class));
      initLos1.put(i+"",JSON.parseObject(initLosStr,LO.class));
      targets1.put(i+"",JSON.parseObject(targetStr,Target.class));
    }
    cacheSession.setLos(los1);
    cacheSession.setInitLos(initLos1);
    cacheSession.setTargets(targets1);
    return cacheSession;
  }

  @Override
  public void setLineModelData(ArrayList<String> xData, ArrayList<Long> sData1, ArrayList<Long> sData2) {
    CacheSession cachesession = getCachesessionByStatic();
    CacheSession cachesession1 = getCachesessionByStatic();
    for(int i = 10;i<1200;i+=10){
      CacheSession cacheSessionByLoNum = getCacheSessionByLoNum(cachesession, cachesession1, i);
      long time3 = System.currentTimeMillis();
      String cacheSession = JSON.toJSONString(cacheSessionByLoNum);
      long time4 = System.currentTimeMillis();
      int length = cacheSession.getBytes().length;
      int kbNum = length / 1024;
      xData.add(i+"/"+kbNum);
      long time1 = System.currentTimeMillis();
      CacheSession cacheSession1 = JSON.parseObject(cacheSession, CacheSession.class);
      long time2 = System.currentTimeMillis();
      sData1.add(time2-time1);
      sData2.add(time4-time3);
    }
  }

  @Override
  public void setredisLineModelData(ArrayList<String> xData, ArrayList<Long> sData) {
    CacheSession cachesession = getCachesessionByStatic();
    CacheSession cachesession1 = getCachesessionByStatic();
    for(int i = 10;i<1200;i+=10){
      CacheSession cacheSessionByLoNum = getCacheSessionByLoNum(cachesession, cachesession1, i);
      String cacheSession = JSON.toJSONString(cacheSessionByLoNum);
      int length = cacheSession.getBytes().length;
      int kbNum = length / 1024;
      xData.add(i+"/"+kbNum);
      redisTestService.setTestCacheSession(cacheSession);
      long time1 = System.currentTimeMillis();
      redisTestService.getTestCacheSession();
      long time2 = System.currentTimeMillis();
      sData.add(time2-time1);
    }
  }

}
