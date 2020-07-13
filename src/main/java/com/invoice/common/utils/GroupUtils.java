package com.invoice.common.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * java 集合分组工具
 */
public class GroupUtils {

    /**
     * 根据集合对象里面某个字段进行分组
     * @param list 需要分组的集合
     * @param groupBy 按什么分组
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, List<V>> group(Collection<V> list, GroupBy<K, V> groupBy) {
        MultiValueMap<K, V> mvm = new LinkedMultiValueMap<K, V>();
        for (V v : list) {
            mvm.add(groupBy.getKey(v), v);
        }
        return mvm;
    }

    /**
     * 内部接口 提供回调
     * @param <K>
     * @param <V>
     */
    public static interface GroupBy<K, V> {

        /**
         * 获取分组条件key
         *
         * @param v
         *            分组集合中当前对象
         * @return 分组条件key
         */
        K getKey(V v);
    }
}
