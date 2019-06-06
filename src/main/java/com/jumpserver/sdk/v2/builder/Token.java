package com.jumpserver.sdk.v2.builder;

import com.jumpserver.sdk.v2.model.User;

public class Token {
    private String token;
    private String endpoint;
    private String username;
    private String password;
    private boolean xpack;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token() {
    }

    public Token(String endpoint, String username, String password) {
        this.endpoint = endpoint;
        this.username = username;
        this.password = password;
    }

    public boolean getXpack() {
        return xpack;
    }

    public void setXpack(boolean xpack) {
        this.xpack = xpack;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", xpack=" + xpack +
                '}';
    }
}
