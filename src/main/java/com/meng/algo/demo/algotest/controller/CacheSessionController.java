package com.meng.algo.demo.algotest.controller;

import com.alibaba.fastjson.JSON;
import com.meng.algo.demo.algotest.echartModel.LineModel1;
import com.meng.algo.demo.algotest.service.CacheSessionService;
import com.meng.algo.demo.algotest.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cachesession")
public class CacheSessionController {

  @Autowired
  public CacheSessionService cacheSessionService;
  @Autowired
  private Util util;
  @RequestMapping("/redistime")
  public String getCacheSessionRedisTime(){
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Long> sData = new ArrayList<>();
    cacheSessionService.setredisLineModelData(xData,sData);
    ArrayList<LineModel1> lineModel1s = new ArrayList<>();
    ArrayList<String> xDataLine = new ArrayList<>();
    ArrayList<Long> sDataLine = new ArrayList<>();
    for(int i = 0;i<xData.size();i++){
      if( i>0 && i%10 == 0){
        LineModel1 lineModel1 = util.getChartData( xDataLine, sDataLine);
        lineModel1s.add(lineModel1);
        xDataLine.clear();
        sDataLine.clear();
        xDataLine.add(xData.get(i));
        sDataLine.add(sData.get(i));
      }else if( i == xData.size()-1){
        xDataLine.add(xData.get(i));
        sDataLine.add(sData.get(i));
        LineModel1 lineModel1 = util.getChartData( xDataLine, sDataLine);
        lineModel1s.add(lineModel1);
      }else{
        xDataLine.add(xData.get(i));
        sDataLine.add(sData.get(i));
      }

    }
    return JSON.toJSONString(lineModel1s);
  }

  @RequestMapping("/jsontime")
  public String getCacheSessionJsonTime(){
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Long> sData1 = new ArrayList<>();
    ArrayList<Long> sData2 = new ArrayList<>();
    cacheSessionService.setLineModelData(xData,sData1,sData2);
    ArrayList<LineModel1> lineModel1s = new ArrayList<>();
    ArrayList<String> xDataLine = new ArrayList<>();
    ArrayList<Long> sDataLine1 = new ArrayList<>();
    ArrayList<Long> sDataLine2 = new ArrayList<>();
    ArrayList<List<Long>> sDataLines = new ArrayList<>();
    sDataLines.add(sDataLine1);
    sDataLines.add(sDataLine2);
    for(int i = 0;i<xData.size();i++){
      if( i>0 && i%10 == 0){
        LineModel1 lineModel1 = util.getChartDataAxis( xDataLine, sDataLines);
        lineModel1s.add(lineModel1);
        xDataLine.clear();
        sDataLine1.clear();
        sDataLine2.clear();
        xDataLine.add(xData.get(i));
        sDataLine1.add(sData1.get(i));
        sDataLine2.add(sData2.get(i));
      }else if( i == xData.size()-1){
        xDataLine.add(xData.get(i));
        sDataLine1.add(sData1.get(i));
        sDataLine2.add(sData2.get(i));
        LineModel1 lineModel1 = util.getChartDataAxis( xDataLine, sDataLines);
        lineModel1s.add(lineModel1);
      }else{
        xDataLine.add(xData.get(i));
        sDataLine1.add(sData1.get(i));
        sDataLine2.add(sData2.get(i));
      }
    }
    return JSON.toJSONString(lineModel1s);
  }
}
