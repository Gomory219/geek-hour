package cn.edu.sxu.gkhuser.service.impl;

import cn.edu.sxu.gkh.domain.dto.UserBriefDTO;
import cn.edu.sxu.gkh.service.InnerUserService;
import cn.edu.sxu.gkhuser.domain.entity.User;
import cn.edu.sxu.gkhuser.service.IUserService;
import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private IUserService userService;

    @Override
    public List<UserBriefDTO> listUserBrief(List<String> userIds) {
        List<User> users = userService.listByIds(userIds);
        return BeanUtil.copyToList(users, UserBriefDTO.class);
    }
}
