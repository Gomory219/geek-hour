package cn.edu.sxu.gkhinteraction.service.impl;

import cn.edu.sxu.common.ThrowUtils;
import cn.edu.sxu.domain.dto.UserDTO;
import cn.edu.sxu.exception.ErrorCode;
import cn.edu.sxu.gkhinteraction.domain.dto.LikeRequestDTO;
import cn.edu.sxu.gkhinteraction.domain.entity.Like;
import cn.edu.sxu.gkhinteraction.mapper.LikeMapper;
import cn.edu.sxu.gkhinteraction.service.ILikeService;
import cn.edu.sxu.utils.AuthUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kotlin.jvm.internal.Lambda;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 点赞表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-04-01
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements ILikeService {

    @Override
    public void like(LikeRequestDTO likeRequestDTO) {
        UserDTO userDTO = AuthUtils.loginUser();

        LambdaQueryWrapper<Like> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Like::getUserId, userDTO.getId());
        lambdaQueryWrapper.eq(Like::getBizType, likeRequestDTO.getBusinessType());
        lambdaQueryWrapper.eq(Like::getBizId, likeRequestDTO.getBusinessId());

        List<Like> likes = list(lambdaQueryWrapper);
        for (Like like : likes) {
            if (!like.getIsDeleted()) {
                throw new IllegalArgumentException("已点赞");
            }
        }

        Like like = Like.builder()
                .bizId(likeRequestDTO.getBusinessId())
                .bizType(likeRequestDTO.getBusinessType())
                .userId(userDTO.getId())
                .build();

        save(like);
    }

    @Override
    public void cancel(LikeRequestDTO likeRequestDTO) {
        UserDTO userDTO = AuthUtils.loginUser();

        LambdaUpdateWrapper<Like> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Like::getUserId, userDTO.getId());
        lambdaUpdateWrapper.eq(Like::getBizType, likeRequestDTO.getBusinessType());
        lambdaUpdateWrapper.eq(Like::getBizId, likeRequestDTO.getBusinessId());
        lambdaUpdateWrapper.set(Like::getIsDeleted, true);

        boolean update = update(lambdaUpdateWrapper);

        ThrowUtils.throwIf(!update, ErrorCode.OPERATION_ERROR,"取消点赞失败");
    }
}
