package cn.edu.sxu.gkhcourse.mapper;

import cn.edu.sxu.gkhcourse.domain.entity.Category;
import cn.edu.sxu.gkhcourse.domain.vo.CategoryVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVO> getTopCategory();

    List<CategoryVO> getSubCategory(Integer baseCategoryId);
}
