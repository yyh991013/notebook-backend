package com.notebook.notebookbackend.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class UpdatePasswordDTO {
    @JSONField(name = "name")
    private String username;

    @JSONField(name = "old")
    private String oldPassword;

    @JSONField(name = "new")
    private String newPassword;

    public UpdatePasswordDTO(String username, String oldPassword, String newPassword) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public UpdatePasswordDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
