package com.boot.smartrelay.service;

import com.boot.smartrelay.repository.DeviceStatusRedisCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;



public class BootRunner implements ApplicationRunner {

    @Autowired
    DeviceStatusRedisCrudRepository redisCrudRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("송용호");
        System.out.println(redisCrudRepository);
    }
}
