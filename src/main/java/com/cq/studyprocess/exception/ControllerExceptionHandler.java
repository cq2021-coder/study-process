package com.cq.studyprocess.exception;

import com.cq.studyprocess.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 控制器异常处理程序
 *
 * @author 程崎
 * @since 2022/08/05
 */
@RestControllerAdvice(annotations = {RestController.class})
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public CommonResponse<Object> validExceptionHandler(BindException e) {
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.warn("parameter validation failed:{}", errorMessage);
        return CommonResponse.error(BusinessCode.PARAMS_ERROR,errorMessage);
    }

    @ExceptionHandler(value = BusinessException.class)
    public CommonResponse<Object> businessExceptionHandler(BusinessException e) {
        log.warn("parameter validation failed:{}", e.getMessage());
        return CommonResponse.error(e.getBusinessCode(), e.getMessage());
    }
}
