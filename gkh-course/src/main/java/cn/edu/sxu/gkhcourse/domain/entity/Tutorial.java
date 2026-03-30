package cn.edu.sxu.gkhcourse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 教程表（课程章节）
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@Getter
@Setter
@ToString
@TableName("tb_tutorial")
public class Tutorial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教程ID，主键
     */
    private String id;

    /**
     * 教程名称
     */
    private String name;

    /**
     * 教程类型：video-视频教程，article-文章教程
     */
    private String type;

    /**
     * 教程内容（markdown格式）
     */
    private String content;

    /**
     * 视频地址URL
     */
    private String videoUrl;

    /**
     * 父教程ID，用于构建章节层级结构
     */
    private String parentTutorialId;

    /**
     * 所属课程ID，关联tb_course.id
     */
    private String courseId;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 作者ID，关联tb_user.id
     */
    private String authorId;

    /**
     * 是否试看：true=可试看，false=需购买/VIP
     */
    private Boolean isTrail;

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
