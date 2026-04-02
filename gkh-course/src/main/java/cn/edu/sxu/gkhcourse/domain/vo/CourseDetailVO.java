package cn.edu.sxu.gkhcourse.domain.vo;

import cn.edu.sxu.gkhcourse.domain.enums.CourseLevel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CourseDetailVO {
    private String id;
    private String name;
    private BigDecimal price;
    private String coverImage;
    private String profile;
    private String introduction;
    private Integer likes;
    private Integer viewCount;
    private Boolean isFree;
    private Boolean isVip;
    private Boolean isFeature;
    private CourseLevel level;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
