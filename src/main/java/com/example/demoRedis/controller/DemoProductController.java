package com.example.demoRedis.controller;

import com.example.demoRedis.service.DemoRedisService;
import com.example.demoRedis.service.impl.DemoRedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class DemoProductController {
    @Autowired
    private DemoRedisService demoRedisService;

    @RequestMapping("message")
    public boolean sendProduct(String message){
        return demoRedisService.sendMessage(message);
    }
}
