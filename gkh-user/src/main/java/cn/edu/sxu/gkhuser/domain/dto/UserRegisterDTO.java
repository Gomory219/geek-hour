package cn.edu.sxu.gkhuser.domain.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String nickname;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    private String avatar;


}
