package com.meng.algo.demo.algotest.pojo;

import java.util.HashSet;
import java.util.Set;
import org.msgpack.annotation.Message;

/**
 * @auther jjp
 * @date 2018/12/5 0005
 */
@Message
public class ShareData {

    private Set<String> noDealedSet;
    private Set<String> masteredSet;
    private Set<String> notMasteredSet;

    public Set<String> getNoDealedSet() {

        if (noDealedSet == null) {
            return new HashSet<>();
        }
        return noDealedSet;
    }

    public void setNoDealedSet(Set<String> noDealedSet) {
        if (this.noDealedSet == null) {
            this.noDealedSet = new HashSet<>();
        }
        this.noDealedSet = noDealedSet;
    }

    public Set<String> getMasteredSet() {
        if (masteredSet == null) {
            return new HashSet<>();
        }
        return masteredSet;
    }

    public void setMasteredSet(Set<String> masteredSet) {
        if (this.masteredSet == null) {
            this.masteredSet = new HashSet<>();
        }
        this.masteredSet = masteredSet;
    }

    public Set<String> getNotMasteredSet() {

        if (notMasteredSet == null) {
            return new HashSet<>();
        }
        return notMasteredSet;
    }

    public void setNotMasteredSet(Set<String> notMasteredSet) {
        if (this.notMasteredSet == null) {
            this.notMasteredSet = new HashSet<>();
        }

        this.notMasteredSet = notMasteredSet;
    }
}
