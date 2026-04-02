package cn.edu.sxu.gkhinteraction.service;

import cn.edu.sxu.domain.dto.UserDTO;
import cn.edu.sxu.domain.enums.BusinessType;
import cn.edu.sxu.gkh.domain.dto.LikeDetailDTO;
import cn.edu.sxu.gkhinteraction.domain.dto.LikeRequestDTO;
import cn.edu.sxu.gkhinteraction.domain.entity.Like;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 点赞表 服务类
 * </p>
 *
 * @author baomidou
 * @since 2026-04-01
 */
public interface ILikeService extends IService<Like> {

    void like(LikeRequestDTO likeRequestDTO);

    void cancel(LikeRequestDTO likeRequestDTO);

    Map<String, Integer> likeCount(List<String> businessIds, BusinessType bizType);

    List<LikeDetailDTO> likeDetail(List<String> businessIds, BusinessType bizType, UserDTO userDTO);
}
