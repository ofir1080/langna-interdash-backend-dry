package com.LangNet.SpringRest.model.month;

public class RecPerMonth {

    private String month;
    private int count;
    private double growth;


    public RecPerMonth(String month, int count, double growth) {
        this.setMonth(month);
        this.setCount(count);
        this.setGrowth(growth);
    }

    public void setMonth(String stringCol) {
        this.month = stringCol;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public String getMonth() {
        return month;
    }
    public int getCount() {
        return count;
    }
    public double getGrowth() {
        return growth;
    }

    @Override
    public String toString() {
        return "RecPerMonth{" +
                "month='" + month + '\'' +
                ", count=" + count +
                ", growth=" + growth +
                '}';
    }
}

