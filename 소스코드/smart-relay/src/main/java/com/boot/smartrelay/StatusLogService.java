package com.boot.smartrelay;

import com.boot.smartrelay.beans.Device;
import com.boot.smartrelay.beans.User;
import com.boot.smartrelay.repository.SampleDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StatusLogService {
    private final Map<String, Long> DEVICE_STATUS;

    private final SampleDB sampleDB;

    public StatusLogService(SampleDB sampleDB){
        DEVICE_STATUS = new HashMap<>();
        this.sampleDB = sampleDB;
    }

    public void setStatusTime(Device device){

        String deviceId = device.getDeviceId();
        long now = System.currentTimeMillis() / 1000L;

        DEVICE_STATUS.put(device.getDeviceId(), now);

       // log.info("deviceId  {} -- timeStamp log -- {} -- 신호가 잘 잡히고 있습니다.", deviceId, now);
    }

    List<String> getOutOfStatusDeviceList() {
        List<String> devices = new ArrayList<>();
        Long now = System.currentTimeMillis() / 1000L;
        DEVICE_STATUS.forEach((k, v)->{
            if(now - v > 15L) {
                log.info("deviceId : {} no action time : {} ", k ,  now - v);
                devices.add(k);
            }
        });
        return devices;
    }

    public boolean isDeviceNowOn(String userId){

        String deviceId = sampleDB.LINK_SCHEMA.get(userId);

        long now = System.currentTimeMillis() / 1000L;

        //신호가 아예 없었다면 / 즉 키가 없다.
        if(!DEVICE_STATUS.containsKey(deviceId)){
            log.info("디바이스 아이디 : " + deviceId + "의 신호가 잡히지 않았습니다.");
            return false;
        }

        //신호가 8초이상 차이가 나면 꺼진것으로 간주
        if(now - DEVICE_STATUS.get(deviceId) > 8){
            log.info("디바이스 아이디 : " + deviceId + "의 신호가 오랫동안 잡히지 않았습니다. 꺼졌는지 확인하십시오");
            return false;
        }

        return true;
    }

    public boolean delDevice(String deviceId){
        try {
            DEVICE_STATUS.remove(deviceId);
            if(sampleDB.ORDER_SCHEMA.containsKey(deviceId)){
                sampleDB.ORDER_SCHEMA.remove(deviceId);
            }
        }catch (Exception e){
            log.error("디바이스 큐에서의 삭제를 실패하였습니다.");
            return false;
        }
        return true;
    }

    public int getNowOnSatusDeviceSize(){
        return this.DEVICE_STATUS.size();
    }
}
