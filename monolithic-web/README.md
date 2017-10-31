# Monolithic Web Application
> 单体Web应用

此类 Application 为传统的部署模式，全部的服务器功能打包为单个`war`或者`jar`.

`monolithic-web` 使用 `spring-boot` 开发一个简单的后台 `REST Service`，提供以下接口：

- [ ] **whatTimeIs**	return current time(formatter: yyyy-MM-dd HH:mm:ss) of server side

## Run on IDE

Just run `org.nerve.docker.monolithic.MonolithicApp`

## Build

在根目录执行`mvn package` 命令即可完成打包

## Build with docker
