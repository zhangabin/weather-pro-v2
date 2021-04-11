package com.ruoyi.weather.sysmanagement.auto.station.service;

import com.ruoyi.weather.sysmanagement.auto.station.domain.StationReturnVo;
import com.ruoyi.weather.sysmanagement.auto.station.domain.TAutoStation;

/**
 * @ClassName MusicService
 * @Author 1129017402@qq.com
 * @Date 2021/2/3 16:18
 */
public interface AutoStationService {
    /**
     * 同步区域自动站站点信息
     * @return 完成条数
     */
    Integer autoStation();
    StationReturnVo bs();
}
