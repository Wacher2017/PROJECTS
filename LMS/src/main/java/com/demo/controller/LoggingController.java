package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@RestController = @Controller + @ResponseBody
@RequestMapping("log")
public class LoggingController {
	
	private Logger log =  LoggerFactory.getLogger(LoggingController.class);
	
	@GetMapping("logging")
	public void logging() {
		
		//level： trace < debug < info < warn < error
		log.info("level： trace < debug < info < warn < error");
		
		log.trace("this is a trace log");
		log.debug("this is a debug log");
		//The default level of Spring Boot : info
		log.info("this is a info log");
		log.warn("this is a warn log");
		log.error("this is a error log");
		
	}
	
}
