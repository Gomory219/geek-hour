package cn.edu.sxu.utils;

import cn.hutool.crypto.digest.DigestUtil;

public class EncryptUtils {
    public static String encryptPassword(String password) {
        return DigestUtil.md5Hex(password);
    }
}
