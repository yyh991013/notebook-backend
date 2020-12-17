package com.notebook.notebookbackend.service;

import com.notebook.notebookbackend.data.cache.Redis;

import com.notebook.notebookbackend.dto.*;
import com.notebook.notebookbackend.error.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

/**
 * @author 22454
 */
@Service
public interface UserService {

    /**
     * 登录接口
     *
     * @param loginDTO 登录数据传输对象
     * @return 登陆结果(key - userName, value - token)
     * @throws BusinessException 业务异常
     */
    Map<String, String> login(LoginDTO loginDTO) throws BusinessException;

    /**
     * 注册接口
     *
     * @param registerDTO 注册数据传输对象
     * @return 注册结果返回
     * @throws BusinessException 业务异常
     */
    String registered(RegisterDTO registerDTO) throws BusinessException;

}
