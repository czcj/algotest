package com.meng.algo.demo.algotest.algomodel.v1.recom.assess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 22/04/2016.
 */
public class KNode {

    private String sId;

//    private static String sCode;
    private String sCode;
    private String sName;

    private int iDepth;

    private List<String> lssPre;
    private List<String> lssPost;

    private int iPreCnt;
    private int iPostCnt;

    private ArrayList<String> lssFullPre;
    private ArrayList<String> lssFullPost;

    private int iFullPreCnt;    // total of all pre-knode backwards
    private int iFullPostCnt;   // total of all post-knode onwards


    public KNode() {
        sId = sCode = sName = "";
        iDepth = iPreCnt = iPostCnt = iFullPreCnt = iFullPostCnt = -1;
        lssPre = new ArrayList<>();
        lssPost = new ArrayList<>();
        lssFullPre = new ArrayList<>();
        lssFullPost = new ArrayList<>();
    }

    public KNode(String _sCode) {
        sCode = _sCode;
        sId = sName = "";
        iDepth = iPreCnt = iPostCnt = iFullPreCnt = iFullPostCnt = -1;
        lssPre = new ArrayList<>();
        lssPost = new ArrayList<>();
        lssFullPre = new ArrayList<>();
        lssFullPost = new ArrayList<>();
    }


    public enum KNodeType {
        PREREQ(1), POSTREQ(2);

        int idx;

        KNodeType(int i) {
            idx = i;
        }
    }


    /*
    * set the pre-list
    *
    * */
    public void setPreList(List<String> _lssPre) {
        lssPre = _lssPre;
        if (lssPre != null) {
            iPreCnt = lssPre.size();
        }
    }


    /*
    * set the post-list
    *
    * */
    public void setPostList(List<String> _lssPost) {
        lssPost = _lssPost;
        if (lssPost != null) {
            iPostCnt = lssPost.size();
        }
    }


    /*
    * get the pre-count
    * */
    public int getPreCnt() {
        return iPreCnt;
    }


    /*
    * get the pre-count
    * */
    public int getPostCnt() {
        return iPostCnt;
    }


    /*
    * get the D value
    * */
    public int getD() {
        return (iPreCnt - iPostCnt);
    }


    /*
    * get the D value
    * */
    public int getAbsD() {
        return Math.abs(iPreCnt - iPostCnt);
    }


    /*
    * get the D value
    * */
    public int getT() {
        return (iPreCnt + iPostCnt);
    }


    /*
    * get the code
    * */
    public String getCode() {
        return sCode;
    }


    /*
    * check whether has pre-requisite nodes
    * */
    public boolean hasPreReqs() {
        boolean bHas = true;
        if (lssPre == null || lssPre.size() == 0) {
            bHas = false;
        }
        return bHas;
    }

    /*
    * check whether has post-requisite nodes
    * */
    public boolean hasPostReqs() {
        boolean bHas = true;
        if (lssPost == null || lssPost.size() == 0) {
            bHas = false;
        }
        return bHas;
    }


}
