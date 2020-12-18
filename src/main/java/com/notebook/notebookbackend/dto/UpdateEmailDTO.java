package com.notebook.notebookbackend.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 22454
 */
public class UpdateEmailDTO {
    @NotNull
    @NotBlank(message = "密码不能为空")
    @Length(min = 32, max = 32)
    private String password;
    @NotNull
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

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

    @Override
    public String toString() {
        return "UpdateEmailDTO{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
