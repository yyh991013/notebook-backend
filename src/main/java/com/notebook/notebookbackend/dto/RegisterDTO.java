package com.notebook.notebookbackend.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author 22454
 */
public class RegisterDTO {
    @NotNull
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotNull
    @NotBlank(message = "密码不能为空")
    @Length(min = 32, max = 32)
    private String password;
    @NotNull
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号格式错误")
    @NotBlank(message = "手机号不能为空")
    private String phone;
    @NotNull
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;
    @NotNull
    @NotBlank(message = "验证码不能为空")
    private String verificationCode;

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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
