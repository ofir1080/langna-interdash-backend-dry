package com.LangNet.SpringRest.model.total;

public class TotalUsers {

    private double total;
    private double growth;

    public TotalUsers(double total, double growth) {
        this.setTotal(total);
        this.setGrowth(growth);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public double getTotal() {
        return total;
    }

    public double getGrowth() {
        return growth;
    }

    @Override
    public String toString() {
        return "TotalUsers{" +
                "total='" + total + '\'' +
                ", growth='" + growth + '\'' +
                '}';
    }
}
