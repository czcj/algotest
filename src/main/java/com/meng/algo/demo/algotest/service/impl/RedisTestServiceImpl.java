package com.meng.algo.demo.algotest.service.impl;

import com.meng.algo.demo.algotest.service.RedisTestService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTestServiceImpl implements RedisTestService {

  @Autowired
  private StringRedisTemplate srt;

  @Qualifier("redisTemplate")
  @Autowired
  private RedisTemplate rt;

  public Map<String, String> getSDM(Integer size){
    StringBuilder sb1=new StringBuilder();
    HashMap<String, String> dataMap = new LinkedHashMap<>();
    //单位KB
    int total = 10240 * size;
    for(int i=0;i<total;i++){
      sb1.append('a'+"");
      //每kb进行一次记录
      if(i>0 && (i+1) % 10240 == 0){
        getRValue(sb1, dataMap,  (i+1)/10240);
      }
    }
//    String s = sb1.toString();
    return dataMap;
  }

  private void getRValue(StringBuilder sb1, HashMap<String, String> dataMap, int i) {
    String name = "SD"+i+"KB";
    String s = sb1.toString();
    dataMap.put(name,s);
  }

  @Override
  public void getWriteData(ArrayList<String> xdatas, ArrayList<Long> sdatas, Integer size) {
    Map<String, String> sdm = getSDM(size);
    sdm.forEach((name,value) ->{
      srt.delete(name);
    });
    sdm.forEach((name,value)->{
      long startTime = System.currentTimeMillis();
      srt.opsForValue().set(name,value);
      long endTiem = System.currentTimeMillis();
      long l = endTiem - startTime;
      if(l > 500){
        l = -50L;
      }else {
        xdatas.add(name);
        sdatas.add(l);
      }
    });
  }



  @Override
  public void getReadData(ArrayList<String> xdatas, ArrayList<Long> sdatas, Integer size) {
    Map<String, String> sdm = getSDM(size);
    sdm.forEach((name,value)->{
      long startTime = System.currentTimeMillis();
      String s = srt.opsForValue().get(name);
      long endTiem = System.currentTimeMillis();
      long l = endTiem - startTime;
      if(l > 500){
        l = -50L;
      }else{
        xdatas.add(name);
        sdatas.add(l);
      }
    });
    sdm.forEach((name,value) ->{
      srt.delete(name);
    });
  }

  @Override
  public void getUpdateData(ArrayList<String> xData, ArrayList<Long> sData, Integer size) {
    Map<String, String> sdm = getSDM(size);
    sdm.forEach((name,value)->{
      long startTime = System.currentTimeMillis();
      srt.opsForValue().set(name,value);
      long endTiem = System.currentTimeMillis();
      long l = endTiem - startTime;
      if(l > 500){
        l = -50L;
      }else{
        xData.add(name);
        sData.add(l);
      }
    });
  }

  @Override
  public void getArrWriteData(ArrayList<String> xData, ArrayList<Long> sData,Integer size){
    StringBuilder sb1=new StringBuilder();
    int total = 10240 * size;
    for(int i=0;i<total;i++){
      sb1.append('a'+"");
    }
    String val = sb1.toString();
    for(int i=1;i<51;i++){
      srt.delete("arrtest");
      long startTime = System.currentTimeMillis();
      srt.opsForValue().set("arrtest",val);
      long endTiem = System.currentTimeMillis();
      long l = endTiem - startTime;
      if(l > 500){
        l = -50L;
      }else {
        xData.add(i+"");
        sData.add(l);
      }
    }
  }

  @Override
  public void getArrUpdateData(ArrayList<String> xData, ArrayList<Long> sData,Integer size){
    StringBuilder sb1=new StringBuilder();
    int total = 10240 * size;
    for(int i=0;i<total;i++){
      sb1.append('a'+"");
    }
    String val = sb1.toString();
    srt.delete("arrtest");
    srt.opsForValue().set("arrtest",val);
    for(int i=1;i<51;i++){
      long startTime = System.currentTimeMillis();
      srt.opsForValue().set("arrtest",val);
      long endTiem = System.currentTimeMillis();
      long l = endTiem - startTime;
      if(l > 500){
        l = -50L;
      }else{
        xData.add(i+"");
        sData.add(l);
      }
    }
    srt.delete("arrtest");
  }

  @Override
  public void getArrReadData(ArrayList<String> xData, ArrayList<Long> sData,Integer size){
    StringBuilder sb1=new StringBuilder();
    int total = 10240 * size;
    for(int i=0;i<total;i++){
      sb1.append('a'+"");
    }
    String val = sb1.toString();
    srt.delete("arrtest");
    srt.opsForValue().set("arrtest",val);
    for(int i=1;i<51;i++){
      long startTime = System.currentTimeMillis();
      String arrtest = srt.opsForValue().get("arrtest");
      long endTiem = System.currentTimeMillis();
      long l = endTiem - startTime;
      if(l > 500){
        l = -50L;
      }else{
        xData.add(i+"");
        sData.add(l);
      }
    }
    srt.delete("arrtest");
  }

  @Override
  public String getValueByKey(String key) {
    String s = srt.opsForValue().get(key);
    Object o = rt.opsForValue().get(key);
    return s;
  }

  @Override
  public void setTestCacheSession(String cacheSession) {
    srt.opsForValue().set("irs_middle:testCachesession",cacheSession);
  }

  @Override
  public void getTestCacheSession() {
    srt.opsForValue().get("irs_middle:testCachesession");
  }
}
