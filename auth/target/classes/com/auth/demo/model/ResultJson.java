package com.auth.demo.model;

import com.auth.demo.enums.ResultCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回类型
 * @author Chunming_Wang
 */
@Data
public class ResultJson<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }

    private ResultJson(ResultCode resultCode) {
        setResultCode(resultCode);
    }

    private ResultJson(ResultCode resultCode, T data) {
        setResultCode(resultCode);
        this.data = data;
    }

    public static ResultJson success() {
        return new ResultJson<>(ResultCode.SUCCESS);
    }

    public static <T> ResultJson<T> success(T data) {
        return new ResultJson<>(ResultCode.SUCCESS, data);
    }

    public static ResultJson failure(ResultCode code) {
        return new ResultJson<>(code);
    }

    public static <T> ResultJson<T> failure(ResultCode code, T data) {
        return new ResultJson<>(code, data);
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.code == ResultCode.SUCCESS.getCode();
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"msg\":\"" + msg + '\"' +
                ", \"data\":\"" + data + '\"'+
                '}';
    }

}
