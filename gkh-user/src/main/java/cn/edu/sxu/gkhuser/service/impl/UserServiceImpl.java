package cn.edu.sxu.gkhuser.service.impl;

import cn.edu.sxu.common.ThrowUtils;
import cn.edu.sxu.domain.enums.UserRole;
import cn.edu.sxu.exception.BusinessException;
import cn.edu.sxu.exception.ErrorCode;
import cn.edu.sxu.domain.dto.UserDTO;
import cn.edu.sxu.gkhuser.domain.dto.UserLoginDTO;
import cn.edu.sxu.gkhuser.domain.dto.UserRegisterDTO;
import cn.edu.sxu.gkhuser.domain.entity.User;
import cn.edu.sxu.gkhuser.mapper.UserMapper;
import cn.edu.sxu.gkhuser.service.IUserService;
import cn.edu.sxu.utils.AuthUtils;
import cn.edu.sxu.utils.EncryptUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getPhone, userRegisterDTO.getPhone());
        ThrowUtils.throwIf(count(qw) != 0, ErrorCode.PARAMS_ERROR, "手机号已存在");

        String encryptedPassword = EncryptUtils.encryptPassword(userRegisterDTO.getPassword());
        String account = generateAccount();

        User user = User.builder()
                .account(account)
                .userName(userRegisterDTO.getUsername())
                .password(encryptedPassword)
                .phone(userRegisterDTO.getPhone())
                .avatarImage(userRegisterDTO.getAvatar())
                .role(UserRole.USER)
                .build();

        try {
            save(user);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "数据库插入失败，请重试");
        }
    }

    @Override
    public void login(UserLoginDTO userLoginDTO, HttpServletResponse response) {
        String account = userLoginDTO.getAccount();
        String phone = userLoginDTO.getPhone();
        String password = userLoginDTO.getPassword();
        String encryptedPassword = EncryptUtils.encryptPassword(password);

        ThrowUtils.throwIf(StrUtil.isAllBlank(account, phone), ErrorCode.PARAMS_ERROR, "账号或手机号不能为空");

        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();

        qw.eq(StrUtil.isNotBlank(account), User::getAccount, account);
        qw.eq(StrUtil.isNotBlank(phone), User::getPhone, phone);
        qw.eq(User::getPassword, encryptedPassword);

        User user = getOne(qw);
        ThrowUtils.throwIf(user == null, ErrorCode.PARAMS_ERROR, "账号或手机号或密码错误");

        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);

        String token = AuthUtils.generateToken(userDTO);

        response.addCookie(new Cookie("token", token));
    }

    private String generateAccount() {
        return RandomUtil.randomNumbers(10);
    }

    public static void main(String[] args) {
      System.out.println(EncryptUtils.encryptPassword("123456"));
    }
}
