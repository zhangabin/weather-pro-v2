package com.ruoyi.weather.sysmanagement.auto.station.service.impl;


import com.ruoyi.weather.music.api.StationApi;
import com.ruoyi.weather.sysmanagement.auto.station.domain.StationReturnVo;
import com.ruoyi.weather.sysmanagement.auto.station.mapper.TAutoStationMapper;
import com.ruoyi.weather.sysmanagement.auto.station.service.AutoStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName MusicServiceImpl
 * @Author 1129017402@qq.com
 * @Date 2021/2/3 16:18
 */
@Service
public class AutoStationServiceImpl implements AutoStationService {

    private final static Logger log = LoggerFactory.getLogger(AutoStationServiceImpl.class);
    @Resource
    private StationApi stationApi;

    @Autowired
    private TAutoStationMapper autoStationMapper;

    /**
     * 同步区域自动站站点信息
     * @return 完成条数
     */
    @Override
    public Integer autoStation(){
        long l = System.currentTimeMillis();
        String sign = "2630B291E2BB14323B7286B778990AA0";
        StationReturnVo stationReturnVo = stationApi.getRegionalAutomaticStationSiteInformation(l, sign);
        return autoStationMapper.save(stationReturnVo.getDS());
    }

    @Override
    public StationReturnVo bs() {

        StationReturnVo test = stationApi.test();
        log.info("===================>bs");
        autoStationMapper.save(test.getDS());
        return test;
    }
}
