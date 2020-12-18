package com.notebook.notebookbackend.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 22454
 */
public class UpdatePasswordDTO {
    @NotNull
    @NotBlank(message = "密码不能为空")
    @Length(min = 32, max = 32)
    private String oldPassword;
    @NotNull
    @NotBlank(message = "密码不能为空")
    @Length(min = 32, max = 32)
    private String newPassword;
    @NotNull
    @NotBlank(message = "验证码不能为空")
    private String verificationCode;

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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "UpdatePasswordDTO{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
