package cn.edu.sxu.gkhcourse.service.impl;

import cn.edu.sxu.gkhcourse.domain.entity.CategoryRelation;
import cn.edu.sxu.gkhcourse.mapper.CategoryRelationMapper;
import cn.edu.sxu.gkhcourse.service.ICategoryRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类关系表（闭包表，存储树形结构） 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@Service
public class CategoryRelationServiceImpl extends ServiceImpl<CategoryRelationMapper, CategoryRelation> implements ICategoryRelationService {

}
