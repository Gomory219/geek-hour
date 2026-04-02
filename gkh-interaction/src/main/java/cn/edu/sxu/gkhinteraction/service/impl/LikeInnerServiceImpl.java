package cn.edu.sxu.gkhinteraction.service.impl;

import cn.edu.sxu.domain.dto.UserDTO;
import cn.edu.sxu.domain.enums.BusinessType;
import cn.edu.sxu.gkh.domain.dto.LikeDetailDTO;
import cn.edu.sxu.gkh.service.InnerLikeService;
import cn.edu.sxu.gkhinteraction.domain.LikeCountDTO;
import cn.edu.sxu.gkhinteraction.domain.entity.Like;
import cn.edu.sxu.gkhinteraction.mapper.LikeMapper;
import cn.edu.sxu.gkhinteraction.service.ILikeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DubboService
@Slf4j
public class LikeInnerServiceImpl implements InnerLikeService {

    @Resource
    private ILikeService likeService;

    @Override
    public Map<String, Integer> likeCount(List<String> businessIds, BusinessType bizType) {
        return likeService.likeCount(businessIds, bizType);
    }

    @Override
    public List<LikeDetailDTO> likeDetail(List<String> businessIds, BusinessType bizType, UserDTO userDTO) {
        return likeService.likeDetail(businessIds,bizType, userDTO);
    }

    @Override
    public boolean isLiked(String userId, String businessId, BusinessType businessType) {
        LambdaQueryWrapper<Like> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(Like::getUserId, userId);
        lambdaQueryWrapper.eq(Like::getBizId, businessId);
        lambdaQueryWrapper.eq(Like::getBizType, businessType);

        long count = likeService.count(lambdaQueryWrapper);

        return count > 0;

    }

}
