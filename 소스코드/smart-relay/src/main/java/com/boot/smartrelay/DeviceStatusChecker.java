package com.boot.smartrelay;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeviceStatusChecker {

    private final StatusLogService statusLogService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat();

    //30초 한번씩 status를 파악 request 요청에 대해 listening
    @Scheduled(fixedRate = 30000)
    public void reportCurrentDeviceStatus() {
        List<String> statusOutDevices = statusLogService.getOutOfStatusDeviceList();

        if(statusLogService.getNowOnSatusDeviceSize() == 0){
            log.info("현재 동작하고 있는 스마트 릴레이 디바이스가 존재하지 않습니다.");
             return;
        }
        if(statusOutDevices.isEmpty()){
            log.info("현재 동작하고 있는 스마트릴레이 디바이스 기기 개수: " + statusLogService.getNowOnSatusDeviceSize());
        }else{
            statusOutDevices.stream().forEach(k->{
                log.info("신호가 없는 디바이스가 있습니다. 디바이스 아이디 : {} / 신호 파악 큐에서 제외하겠습니다.", k);
                boolean delResult = statusLogService.delDevice(k);
                if(delResult){
                    log.info("삭제 완료");
                }else{
                    log.info("삭제 실패");
                }
            });

            System.out.println("신호가 없는 디바이스에 대한 처리");
        }
    }


}
