package com.n11.odev2.auth;


import java.util.Date;

public class RefreshToken {

    private String token;
    private String username;
    private Date expiryDate;

    public RefreshToken() {
    }

    public RefreshToken(String token, String username, Date expiryDate) {
        this.token = token;
        this.username = username;
        this.expiryDate = expiryDate;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
        return expiryDate.before(new Date());
    }
}