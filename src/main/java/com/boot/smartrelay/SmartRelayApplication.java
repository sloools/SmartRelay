package com.boot.smartrelay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartRelayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartRelayApplication.class, args);
    }

}
