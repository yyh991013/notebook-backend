package com.notebook.notebookbackend.utils;

/**
 * @author 22454
 */
public class RedisUtil {
    public static synchronized String generatorKey(String prefix, String key) {
        return prefix + key;
    }

}
