package com.meng.algo.demo.algotest.util;

import com.meng.algo.demo.algotest.echartModel.LineModel1;
import com.meng.algo.demo.algotest.echartModel.Series;
import com.meng.algo.demo.algotest.echartModel.Tooltip;
import com.meng.algo.demo.algotest.echartModel.XAxis;
import com.meng.algo.demo.algotest.echartModel.YAxis;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Util {
  public LineModel1 getChartData( ArrayList<String> xData1,
      ArrayList<Long> sData1) {
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Long> sData = new ArrayList<>();
    ArrayList<Series> seriesList = new ArrayList<>();
    for(int i= 0;i<xData1.size();i++){
      xData.add(xData1.get(i));
      sData.add(sData1.get(i));
    }
    LineModel1 lineModel1 = new LineModel1();
    XAxis xAxis = new XAxis();
    YAxis yAxis = new YAxis();
    Series series = new Series();
    xAxis.setType("category");
    xAxis.setData(xData);
    yAxis.setType("value");
    series.setType("line");
    series.setData(sData);
    lineModel1.setxAxis(xAxis);
    lineModel1.setyAxis(yAxis);
    seriesList.add(series);
    lineModel1.setSeries(seriesList);
    return lineModel1;
  }

  public LineModel1 getChartDataAxis( ArrayList<String> xData1,
      List<List<Long>>sDatas) {
    ArrayList<String> xData = new ArrayList<>();
//    ArrayList<Long> sData = new ArrayList<>();
    ArrayList<Series> seriesList = new ArrayList<>();
    for(int i= 0;i<xData1.size();i++){
      xData.add(xData1.get(i));
//      sData.add(sData1.get(i));
    }
    LineModel1 lineModel1 = new LineModel1();
    XAxis xAxis = new XAxis();
    YAxis yAxis = new YAxis();
    xAxis.setType("category");
    xAxis.setData(xData);
    yAxis.setType("value");
    for(int i = 0;i<sDatas.size();i++){
      Series series = new Series();
      if(i == 0){
        series.setName("str->obj");
      }else{
        series.setName("obj->str");
      }
      List<Long> sData1 = sDatas.get(i);
      series.setType("line");
      ArrayList<Long> sData = new ArrayList<>();
      for(int k = 0;k<sData1.size();k++){
        sData.add(sData1.get(k));
      }
      series.setData(sData);
      seriesList.add(series);
    }
//    series.setData(sData);
    Tooltip tooltip = new Tooltip();
    tooltip.setTrigger("axis");
    lineModel1.setTooltip(tooltip);
    lineModel1.setxAxis(xAxis);
    lineModel1.setyAxis(yAxis);
    lineModel1.setSeries(seriesList);
    return lineModel1;
  }
}
