package cn.edu.sxu.gkhuser.controller;

import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import cn.edu.sxu.domain.dto.UserDTO;
import cn.edu.sxu.gkhuser.domain.dto.UserLoginDTO;
import cn.edu.sxu.gkhuser.domain.dto.UserRegisterDTO;
import cn.edu.sxu.gkhuser.domain.entity.User;
import cn.edu.sxu.gkhuser.service.IUserService;
import cn.edu.sxu.utils.AuthUtils;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody @Validate UserRegisterDTO userRegisterDTO) {
        log.info("register: {}", JSONUtil.toJsonStr(userRegisterDTO));
        userService.register(userRegisterDTO);
        return ResultUtils.success();
    }

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody @Validate UserLoginDTO userLoginDTO, HttpServletResponse response) {
        log.info("login: {}", JSONUtil.toJsonStr(userLoginDTO));
        userService.login(userLoginDTO, response);
        return ResultUtils.success();
    }

    @GetMapping("/loginUser")
    public BaseResponse<UserDTO> loginUser(HttpServletRequest request) {
        UserDTO userDTO = AuthUtils.getLoginUserFromRequest(request);
        return ResultUtils.success(userDTO);
    }

    @GetMapping("/detail")
    public BaseResponse<User> userDetail(HttpServletRequest request) {
        UserDTO userDTO = AuthUtils.getLoginUserFromRequest(request);
        User user = userService.getById(userDTO.getId());
        return ResultUtils.success(user);
    }
}
