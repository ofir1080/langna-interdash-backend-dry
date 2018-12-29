package com.LangNet.SpringRest.model.total;

public class TotalLang {

    private double total;
    private double growth;

    public TotalLang(double total, double growth) {
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
        return "TotalLang{" +
                "total='" + total + '\'' +
                ", growth='" + growth + '\'' +
                '}';
    }
}
