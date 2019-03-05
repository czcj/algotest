package com.meng.algo.demo.algotest.algomodel.v1.stoppolicy;

import java.io.Serializable;

/**
 * @Description : .
 * @Author : robinwb.
 * @Date : 2017年05月06日 10:06
 * demo data:{"code":"101","mastered":false,"session_id":"121333"}
 */
public class StopPolicyDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2111832122083334164L;

    /**
     * 学习是否达标，true-达标，false-未达标
     */
    private  boolean mastered;

    /**
     * 是否停止
     */
    private boolean stopped;

    /**
     * 停止及达标的原因
     */
    private String cause;

    public boolean isMastered() {
        return mastered;
    }

    public void setMastered(boolean mastered) {
        this.mastered = mastered;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }





}
