# 概述
flock 项目收集一些学习的知识点及项目结构，简要概述如下：

1. 项目通过maven构建
2. flock 统一管理所有项目，在其 `pom.xml` 文件构建管理项目
3. parent 继承 flock 的 `pom.xml`, 统一管理所有 jar 包, 其他服务则均继承 parent
4. base 集成其他项目需要的基本项目服务
5. framework 集成其他项目公用框架服务
6. services 集成各个业务服务
7. demo 集成学习积累相关知识点的实例
