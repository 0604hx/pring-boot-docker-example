# spring-boot-docker-example

Here describes the deployment options of monolithic-application and micro-service application base on docker.

## version information

item|version|remark
------|------|------
develop OS|windows 10| 64 bit
deploy OS|ubuntu 16.04| 64 bit
java|1.8+|64 bit
spring boot|1.5.8|
maven|3.2+|
docker|17.06.2| docker-ce

## build and run on docker

> I do not use maven docker plugin( e.g.: [spotify](https://github.com/spotify/dockerfile-maven)) for no working Docker set-up on my windows developing machine =.=
> So I will build and run docker container on remote ubuntu(`16.04 64 bit`) at this section.

[Build and run on docker](docs/build_and_run_on_docker.md)

## improved deployment

So far, we have successfully run our application with docker container.

However, there are some problems:

- [ ] we need to rebuild `docker image` while repackage our applicaion
- [ ] hard to change application properties under the running container
- [ ] have not persistent data & log file

See [improved deployment](docs/improved_deployment.md) for detail.

## LOGS

### 2017-10-31

1. 新建项目，完成 `monolithic-web` 模块的部署示例
2. 增加`improved_deployment`