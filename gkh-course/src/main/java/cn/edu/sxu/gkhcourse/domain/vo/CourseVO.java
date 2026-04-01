package cn.edu.sxu.gkhcourse.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CourseVO {
    private String id;
    private Integer categoryId;
    private String name;
    private BigDecimal price;
    private String coverImage;
    private Integer likes;
    private Boolean isLiked;
    private Integer viewCount;
    private Boolean isFree;
    private Boolean isVip;
    private Boolean isFeature;
    private String level;
    private LocalDateTime createTime;
}
