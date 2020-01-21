package com.example.demoRedis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demoRedis.entity.DemoRedis;
import com.example.demoRedis.mapper.DemoRedisMapper;
import com.example.demoRedis.service.DemoRedisService;
import com.example.demoRedis.until.RedisKeys;
import com.example.demoRedis.until.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author xwz
 * @Date 2020/1/20 10:41
 * @Version 1.0
 */
@Service
@Slf4j
public class DemoRedisServiceImpl implements DemoRedisService {

    @Autowired
    RedisUtil redisUtil;
    @Resource
    private DemoRedisMapper demoRedisMapper;

    @Override
    public Object setOrderMessage(Integer id) {
        if(redisUtil.hasKey(RedisKeys.REDIS_DOME_ID+id)){
            return redisUtil.get(RedisKeys.REDIS_DOME_ID+id);
        }
        DemoRedis demoRedis = demoRedisMapper.selectById(id);
        redisUtil.setAddRandomTime(RedisKeys.REDIS_DOME_ID+id, JSONObject.toJSON(demoRedis).toString(),60*1000);
        log.info(JSONObject.toJSON(demoRedis).toString());
        log.info(redisUtil.getExpire(RedisKeys.REDIS_DOME_ID)+"3");
        return demoRedis;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String decOrderCount(Integer id, Integer count) {
        int i = demoRedisMapper.updateCount(id, count);
        if(i == 1){
            return "下单成功3";
        }
        return "下单失败3";
    }

}
