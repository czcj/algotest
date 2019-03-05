package com.meng.algo.demo.algotest.algomodel.v1.util;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by WeiCui on 01/27/2016.
 *
 * 2016.02.25 added knowledge map for topic_601
 *
 */


public final class KMapHelper
{

    public KMapHelper()
    {

    }

    /*
    * find the all the prereqs
    * */
    public static ArrayList<String> traceAndGetPreReqs(ArrayList<String> lssPreReqTmp, HashMap<String, ArrayList<String>> hmDPreKMap)
    {
        ArrayList<String> lssRst = new ArrayList<String>();

        try
        {
            if (lssPreReqTmp != null && lssPreReqTmp.size() > 0)
            {
                // lssRst.addAll(lssPreReqTmp);
                lssRst = ArrayHelper.getAllWithoutDuplicates(lssRst, lssPreReqTmp);
                for (String sTmp : lssPreReqTmp)
                {
                    if(hmDPreKMap.containsKey(sTmp))
                    {
                        // lssRst.addAll(traceAndGetPreReqs(hmDPreKMap.get(sTmp), hmDPreKMap));
                        lssRst = ArrayHelper.getAllWithoutDuplicates(lssRst, traceAndGetPreReqs(hmDPreKMap.get(sTmp), hmDPreKMap));
                    }
                    else
                    {
                        // 1todo logging
                        // System.out.println("Could not find " + sTmp + " in the hmDPreKMap!!!");
                    }
                }
            }
        }
        catch (Exception e)
        {
             System.out.println(e.getMessage());
        }

        return ArrayHelper.rmvDuplicates(lssRst);
    }





    /*
    * find all the post-reqs
    * */
    public static ArrayList<String> traceAndGetPostReqs(ArrayList<String> lssPostReqTmp, HashMap<String, ArrayList<String>> hmDPostKMap)
    {
        ArrayList<String> lssRst = new ArrayList<String>();

        try
        {
            if (lssPostReqTmp != null && lssPostReqTmp.size() > 0)
            {
                // lssRst.addAll(lssPostReqTmp);
                lssRst = ArrayHelper.getAllWithoutDuplicates(lssRst, lssPostReqTmp);
                for (String sTmp : lssPostReqTmp)
                {
                    if(hmDPostKMap.containsKey(sTmp))
                    {
                        // lssRst.addAll(traceAndGetPreReqs(hmDPostKMap.get(sTmp), hmDPostKMap));
                        lssRst = ArrayHelper.getAllWithoutDuplicates(lssRst, traceAndGetPreReqs(hmDPostKMap.get(sTmp), hmDPostKMap));
                    }
                    else
                    {
                        // 1todo logging
                         System.out.println("Could not find " + sTmp + " in the hmDPreKMap!!!");
                    }
                }
            }
        }
        catch (Exception e)
        {
             System.out.println(e.getMessage());
        }

        return ArrayHelper.rmvDuplicates(lssRst);
    }







}
