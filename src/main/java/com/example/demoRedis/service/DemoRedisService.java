package com.example.demoRedis.service;

/**
 * @Description TODO
 * @Author xwz
 * @Date 2020/1/20 10:41
 * @Version 1.0
 */
public interface DemoRedisService {
    Object setOrderMessage(Integer id);

    String decOrderCount(Integer id, Integer count);
}
