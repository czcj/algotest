package com.meng.algo.demo.algotest.algomodel.v1.map;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 图谱计算类
 * Created by robinwb
 * Date:2017/12/29 ${DATETIME}.
 */
public class MapCalc {

  /**
   * 描述：递归找所有前置(直接+间接)
   * @param prenode:知识点列表
   * @param map：直接前置知识点map
   * @param result:累计的直接前置知识点
   * @return
   * 创建时间：2017年12月29日
   * 作者：王儒作
   */
  public Set<String> getAllFoundNodes(Set<String> prenode, Map<String,List<String>> map,Set<String> result)//result第一次为空
  {
    Set<String> strings = new HashSet<>();
    int over = 0;
    for (String string : prenode) {
      if(null!=map.get(string))
      {
        //每一行的直接前置知识点
        List<String> str = map.get(string);
        for (String string2 : str)
        {
          strings.add(string2);
          result.add(string2);
        }
        over++;
      }

    }
    if(over ==0)
    {
      return result;
    }

    return getAllFoundNodes(strings, map,result);
  }

  /**
   * 描述：递归找所有后置(直接+间接)、sCandidateS:需要求后置的薄弱知识点、result:所有所求后置的集合
   * @param hmPostKMap
   * @param sCandidateS
   * @param result
   * @return
   * 创建时间：2017年12月29日
   * 作者：王儒作
   */
  public Set<String> getAllPostNodes(Map<String, List<String>> hmPostKMap,Set<String> sCandidateS,Set<String> result)
  {
    Set<String> proSetAll = new HashSet<>();
    for (String sCandidate : sCandidateS) {
      List<String> prolist = hmPostKMap.get(sCandidate);
      if (prolist!=null){
      for (String sCandidatePro : prolist) {
        proSetAll.add(sCandidatePro);
      }
      }
    }
    int i = result.size();
    for (String string : proSetAll) {
      result.add(string);
    }
    if(i == result.size())
    {
      return result;
    }else{
      return getAllPostNodes(hmPostKMap, proSetAll, result);
    }
  }
}
