package cn.edu.sxu.gkh.service;

import cn.edu.sxu.domain.dto.UserDTO;
import cn.edu.sxu.domain.enums.BusinessType;
import cn.edu.sxu.gkh.domain.dto.LikeDetailDTO;

import java.util.List;
import java.util.Map;

public interface InnerLikeService {

    Map<String, Integer> likeCount(List<String> businessIds, BusinessType bizType);

    List<LikeDetailDTO> likeDetail(List<String> businessIds, BusinessType bizType, UserDTO userDTO);

    boolean isLiked(String userId, String businessId, BusinessType businessType);
}
