package com.notebook.notebookbackend.data.database.mapper;

import com.notebook.notebookbackend.data.database.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM User")
    List<User> getAll();

    @Select("SELECT * FROM User WHERE UserName = #{name} AND UserPassword = #{password}")
    User getByNameAndPassword(String name, String password);

    @Select("SELECT * FROM User WHERE UserName = #{name}")
    User getByName(String name);

    @Update("UPDATE User SET UserPassword = #{newPassword} WHERE UserName = #{name} AND UserPassword = #{oldPassword}")
    void updatePassword(String name, String oldPassword, String newPassword);

    @Update("UPDATE User SET UserPhone = #{phone} WHERE UserName = #{name} AND UserPassword = #{password}")
    void updatePhone(String name, String password, String phone);

    @Update("UPDATE User SET UserEMail = #{email} WHERE UserName = #{name} AND UserPassword = #{password}")
    void updateEMail(String name, String password, String email);

    @Insert("INSERT INTO User (UserName, UserPassword, UserPhone, UserEMail) VALUES (#{userName}, #{userPassword}, #{userPhone}, #{userEmail})")
    void insertUser(User user);
}
