package com.flock.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * Api文档
 * </p>
 *
 * @author: wangchunming
 * @date: 2019-08-16 15:27
 */
@ApiIgnore
@Controller
@RequestMapping("/docs")
public class ApiDocController extends BaseController {

    /**
    * swaggerUI
    */
    @GetMapping("")
    public String swaggerUI(){
        return "redirect:/swagger-ui.html";
    }

}
