package com.lms.common.exception;

import com.lms.common.api.ResultCode;

/**
 * API 异常
 */
public class APIException extends RuntimeException {

    private int code;
    private String msg;

    public APIException() {
        this(ResultCode.SYSTEM_EXCEPTION.getCode(), ResultCode.SYSTEM_EXCEPTION.getMsg());
    }

    public APIException(String msg) {
        this(1001, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
