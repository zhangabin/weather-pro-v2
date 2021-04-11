package com.ruoyi.weather.sysmanagement.auto.station.mapper;

import com.ruoyi.weather.sysmanagement.auto.station.domain.TAutoStation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TAutoStationMapper {
    int save (List<TAutoStation> autoStations);
}