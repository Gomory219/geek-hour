package cn.edu.sxu.gkhinteraction.domain.entity;

import cn.edu.sxu.domain.enums.BusinessType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author baomidou
 * @since 2026-04-01
 */
@Getter
@Setter
@ToString
@TableName(value = "tb_comment", autoResultMap = true)
@Builder
public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评论ID，主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 评论用户ID，关联tb_user.id
     */
    private String userId;

    /**
     * 业务类型：course-课程，tutorial-教程
     */
    private BusinessType bizType;

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


    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> imageUrls;
}
