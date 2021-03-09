package com.bodystem.android.models;

public class UserModel {
    String email;
    String name;
    String userId;

    public UserModel(String email, String name, String userId) {
        this.email = email;
        this.name = name;
        this.userId = userId;
    };

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
