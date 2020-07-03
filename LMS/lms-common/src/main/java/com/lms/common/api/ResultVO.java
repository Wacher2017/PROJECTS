package com.lms.common.api;

import lombok.Getter;

@Getter
public class ResultVO<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 相应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

}
