package com.boot.smartrelay.repository;

import com.boot.smartrelay.beans.DataBean;
import com.boot.smartrelay.beans.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepository {

    final SampleDB sampleDB;

    public boolean setData(String userId, DataBean dataBean){
        try {
            String deviceId = sampleDB.LINK_SCHEMA.get(userId);
            log.info("user {} ---- device {} setting ---- ", userId, deviceId);
            sampleDB.ORDER_SCHEMA.put(deviceId, dataBean);
        }catch (Exception e){
            return false;
        }
        System.out.println("data setting 완료 : ");
        return true;
    }
}
