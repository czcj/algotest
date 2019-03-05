package com.meng.algo.demo.algotest.algomodel.v2.Ability;
//import com.google.gson.*;

import com.meng.algo.demo.algotest.algomodel.enums.ModelVersion;
import com.meng.algo.demo.algotest.algomodel.v1.ability.AbilityDto;
import com.meng.algo.demo.algotest.algomodel.v1.ability.AbilityParam;
import com.meng.algo.demo.algotest.algomodel.v1.util.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tao
 *         @ 2017.07.25
 * 	   1) cited Ability from original file
 * 	   2) added new parameters adTimeStand and adTimeUsed
 */

public class AbilityWithTime{

  public static String version = ModelVersion.ABI_V2.getModelVersion();
  private static final double RHO = 0.25;    /// default soften weight value

  private static final double INITPROB = -10000000.0;

  private int iContentType;     // content type:  assessment question (1), practice question (2),

  private List<Double> adDifficulty;
  private List<Double> adScore;
  /**
   * added by Tao @ 2017.07.26
   * Standard time for each question, with the same length with the above two
   */
  private List<Long> adTimeStand;
  /**
   * added by Tao @ 2017.07.26
   * Used time for each question
   */
  private List<Long> adTimeUsed;
  /**
   * added by Tao @ 2017.07.26
   * Related time, calculated by function transTime(adTimeUsed, adTimeStand);
   */
  private List<Double> adRelativeTime;

  private boolean bFirst;   // the first time to estimate ability

  private List<Double> adMaxAPosterior;
  private List<Double> adOldMaxAPosterior;


  private int iMostLikely;
  private double dMostLikely;
  private double dMostLikelyProb;   // #Maximum A Poteria for probability of correct response#
  protected int totalLevel = 9;  //能力值级别数量 默认3个级别
  private String sErrors;
  // Constructor
  public AbilityWithTime() {
    initDatas();
  }
  public AbilityWithTime(List<Long> adTimeStand, List<Long> adTimeUsed) {
    this.adTimeStand = adTimeStand;
    this.adTimeUsed = adTimeUsed;
    initDatas();
  }

  private void initDatas() {
    iContentType = 1;
    Double[] doubles = new Double[Constants.NUMABILITIES];
    adMaxAPosterior = Arrays.asList(doubles);
    //		adMaxAPosterior = null;
    bFirst = true;

    iMostLikely = -1;
    dMostLikelyProb = INITPROB;

    sErrors = "";

  }

  // used by main system
  public AbilityDto getResult(AbilityParam abilityParam) {
    AbilityDto abilityDto = new AbilityDto();
    try {
      parseJSONData(abilityParam);
      double dRtn = getMostLikely();
      abilityDto = new AbilityDto(String.valueOf(dRtn),adMaxAPosterior,dMostLikelyProb);
      abilityDto.setVersion(version);
      adDifficulty = new ArrayList<>();
      adScore = new ArrayList<>();
      adMaxAPosterior = new ArrayList<>();
      adOldMaxAPosterior = new ArrayList<>();
      bFirst = true;
//      res = composeReturnResult(sErrors, String.valueOf(dRtn));
    }catch (Exception biz){

    }

    return abilityDto;
  }
  /**
   * added by robinwb on 20161226
   *
   * @param ques_diffs
   * @param quesAnsers
   * @param contentType
   * @return
   */
  public double getAbility(Double[] ques_diffs, Double[] quesAnsers, int contentType) {
    try {
      adDifficulty = Arrays.asList(ques_diffs);
      adScore = Arrays.asList(quesAnsers);
      adOldMaxAPosterior = Arrays.asList(new Double[0]);
      iContentType = contentType;
      /*
       * adjust difficulty values
       * difficulty level should be -1, 0, 1, easy, moderate, difficult
       */
      if (adDifficulty != null && adDifficulty.size() > 0) {
        for (int i = 0; i < adDifficulty.size(); i++) {
          double v = adDifficulty.get(i) - 2;
          adDifficulty.set(i,v);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    checkIsFirstEstimation();
    //end parse
    double dRtn = getMostLikely();
    return dRtn;
  }

  private double DiscreteAbility(int iAbility) {
    return (double) iAbility / (Constants.NUMABILITIES + 1);  // Discrete points of ability
  }

  // get log ability value
  private double LogAbility(double dTheta) {
    return Math.log(dTheta / (1 - dTheta));
    //return dTheta;
  }

  void checkIsFirstEstimation() {
    // check if it is the first time
    // if not, modify bFirst to false, and initialize adOldMaxAPosterior
    if (adOldMaxAPosterior != null && adOldMaxAPosterior.size() == Constants.NUMABILITIES)
      bFirst = false;
  }

  // to calculate joint probability function for each ability
  private List<Double> calcPosteriorLogVal() {
    double dTheta = 0;
    double dInitVal = 0;
    boolean bAssmQuest = iContentType == 1;
    for (int i = 0; i < Constants.NUMABILITIES; i++) {
      dTheta = LogAbility(DiscreteAbility(i + 1));
      if (bFirst == true) {
        dInitVal = IRTModelWithTime
            .DefaultLogLK(dTheta, bAssmQuest);  // initial log likelihood function given zero difficulty
      }

      double dLhWeight = Constants.LH_WEIGHT_ASSM;
      if (!bAssmQuest) {
        dLhWeight = Constants.LH_WEIGHT_PRAC;
      }
      double v = IRTModelWithTime
          .LogJointLikeliHoodFunction(dTheta, adDifficulty, adScore, dInitVal, dLhWeight,
              adRelativeTime);
      adMaxAPosterior.set(i,v);
    }
    if (bFirst == false)
      softenOldMaxAPosterior();
    return adMaxAPosterior;
  }

  // update ability when new evidence comes
  private void softenOldMaxAPosterior() {
    for (int i = 0; i < Constants.NUMABILITIES; i++) {
      try {
        Double aDouble = adMaxAPosterior.get(i);
        aDouble = aDouble * adOldMaxAPosterior.get(i);   // not to soften temporarily
        adMaxAPosterior.set(i,aDouble);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  // get estimated ability value
  private double getMostLikely() {
    calcPosteriorLogVal();
    dMostLikelyProb = INITPROB;
    for (int i = 0; i < Constants.NUMABILITIES; i++) {
      if (dMostLikelyProb < adMaxAPosterior.get(i)) {
        dMostLikelyProb = adMaxAPosterior.get(i);
        iMostLikely = i + 1;
      }
    }
    dMostLikelyProb = (double) dMostLikelyProb;
    dMostLikely = DiscreteAbility(iMostLikely);   // decimal ability
    return dMostLikely;
  }

  /**
   * added by Tao @ 2017.07.26
   * Param in: adTimeUsed, time used array for each question
   * 	     adTimeStand, standard time for each question
   */
  // Transfer time to relative time
  private void transTime(List<Long> adTimeUsed, List<Long> adTimeStand) {
    if (adTimeUsed == null || adTimeStand == null || adTimeUsed.size() != adTimeStand.size()) {
      return;
    }
    adRelativeTime = Arrays.asList(new Double[adTimeStand.size()]);
    for (int i = 0; i < adTimeUsed.size(); i++) {

//			adRelativeTime[i] = adTimeUsed[i] / adTimeStand[i];
      //因为精度问题。所以要优先将除数强转为double类型，这样才会避免结果精度不准变成0导致后面对数为NaN的异常
      adRelativeTime.set(i,((double) adTimeStand.get(i) / adTimeUsed.get(i)));
    }
  }

  /**
   * edited by Tao @ 2017.07.26
   * added two more parameters: adTimeStand & adTimeUsed
   */
  private void parseJSONData(AbilityParam abilityParam) throws Exception{
    try {
      adDifficulty = new ArrayList<Double>(
          abilityParam.getDifficulty());// (double[]) mInputData.get("difficulty");
      adScore = abilityParam.getScore();// (double[]) mInputData.get("score");
      adOldMaxAPosterior = abilityParam.getLikelihood();// (double[])
      // mInputData.get("likelihood");
      if (abilityParam.getTotalLevel() != null) {
        if (abilityParam.getTotalLevel() > 0) {
          totalLevel = abilityParam.getTotalLevel();
        }
      }

      iContentType = abilityParam.getType().get(0).intValue(); // 1: assessment; 2: practice;
      /*
       * adjust difficulty values
       * difficulty level should be -1, 0, 1, easy, moderate, difficult
       */
      if (adDifficulty != null && adDifficulty.size() > 0) {
        if(adTimeUsed.size() != adDifficulty.size()){
          adTimeUsed = Arrays.asList(new Long[adDifficulty.size()]);
          adTimeStand = Arrays.asList(new Long[adDifficulty.size()]);
        }
        for (int i = 0; i < adDifficulty.size(); i++) {
          //adDifficulty[i] = adDifficulty[i] - 2;
          //adDifficulty[i]=-1+0.25*(adDifficulty[i]-1);
          double v = transDiff(adDifficulty.get(i));
          adDifficulty.set(i,v);
          if(adTimeUsed.get(i)==null || adTimeStand == null || adTimeUsed.get(i) <= 0 || adTimeStand.get(i) <= 0){
            adTimeStand.set(i,-1l);
            adTimeUsed.set(i,-1l);
          }
        }
      }
      // Transfer time
      if (adTimeStand != null && adTimeStand.size() != 0) {
        transTime(adTimeUsed, adTimeStand);
      }

    } catch (Exception biz){

    }
    checkIsFirstEstimation();

  }

  /**
   * 难度翻译 题目难度保持在[-1,1]
   *
   * @param diff
   * @return
   */
  private double transDiff(Double diff) {
    if (totalLevel == 3 && diff <= 3) {
      return diff - 2;
    } else {
      return -1 + 0.25 * (diff - 1);
    }
  }
}
