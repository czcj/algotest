package com.meng.algo.demo.algotest.algomodel.v1.map;

import java.util.List;

/**
 * @Description : .
 * @Author : Administrator.
 * @Date : 2017年12月29日 15:08
 */
public class MapCalcParam {
  //所需要查询的知识点集合
  private List<String> nodes;

  //知识图谱
  private String kmap_code;

  public List<String> getNodes() {
    return nodes;
  }

  public void setNodes(List<String> nodes) {
    this.nodes = nodes;
  }

  public String getKmap_code() {
    return kmap_code;
  }

  public void setKmap_code(String kmap_code) {
    this.kmap_code = kmap_code;
  }



}
