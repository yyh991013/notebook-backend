package com.notebook.notebookbackend.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginDTO {
    @JSONField(name = "name")
    private String username;

    @JSONField(name = "password")
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDTO() {}

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
}
