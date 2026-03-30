package cn.edu.sxu.gkhcourse.service;

import cn.edu.sxu.gkhcourse.domain.dto.CategoryQueryDTO;
import cn.edu.sxu.gkhcourse.domain.entity.Category;
import cn.edu.sxu.gkhcourse.domain.vo.CategoryVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
public interface ICategoryService extends IService<Category> {

    List<CategoryVO> listSubCategory(CategoryQueryDTO categoryQueryDTO);
}
