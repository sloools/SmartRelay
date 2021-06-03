package com.boot.smartrelay.repository;

import com.boot.smartrelay.beans.DataBean;
import com.boot.smartrelay.beans.Device;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DeviceRepository {

    final SampleDB DB;

    /**
     * 데이터를 조회
     *
     * @param device
     * @return
     */
    public DataBean selectData(Device device){
        DataBean dataBean = null;
        try {
            //데이터 요청
            dataBean = DB.ORDER_SCHEMA.get(device.getDeviceId());
            if(dataBean != null){
                //데이터 지우기
                removeData(device);
            }
        }catch (Exception e){
            log.error("db 조회 실패 디바이스 아이디 : {}", device.getDeviceId());
        }
        return dataBean;
    }

    private void removeData(Device device){
       DB.ORDER_SCHEMA.remove(device.getDeviceId());
       log.info("해당 명령어 지웁니다.");
    }
}
