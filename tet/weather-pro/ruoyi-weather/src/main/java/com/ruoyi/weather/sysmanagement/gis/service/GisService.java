package com.ruoyi.weather.sysmanagement.gis.service;

import java.io.IOException;
import java.util.Map;

/**
 * @author 良子
 * @title: GisService
 * @projectName ruoyi
 * @description: TODO
 * @date 2021/3/24 15:43
 */
public interface GisService {

    /**
     * 获取新朔铁路gis数据（从缓存）
     * @return map
     */
    Map<String,Object> getXsRailGisDataFormCache() throws IOException;
}
