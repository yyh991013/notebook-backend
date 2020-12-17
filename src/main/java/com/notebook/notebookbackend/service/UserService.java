package com.notebook.notebookbackend.service;

import com.notebook.notebookbackend.data.cache.Redis;
import com.notebook.notebookbackend.data.database.entity.User;
import com.notebook.notebookbackend.data.database.mapper.UserMapper;
import com.notebook.notebookbackend.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final Redis redis;

    @Autowired
    public UserService(UserMapper userMapper, Redis redis) {
        this.userMapper = userMapper;
        this.redis = redis;
    }

    public String login(LoginDTO loginData) {
        User user = userMapper.getByNameAndPassword(loginData.getUsername(), loginData.getPassword());

        if (user == null) {
            return null;
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redis.set(token, String.valueOf(user.getUserId()), Duration.ofHours(6));
        return token;
    }

    public boolean register(RegisterDTO registerData) {
        User current = userMapper.getByName(registerData.getUsername());

        if (current == null) {
            User user = new User();
            user.setUserName(registerData.getUsername());
            user.setUserPassword(registerData.getPassword());
            user.setUserPhone(registerData.getPhone());
            user.setUserEmail(registerData.getEmail());

            userMapper.insertUser(user);
            return true;
        }
        return false;
    }

    public boolean updatePassword(UpdatePasswordDTO updatePasswordData) {
        String name = updatePasswordData.getUsername();
        String old = updatePasswordData.getOldPassword();

        User current = userMapper.getByNameAndPassword(name, old);

        if (current == null) {
            return false;
        }

        userMapper.updatePassword(name, old, updatePasswordData.getNewPassword());
        return true;
    }

    public boolean updatePhone(UpdatePhoneDTO updatePhoneData) {
        String name = updatePhoneData.getUsername();
        String password = updatePhoneData.getPassword();

        User current = userMapper.getByNameAndPassword(name, password);

        if (current == null) {
            return false;
        }

        userMapper.updatePhone(name, password, updatePhoneData.getPassword());
        return true;
    }

    public boolean updateEmail(UpdateEmailDTO updateEmailData) {
        String name = updateEmailData.getUsername();
        String password = updateEmailData.getPassword();

        User current = userMapper.getByNameAndPassword(name, password);

        if (current == null) {
            return false;
        }

        userMapper.updateEMail(name, password, updateEmailData.getEmail());
        return true;
    }

    public int getUserIdFromCache(String token) {
        return Integer.parseInt(redis.get(token));
    }

    public boolean verifyToken(String token) {
        return redis.get(token) != null;
    }
}
