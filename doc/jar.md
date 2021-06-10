# Java Archive - JAR

通常JAR文件中包括class文件和应用辅助文件

**使用JAR的优点**

- 安全：JAR支持电子签名
- 压缩存储
- 打包扩展
- 包封装：JAR可以强制所有文件必须要在包中
- 版本控制
- 便携

# 使用JAR文件基本操作

JAR文件被打包为ZIP格式

通用jar文件操作

| 操作             | 命令                             |
| ---------------- | -------------------------------- |
| 创建             | jar cf jar-file input-file(s)    |
| 查看内容         | jar tf jar-file                  |
| 提取             | jar xf jar-file                  |
| 提取特定文件     | jar xf jar-file archived-file(s) |
| 更新（添加）     | jar uf jar-file input-file(s)    |
| 运行（需要main） | java -jar app.jar                |

>Note:
>
>| 缩写 | 含义                           |
>| ---- | ------------------------------ |
>| c    | create                         |
>| t    | table of content               |
>| f    | file specified by command line |
>| x    | extract                        |
>| u    | update                         |

# Manifest File

jar元文件，用于版本控制，电子签名，包封装等

## **默认manifest文件**

创建jar时自动添加

```
META-INF/MANIFEST.MF

Manifest-Version: 1.0
Created-By: 1.7.0_06 (Oracle Corporation)
```

## 修改manifest文件

将现有的文本文件作为manifest文件，要求：

1. 必须为 UTF-8编码
2. 结尾应该是空行，否则最后一行不会被读取

```shell
jar cfm jar-file manifest-addition input-file(s)
```

> m表示使用现有的文件作为manifest文件

### 设置程序入口

```
Main-Class: classname
```

设置入口后可以直接执行jar文件

```shell
java -jar JAR-name
```

通过JAR命令行来设置manifest中的程序入口

```shell
jar cfe app.jar MyApp MyApp.class
```

e表示`entrypoint` 添加或覆盖mainfest文件，如果入口在包内则用

```
jar cfe Main.jar foo.Main foo/Main.class
```

### 添加Classpath

```
Class-Path: jar1-name jar2-name directory-name/jar3-name
```

**Example**

向MyUtils.jar中的类添加classpath

1. 创建一个文本文件Manifest.txt并添加以下内容

```txt
Class-Path: MyUtils.jar

```

> 结尾注意另起一行

2. 打包创建JAR

```shell
jar cfm MyJar.jar Manifest.txt MyPackage/*.class
```

### 设置版本信息

| Header                 | Exp                                       |
| ---------------------- | ----------------------------------------- |
| Name                   | Name: java/util/                          |
| Specification-Title    | Specification-Title: Java Utility Classes |
| Specification-Version  | Specification-Version: 1.2                |
| Specification-Vendor   | Specification-Vendor: Example Tech, Inc.  |
| Implementation-Title   | Implementation-Title: java.util           |
| Implementation-Version | Implementation-Version: build57           |
| Implementation-Vendor  | Implementation-Vendor: Example Tech, Inc. |

for more see [Java™ Product Versioning (oracle.com)](https://docs.oracle.com/javase/8/docs/technotes/guides/versioning/spec/versioning2.html#wp89936)

### 封包

```
Name: myCompany/myPackage/
Sealed: true
```

**Example**

如果想封装MyJar.jar中的两个包firstPackage和firstPackage

```
Name: myCompany/firstPackage/
Sealed: true

Name: myCompany/secondPackage/
Sealed: true
```

