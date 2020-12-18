package com.notebook.notebookbackend.service.Impl;

import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.error.EmBusinessErr;
import com.notebook.notebookbackend.service.RedisService;
import com.notebook.notebookbackend.utils.RedisConstants;
import com.notebook.notebookbackend.utils.RedisUtil;
import com.notebook.notebookbackend.utils.VerificationGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @author 22454
 */
@Service
public class RedisServiceImpl implements RedisService {
    private final JavaMailSenderImpl javaMailSender;
    private final StringRedisTemplate stringRedisTemplate;

    @Value("${spring.mail.username}")
    private String myEmailAddress;

    public RedisServiceImpl(JavaMailSenderImpl javaMailSender, StringRedisTemplate stringRedisTemplate) {
        this.javaMailSender = javaMailSender;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public String getVerificationCode(String email) throws BusinessException {
        try {
            String verificationCode = VerificationGenerator.buildVerificationCode();
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            if (null != myEmailAddress && myEmailAddress.length() > 0) {
                helper.setFrom(myEmailAddress);
                helper.setTo(email);
                helper.setSubject("NodeBook-验证邮件");
                helper.setText("<html><body><h1><span style='color: #ff0000'>" + verificationCode + "</span></h1></body></html>", true);
                javaMailSender.send(mimeMessage);
                stringRedisTemplate.opsForValue()
                        .set(
                                RedisUtil.generatorKey(RedisConstants.REDIS_VERIFICATION_CODE_KEY_PREFIX, email),
                                verificationCode,
                                RedisUtil.generatorTtl(60)
                        );
                return verificationCode;
            }
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.FAILED_TO_CREATE_VERIFICATION_CODE);
        }
    }


}
