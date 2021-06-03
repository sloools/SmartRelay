package com.boot.smartrelay.service;

import com.boot.smartrelay.beans.DataBean;
import com.boot.smartrelay.beans.Device;

public interface DeviceService {
    DataBean getData(Device device);
    void setDeviceStatus(Device device);
}
