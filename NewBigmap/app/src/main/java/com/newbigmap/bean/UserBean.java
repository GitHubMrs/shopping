package com.newbigmap.bean;

public class UserBean {

    private String token;
    private String id;
    private String account;
    private int isGoogleOpenClose;


    private int isGoogleAuthentication;


    public int getIsGoogleOpenClose() {
        return isGoogleOpenClose;
    }

    public void setIsGoogleOpenClose(int isGoogleOpenClose) {
        this.isGoogleOpenClose = isGoogleOpenClose;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getIsGoogleAuthentication() {
        return isGoogleAuthentication;
    }

    public void setIsGoogleAuthentication(int isGoogleAuthentication) {
        this.isGoogleAuthentication = isGoogleAuthentication;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "token='" + token + '\'' +
                ", id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", isGoogleOpenClose=" + isGoogleOpenClose +
                ", isGoogleAuthentication=" + isGoogleAuthentication +
                '}';
    }

}
