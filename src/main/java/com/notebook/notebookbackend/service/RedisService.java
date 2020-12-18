package com.notebook.notebookbackend.service;

import com.notebook.notebookbackend.error.BusinessException;
import org.springframework.stereotype.Service;

/**
 * @author 22454
 */
@Service
public interface RedisService {

    /**
     * 发送验证码到指定邮箱，同保存该验证码到 redis
     *
     * @param email 邮箱
     * @return 验证码
     * @throws BusinessException 业务异常
     */
    String getVerificationCode(String email) throws BusinessException;

}
