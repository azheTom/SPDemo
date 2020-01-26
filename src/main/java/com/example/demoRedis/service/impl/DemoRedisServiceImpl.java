package com.example.demoRedis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
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
    @Autowired
    DefaultMQProducer producer;

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

    @Override
    public boolean sendMessage(String message) {
        Message message1 = new Message("TopicTest","1",message.getBytes());
        try {
            producer.send(message1, new SendCallback(){

                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送结果："+sendResult.getSendStatus());
                }

                @Override
                public void onException(Throwable throwable) {

                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

}
