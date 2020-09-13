package org.top.thymeboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.top.thymeboot.system.mapper")
public class ThymeBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeBootApplication.class, args);
    }

}
