package cn.edu.sxu.common;


import cn.edu.sxu.exception.BusinessException;
import cn.edu.sxu.exception.ErrorCode;

public class ThrowUtils {
    public static void throwIf(boolean condition, RuntimeException e) {
        if (condition) { throw e; }
    }

    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        if (condition) { throw new BusinessException(errorCode, message); }
    }
}
