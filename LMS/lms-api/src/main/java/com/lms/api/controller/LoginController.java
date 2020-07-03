package com.lms.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@RestController = @Controller + @ResponseBody
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("login")
	public String login() {
		log.info("=====login:");
		return "login";
	}

}
