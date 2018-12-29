package com.LangNet.SpringRest.model;

public class NewUsersList {

    private long userId;
    private String username;
    private String email;
    private String country;
    private String date;

    public NewUsersList(long userId, String username, String email, String country, String date) {
        this.setUserId(userId);
        this.setUsername(username);
        this.setEmail(email);
        this.setCountry(country);
        this.setDate(date);
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "NewUsersList{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
