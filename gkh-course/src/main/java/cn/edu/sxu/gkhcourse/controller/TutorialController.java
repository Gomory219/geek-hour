package cn.edu.sxu.gkhcourse.controller;

import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import cn.edu.sxu.gkhcourse.domain.vo.TutorialBriefVO;
import cn.edu.sxu.gkhcourse.service.ITutorialService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 教程表（课程章节） 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@RestController
@RequestMapping("/tutorial")
public class TutorialController {
    @Resource
    private ITutorialService tutorialService;

    @GetMapping("/brief")
    public BaseResponse<List<TutorialBriefVO>> getTutorialBrief(String courseId) {
        return ResultUtils.success(tutorialService.getTutorialBrief(courseId));
    }
}
