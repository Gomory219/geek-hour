package cn.edu.sxu.utils;

import cn.edu.sxu.common.ThrowUtils;
import cn.edu.sxu.domain.dto.UserDTO;
import cn.edu.sxu.exception.BusinessException;
import cn.edu.sxu.exception.ErrorCode;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuthUtils {
    private static final String SECRET = "gkh-secret";
    private static final byte[] key = SECRET.getBytes();

    public static String generateToken(UserDTO userDTO) {
        return JWT.create()
                .setHeader("alg", "HS256")
                .setHeader("typ", "JWT")
                .setPayload("user", JSONUtil.toJsonStr(userDTO))
                .setKey(key)
                .sign();
    }

    public static UserDTO loginUser() {
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        ThrowUtils.throwIf(requestAttributes == null, ErrorCode.NO_AUTH_ERROR, "用户未登录");
        HttpServletRequest request = requestAttributes.getRequest();
        return getLoginUserFromRequest(request);
    }

    public static UserDTO parseUserDTOFromToken(String token) {
        boolean valid = JWTUtil.verify(token, key);
        if (!valid) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "认证失败");
        }
        JWT jwt = JWTUtil.parseToken(token);
        String userJson = (String) jwt.getPayload("user");
        return JSONUtil.toBean(userJson, UserDTO.class);
    }

    public static UserDTO getLoginUserFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        ThrowUtils.throwIf(StrUtil.isBlank(token), ErrorCode.NO_AUTH_ERROR, "用户未登录");

        return parseUserDTOFromToken(token);
    }

}
