package cn.edu.sxu.gkhinteraction.domain.entity;

import cn.edu.sxu.domain.enums.BusinessType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 点赞表
 * </p>
 *
 * @author baomidou
 * @since 2026-04-01
 */
@Getter
@Setter
@ToString
@TableName("tb_like")
@Builder
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞记录ID，主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户ID，关联tb_user.id
     */
    private String userId;

    /**
     * 业务类型：course-课程，tutorial-教程，comment-评论
     */
    private BusinessType bizType;

    /**
     * 业务对象ID
     */
    private String bizId;

    /**
     * 创建时间（点赞时间）
     */
    private LocalDateTime createTime;

    /**
     * 更新时间，自动更新
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除（软删除）：true=已取消点赞
     */
    private Boolean isDeleted;
}
