package com.auth.demo.exception;

import com.auth.demo.enums.ResultCode;
import com.auth.demo.model.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类
 * controller层异常无法捕获处理，需要自己处理
 * @author Chunming_Wang
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 处理所有自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public ResultJson handleCustomException(CustomException e){
        log.error(e.getResultJson().getMsg());
        return e.getResultJson();
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJson handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        if(e.getBindingResult().getFieldError() != null) {
            log.error(e.getBindingResult().getFieldError().getField() + e.getBindingResult().getFieldError().getDefaultMessage());
            return ResultJson.failure(ResultCode.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage());
        }
        return ResultJson.failure(ResultCode.BAD_REQUEST);
    }

}
