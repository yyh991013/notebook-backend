package com.notebook.notebookbackend.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author 22454
 */
public class LoginDTO {
    @NotNull
    @Length(min = 6, max = 30)
    private String userName;
    @NotNull
    @Length(min = 32, max = 32)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
