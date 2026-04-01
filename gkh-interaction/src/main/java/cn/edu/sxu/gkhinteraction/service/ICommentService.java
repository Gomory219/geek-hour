package cn.edu.sxu.gkhinteraction.service;

import cn.edu.sxu.common.RollQueryResponse;
import cn.edu.sxu.gkhinteraction.domain.dto.CommentQueryDTO;
import cn.edu.sxu.gkhinteraction.domain.dto.CommentRequestDTO;
import cn.edu.sxu.gkhinteraction.domain.entity.Comment;
import cn.edu.sxu.gkhinteraction.domain.vo.CommentVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author baomidou
 * @since 2026-04-01
 */
public interface ICommentService extends IService<Comment> {

    void publishComment(CommentRequestDTO commentRequestDTO);

    RollQueryResponse<CommentVO> rollQuery(CommentQueryDTO commentQueryDTO);
}
