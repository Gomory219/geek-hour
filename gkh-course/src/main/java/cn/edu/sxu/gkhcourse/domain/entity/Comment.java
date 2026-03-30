package cn.edu.sxu.gkhcourse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@Getter
@Setter
@ToString
@TableName("tb_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID，主键
     */
    private String id;

    /**
     * 评论用户ID，关联tb_user.id
     */
    private String userId;

    /**
     * 业务类型：course-课程，tutorial-教程
     */
    private String bizType;

    /**
     * 业务对象ID
     */
    private String bizId;

    /**
     * 父评论ID（用于回复功能）
     */
    private String parentId;

    /**
     * 被回复用户ID，关联tb_user.id
     */
    private String replyUserId;

    /**
     * 会话ID（同一对话的评论共享此ID）
     */
    private String conversationId;

    /**
     * 内容是否为markdown格式：true=是，false=否
     */
    private Boolean isMarkdown;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 创建时间（评论时间）
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
