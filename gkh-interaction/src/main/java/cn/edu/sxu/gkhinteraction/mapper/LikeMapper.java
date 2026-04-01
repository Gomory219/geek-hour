package cn.edu.sxu.gkhinteraction.mapper;

import cn.edu.sxu.domain.enums.BusinessType;
import cn.edu.sxu.gkh.domain.dto.LikeDetailDTO;
import cn.edu.sxu.gkhinteraction.domain.LikeCountDTO;
import cn.edu.sxu.gkhinteraction.domain.entity.Like;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 点赞表 Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2026-04-01
 */
public interface LikeMapper extends BaseMapper<Like> {

    List<LikeCountDTO> likeCount(List<String> businessIds, BusinessType bizType);

    List<LikeDetailDTO> likeDetail(List<String> businessIds, BusinessType bizType, String userId);
}
