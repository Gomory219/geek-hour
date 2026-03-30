package cn.edu.sxu.gkhcourse.mapper;

import cn.edu.sxu.gkhcourse.domain.entity.CategoryRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 分类关系表（闭包表，存储树形结构） Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
public interface CategoryRelationMapper extends BaseMapper<CategoryRelation> {

}
