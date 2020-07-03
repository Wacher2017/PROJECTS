package com.lms.framework.advice;

import com.lms.common.api.ResultCode;
import com.lms.common.api.ResultVO;
import com.lms.common.exception.APIException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局处理异常
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 所有验证框架异常捕获处理
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public String MethodArgumentNotValidExceptionHandler(Exception e) {
        BindingResult bindResult = null;
        if (e instanceof BindException) {
            bindResult = ((BindException) e).getBindingResult();
        } else if (e instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) e).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg != null && msg.contains("NumberFormatException")) {
                msg = ResultCode.PARAMETER_TYPE_EXCEPTION.getMsg();
            }
        }else {
            msg = ResultCode.SYSTEM_BUSY_RETRY_LATER.getMsg();
        }
        return msg;
    }

    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        // 这里传递的响应码枚举
        return new ResultVO<>(ResultCode.FAILED, e.getMsg());
    }

}
