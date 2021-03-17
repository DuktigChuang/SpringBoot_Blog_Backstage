package com.duktig.springboot.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/*
 * @Description: 登录或注册成功后,生成保持用户登录状态会话token值
 * @Creator: flanderschuang
 * @Date: 2021/3/16 12:24 下午
 * * @param src:为用户最新一次登录时的now()+user.id+random(4)
 * @return
 */
public class SystemUtil {
    public static String getToken(String src) {
        if (null == src || "".equals(src)) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return null;
        }

    }
}
