package com.example.demoRedis.commons;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author xwz
 * @Date 2019/12/11 17:01
 * @Version 1.0
 */
public interface ErrorCode extends Serializable {
    /**
     * 错误码
     *
     * @return
     */
    String getCode();

    /**
     * 错误信息
     *
     * @return
     */
    String getMsg();
}
