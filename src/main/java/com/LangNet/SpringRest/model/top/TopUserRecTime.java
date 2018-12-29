package com.LangNet.SpringRest.model.top;

public class TopUserRecTime {

    private long id;
    private String email;
    private String recTime;


    public TopUserRecTime(long id, String email, String recTime) {
        this.setId(id);
        this.setEmail(email);
        this.setRecTime(recTime);
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRecTime(String recTime) {
        this.recTime = recTime;
    }
    public long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getRecTime() {
        return recTime;
    }

    @Override
    public String toString() {
        return "TopUserRecTime{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", recTime='" + recTime + '\'' +
                '}';
    }
}

