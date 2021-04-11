package com.ruoyi.weather.sysmanagement.gis.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.weather.sysmanagement.gis.service.GisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 良子
 * @title: GISController
 * @projectName weather
 * @description: TODO
 * @date 2021/3/24 15:40
 */
@RestController
@RequestMapping("/gis")
public class GisController {

    @Autowired
    private GisService gisService;

    /**
     * 获取新朔铁路的gis数据
     * @return json
     */
    @GetMapping("xs")
    public AjaxResult getXsRailGisData(){
        try {
            return AjaxResult.success(gisService.getXsRailGisDataFormCache());
        }catch (Exception e) {
            return AjaxResult.error("请求地图数据错误!");
        }
    }
}
