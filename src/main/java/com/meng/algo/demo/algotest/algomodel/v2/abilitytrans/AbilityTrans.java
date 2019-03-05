package com.meng.algo.demo.algotest.algomodel.v2.abilitytrans;

import com.meng.algo.demo.algotest.algomodel.v1.util.NumberHelper;
import java.util.ArrayList;
import java.util.List;

public class AbilityTrans {
  //  传递的最大能力值
  double maxPropagateAbility = 0.9;
  //  传递的最小能力值
  double minPropagateAbility = 0.2;

  /**
   * @Author: Tao Jiang
   * @Date: 2018/10/18 15:30
   * @Param: String curtNode  结束测试的知识点的tag_code；
   * @Param: Map<String, Double> nodeWithAbility 计算得到能力值的所有知识点HashMap；
   * @Param: boolean isPost   向前/向后传递
   */
  public List<AbilityTransNode> getAbilityTrans(AbilityTransNode abilityTransNode,ArrayList<AbilityTransNode> transNodes,Boolean isPost) {
    transAbility(Double.valueOf(abilityTransNode.getAbility()),transNodes,isPost);
    return transNodes;
  }

  /**
   * @Author: Tao Jiang
   * @Date: 2018/10/18 16:00
   * @Param: String curtNode  结束测试的知识点的tag_code；
   * @Param: Double ability   上述知识点测试得到的能力值；
   * @Param: Map<String, List<String>> nodeToTrans  所有的前置/后置知识点关系图；
   * @Param: boolean isPost   向前/向后传递
   */
  private void transAbility(Double ability,List<AbilityTransNode> nodes, boolean isPost) {
    for(AbilityTransNode node:nodes){
      double proAbility = calProAbility(Integer.valueOf(node.transLevel), ability, isPost);
      node.setTransAbility(String.valueOf(proAbility));
//      node.setReason(LoMasterType.TRANSABI.getCode());
    }
  }

  /**
   * @Author: Tao Jiang
   * @Date: 2018/10/18 16:00
   * @Param: int levels 层数
   * @Param: double ability 传递前的能力值
   * @Param: boolean isPost 往前往后传
   * @Return：double 传递的能力值
   */
  private double calProAbility(int levels, double ability, boolean isPost) {
////传递前的能力值大于传递最大或者
    double res = 0.0;
//      往后传，逐层递减
    if (isPost) {
      res = ability * (1.0 - levels * 0.05);
      res = NumberHelper.RoundDecimal(res, 2);
//
      if (res < minPropagateAbility) {
        res = minPropagateAbility;
      }
    } else {
//      往前传递，逐层递增
      res = ability * (1.0 + levels * 0.05);
      res = NumberHelper.RoundDecimal(res, 2);
      if (res > maxPropagateAbility) {
        res = maxPropagateAbility;
      }
    }
    return res;
  }
}
