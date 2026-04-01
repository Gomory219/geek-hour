package cn.edu.sxu.gkhinteraction.service.impl;

import cn.edu.sxu.domain.dto.UserDTO;
import cn.edu.sxu.domain.enums.BusinessType;
import cn.edu.sxu.gkh.domain.dto.LikeDetailDTO;
import cn.edu.sxu.gkh.service.InnerLikeService;
import cn.edu.sxu.gkhinteraction.domain.LikeCountDTO;
import cn.edu.sxu.gkhinteraction.mapper.LikeMapper;
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
    private LikeMapper likeMapper;

    @Override
    public Map<String, Integer> likeCount(List<String> businessIds, BusinessType bizType) {

        List<LikeCountDTO> likeList = likeMapper.likeCount(businessIds, bizType);
        Map<String, Integer> resultMap = likeList.stream().collect(Collectors.toMap(LikeCountDTO::getBizId, LikeCountDTO::getCount));

        return businessIds.stream().collect(Collectors.toMap(
                id -> id,
                id -> resultMap.getOrDefault(id, 0)
        ));
    }

    @Override
    public List<LikeDetailDTO> likeDetail(List<String> businessIds, BusinessType bizType, UserDTO userDTO) {
        List<LikeDetailDTO> likeDetailDTOS = likeMapper.likeDetail(businessIds, bizType, userDTO.getId());
        Map<String, LikeDetailDTO> bizIdDTOMap = likeDetailDTOS.stream().collect(Collectors.toMap(
                LikeDetailDTO::getBusinessId,
                d -> d
        ));
        List<LikeDetailDTO> retList = new ArrayList<>();

        for (String businessId : businessIds) {
            LikeDetailDTO dto = LikeDetailDTO.builder()
                    .businessId(businessId).likeCount(0).isLiked(false).build();

            LikeDetailDTO likeDetailDTO = bizIdDTOMap.get(businessId);
            if (likeDetailDTO != null) {
                dto.setLikeCount(likeDetailDTO.getLikeCount());
                dto.setIsLiked(likeDetailDTO.getIsLiked());
            }
        }

        return retList;
    }


}
