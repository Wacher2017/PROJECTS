package com.flock.cmdb.controller;

import com.flock.common.controller.BaseController;
import com.flock.common.model.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 * Created by Chunming_Wang on 2019/12/25.
 */
@Api("测试Controller")
@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController extends BaseController {

    @GetMapping("")
    @ApiOperation(value = "获取信息",notes = "查看数据",response = ApiResult.class)
    public ApiResult<String> sayHello() {
        return success("Hello, everyone.");
    }

}
