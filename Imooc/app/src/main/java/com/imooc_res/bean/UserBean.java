package com.imooc_res.bean;

import java.io.Serializable;

public class UserBean implements Serializable {

    private int id,icon;
    private String username;

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", icon=" + icon +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
