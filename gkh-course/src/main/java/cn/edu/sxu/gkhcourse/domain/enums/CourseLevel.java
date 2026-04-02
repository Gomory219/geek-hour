package cn.edu.sxu.gkhcourse.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CourseLevel {
    JUNIOR("入门"),
    MIDDLE("进阶"),
    SENIOR("高级");

    private final String desc;

    CourseLevel(String desc) {
        this.desc = desc;
    }

    @JsonValue
    public String getDesc() {
        return this.desc;
    }
}
