package com.example.demoRedis.controller;

import com.example.demoRedis.service.DemoRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author xwz
 * @Date 2020/1/20 10:32
 * @Version 1.0
 */
@RestController
@RequestMapping("demoRedis")
public class DemoRedisController {
    @Autowired
    private DemoRedisService demoRedisService;

    @RequestMapping("setOrderMessage")
    public Object setOrderMessage(Integer id){
        return demoRedisService.setOrderMessage(id);
    }

    /**
     * 下单秒杀
     */
    @RequestMapping("decOrderCount")
    public String decOrderCount(Integer id,Integer count){
        return demoRedisService.decOrderCount(id,count);
    }
}
