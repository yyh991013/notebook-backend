package com.notebook.notebookbackend.service.Impl;

import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.error.EmBusinessErr;
import com.notebook.notebookbackend.service.RedisService;
import com.notebook.notebookbackend.utils.VerificationUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

/**
 * @author 22454
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Value("${spring.mail.username}")
    private String myEmailAddress;
    private final JavaMailSenderImpl javaMailSender;
    private final StringRedisTemplate stringRedisTemplate;

    public RedisServiceImpl(JavaMailSenderImpl javaMailSender, StringRedisTemplate stringRedisTemplate) {
        this.javaMailSender = javaMailSender;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void getVerificationCode(String key, String email, int verificationCodeType) throws BusinessException {
        try {
            String verificationCodeKey = VerificationUtil.getVerificationCodeKey(verificationCodeType, key);
            String value = stringRedisTemplate.opsForValue().get(verificationCodeKey);
            if (value == null) {
                String verificationCode = VerificationUtil.buildVerificationCode();
                senMessage(email, verificationCode, verificationCodeKey);
            } else {
                throw new BusinessException(EmBusinessErr.VERIFICATION_HAS_SEND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BusinessException) {
                throw (BusinessException) e;
            }
            throw new BusinessException(EmBusinessErr.FAILED_TO_CREATE_VERIFICATION_CODE);
        }
    }

    @Async("asyncServiceExecutor")
    public void senMessage(String email, String verificationCode, String redisKey) throws Exception {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            if (null != myEmailAddress && myEmailAddress.length() > 0) {
                helper.setFrom(myEmailAddress);
                helper.setTo(email);
                helper.setSubject("NodeBook-验证邮件");
                helper.setText(createHtmlMessage(verificationCode), true);
                javaMailSender.send(mimeMessage);
                stringRedisTemplate.opsForValue()
                        .set(
                                redisKey,
                                verificationCode
                        );
                stringRedisTemplate.expire(redisKey, 60, TimeUnit.SECONDS);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }

    }

    private synchronized String createHtmlMessage(String verificationCode) {
        String htmlStart = "<html>\n" +
                "    <head>\n" +
                "        <base target=\"_blank\" />\n" +
                "        <style type=\"text/css\">::-webkit-scrollbar{ display: none; }</style>\n" +
                "        <style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}</style>\n" +
                "        <style id=\"blockquoteStyle\" type=\"text/css\">blockquote{display:none;}</style>\n" +
                "        <style type=\"text/css\">\n" +
                "            body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\n" +
                "            td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}\n" +
                "            pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\n" +
                "            th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\n" +
                "            img{ border:0}\n" +
                "            header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\n" +
                "            blockquote{margin-right:0px}\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body tabindex=\"0\" role=\"listitem\">\n" +
                "    <table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\">\n" +
                "                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\">\n" +
                "                        <tbody><tr><td width=\"210\"></td></tr></tbody>\n" +
                "                    </table>\n" +
                "                </div>\n" +
                "                <div style=\"width:680px;padding:0 10px;margin:0 auto;\">\n" +
                "                    <div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\">\n" +
                "                        <strong style=\"display:block;margin-bottom:15px;\">尊敬的用户：<span style=\"color:#f60;font-size: 16px;\"></span>您好！</strong>\n" +
                "                        <strong style=\"display:block;margin-bottom:15px;\">\n" +
                "                            您正在进行<span style=\"color: red\"></span>操作，请在验证码输入框中输入：<span style=\"color:#f60;font-size: 24px\">";
        String htmlEnd = "</span>，以完成操作。\n" +
                "                        </strong>\n" +
                "                    </div>\n" +
                "                    <div style=\"margin-bottom:30px;\">\n" +
                "                        <small style=\"display:block;margin-bottom:20px;font-size:12px;\">\n" +
                "                            <p style=\"color:#747474;\">\n" +
                "                                注意：此操作可能会修改您的密码、登录邮箱或绑定手机。如非本人操作，请及时登录并修改密码以保证帐户安全\n" +
                "                                <br>（工作人员不会向你索取此验证码，请勿泄漏！)\n" +
                "                            </p>\n" +
                "                        </small>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div style=\"width:700px;margin:0 auto;\">\n" +
                "                    <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                "                        <p>此为系统邮件，请勿回复<br>\n" +
                "                            请保管好您的邮箱，避免账号被他人盗用\n" +
                "                        </p>\n" +
                "                        <p>Notebook System.(C) 2020 All rights reserved.</p>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "    </body>\n" +
                "</html>";
        return htmlStart + verificationCode + htmlEnd;
    }

    @Override
    public boolean compareVerificationCode(String key, String verificationCode) throws BusinessException {
        try {
            String value = stringRedisTemplate.opsForValue().get(key);
            return value != null && value.equals(verificationCode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.ERROR_VERIFICATION_CODE);
        }
    }
}
