package cn.edu.sxu.gkhinteraction.controller;

import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import cn.edu.sxu.gkhinteraction.domain.dto.LikeRequestDTO;
import cn.edu.sxu.gkhinteraction.service.ILikeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 点赞表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2026-04-01
 */
@RestController
@RequestMapping("/like")
public class LikeController {

    @Resource
    private ILikeService likeService;

    @PostMapping
    public BaseResponse<String> like(@RequestBody LikeRequestDTO likeRequestDTO) {
        likeService.like(likeRequestDTO);
        return ResultUtils.success();
    }

    @PostMapping("/cancel")
    public BaseResponse<String> cancel(@RequestBody LikeRequestDTO likeRequestDTO) {
        likeService.cancel(likeRequestDTO);
        return ResultUtils.success();
    }

}






















