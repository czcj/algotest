package com.meng.algo.demo.algotest.echartModel;

import java.util.List;

public class LineModel1 {
  XAxis xAxis;
  YAxis yAxis;
  List<Series> series;

  public Tooltip getTooltip() {
    return tooltip;
  }

  public void setTooltip(Tooltip tooltip) {
    this.tooltip = tooltip;
  }

  Tooltip tooltip;

  public XAxis getxAxis() {
    return xAxis;
  }

  public void setxAxis(XAxis xAxis) {
    this.xAxis = xAxis;
  }

  public YAxis getyAxis() {
    return yAxis;
  }

  public void setyAxis(YAxis yAxis) {
    this.yAxis = yAxis;
  }

  public List<Series> getSeries() {
    return series;
  }

  public void setSeries(List<Series> series) {
    this.series = series;
  }
}
