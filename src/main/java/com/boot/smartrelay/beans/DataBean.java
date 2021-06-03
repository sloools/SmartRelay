package com.boot.smartrelay.beans;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Setter
public class DataBean {

    //모드 설정
    private String mode;

    //모드 시작 시간
    private String startTime;

    //모드 종료 시간
    private String endTime;

}
