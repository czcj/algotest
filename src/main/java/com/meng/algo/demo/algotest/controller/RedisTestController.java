package com.meng.algo.demo.algotest.controller;

import com.alibaba.fastjson.JSON;
import com.meng.algo.demo.algotest.echartModel.LineModel1;
import com.meng.algo.demo.algotest.service.RedisTestService;
import com.meng.algo.demo.algotest.util.Util;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {
  @Autowired
  private Util util;
  @Autowired
  private RedisTestService redisTestService;

  @RequestMapping("/json/redisWriteTest")
  public String getRedisWriteData(Integer size){
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Long> sData = new ArrayList<>();
    redisTestService.getWriteData(xData,sData,size);
    LineModel1 lineModel1 = util.getChartData( xData, sData);
    return JSON.toJSONString(lineModel1);
  }
  @RequestMapping("/json/redisReadTest")
  public String getRedisReadData(Integer size){
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Long> sData = new ArrayList<>();
    redisTestService.getReadData(xData,sData,size);
    LineModel1 lineModel1 = util.getChartData( xData, sData);
    return JSON.toJSONString(lineModel1);
  }
  @RequestMapping("/json/redisUpdateTest")
  public String redisUpdateTest(Integer size){
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Long> sData = new ArrayList<>();
    redisTestService.getUpdateData(xData,sData,size);
    LineModel1 lineModel1 = util.getChartData( xData, sData);
    return JSON.toJSONString(lineModel1);
  }

  @RequestMapping("/json/redisArrWriteTest")
  public String redisArrWriteTest(Integer size){
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Long> sData = new ArrayList<>();
    redisTestService.getArrWriteData(xData,sData,size);
    LineModel1 lineModel1 = util.getChartData( xData, sData);
    return JSON.toJSONString(lineModel1);
  }
  @RequestMapping("/json/redisArrReadTest")
  public String redisArrReadTest(Integer size){
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Long> sData = new ArrayList<>();
    redisTestService.getArrReadData(xData,sData,size);
    LineModel1 lineModel1 = util.getChartData( xData, sData);
    return JSON.toJSONString(lineModel1);
  }
  @RequestMapping("/json/redisArrUpdateTest")
  public String redisArrUpdateTest(Integer size){
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Long> sData = new ArrayList<>();
    redisTestService.getArrUpdateData(xData,sData,size);
    LineModel1 lineModel1 = util.getChartData( xData, sData);
    return JSON.toJSONString(lineModel1);
  }
}
