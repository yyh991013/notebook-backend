package com.notebook.notebookbackend.utils;

import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.error.EmBusinessErr;

/**
 * @author 22454
 */
public class VerificationUtil {
    private static final char[] CHARS = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z', '0', '1',
            '2', '3', '4', '5', '6', '7', '8',
            '9', '{', '}', '[', ']', '(', ')'
    };
    private static final int VERIFICATION_CODE_LENGTH = 6;


    public synchronized static String buildVerificationCode() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
            int index = (int) (Math.random() * CHARS.length);
            builder.append(CHARS[index]);
        }
        return builder.toString();
    }

    public synchronized static String getVerificationCodeKey(int verificationCodeType, String key) throws BusinessException {
        String prefix = null;
        switch (verificationCodeType) {
            case VerificationCodeType.Registered:
                prefix = RedisConstants.REDIS_REGISTERED_VERIFICATION_CODE_KEY_PREFIX;
                break;
            case VerificationCodeType.UPDATE_PASSWORD:
                prefix = RedisConstants.REDIS_UPDATE_PASSWORD_VERIFICATION_CODE_KEY_PREFIX;
                break;
            default:
                throw new BusinessException(EmBusinessErr.VERIFICATION_TYPE_ERROR);
        }
        return RedisUtil.generatorKey(prefix, key);
    }
}
