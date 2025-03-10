package edu.cugb.subject.domain.service.util;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import edu.cugb.subject.domain.entity.SubjectCategoryBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Author: pengjia
 * @Description: 缓存工具类
 */
public class CacheUtil<K,V> {
    private Cache<String,String> localCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();

    /**
     *
     * @param cacheKey
     * @param clazz
     * @param function
     * @return 获取本地缓存
     */
    public List<V> getResult(String cacheKey, Class<V> clazz, Function<String,List<V>> function){
        List<V> resultList = new LinkedList<>();
        String content = localCache.getIfPresent(cacheKey);
        if (!StringUtils.isBlank(content)){
           resultList = JSON.parseArray(content, clazz);
        }else {
            resultList = function.apply(cacheKey);
            if (!CollectionUtils.isEmpty(resultList)){
                localCache.put(cacheKey, JSON.toJSONString(resultList));
            }
        }

        return resultList;
    }

    public Map<K,V> getMapResult(String cacheKey, Class<V> clazz, Function<String,List<V>> function){
        //具体实现。。。。

        return new HashMap<>();
    }

}
