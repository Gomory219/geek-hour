package cn.edu.sxu.gkhcourse.service.impl;

import cn.edu.sxu.gkhcourse.domain.dto.CategoryQueryDTO;
import cn.edu.sxu.gkhcourse.domain.entity.Category;
import cn.edu.sxu.gkhcourse.domain.vo.CategoryVO;
import cn.edu.sxu.gkhcourse.mapper.CategoryMapper;
import cn.edu.sxu.gkhcourse.service.ICategoryService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> listSubCategory(CategoryQueryDTO categoryQueryDTO) {
        if (categoryQueryDTO == null || categoryQueryDTO.getBaseCategoryId() == null) {
            return categoryMapper.getTopCategory();
        }
        return categoryMapper.getSubCategory(categoryQueryDTO.getBaseCategoryId());
    }
}
