package cn.edu.sxu.gkhcourse.service.impl;

import cn.edu.sxu.common.ThrowUtils;
import cn.edu.sxu.domain.dto.UserDTO;
import cn.edu.sxu.domain.enums.BusinessType;
import cn.edu.sxu.exception.ErrorCode;
import cn.edu.sxu.gkh.domain.dto.UserBriefDTO;
import cn.edu.sxu.gkh.service.InnerLikeService;
import cn.edu.sxu.gkh.service.InnerUserService;
import cn.edu.sxu.gkhcourse.domain.entity.Tutorial;
import cn.edu.sxu.gkhcourse.domain.enums.ProcessType;
import cn.edu.sxu.gkhcourse.domain.vo.TutorialBriefVO;
import cn.edu.sxu.gkhcourse.domain.vo.TutorialDetailVO;
import cn.edu.sxu.gkhcourse.mapper.TutorialMapper;
import cn.edu.sxu.gkhcourse.service.ITutorialService;
import cn.edu.sxu.utils.AuthUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboReference;
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

    @DubboReference
    private InnerUserService userService;

    @DubboReference
    private InnerLikeService likeService;

    @Override
    public List<TutorialBriefVO> getTutorialBrief(String courseId) {
        LambdaQueryWrapper<Tutorial> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tutorial::getCourseId, courseId)
                .orderByAsc(Tutorial::getCreateTime);
        List<Tutorial> tutorials = baseMapper.selectList(queryWrapper);

        List<TutorialBriefVO> tutorialBriefVOS = BeanUtil.copyToList(tutorials, TutorialBriefVO.class);

        // TODO 设置教程进度
        for (TutorialBriefVO tutorialBriefVO : tutorialBriefVOS) {
            tutorialBriefVO.setProcessType(ProcessType.UNLEARNED);
        }

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

    @Override
    public TutorialDetailVO getTutorialDetail(String tutorialId) {
        UserDTO userDTO = AuthUtils.loginUser();

        Tutorial tutorial = getById(tutorialId);
        ThrowUtils.throwIf(tutorial == null, ErrorCode.NOT_FOUND_ERROR, "相关教程不存在");
        TutorialDetailVO tutorialDetailVO = BeanUtil.copyProperties(tutorial, TutorialDetailVO.class);

        String authorId = tutorialDetailVO.getAuthorId();
        UserBriefDTO userBriefDTO = userService.userBrief(authorId);

        tutorialDetailVO.setAuthorName(userBriefDTO.getUserName());
        tutorialDetailVO.setAuthorAvatarUrl(userBriefDTO.getAvatarImage());

        boolean isLiked = likeService.isLiked(userDTO.getId(), tutorialId, BusinessType.TUTORIAL);
        tutorialDetailVO.setLiked(isLiked);

        // TODO 学习进度

        return tutorialDetailVO;
    }

}
