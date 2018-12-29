package com.LangNet.SpringRest.model.month;

public class UserReferencePerMonth {

    private String month;
    private int tel;
    private int dash;
    private int both;

    public UserReferencePerMonth(String month, int tel, int dash, int both) {
        this.setMonth(month);
        this.setTel(tel);
        this.setDash(dash);
        this.setBoth(both);
    }

    public void setMonth(String month) {
        this.month = month;
    }
    public void setTel(int tel) {
        this.tel = tel;
    }
    public void setDash(int dash) {
        this.dash = dash;
    }
    public void setBoth(int both) {
        this.both = both;
    }


    public String getMonth() {
        return month;
    }
    public int getTel() {
        return tel;
    }
    public int getDash() {
        return dash;
    }
    public int getBoth() {
        return both;
    }

    @Override
    public String toString() {
        return "UserReferencePerMonth{" +
                "month='" + month + '\'' +
                ", tel=" + tel +
                ", dash=" + dash +
                ", both=" + both +
                '}';
    }
}
