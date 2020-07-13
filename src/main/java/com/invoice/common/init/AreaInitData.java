package com.invoice.common.init;

import com.google.common.collect.Maps;
import com.invoice.common.utils.GroupUtils;
import com.invoice.common.utils.RedisUtils;
import com.invoice.common.utils.StringUtils;
import com.invoice.system.domain.SysArea;
import com.invoice.system.service.ISysAreaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;

/**
 * @author lijialin
 * @description 工程启动完毕后初始化所有地区
 * @date 2020/6/10
 */
@Component
@Order(value = 1)
public class AreaInitData implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AreaInitData.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private ISysAreaService iSysAreaService;
    @Override
    public void run(String... args) throws Exception {
        try{//1.先判断redis 是否已写入过
            Set areaSet = redisTemplate.opsForHash().keys("area");
            if (areaSet.size() == 0){
                //表示没有加入Redis  缓存  冲数据库读取添加到Redis
                List<SysArea> areas =iSysAreaService.selectAreaList(new SysArea());
                //写入Redis 缓存中
                RedisUtils.opsForObj("area",  organizedArea(areas));
                //redisTemplate.opsForHash().putAll("area", organizedArea(areas));
            }
            System.out.println(RedisUtils.getAreaIdByAreaName("河南省"));RedisUtils.getAreaIdByAreaName("河南省");
            //System.out.println("河南省#新乡市"+redisTemplate.opsForHash().get("area", "河南省#新乡市"));
        }catch (Exception ex){
            logger.info("Redis 未启动 ,Redis 暂时无法使用,请启动防止功能报错", ex);
        }

    }

    /*/**
     * 组织地区 只取省市数据  不考虑直辖市
     * @author lijialin
     * @date 2020/6/10 11:57
     * @param [areas]
     * @return java.util.Map
     */
    private Map<String,String>  organizedArea(List<SysArea> areas){
        //查询所有的地区数据 按名称分组
        Map<Integer,List<SysArea>> areaGroupMaps = GroupUtils.group(areas, new GroupUtils.GroupBy<Integer, SysArea>() {
            @Override
            public Integer getKey(SysArea sysArea) {
                return sysArea.getAreaId();
            }
        });
        //2.创建一个Map组装省市地区
        Map rtnAreaMap = Maps.newHashMap();
        for (SysArea sysArea :areas){
            if (sysArea.getLevel() ==1){
                rtnAreaMap.put(sysArea.getArea(), String.valueOf(sysArea.getAreaId()));
            }else if (sysArea.getLevel() ==2){
                rtnAreaMap.putAll(rtnProvinceAndCityMap(sysArea,areaGroupMaps));
            }
            continue;
        }
        return rtnAreaMap;
    }

    /*/**
     * 根据市级Id 找到省级 并且组装 省+市名称当做key  val省+市id  以# 作为分隔符
     * @author lijialin
     * @date 2020/6/10 13:45
     * @param [areaId]
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    private Map<String,String> rtnProvinceAndCityMap(SysArea sysArea, Map<Integer,List<SysArea>> AreaMaps){
        Map provinceAndCityMap = Maps.newHashMap();
        //获取到省级对象
        SysArea provinceOjb = AreaMaps.get(sysArea.getParentId()).get(0);
        //组装 key 和val  key、val 省级+市级
        provinceAndCityMap.put(StringUtils.join(provinceOjb.getArea(),sysArea.getArea()), StringUtils.join(String.valueOf(provinceOjb.getAreaId()),String.valueOf(sysArea.getAreaId())));
        return provinceAndCityMap;
    }
}
