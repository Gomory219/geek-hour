package cn.edu.sxu.gkhuser.service;

import cn.edu.sxu.gkhuser.domain.dto.UserRegisterDTO;
import cn.edu.sxu.gkhuser.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserService extends IService<User> {
    void register(UserRegisterDTO userRegisterDTO);
}
