package com.boot.smartrelay.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    //해당 ip 설정
    //혹은 약속 된 키값을 보내준다.
    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }
}
