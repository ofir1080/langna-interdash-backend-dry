package com.LangNet.SpringRest.model;

public class FinishedCampaign {

    private String campaignName;
    private int limit;
    private int usersStarted;
    private int usersFinished;
    private double precent;
    private long camId;

    public FinishedCampaign(String campaignName, int limit, int usersStarted, int usersFinished, double precent, long camId) {
        this.setCampaignName(campaignName);
        this.setLimit(limit);
        this.setUsersStarted(usersStarted);
        this.setUsersFinished(usersFinished);
        this.setPrecent(precent);
        this.setCamId(camId);
    }


    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setUsersStarted(int usersStarted) {
        this.usersStarted = usersStarted;
    }

    public void setUsersFinished(int usersFinished) {
        this.usersFinished = usersFinished;
    }

    public void setPrecent(double precent) {
        this.precent = precent;
    }

    public void setCamId(long camId) {
        this.camId = camId;
    }



    public String getCampaignName() {
        return campaignName;
    }

    public int getLimit() {
        return limit;
    }

    public int getUsersStarted() {
        return usersStarted;
    }

    public int getUsersFinished() {
        return usersFinished;
    }

    public double getPrecent() {
        return precent;
    }

    public long getCamId() {
        return camId;
    }

    @Override
    public String toString() {
        return "FinishedCampaign{" +
                ", campaignName='" + campaignName + '\'' +
                ", limit=" + limit +
                ", usersStarted=" + usersStarted +
                ", usersFinished=" + usersFinished +
                ", precent=" + precent +
                ", camId=" + camId +
                '}';
    }
}

