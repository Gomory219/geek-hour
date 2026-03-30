package cn.edu.sxu.gkhcourse.mapper;

import cn.edu.sxu.gkhcourse.domain.entity.Course;
import cn.edu.sxu.gkhcourse.domain.vo.CourseVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVO> listByCategory(Integer categoryId);
}
