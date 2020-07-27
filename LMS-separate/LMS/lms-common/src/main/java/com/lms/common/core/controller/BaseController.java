package com.lms.common.core.controller;

import com.lms.common.api.ResultCode;
import com.lms.common.api.ResultVO;
import com.lms.common.utils.ServletUtils;
import com.lms.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * web层通用数据处理
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 返回成功
     */
    public ResultVO success() {
        return new ResultVO<>(null);
    }

    /**
     * 返回失败消息
     */
    public ResultVO error() {
        return new ResultVO<>(ResultCode.FAILED, null);
    }

    /**
     * 返回成功消息
     */
    public ResultVO success(String message) {
        return new ResultVO<>(message);
    }

    /**
     * 返回失败消息
     */
    public ResultVO error(String message) {
        return new ResultVO<>(ResultCode.FAILED, message);
    }

    /**
     * 返回错误码消息
     */
    public ResultVO error(ResultCode type, String message) {
        return new ResultVO<>(type, message);
    }

}
