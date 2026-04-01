package cn.edu.sxu.gkhinteraction.controller;

import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import cn.edu.sxu.common.RollQueryResponse;
import cn.edu.sxu.common.ThrowUtils;
import cn.edu.sxu.exception.ErrorCode;
import cn.edu.sxu.gkhinteraction.domain.dto.CommentQueryDTO;
import cn.edu.sxu.gkhinteraction.domain.dto.CommentRequestDTO;
import cn.edu.sxu.gkhinteraction.domain.vo.CommentVO;
import cn.edu.sxu.gkhinteraction.mapper.CommentMapper;
import cn.edu.sxu.gkhinteraction.service.ICommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2026-04-01
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    @PostMapping("/publish")
    public BaseResponse<String> publish(@RequestBody CommentRequestDTO commentRequestDTO) {
        commentService.publishComment(commentRequestDTO);
        return ResultUtils.success();
    }

    @PostMapping("/delete/{id}")
    public BaseResponse<String> delete(@PathVariable String id) {
        boolean b = commentService.removeById(id);
        ThrowUtils.throwIf(!b, ErrorCode.PARAMS_ERROR, "评论不存在");
        return ResultUtils.success();
    }

    @GetMapping("/query")
    public BaseResponse<RollQueryResponse<CommentVO>> query(@RequestBody CommentQueryDTO commentQueryDTO) {
        return ResultUtils.success(commentService.rollQuery(commentQueryDTO));
    }
}
















