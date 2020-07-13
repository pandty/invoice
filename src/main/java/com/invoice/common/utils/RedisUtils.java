package com.invoice.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.invoice.common.json.JSONObject;
import com.invoice.common.utils.spring.SpringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * @author lijialin
 * @description Redis 常用工具类
 * @date 2020/6/12
 */
public class RedisUtils {

    @Autowired
    private static RedisTemplate redisTemplate;

    static {
        redisTemplate=  SpringUtils.getBean("redisTemplate");
    }


    /**
     * 根据地区中文名找对应的Id
     * @author lijialin
     * @date 2020/6/12 10:36
     */
    public static String getAreaIdByAreaName(String ...args){
        return (String) redisTemplate.opsForHash().get("area", StringUtils.join(args));
    }

    /**
     * 添加添加到Redis 缓存
     * @author lijialin
     * @date 2020/6/12 10:54
     * @param key redis key
     * @param obj redis val
     */
    public static void opsForObj(String key,Object obj){
        if (obj instanceof Map){
            redisTemplate.opsForHash().putAll(key, (Map) obj);
        }else if (obj instanceof List){
            redisTemplate.opsForList().leftPushAll(key, (List)obj);
        }else if (obj instanceof String){
            redisTemplate.opsForValue().set(key, obj);
        }else {
            //其他情况转化为JSON串
            redisTemplate.opsForValue().set(key, JSONArray.toJSONString(obj));
        }
    }


}
