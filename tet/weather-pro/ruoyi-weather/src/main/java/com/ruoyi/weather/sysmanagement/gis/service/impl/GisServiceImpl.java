package com.ruoyi.weather.sysmanagement.gis.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.weather.sysmanagement.gis.service.GisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

/**
 * @author 良子
 * @title: GisServiceImpl
 * @projectName ruoyi
 * @description: TODO
 * @date 2021/3/24 15:43
 */
@Service
public class GisServiceImpl implements GisService {

    private static final String GIS_KEY = "gis";

    @Autowired
    private RedisCache redisCache;

    @Override
    public Map<String, Object> getXsRailGisDataFormCache() throws IOException {
        Map<String, Object> gis = redisCache.getCacheMap(GIS_KEY);
        if (CollectionUtils.isEmpty(gis)) {
            ClassPathResource xs = new ClassPathResource("gis/xinshuo.geojson");
            InputStream inputStream = xs.getInputStream();
            Map map = new ObjectMapper().readValue(inputStream, Map.class);
            redisCache.setCacheMap(GIS_KEY,map);
            return map;
        }else{
            return gis;
        }
    }
}
