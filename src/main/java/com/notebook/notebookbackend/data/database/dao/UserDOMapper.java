package com.notebook.notebookbackend.data.database.dao;

import com.notebook.notebookbackend.BO.UserBO;
import com.notebook.notebookbackend.data.database.DO.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 22454
 */
@Repository
public interface UserDOMapper {
    int deleteByPrimaryKey(Integer userId);

    int createUser(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    UserBO selectByUserName(String userName);

    int createUser(@Param("userName") String userName,
                   @Param("email") String email,
                   @Param("password") String password,
                   @Param("phone") String phone);

    int updatePasswordByUserName(@Param("userName") String userName,
                                 @Param("oldPassword") String oldPassword,
                                 @Param("newPassword") String newPassword);

    int updatePhoneByUserName(@Param("userName") String userName, @Param("phone") String phone);

    int updateEmailByUserName(@Param("userName") String userName, @Param("email") String email);
}