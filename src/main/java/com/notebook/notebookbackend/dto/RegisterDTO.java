package com.notebook.notebookbackend.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class RegisterDTO {
    @JSONField(name = "name")
    private String username;

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "phone")
    private String phone;

    @JSONField(name = "email")
    private String email;

    public RegisterDTO(String username, String password, String phone, String email) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public RegisterDTO() {}

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
