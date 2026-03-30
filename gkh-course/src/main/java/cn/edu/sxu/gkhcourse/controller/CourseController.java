package cn.edu.sxu.gkhcourse.controller;

import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import cn.edu.sxu.gkhcourse.domain.dto.CourseQueryDTO;
import cn.edu.sxu.gkhcourse.domain.vo.CourseVO;
import cn.edu.sxu.gkhcourse.service.ICourseService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private ICourseService courseService;

    @GetMapping("/listByCategory")
    public BaseResponse<List<CourseVO>> listByCategory(CourseQueryDTO courseQueryDTO) {
        return ResultUtils.success(courseService.listByCategory(courseQueryDTO));
    }
}
