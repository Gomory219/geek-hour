package cn.edu.sxu.gkhuser.controller;

import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import cn.edu.sxu.gkhuser.domain.dto.UserLoginDTO;
import cn.edu.sxu.gkhuser.domain.dto.UserRegisterDTO;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.xml.core.Validate;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2026-03-27
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody @Validate UserRegisterDTO userRegisterDTO){
        return ResultUtils.success();
    }

    @GetMapping("/login")
    public BaseResponse<String> login(UserLoginDTO userLoginDTO){
        log.info("login: {}", JSONUtil.toJsonStr(userLoginDTO));
        return ResultUtils.success();
    }
}
