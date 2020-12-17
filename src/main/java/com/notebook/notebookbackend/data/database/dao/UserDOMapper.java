package com.notebook.notebookbackend.data.database.dao;

import com.notebook.notebookbackend.BO.UserBO;
import com.notebook.notebookbackend.data.database.DO.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

}