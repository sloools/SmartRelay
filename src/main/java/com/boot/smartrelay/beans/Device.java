package com.boot.smartrelay.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * UTC-0
 *  mode -> mon / moff / s / a /
 *  channel 1,2,3 / 앱 유저가 고름
 *  [[],[],[]]
 *  로그인 id - [device-id,..]
 *  날짜는 스트링값으로 / 파싱
 *
 */
@Getter
@Setter
@RedisHash("device")
public class Device {
    @Id
    private String deviceId;
    //private String deviceKey;

    private String mode;
}
