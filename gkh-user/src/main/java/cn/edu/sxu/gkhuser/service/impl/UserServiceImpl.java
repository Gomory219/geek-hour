package cn.edu.sxu.gkhuser.service.impl;

import cn.edu.sxu.common.ThrowUtils;
import cn.edu.sxu.exception.ErrorCode;
import cn.edu.sxu.gkhuser.domain.dto.UserRegisterDTO;
import cn.edu.sxu.gkhuser.domain.entity.User;
import cn.edu.sxu.gkhuser.mapper.UserMapper;
import cn.edu.sxu.gkhuser.service.IUserService;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2026-03-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        ThrowUtils.throwIf(!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword()), ErrorCode.PARAMS_ERROR, "两次密码不一致");

//        DigestUtil.md5Hex(userRegisterDTO.get)
    }
}
