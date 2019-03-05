package com.meng.algo.demo.algotest.controller;

import com.alibaba.fastjson.JSON;
import com.meng.algo.demo.algotest.echartModel.LineModel1;
import com.meng.algo.demo.algotest.echartModel.Series;
import com.meng.algo.demo.algotest.echartModel.XAxis;
import com.meng.algo.demo.algotest.echartModel.YAxis;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class TestController {

  @RequestMapping("/test")
  public String testJson(){
    LineModel1 lineModel1 = new LineModel1();
    XAxis xAxis = new XAxis();
    YAxis yAxis = new YAxis();
    Series series = new Series();
    xAxis.setType("category");
    ArrayList<String> xData = new ArrayList<>();
    xData.add("mon");
    xData.add("tue");
    xData.add("web");
    xData.add("thu");
    xData.add("fri");
    xData.add("sat");
    xData.add("sun");
    xAxis.setData(xData);
    yAxis.setType("value");
    series.setType("line");
    ArrayList<Long> sData = new ArrayList<>();
    series.setData(sData);
    sData.add(820L);
    sData.add(932L);
    sData.add(901L);
    sData.add(934L);
    sData.add(1290L);
    sData.add(1330L);
    sData.add(1320L);
    lineModel1.setxAxis(xAxis);
    lineModel1.setyAxis(yAxis);
    List<Series> seriesList = new ArrayList<>();
    seriesList.add(series);
    lineModel1.setSeries(seriesList);
    return JSON.toJSONString(lineModel1);
  }
}
