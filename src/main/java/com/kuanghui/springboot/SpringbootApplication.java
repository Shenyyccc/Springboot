package com.kuanghui.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication标注这个类是springboot的应用
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        //将SpringBoot启动
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
