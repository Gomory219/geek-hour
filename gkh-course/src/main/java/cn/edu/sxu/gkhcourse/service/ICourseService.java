package cn.edu.sxu.gkhcourse.service;

import cn.edu.sxu.gkhcourse.domain.dto.CourseQueryDTO;
import cn.edu.sxu.gkhcourse.domain.entity.Course;
import cn.edu.sxu.gkhcourse.domain.vo.CourseVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程表 服务类
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
public interface ICourseService extends IService<Course> {

    List<CourseVO> listByCategory(CourseQueryDTO courseQueryDTO);
}
