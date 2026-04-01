package cn.edu.sxu.utils;

import cn.hutool.core.lang.generator.SnowflakeGenerator;

public class IdUtils {

    private static final SnowflakeGenerator generator = new SnowflakeGenerator();

    public static String generateSnowflakeId() {
        return String.valueOf(generator.next());
    }
}
