package cn.edu.sxu.gkhcourse.service.impl;

import cn.edu.sxu.gkhcourse.domain.entity.Tutorial;
import cn.edu.sxu.gkhcourse.mapper.TutorialMapper;
import cn.edu.sxu.gkhcourse.service.ITutorialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教程表（课程章节） 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-03-30
 */
@Service
public class TutorialServiceImpl extends ServiceImpl<TutorialMapper, Tutorial> implements ITutorialService {

}
