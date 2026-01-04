# websocket的开发

## 项目结构

mvc 结构，前后端分离。

controller 接收 前端请求， service 写功能代码，

mapper 操作数据库。

pojo 是 实体类的地方。

interceptor 是拦截器，前端发送到后端的请求，会先经过它。

aop 是拦截某些方法执行前及执行后，修改它输入的参数或结果的。

## 技术

### 后端

springboot

mybatis 是操作数据库的，操作的数据库在 src/main/resources/application.yml 里有写

具体看pom.xml文件。

数据库是 redis 加 mysql

配置文件 src/main/resources/application.yml 里面定义了占用的端口，可以修改，

但记得需要修改下 前端中 vue.config.js 写的后端的端口。

### 前端

前端是 vue 框架 加上 element ui ， 具体看 package.json 文件。



