package com.example.demoRedis.entity;

import lombok.Data;

/**
 * @Description TODO
 * @Author xwz
 * @Date 2020/1/20 10:25
 * @Version 1.0
 */
@Data
public class DemoRedis {
    private Integer id;
    private Integer count;
    private Integer lockNum;
    private String orderMessage;
    private Byte isDelete;
    private Byte isShow;
}
