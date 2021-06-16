# Tomcat

web服务器

- 类似python中的flask，django

## 下载&安装

下载：[tomcat.apache.org](https://tomcat.apache.org/)

依赖：JRE + JAVA_HOME环境变量配置

- 实际使用时发现如果不配置`CATALINA_HOME`，启动时会闪退

下载解压后的目录结构

```
tomcat
	- bin 二进制命令
	- conf tomc配置
		- server.xml 服务器配置文件
	- lib
	- logs
	- temp
	- webapps
		- ROOT
        - yourapp
        	- WEB-INF 网站程序
        		- classes
        		- lib 依赖
        		- web.xml app配置
        	- index.html
        	- static
        		- css
        		- js
        		- img
	- work
```

