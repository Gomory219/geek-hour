package cn.edu.sxu.gkhcourse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 分类关系表（闭包表，存储树形结构）
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@Getter
@Setter
@ToString
@TableName("tb_category_relation")
public class CategoryRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类关系ID
     */
    private String id;

    /**
     * 祖先节点ID，关联tb_category.id
     */
    private Long ancestor;

    /**
     * 子孙节点ID，关联tb_category.id
     */
    private Long descendant;

    /**
     * 层级深度：0表示自己，1表示直接子节点，以此类推
     */
    private Integer depth;

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
