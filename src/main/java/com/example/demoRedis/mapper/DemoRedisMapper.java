package com.example.demoRedis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demoRedis.entity.DemoRedis;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Description TODO
 * @Author xwz
 * @Date 2020/1/20 10:27
 * @Version 1.0
 */
public interface DemoRedisMapper extends BaseMapper<DemoRedis> {
    @Update("update test_demo_redis set count = count - ${count} where id = #{id} and count - ${count} >= 0")
    int updateCount(@Param("id") Integer id, @Param("count")Integer count);
}
