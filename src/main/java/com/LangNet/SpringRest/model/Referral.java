package com.LangNet.SpringRest.model;

public class Referral {

    private String userName;
    private String email;
    private int totalReferrals;
    private int completed;
    private double precent;

    public Referral(String userName, String email, int totalReferrals, int completed, double precent) {
        this.setUserName(userName);
        this.setEmail(email);
        this.setTotalReferrals(totalReferrals);
        this.setCompleted(completed);
        this.setPrecent(precent);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTotalReferrals(int totalReferrals) {
        this.totalReferrals = totalReferrals;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public void setPrecent(double precent) {
        this.precent = precent;
    }


    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalReferrals() {
        return totalReferrals;
    }

    public int getCompleted() {
        return completed;
    }

    public double getPrecent() {
        return precent;
    }

    @Override
    public String toString() {
        return "Referral{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", totalReferrals=" + totalReferrals +
                ", completed=" + completed +
                ", precent=" + precent +
                '}';
    }
}
