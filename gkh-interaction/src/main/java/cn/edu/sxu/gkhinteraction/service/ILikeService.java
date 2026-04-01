package cn.edu.sxu.gkhinteraction.service;

import cn.edu.sxu.gkhinteraction.domain.dto.LikeRequestDTO;
import cn.edu.sxu.gkhinteraction.domain.entity.Like;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
