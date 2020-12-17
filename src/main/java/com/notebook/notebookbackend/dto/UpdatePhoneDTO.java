package com.notebook.notebookbackend.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class UpdatePhoneDTO {
    @JSONField(name = "name")
    private String username;

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "phone")
    private String phone;

    public UpdatePhoneDTO(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public UpdatePhoneDTO() {}

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
}