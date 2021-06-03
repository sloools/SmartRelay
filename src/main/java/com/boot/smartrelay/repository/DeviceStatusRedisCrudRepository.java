package com.boot.smartrelay.repository;

import com.boot.smartrelay.beans.Device;
import com.boot.smartrelay.beans.DeviceStatus;
import org.springframework.data.repository.CrudRepository;

public interface DeviceStatusRedisCrudRepository extends CrudRepository<Device, String> {

}
