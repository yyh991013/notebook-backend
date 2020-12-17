package com.notebook.notebookbackend.dto;


/**
 * @author 22454
 */
public class UpdatePhoneDTO {

    private String userName;

    private String password;

    private String phone;

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

    @Override
    public String toString() {
        return "UpdatePhoneDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
