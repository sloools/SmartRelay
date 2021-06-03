package com.boot.smartrelay.repository;

import com.boot.smartrelay.beans.DataBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SampleDB {
    public Map<String, DataBean> ORDER_SCHEMA = new HashMap<>();

    public Map<String, String> LINK_SCHEMA = new HashMap<>();

    public SampleDB(){
        LINK_SCHEMA.put("test-1", "device-1");
        LINK_SCHEMA.put("test-2", "device-2");
        LINK_SCHEMA.put("test-3", "device-3");
    }
}
