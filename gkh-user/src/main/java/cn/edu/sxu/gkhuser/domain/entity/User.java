package cn.edu.sxu.gkhuser.domain.entity;

import cn.edu.sxu.domain.enums.UserRole;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author baomidou
 * @since 2026-03-27
 */
@TableName("tb_user")
@Builder
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID，主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 账号，唯一
     */
    private String account;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码，bcrypt哈希存储
     */
    private String password;

    /**
     * 头像URL
     */
    private String avatarImage;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 个人简介
     */
    private String profile;

    /**
     * 角色：user-普通用户，admin-管理员，vip-VIP用户
     */
    private UserRole role;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间，自动更新
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除，默认False
     */
    private Boolean isDeleted;


}
