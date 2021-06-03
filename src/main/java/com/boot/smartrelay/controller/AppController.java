package com.boot.smartrelay.controller;


import com.boot.smartrelay.beans.DataBean;
import com.boot.smartrelay.beans.User;
import com.boot.smartrelay.beans.UserDataBean;
import com.boot.smartrelay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String user(){

        return "user";
    }

    @GetMapping("/login")
    public String login(){


        return "login";
    }

    @PostMapping(value = "/login_pro")
    @ResponseBody
    public String login(@RequestBody User user){

        System.out.println(user.getId());
        System.out.println(user.getPassword());

        if("test".equals(user.getId()) && "test".equals(user.getPassword())){
            return "Y";
        }
        return "N";
    }

    @ResponseBody
    @PostMapping("/mode/{userId}")
    public Map<String, String> onOff(@PathVariable String userId, @RequestBody DataBean dataBean){
        //로그인 여부 체크
        Map<String,String> json = new HashMap<>();
        if("test-1".equals(userId) || "test-2".equals(userId)  || "test-3".equals(userId) ){
           boolean result =  userService.setData(userId, dataBean);
           if(result){
               json.put("status", "200");
           }else{
               json.put("status", "500");
               json.put("error", "해당 스마트 릴레이 디바이스의 신호가 잡히지 않습니다.");
           }
        }else{
            json.put("status", "500");
            json.put("error", "유효한 아이디가 아닙니다.");
        }
       return json;
    }

    @GetMapping("/testboard")
    String testBoard(){
        return "testboard";
    }


}
