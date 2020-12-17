package com.notebook.notebookbackend.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class UpdateEmailDTO {
    @JSONField(name = "name")
    private String username;

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "email")
    private String email;

    public UpdateEmailDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UpdateEmailDTO() {}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
