package com.ctrip.datasource.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootStart {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringBootStart.class, args);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
