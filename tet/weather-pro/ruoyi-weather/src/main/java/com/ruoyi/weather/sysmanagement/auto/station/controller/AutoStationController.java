package com.ruoyi.weather.sysmanagement.auto.station.controller;

import com.ruoyi.weather.sysmanagement.auto.station.domain.StationReturnVo;
import com.ruoyi.weather.sysmanagement.auto.station.domain.TAutoStation;
import com.ruoyi.weather.sysmanagement.auto.station.service.AutoStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MyController
 * @Author 1129017402@qq.com
 * @Date 2021/2/3 16:46
 */
@RestController
@RequestMapping("/hahaha")
public class AutoStationController {

    @Autowired
    private AutoStationService autoStationService;


    @GetMapping("/a")
    public StationReturnVo test(){
        return autoStationService.bs();
    }


}
