package com.meng.algo.demo.algotest.algomodel.v1.util;

import java.util.ArrayList;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yixue on 2016/3/20.
 */

public final class ArrayHelper

{
  private static final Logger logger = LoggerFactory.getLogger(ArrayHelper.class);

  public ArrayHelper() {

  }

  /*
  * remove duplicates from list of strings
  * */
  public static ArrayList<String> rmvDuplicates(ArrayList<String> lssOld) {
    HashSet<String> hsNew = new HashSet<>(lssOld);
    ArrayList<String> lssNew = new ArrayList<>(hsNew);
    return lssNew;
  }


  /*
  * merging multiple list without duplicates
  * */
  public static ArrayList<String> getAllWithoutDuplicates(ArrayList<String> lssA,
      ArrayList<String> lssB) {
    HashSet<String> hsNew = new HashSet<>();

    if (lssA != null && lssA.size() > 0) {
      hsNew.addAll(lssA);
    }

    if (lssB != null && lssB.size() > 0) {

      try {
        hsNew.addAll(lssB);
      } catch (Exception e) {
//        Gson gson = new Gson();
//        System.err.println("ArrayHelper_getAllWithoutDuplicates--lessB--" + gson.toJson(lssB));
//        System.err.println("ArrayHelper_getAllWithoutDuplicates--lessA--" + gson.toJson(lssA));
        logger.error(e.getMessage());
      }
    }

    ArrayList<String> lssNew = new ArrayList<>(hsNew);
    return lssNew;
  }

}
