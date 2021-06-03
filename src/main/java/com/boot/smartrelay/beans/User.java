package com.boot.smartrelay.beans;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class User {
    private String id;
    private String password;
    private String deviceId;
    private boolean loginFlg;
}
