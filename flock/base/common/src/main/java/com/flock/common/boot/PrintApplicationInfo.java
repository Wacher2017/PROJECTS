package com.flock.common.boot;

import com.flock.common.utils.AnsiUtil;
import com.flock.common.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.jansi.Ansi;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * <p>
 *  打印项目信息
 * </p>
 * @author wangchunming
 * @version 1.0
 * @date 2019-08-16 13:40
 **/
@Slf4j
public class PrintApplicationInfo {

    /**
     * 执行之前，打印前置条件提示
     */
    public static void printTip(){
        StringBuffer tip = new StringBuffer();
        tip.append("======================================================================================\n");
        tip.append("                                                                                      \n");
        tip.append("                               !!!准备工作!!!                                         \n");
        tip.append(" 1.创建数据库                                                                         \n");
        tip.append(" 2.数据库脚本在项目docs/***.sql                                                       \n");
        tip.append(" 3.启动redis服务                                                                      \n");
        tip.append(" 4.更多注意事项：请查看: https://***                                                  \n");
        tip.append("                                                                                      \n");
        tip.append("======================================================================================\n");
        log.info("\n{}",Ansi.ansi().eraseScreen().fg(Ansi.Color.YELLOW).a(tip.toString()).reset().toString());
    }

    /**
     * 启动成功之后，打印项目信息
     */
    public static void print(ConfigurableApplicationContext context){
        ConfigurableEnvironment environment = context.getEnvironment();

        // 项目名称
        String projectFinalName = environment.getProperty("info.project-finalName");
        // 项目版本
        String projectVersion = environment.getProperty("info.project-version");
        // 项目profile
        String profileActive = environment.getProperty("spring.profiles.active");
        // 项目路径
        String contextPath = environment.getProperty("server.servlet.context-path");
        // 项目端口
        String port = environment.getProperty("server.port");

        log.info("projectFinalName : {}",projectFinalName);
        log.info("projectVersion : {}",projectVersion);
        log.info("profileActive : {}",profileActive);
        log.info("contextPath : {}",contextPath);
        log.info("port : {}",port);

        String startSuccess = "        _                    _                                                    \n" +
                "  ___  | |_    __ _   _ __  | |_     ___   _   _    ___    ___    ___   ___   ___ \n" +
                " / __| | __|  / _` | | '__| | __|   / __| | | | |  / __|  / __|  / _ \\ / __| / __|\n" +
                " \\__ \\ | |_  | (_| | | |    | |_    \\__ \\ | |_| | | (__  | (__  |  __/ \\__ \\ \\__ \\\n" +
                " |___/  \\__|  \\__,_| |_|     \\__|   |___/  \\__,_|  \\___|  \\___|  \\___| |___/ |___/\n" +
                "                                                                                  ";

        String homeUrl = "http://" + IpUtil.getLocalhostIp() + ":" + port + contextPath;
        String swaggerUrl = "http://" + IpUtil.getLocalhostIp() + ":" + port + contextPath + "/docs";
        log.info("home:{}",homeUrl);
        log.info("docs:{}",swaggerUrl);
        log.info("cmdb project start success...........");
        log.info("\n{}", AnsiUtil.getAnsi(Ansi.Color.BLUE,startSuccess));
    }

}
