package com.jumpserver.sdk.model;

import java.util.Date;

public class User {
    private String id;

    private String password;

    private Date last_login;

    private String first_name;

    private String last_name;

    private Boolean is_active;

    private Date date_joined;
    //required
    private String username;
    //required
    private String name;
    //required
    private String email;
    //required
    private String[] groups;

    private String role;

    private String avatar;

    private String wechat;

    private String phone;

    private Boolean enable_otp;

    private String secret_key_otp;

    private String privateKey;

    private String publicKey;

    private Boolean is_first_login;

    private Date date_expired;

    private String created_by;

    private String comment;

    private String[] user_permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Date getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(Date date_joined) {
        this.date_joined = date_joined;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getEnable_otp() {
        return enable_otp;
    }

    public void setEnable_otp(Boolean enable_otp) {
        this.enable_otp = enable_otp;
    }

    public String getSecret_key_otp() {
        return secret_key_otp;
    }

    public void setSecret_key_otp(String secret_key_otp) {
        this.secret_key_otp = secret_key_otp;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Boolean getIs_first_login() {
        return is_first_login;
    }

    public void setIs_first_login(Boolean is_first_login) {
        this.is_first_login = is_first_login;
    }

    public Date getDate_expired() {
        return date_expired;
    }

    public void setDate_expired(Date date_expired) {
        this.date_expired = date_expired;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getUser_permissions() {
        return user_permissions;
    }

    public void setUser_permissions(String[] user_permissions) {
        this.user_permissions = user_permissions;
    }
}