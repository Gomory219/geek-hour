package cn.edu.sxu.gkh.service;

import cn.edu.sxu.gkh.domain.dto.UserBriefDTO;

import java.util.List;

public interface InnerUserService {

    List<UserBriefDTO> listUserBrief(List<String> userIds);

}
