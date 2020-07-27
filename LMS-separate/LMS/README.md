#系统简介

基于SpringBoot的权限管理系统，前后端未分离版本。

核心技术采用Spring框架，权限校验采用Shiro框架。

系统启动即可运行，暂时未集成ORM框架，故无需配置数据源，数据均为模拟数据，只为学习。

PS: [学习主要来源-RuoYi](https://gitee.com/y_project)

#系统模块

~~~
LMS 
├── lms-admin             // 启动模块 [8080]
├── lms-common            // 公共模块
├── lms-framework         // 核心模块
├── lms-sample            // 实例模块
├── lms-system            // 系统模块
├──pom.xml                // 公共依赖
~~~

#内置功能
