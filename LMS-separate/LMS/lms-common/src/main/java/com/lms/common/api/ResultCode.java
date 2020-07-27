package com.lms.common.api;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    CREATED(201, "对象创建成功"),
    ACCEPTED(202, "请求已经被接受"),
    NO_CONTENT(204, "操作已经执行成功，但是没有返回数据"),

    MOVED_PERM(301, "资源已被移除"),
    REDIRECT(303, "重定向"),
    NOT_MODIFIED(303, "资源没有被修改"),

    UNAUTHORIZED(401, "未授权"),
    NOT_PERMISSION(403, "访问受限，授权过期"),
    NOT_FOUND(404, "资源，服务未找到"),
    BAD_METHOD(405, "不允许的http方法"),
    CONFLICT(409, "资源冲突，或者资源被锁"),
    UNSUPPORTED_TYPE(415, "不支持的数据，媒体类型"),

    FAILED(500, "系统内部错误"),
    NOT_IMPLEMENTED(501, "接口未实现"),
    SYSTEM_EXCEPTION(5000,"系统异常!"),
    PARAMETER_EXCEPTION(5001,"请求参数校验异常"),
    PARAMETER_TYPE_EXCEPTION(5002,"请求参数类型异常"),
    PARAMETER_PARSE_EXCEPTION(5003,"请求参数解析异常"),
    HTTP_MEDIA_TYPE_EXCEPTION(5004,"HTTP Media 类型异常"),
    SYSTEM_LOGIN_EXCEPTION(5005,"系统登录异常"),

    SYSTEM_BUSY_RETRY_LATER(9999, "系统繁忙，请稍后重试...")

    ;

    private final int code;
    private final String msg;

    ResultCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultCode getResultCode(int code) {
        ResultCode[] ecs = ResultCode.values();
        for (ResultCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

}
