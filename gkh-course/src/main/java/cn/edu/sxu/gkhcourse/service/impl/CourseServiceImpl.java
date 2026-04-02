package cn.edu.sxu.gkhcourse.service.impl;

import cn.edu.sxu.common.PageResult;
import cn.edu.sxu.gkhcourse.domain.dto.CoursePageQueryDTO;
import cn.edu.sxu.gkhcourse.domain.dto.CourseQueryDTO;
import cn.edu.sxu.gkhcourse.domain.entity.Course;
import cn.edu.sxu.gkhcourse.domain.vo.CourseDetailVO;
import cn.edu.sxu.gkhcourse.domain.vo.CourseVO;
import cn.edu.sxu.gkhcourse.mapper.CourseMapper;
import cn.edu.sxu.gkhcourse.service.ICourseService;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Override
    public List<CourseVO> listByCategory(CourseQueryDTO courseQueryDTO) {
        return baseMapper.listByCategory(courseQueryDTO.getCategoryId());
    }

    @Override
    public PageResult<CourseVO> pageQuery(CoursePageQueryDTO coursePageQueryDTO) {
        return null;
    }

    @Override
    public CourseDetailVO detail(String courseId) {
        Course course = getById(courseId);
        return BeanUtil.copyProperties(course, CourseDetailVO.class);
    }
}
