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
     *
     * @param key
     * @param email                邮箱
     * @param verificationCodeType 验证码类型
     * @throws BusinessException 业务异常
     */
    void getVerificationCode(String key, String email, int verificationCodeType) throws BusinessException;

    /**
     * 验证验证码
     *
     * @param key              redis里面存的key
     * @param verificationCode 验证码
     * @return 是否一致
     */
    boolean compareVerificationCode(String key, String verificationCode) throws BusinessException;

}
