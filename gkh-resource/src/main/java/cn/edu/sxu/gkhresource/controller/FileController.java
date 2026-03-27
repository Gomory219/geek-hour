package cn.edu.sxu.gkhresource.controller;

import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import org.simpleframework.xml.core.Validate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public BaseResponse<String> upload(MultipartFile file){
        return ResultUtils.success();
    }
}
