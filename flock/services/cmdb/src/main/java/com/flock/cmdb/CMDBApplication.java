package com.flock.cmdb;

import com.flock.common.boot.PrintApplicationInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.flock.common.config",
        "com.flock.common.controller",
        "com.flock.cmdb.controller"
})
public class CMDBApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CMDBApplication.class, args);
        PrintApplicationInfo.print(context);
    }

}
