package cn.edu.sxu.gkhcourse.controller;

import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import cn.edu.sxu.gkhcourse.domain.dto.CategoryQueryDTO;
import cn.edu.sxu.gkhcourse.domain.vo.CategoryVO;
import cn.edu.sxu.gkhcourse.service.ICategoryService;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Resource
    private ICategoryService categoryService;

    @GetMapping("/list")
    public BaseResponse<List<CategoryVO>> list(CategoryQueryDTO categoryQueryDTO) {
        log.info("类别查询接口: {}", JSONUtil.toJsonPrettyStr(categoryQueryDTO));
        return ResultUtils.success(categoryService.listSubCategory(categoryQueryDTO));
    }
}
