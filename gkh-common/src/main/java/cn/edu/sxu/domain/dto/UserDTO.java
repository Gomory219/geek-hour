package cn.edu.sxu.domain.dto;

import cn.edu.sxu.domain.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String account;
    private String userName;
    private String phone;
    private String avatarImage;
    private UserRole role;
}
