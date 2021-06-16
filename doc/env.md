# Java平台环境配置

应用运行得平台由底层操作系统，jvm，libraries和一些配置数据决定

- 配置工具：应用程序访问配置数据的API
- 系统工具：由系统和运行时环境定义的各种API
- PATH和CLASSPATH：用于配置JDK的环境变量

# PATH和CLASSPATH

一般的JDK目录，其中bin目录包含编译器和启动器

![JDK directory structure](https://docs.oracle.com/javase/tutorial/figures/essential/environment-directories.gif)

PATH: `javac.exe`, `java.exe`, `javadoc.exe`等

CLASSPATH

1. 告诉应用程序和JDK工具用户定义的类的所在位置

2. （推荐）可以通过命令行 `-cp`切换

3. 默认值是`.`

