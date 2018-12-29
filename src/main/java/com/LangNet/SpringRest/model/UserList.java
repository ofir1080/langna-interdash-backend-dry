package com.LangNet.SpringRest.model;

public class UserList {

    private String username;
    private String email;
    private String country;

    public UserList(String username, String email, String country) {
        this.setUsername(username);
        this.setEmail(email);
        this.setCountry(country);
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


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
