package com.meng.algo.demo.algotest.algomodel.v1.recom.assess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：知识点测评算法原子部分,接口功能：挑选知识点
 * 作者：王儒作
 * 创建时间：2017年10月31日11:57:18
 */
public class KSTMode {

    private static Logger logger = LoggerFactory.getLogger(KSTMode.class);
//    private volatile static KSTMode kstMode;

//    private KSTMode() {
//    }

    /**
     * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
     *
     * @return
     */
//    public static KSTMode getSingleton() {
//        if (kstMode == null) {
//            synchronized (KSTMode.class) {
//                if (kstMode == null) {
//                    kstMode = new KSTMode();
//                }
//            }
//        }
//        return kstMode;
//    }


    //先行测试知识点测评的状态数据实体类
    private AssessParam lomapCounterEntity = new AssessParam();

    //剩余知识点集合
    private List<String> kNodeCandidate;

    //剩余知识点列表
    private List<String> lssForcedKNode;

    //最终返回的知识点
    private String sKNodeToAskQ;

    //当前图谱下所有前置知识点，key:所有知识点，value:知识点对应的直接前置知识点
    private Map<String, List<String>> hmUpdatedPreKMap;

    //当前图谱下所有后置知识点，key:所有知识点，value:知识点对应的直接后置知识点
    private Map<String, List<String>> hmUpdatedPostKMap;

    //
    private int iCP = 2;

    //没有直接后置的知识点集合
    private List<String> lssUpdatedEndKNode = new ArrayList<>();

    /**
     * 接口入口
     *
     * @param assessParam
     * @return
     */
    public String getResult(AssessParam assessParam) {
        //参数解析
        parseJSONData(assessParam);

        //切换下一个知识点
        return getKnode();
    }

    /**
     * 参数解析
     *
     * @param assessParam
     */
    private void parseJSONData(AssessParam assessParam) {

        //已学习知识点集合
        lssForcedKNode = assessParam.getRestKNodes();

        //所有知识点的直接后置
        hmUpdatedPostKMap = assessParam.getHmUpdatedPostKMap();

        //所有知识点的直接前置
        hmUpdatedPreKMap = assessParam.getHmUpdatedPreKMap();

        //当前图谱下所有没有后置知识点的集合
        lssUpdatedEndKNode = assessParam.getUpdatedEndKNode();
        //取出图谱后置关系中后置知识点size为0的知识点
        Set<String> postListKeys = hmUpdatedPostKMap.keySet();
//        for(String postListKey : postListKeys){
//            if (0 == hmUpdatedPostKMap.get(postListKey).size()){
//                lssUpdatedEndKNode.add(postListKey);
//            }
//        }

        //知识点范围
        kNodeCandidate = assessParam.getkNodesCandidate();

    }

    /**
     * 切换下一个知识点
     *
     * @return
     */
    private String getKnode() {

        //根据图谱下没有后置知识点的知识点集合
        Map<String, List<String>> hmKBranch = determineKBranches(lssUpdatedEndKNode);

        //没知识点可取时，给知识点赋成-1
//            if(hmKBranch == null || hmKBranch.size() == 0)
//            {
//                sKNodeToAskQ = "-1";
        //已学习知识点列表 lssForcedKNode
        if (lssForcedKNode != null && lssForcedKNode.size() > 0) {
            sKNodeToAskQ = lssForcedKNode.get(0);
            //去除已学习知识点形成新图谱
//            updateAssessParam(lssForcedKNode);
        } else {
            //知识点范围
            if (kNodeCandidate == null || kNodeCandidate.size() == 0) {
                sKNodeToAskQ = "-1";    // means the pre-assessment finishes
            } else {
                ArrayList<String> lssBestKNode = null;
                for (HashMap.Entry oPair : hmKBranch.entrySet()) {
//            }
                    ArrayList<String> lssTmp = (ArrayList<String>) oPair.getValue();
                    if (lssBestKNode == null || lssTmp.size() > lssBestKNode.size()) {
                        lssBestKNode = lssTmp;
                    }
                }

                if (lssBestKNode == null || lssBestKNode.size() == 0) {
                    sKNodeToAskQ = "-1";
                } else {
                    lssBestKNode = (ArrayList<String>) sortKNodeInKBranch(lssBestKNode);
                    for (int i = 0; i < iCP; i++) {
                        if (i == 0) {
                            sKNodeToAskQ = lssBestKNode.get(0);
                        } else {
                            if (i < lssBestKNode.size()) {
                                int iCnt1 = getCntOfConnectedKNode(lssBestKNode.get(i));
                                int iCnt2 = getCntOfConnectedKNode(sKNodeToAskQ);
                                if (iCnt1 > iCnt2) {
                                    sKNodeToAskQ = lssBestKNode.get(i);
                                }
                            }
                        }
                    }
                }
            }
        }
      if (!"-1".equals(sKNodeToAskQ) && !kNodeCandidate.contains(sKNodeToAskQ)) {
        sKNodeToAskQ = kNodeCandidate.get(0);
      }
      AssessParam assessParam = new AssessParam();
        return sKNodeToAskQ;
    }

    //根据已掌握知识点形成新的前后置关系图谱
    private void updateAssessParam(List<String> lssForcedKNodes) {
        for(String lssForcedKNode :lssForcedKNodes ){
            //所有知识点的直接后置
            //去除已掌握知识点
            hmUpdatedPostKMap.remove(lssForcedKNode);
            //去除后置知识点中对于已掌握知识点的关系
            Set<String> hmPostKMapKeys = hmUpdatedPostKMap.keySet();
            for(String postKey:hmPostKMapKeys){
                hmUpdatedPostKMap.get(postKey).remove(lssForcedKNode);
            }

            //所有知识点的直接前置
            //去除已掌握知识点
            hmUpdatedPreKMap.remove(lssForcedKNode);
            //去除前置知识点中对于已掌握知识点的关系
            Set<String> hmPoreKMapKeys = hmUpdatedPreKMap.keySet();
            for(String preKey:hmPoreKMapKeys){
                hmUpdatedPostKMap.get(preKey).remove(lssForcedKNode);
            }

            //知识点范围
            //去除已掌握知识点
            kNodeCandidate.remove(lssForcedKNode);
        }


        //当前图谱下所有没有后置知识点的集合
//        lssUpdatedEndKNode = assessParam.getUpdatedEndKNode();
        //取出图谱后置关系中后置知识点size为0的知识点
        Set<String> postListKeys = hmUpdatedPostKMap.keySet();
        for(String postListKey : postListKeys){
            if (0 == hmUpdatedPostKMap.get(postListKey).size()){
                lssUpdatedEndKNode.add(postListKey);
            }
        }
    }

    /*
   * determine the knowledge branches which exist in the current kmap
   * */
    private Map<String, List<String>> determineKBranches(List<String> lssEndKNode) {
        Map<String, List<String>> hmKBranch = new HashMap<String, List<String>>();
        for (String sKNode : lssEndKNode) {
            List<String> lssKNode = getAllPreReqsForKNode(sKNode, true);
            hmKBranch.put(sKNode, lssKNode);
        }
        return hmKBranch;
    }

    /*
   * get all pre-requisite knodes given the knode code
   * */
    private List<String> getAllPreReqsForKNode(String sKNode, boolean bIncludeSelf) {
        List<String> lssPreReqs = new ArrayList<>();
        if (bIncludeSelf) {
            lssPreReqs.add(sKNode);
        }

        if (hmUpdatedPreKMap.containsKey(sKNode)) {
            List<String> lssKNodeTmp = traceAndGetPreReqs(hmUpdatedPreKMap.get(sKNode), hmUpdatedPreKMap);
            lssPreReqs.addAll(lssKNodeTmp);
        }
        return rmvDuplicates(lssPreReqs);
    }

    /*
   * find the all the prereqs
   * */
    public static List<String> traceAndGetPreReqs(List<String> lssPreReqTmp, Map<String, List<String>> hmDPreKMap) {
        List<String> lssRst = new ArrayList<String>();

        try {
            if (lssPreReqTmp != null && lssPreReqTmp.size() > 0) {
                // lssRst.addAll(lssPreReqTmp);
                lssRst = getAllWithoutDuplicates(lssRst, lssPreReqTmp);
                for (String sTmp : lssPreReqTmp) {
                    if (hmDPreKMap.containsKey(sTmp)) {
                        // lssRst.addAll(traceAndGetPreReqs(hmDPreKMap.get(sTmp), hmDPreKMap));
//                        logger.info("KSTMODE 递归知识点:"+sTmp);
                        lssRst = getAllWithoutDuplicates(lssRst, traceAndGetPreReqs(hmDPreKMap.get(sTmp), hmDPreKMap));
                    } else {
                        // System.out.println("Could not find " + sTmp + " in the hmDPreKMap!!!");
                    }
                }
            }
        } catch (Exception e) {
            // 1todo logging
            // System.out.println(e.getMessage());
        }

        return rmvDuplicates(lssRst);
    }

    /*
   * remove duplicates from list of strings
   * */
    public static List<String> rmvDuplicates(List<String> lssOld) {
        Set<String> hsNew = new HashSet<>(lssOld);
        List<String> lssNew = new ArrayList<>(hsNew);
        return lssNew;
    }

    /*
   * merging multiple list without duplicates
   * */
    public static List<String> getAllWithoutDuplicates(List<String> lssA, List<String> lssB) {
        Set<String> hsNew = new HashSet<>();

        if (lssA != null && lssA.size() > 0) {
            hsNew.addAll(lssA);
        }
        if (lssB != null && lssB.size() > 0) {

            hsNew.addAll(lssB);
        }

        List<String> lssNew = new ArrayList<>(hsNew);
        return lssNew;
    }

    /**
     * 知识点排序
     *
     * @param lssToSort
     * @return
     */
    private List<String> sortKNodeInKBranch(List<String> lssToSort) {
//        List<String> lssNew = lssToSort;
        List<String> lssNew = new ArrayList<>();
        lssToSort = rmvDuplicates(lssToSort);
        // sort it by D and then T
        List<KNode> lsoToSort = new ArrayList<KNode>();

        for (String sObj : lssToSort) {
            KNode oKNode = new KNode(sObj);
            oKNode.setPreList(hmUpdatedPreKMap.get(sObj));
            oKNode.setPostList(hmUpdatedPostKMap.get(sObj));
            if (!lsoToSort.contains(oKNode)) {
                lsoToSort.add(oKNode);
            }
        }

        Collections.sort(lsoToSort, new Comparator<KNode>() {

            public int compare(KNode node1, KNode node2) {
                //1TODO return 1 if node2 should be before node1
                //     return -1 if node1 should be before node2
                //     return 0 otherwise

                if (node1 == null) {
                    return node2 == null ? 0 : -1;
                }
                if (node2 == null) {
                    return 1;
                }

                if (node1.getAbsD() == node2.getAbsD()) {
//                    return node1.getT() < node2.getT() ? -1 : (node1.getT() == node2.getT() ? 0 : 1);
                    return node1.getT() < node2.getT() ? 1 : (node1.getT() == node2.getT() ? 0 : -1);
                } else {
                    return node1.getAbsD() < node2.getAbsD() ? 1 : -1;
                }
            }

        });
        for (KNode oNodeTmp : lsoToSort) {
            lssNew.add(oNodeTmp.getCode());
        }
//        return rmvDuplicates(lssNew);
        return lssNew;
    }

    /*
   * get the total count of prereqs and postreqs
   * */
    private int getCntOfConnectedKNode(String sKNode) {
        int iCnt = 0;
        if (hmUpdatedPreKMap.containsKey(sKNode) && hmUpdatedPostKMap.containsKey(sKNode)) {
            iCnt = hmUpdatedPreKMap.get(sKNode).size() + hmUpdatedPostKMap.get(sKNode).size();
        } else {
        }
        return iCnt;
    }

}
