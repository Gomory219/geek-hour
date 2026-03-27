package cn.edu.sxu.gkhgateway.controller;


import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public Mono<String> health(){
        return Mono.just("gkh-gateway health");
    }

}
