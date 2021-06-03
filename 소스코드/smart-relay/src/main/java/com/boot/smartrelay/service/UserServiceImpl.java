package com.boot.smartrelay.service;

import com.boot.smartrelay.StatusLogService;
import com.boot.smartrelay.beans.DataBean;
import com.boot.smartrelay.beans.User;
import com.boot.smartrelay.repository.SampleDB;
import com.boot.smartrelay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    final StatusLogService statusLogService;

    final SampleDB sampleDB;

    private static String MODE_OFF = "OFF";

    private static String MODE_AUTO = "AUTO";

    @Override
    public boolean setData(String userId, DataBean dataBean) {
        //작동 중일때만 세팅함
        if(statusLogService.isDeviceNowOn(userId)){
            if(MODE_OFF.equals(dataBean.getMode())){
                log.info("MODE_OFF -----------------");
                log.info("{}의 디바이스가 종료 됩니다.", userId);
                String deviceId = sampleDB.LINK_SCHEMA.get(userId);
                statusLogService.delDevice(deviceId);
            }else if(MODE_AUTO.equals(dataBean.getMode())){
                log.info("MODE_AUTO -----------------");
                log.info("{}의 디바이스에 새로운 메시지를 전달합니다.", userId);
            }
            userRepository.setData(userId, dataBean);
        }else{
           return false;
        }
        return true;
    }
}
