package com.meng.algo.demo.algotest.pojo;

import org.msgpack.annotation.Message;

/**
 * @auther jjp
 * @date 2018/12/19 0019
 */
@Message
public class ItemProcess {

    private String itemType;
    private String status;
    private String itemOrder;
    private String number;
    private String itemMode;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(String itemOrder) {
        this.itemOrder = itemOrder;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getItemMode() {
        return itemMode;
    }

    public void setItemMode(String itemMode) {
        this.itemMode = itemMode;
    }
}
