package com.boot.smartrelay.controller;


import com.boot.smartrelay.StatusLogService;
import com.boot.smartrelay.beans.DataBean;
import com.boot.smartrelay.beans.DataEntity;
import com.boot.smartrelay.beans.Device;
import com.boot.smartrelay.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

@Slf4j
@RestController
@RequestMapping("/device")

@RequiredArgsConstructor
public class DeviceController {

    private static String END = "END";

    private static String START = "START";

    final DeviceService deviceService;

    final StatusLogService statusLogService;

    @PostMapping("/data")
    DataEntity deviceGateway(@RequestBody Device device){
       // System.out.println("Hello");

       // log.info("device-{} is now listening", device.getDeviceId());

        //1. timestamp
        statusLogService.setStatusTime(device);

        //2. 데이터 조회
        DataBean dataBean = deviceService.getData(device);
        String status = null == dataBean ? "N" : "Y";

        deviceService.setDeviceStatus(device);

        if(status.equals("Y")){
            log.info("디바이스 : {} - 에 새로운 데이터셋을 보내줍니다. -- ", device.getDeviceId());
        }

        DataEntity dataEntity = DataEntity.builder()
                .status(status)
                .dataBean(dataBean)
                .build();

        return dataEntity;
    }

}
