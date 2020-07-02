package com.demo.lms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class LoggingController {
	
	private Logger log =  LoggerFactory.getLogger(LoggingController.class);
	
	@GetMapping("logging")
	public void logging() {
		log.info("level： trace < debug < info < warn < error");
		log.info("The default level of Spring Boot is info");
	}
	
}
