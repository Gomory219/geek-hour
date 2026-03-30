package cn.edu.sxu.exception;

import cn.edu.sxu.common.BaseResponse;
import cn.edu.sxu.common.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<String> handleBusinessException(BusinessException e) {
        return ResultUtils.error(e.getErrorCode(), e.getMessage());
    }

}
