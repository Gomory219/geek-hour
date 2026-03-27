package cn.edu.sxu.gkhuser.controller;


import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping
    public BaseResponse<String> health() {
        return ResultUtils.success("gkh-user health");
    }
}
