package cn.edu.sxu.gkhcourse.service.impl;

import cn.edu.sxu.gkhcourse.domain.entity.Tutorial;
import cn.edu.sxu.gkhcourse.domain.vo.TutorialBriefVO;
import cn.edu.sxu.gkhcourse.mapper.TutorialMapper;
import cn.edu.sxu.gkhcourse.service.ITutorialService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Override
    public List<TutorialBriefVO> getTutorialBrief(String courseId) {
        LambdaQueryWrapper<Tutorial> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tutorial::getCourseId, courseId)
                .orderByAsc(Tutorial::getCreateTime);
        List<Tutorial> tutorials = baseMapper.selectList(queryWrapper);

        List<TutorialBriefVO> tutorialBriefVOS = BeanUtil.copyToList(tutorials, TutorialBriefVO.class);
        Map<String, Integer> idIndexMap =
                IntStream.range(0, tutorialBriefVOS.size())
                .boxed()
                .collect(Collectors.toMap(
                        index -> {
                            return tutorialBriefVOS.get(index).getId();
                        },
                        index -> index
                ));

        for (TutorialBriefVO vo : tutorialBriefVOS) {
            if (StrUtil.isBlank(vo.getParentTutorialId())) {
                continue;
            }
            int parentIndex = idIndexMap.get(vo.getParentTutorialId());
            TutorialBriefVO tutorialBriefVO = tutorialBriefVOS.get(parentIndex);
            if (tutorialBriefVO.getChildren() == null) {
                tutorialBriefVO.setChildren(new ArrayList<>());
            }
            tutorialBriefVO.getChildren().add(vo);
        }
        return tutorialBriefVOS.stream().
                filter(vo -> StrUtil.isBlank(vo.getParentTutorialId()))
                .collect(Collectors.toList());
    }

}
