package com.wahson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InfoSystemApplication {

    public static void main(String[] args) {
        // 直接运行此类而不是右键项目选Tomcat7:run
        SpringApplication.run(InfoSystemApplication.class, args);
    }

}
