package com.LangNet.SpringRest.model.top;

public class TopUserRecCount {

    private long id;
    private String email;
    private int recCount;


    public TopUserRecCount(long id, String email, int recTime) {
        this.setId(id);
        this.setEmail(email);
        this.setRecCount(recTime);
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRecCount(int recTime) {
        this.recCount = recTime;
    }


    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getRecCount() {
        return recCount;
    }

    @Override
    public String toString() {
        return "TopUserRecCount{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", recCount=" + recCount +
                '}';
    }
}
