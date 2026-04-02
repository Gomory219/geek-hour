package cn.edu.sxu.gkhcourse.domain.entity;

import cn.edu.sxu.gkhcourse.domain.enums.CourseLevel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@Getter
@Setter
@ToString
@TableName("tb_course")
public class Course implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    private String id;

    /**
     * 分类ID，关联tb_category.id
     */
    private Integer categoryId;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程价格
     */
    private BigDecimal price;

    /**
     * 课程封面图片地址
     */
    private String coverImage;

    /**
     * 课程简介
     */
    private String profile;

    /**
     * 课程详情介绍
     */
    private String introduction;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 是否免费：true=是，false=否
     */
    private Boolean isFree;

    /**
     * 是否VIP专属：true=是，false=否
     */
    private Boolean isVip;

    /**
     * 是否推荐/精选：true=是，false=否
     */
    private Boolean isFeature;

    /**
     * 课程难度：beginner-入门，intermediate-进阶，advanced-高级
     */
    private CourseLevel level;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间，自动更新
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除（软删除）：true=已删除
     */
    private Boolean isDeleted;
}
