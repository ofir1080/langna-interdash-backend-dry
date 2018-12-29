package com.LangNet.SpringRest.model.top;

public class TopCountries {

    private int rank;
    private String country;
    private double recTime;
    private int userCount;
    private double avgPerUser;


    public TopCountries(int rank, String country, double recTime, int userCount, double avgPerUser) {
        this.setRank(rank);
        this.setCountry(country);
        this.setRecTime(recTime);
        this.setUserCount(userCount);
        this.setAvgPerUser(avgPerUser);
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRecTime(double recTime) {
        this.recTime = recTime;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public void setAvgPerUser(double avgPerUser) {
        this.avgPerUser = avgPerUser;
    }

    public int getRank() {
        return rank;
    }

    public String getCountry() {
        return country;
    }

    public double getRecTime() {
        return recTime;
    }

    public int getUserCount() {
        return userCount;
    }

    public double getAvgPerUser() {
        return avgPerUser;
    }

    @Override
    public String toString() {
        return "TopCountries{" +
                "rank=" + rank +
                ", country='" + country + '\'' +
                ", recTime=" + recTime +
                ", userCount=" + userCount +
                ", avgPerUser=" + avgPerUser +
                '}';
    }
}
