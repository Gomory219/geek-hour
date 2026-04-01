package cn.edu.sxu.gkhinteraction.service.impl;

import cn.edu.sxu.common.RollQueryResponse;
import cn.edu.sxu.common.ThrowUtils;
import cn.edu.sxu.domain.dto.UserDTO;
import cn.edu.sxu.exception.BusinessException;
import cn.edu.sxu.exception.ErrorCode;
import cn.edu.sxu.gkh.domain.dto.UserBriefDTO;
import cn.edu.sxu.gkh.service.InnerUserService;
import cn.edu.sxu.gkhinteraction.domain.dto.CommentQueryDTO;
import cn.edu.sxu.gkhinteraction.domain.dto.CommentRequestDTO;
import cn.edu.sxu.gkhinteraction.domain.entity.Comment;
import cn.edu.sxu.gkhinteraction.domain.vo.CommentVO;
import cn.edu.sxu.gkhinteraction.mapper.CommentMapper;
import cn.edu.sxu.gkhinteraction.service.ICommentService;
import cn.edu.sxu.gkhinteraction.service.ILikeService;
import cn.edu.sxu.utils.AuthUtils;
import cn.edu.sxu.utils.IdUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import kotlin.jvm.internal.Lambda;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.edu.sxu.common.RollQueryRequest.DEFAULT_RECORD_SIZE;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-04-01
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @DubboReference
    private InnerUserService userService;

    @Resource
    private ILikeService likeService;


    /**
     * 发布评论
     * 评论分为三类：
     * 1. 顶级评论（parientId为空）
     * 2. 顶级评论的回复（parentId不为空，targetUserId为空，conversationId为空）
     * 3. 回复的回复（parentId和conversationUserId不为空）
     *
     * @param commentRequestDTO 评论请求DTO
     */
    @Override
    public void publishComment(CommentRequestDTO commentRequestDTO) {

        UserDTO loginUser = AuthUtils.loginUser();

        Comment comment = Comment.builder()
                .userId(loginUser.getId())
                .bizType(commentRequestDTO.getBusinessType())
                .bizId(commentRequestDTO.getBusinessId())
//                .parentId()
//                .replyUserId()
//                .conversationId()
                .isMarkdown(commentRequestDTO.getIsMarkdown())
                .content(commentRequestDTO.getContent())
                .likes(0)
                .imageUrls(commentRequestDTO.getImageUrls())
                .build();

        // 如果父级评论为空，那么这条就是顶级评论
        if (StrUtil.isBlank(commentRequestDTO.getParentId())) {
            save(comment);
            return;
        }
        // 如果父级评论不为空，那么这条就是评论的回复
        // 这时候判断是顶级回复，还是回复回复的回复
        // replyUserId为空，那么这条就是顶级回复
        if (StrUtil.isBlank(commentRequestDTO.getReplyUserId())) {
            comment.setParentId(commentRequestDTO.getParentId());
            comment.setConversationId(IdUtils.generateSnowflakeId());
            save(comment);
            return;
        }
        // 这时候，就应该是回复回复的回复了
        // 需要查询目标回复实体，并拿到对话树的ID
        if (StrUtil.isBlank(commentRequestDTO.getTargetReplyId())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }

        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getId, commentRequestDTO.getTargetReplyId());

        Comment targetComment = getOne(lambdaQueryWrapper);
        if (targetComment == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "目标回复不存在");
        }

        comment.setParentId(commentRequestDTO.getParentId());
        comment.setReplyUserId(commentRequestDTO.getReplyUserId());
        comment.setConversationId(targetComment.getConversationId());

        save(comment);
    }

    @Override
    public RollQueryResponse<CommentVO> rollQuery(CommentQueryDTO commentQueryDTO) {
        String sortField = commentQueryDTO.getSortField();
        ThrowUtils.throwIf(StrUtil.isBlank(sortField), ErrorCode.PARAMS_ERROR, "参数错误");
        List<Comment> comments = switch (sortField) {
            case "default" -> {
                yield null;
            }
            case "createTime" -> {
                LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();

                lambdaQueryWrapper.eq(Comment::getBizId, commentQueryDTO.getBusinessId());
                lambdaQueryWrapper.eq(Comment::getBizType, commentQueryDTO.getBusinessType());
                lambdaQueryWrapper.isNull(Comment::getParentId);
                lambdaQueryWrapper.lt(StrUtil.isNotBlank(commentQueryDTO.getLastId()), Comment::getId, commentQueryDTO.getLastId());
                lambdaQueryWrapper.orderByDesc(Comment::getCreateTime);
                lambdaQueryWrapper.last("LIMIT " + DEFAULT_RECORD_SIZE);

                yield list(lambdaQueryWrapper);
            }
            default -> {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
            }
        };

        // 附加用户信息
        List<String> userIds = comments.stream().map(Comment::getUserId).toList();
        List<UserBriefDTO> userBriefDTOS = userService.listUserBrief(userIds);
        Map<String, UserBriefDTO> userBriefDTOMap = userBriefDTOS.stream().collect(Collectors.toMap(UserBriefDTO::getId, userBriefDTO -> userBriefDTO));

        List<CommentVO> commentVOs = BeanUtil.copyToList(comments, CommentVO.class);
        for (CommentVO commentVO : commentVOs) {
            UserBriefDTO userBriefDTO = userBriefDTOMap.get(commentVO.getUserId());
            if (userBriefDTO == null) {
                commentVO.setUserName("无名");
                continue;
            }
            commentVO.setUserName(userBriefDTO.getUserName());
            commentVO.setUserAvatar(userBriefDTO.getAvatarImage());
        }

        // 点赞评论信息
//        List<>


        return RollQueryResponse.fromData(commentVOs);
    }
}



















