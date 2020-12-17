package com.notebook.notebookbackend.utils;


import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.error.EmBusinessErr;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author 22454
 */
public final class Md5Util {
    public static String getMd5(String str) throws BusinessException {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5 = new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            return fillMd5(md5);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "md5加密失败");
        }
    }

    /**
     * 补齐32位
     */
    private static String fillMd5(String md5) {
        return md5.length() == 32 ? md5 : fillMd5("0" + md5);
    }

    public static void main(String[] args) throws BusinessException {
        System.out.println(getMd5("123456"));
    }
}
