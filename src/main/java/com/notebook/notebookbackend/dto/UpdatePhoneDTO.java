package com.notebook.notebookbackend.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author 22454
 */
public class UpdatePhoneDTO {
    @NotNull
    @NotBlank(message = "密码不能为空")
    @Length(min = 32, max = 32)
    private String password;
    @NotNull
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号格式错误")
    @NotBlank(message = "手机号不能为空")
    private String phone;

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

    @Override
    public String toString() {
        return "UpdatePhoneDTO{" +
                "password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
