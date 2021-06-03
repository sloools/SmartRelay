package com.boot.smartrelay;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SmartRelayApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void test() throws InterruptedException {
        //http://15.164.240.79:10010/app/testboard
        int T = 0;
        while(T < 10) {
            for (int k = 0; k < 1000; k++) {
                String postJson = "{\"deviceId\" : \"device-" + k + "\", \"mode\" : \"auto\"}";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> entity = new HttpEntity<String>(postJson, headers);
              //  System.out.println(postJson);
                if(k % 2 == 0) {
                    testRestTemplate.postForEntity("http://localhost:10010/device/data", entity, Object.class);
                }else{
                    testRestTemplate.postForEntity("http://localhost:10020/device/data", entity, Object.class);
                }
            }
            T++;
            Thread.sleep(5000);
        }

    }

}
