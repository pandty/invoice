package com.invoice.common.utils;

import com.google.common.collect.Lists;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lijialin
 * @description 类型转换
 * @date 2020/6/5
 */
public class CastUtils {

    /*/**
     * 对象互转
     * @author lijialin
     * @date 2020/6/5 10:34
     * @param [t, map2Obj]
     * @return V
     */
    public static <V,T> V map2Obj(T t,Map2Obj map2Obj) {
        return (V) map2Obj.getObj(t);
    }

    /*/**
     * 集合转换
     * @author lijialin
     * @date 2020/6/5 10:30
     * @param [maps, map2Obj]
     * @return java.util.List<V>
     */
    public static <V,T>List<V> maps2ListObj(Collection<T> maps, Map2Obj<V,T> map2Obj) {
        List<V> rtnLists = Lists.newArrayList();
        for (T map :maps){
            rtnLists.add(map2Obj.getObj(map));
        }
        return rtnLists;
    }

    /**
     * 用于回调
     * @author lijialin
     * @date 2020/6/5 10:29
     * @param
     * @return
     */
    public  interface Map2Obj<V,T>{
        //回调方法
        V getObj(T t);
    }

}
