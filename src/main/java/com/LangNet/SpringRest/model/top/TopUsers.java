package com.LangNet.SpringRest.model.top;

public class TopUsers {

    private long id;
    private long userId;
    private String telegram;
    private String email;
    private String recTime;
    private int recCount;
    private double amount;


    public TopUsers(long id, long userId, String telegram, String email, String recTime, int recCount, double amount) {
        this.setId(id);
        this.setUserId(userId);
        this.setTelegram(telegram);
        this.setEmail(email);
        this.setRecTime(recTime);
        this.setRecCount(recCount);
        this.setAmount(amount);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRecTime(String recTime) {
        this.recTime = recTime;
    }

    public void setRecCount(int recCount) {
        this.recCount = recCount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTelegram() {
        return telegram;
    }

    public String getEmail() {
        return email;
    }

    public String getRecTime() {
        return recTime;
    }

    public int getRecCount() {
        return recCount;
    }

    public double getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return "TopUsers{" +
                "id=" + id +
                ", userId=" + userId +
                ", telegram='" + telegram + '\'' +
                ", email='" + email + '\'' +
                ", recTime='" + recTime + '\'' +
                ", recCount=" + recCount +
                ", amount=" + amount +
                '}';
    }
}
