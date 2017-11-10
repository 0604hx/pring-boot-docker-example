package org.nerve.docker.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * org.nerve.docker.server
 * Created by zengxm on 2017/11/1.
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterApp {
	public static void main(String[] args) {
		SpringApplication.run(RegisterApp.class, args);
	}
}
