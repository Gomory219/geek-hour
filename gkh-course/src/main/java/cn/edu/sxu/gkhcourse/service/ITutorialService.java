package cn.edu.sxu.gkhcourse.service;

import cn.edu.sxu.gkhcourse.domain.entity.Tutorial;
import cn.edu.sxu.gkhcourse.domain.vo.TutorialBriefVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 教程表（课程章节） 服务类
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
public interface ITutorialService extends IService<Tutorial> {

    List<TutorialBriefVO> getTutorialBrief(String courseId);
}
