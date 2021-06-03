package com.boot.smartrelay.service;

import com.boot.smartrelay.beans.DataBean;
import com.boot.smartrelay.beans.Device;
import com.boot.smartrelay.repository.DeviceRepository;
import com.boot.smartrelay.repository.DeviceStatusRedisCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeviceServiceImpl implements DeviceService {

    final DeviceRepository deviceRepository;

    final DeviceStatusRedisCrudRepository redisCrudRepository;

    @Override
    public DataBean getData(Device device) {
        DataBean dataBean = deviceRepository.selectData(device);
        return dataBean;
    }

    @Override
    public void setDeviceStatus(Device device) {
        redisCrudRepository.save(device);
        redisCrudRepository.delete(device);
    }
}
