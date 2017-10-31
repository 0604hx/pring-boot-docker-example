## Build and run on docker

### package

just run `mvn package` on project directory.

### build docker image

After `package` we got `flat jar` on `target` directory under each module, e.g.:

`spring-boot-docker-example\monolithic-web\target\monolithic-web-1.0.0.jar`

Create new file `Dockerfile`  with below content:

```docker
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD monolithic-web-1.0.0.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
```

Note that `Dockerfile` and `monolithic-web-1.0.0.jar` are in the same directory.

Then run

```shell
docker image build -t nerve/monolithic-web .


# output look like
Sending build context to Docker daemon  17.89MB
Step 1/5 : FROM openjdk:8-jdk-alpine
8-jdk-alpine: Pulling from library/openjdk
88286f41530e: Pull complete
720349d0916a: Pull complete
42a4b3080d3c: Pull complete
Digest: sha256:1c6a5d2aee2a9cb6c5463c54ba7877f5b4cfb75c86dfdc7b338fe952254e4707
Status: Downloaded newer image for openjdk:8-jdk-alpine
 ---> a8bd10541772
Step 2/5 : VOLUME /tmp
 ---> Running in 4a9a8f33879c
 ---> 03eca50ca137
Removing intermediate container 4a9a8f33879c
Step 3/5 : ADD monolithic-web-1.0.0.jar app.jar
 ---> 83cd080f0f37
Removing intermediate container 5a911889c29d
Step 4/5 : ENV JAVA_OPTS ""
 ---> Running in 448b098b9f85
 ---> 0d3b0f2dc123
Removing intermediate container 448b098b9f85
Step 5/5 : ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
 ---> Running in cd6aaf93e76d
 ---> a6b083518f6d
Removing intermediate container cd6aaf93e76d
Successfully built a6b083518f6d
Successfully tagged nerve/monolithic-web:latest
```

Check our images

```
root@a8832d857bd6:/mnt/docker/monolithic-web# docker images
REPOSITORY             TAG                 IMAGE ID            CREATED              SIZE
nerve/monolithic-web   latest              a6b083518f6d        About a minute ago   119MB
openjdk                8-jdk-alpine        a8bd10541772        6 weeks ago          101MB
```

### run as container

```shell
docker run -d --name monolithic -p 8080:8080 nerve/monolithic-web
```

Create new container name `monolithic` base `nerve/monolithic-web`.

Check start up logs

```
root@a8832d857bd6:/mnt/docker/monolithic-web# docker logs monolithic

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.8.RELEASE)

2017-10-31 02:52:28.652  INFO 1 --- [           main] o.n.docker.monolithic.MonolithicAppKt    : Starting MonolithicAppKt v1.0.0 on c9a42451e184 with PID 1 (/app.jar started by root in /)
2017-10-31 02:52:28.655  INFO 1 --- [           main] o.n.docker.monolithic.MonolithicAppKt    : No active profile set, falling back to default profiles: default
2017-10-31 02:52:28.727  INFO 1 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@46fbb2c1: startup date [Tue Oct 31 02:52:28 GMT 2017]; root of context hierarchy
2017-10-31 02:52:30.172  INFO 1 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2017-10-31 02:52:30.188  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2017-10-31 02:52:30.190  INFO 1 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.23
2017-10-31 02:52:30.289  INFO 1 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2017-10-31 02:52:30.289  INFO 1 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1566 ms
2017-10-31 02:52:30.411  INFO 1 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-10-31 02:52:30.414  INFO 1 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-10-31 02:52:30.415  INFO 1 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2017-10-31 02:52:30.416  INFO 1 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2017-10-31 02:52:30.416  INFO 1 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2017-10-31 02:52:30.835  INFO 1 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@46fbb2c1: startup date [Tue Oct 31 02:52:28 GMT 2017]; root of context hierarchy
2017-10-31 02:52:30.965  INFO 1 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/whatTimeIs]}" onto public java.lang.String org.nerve.docker.monolithic.IndexController.whatIsTheTime()
2017-10-31 02:52:30.969  INFO 1 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-10-31 02:52:30.969  INFO 1 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-10-31 02:52:31.002  INFO 1 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-10-31 02:52:31.002  INFO 1 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-10-31 02:52:31.043  INFO 1 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-10-31 02:52:31.221  INFO 1 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-10-31 02:52:31.308  INFO 1 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-10-31 02:52:31.313  INFO 1 --- [           main] o.n.docker.monolithic.MonolithicAppKt    : Started MonolithicAppKt in 3.006 seconds (JVM running for 3.656)

```

### verify

```
root@a8832d857bd6:/mnt/docker/monolithic-web# curl localhost:8080/whatTimeIs && echo
2017-10-31 02:54:29
```

Awesome, we got correct output!