package com.wy.mca.common;

//@Data
public class Customer {

    /**
     * 用户Id
     */
    public int id;

    /**
     * 用户购买的数量
     */
    public int buy;

    /**
     * 进入得奖区或者候选区的时间
     */
    public int enterTime;

    public Customer(){

    }

    public Customer(int id, int buy, int enterTime) {
        this.id = id;
        this.buy = buy;
        this.enterTime = enterTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(int enterTime) {
        this.enterTime = enterTime;
    }
}
