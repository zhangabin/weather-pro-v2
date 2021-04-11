package com.ruoyi.weather.sysmanagement.alarmtype.service;

import com.ruoyi.weather.sysmanagement.alarmtype.domain.AlarmType;

import java.util.List;

/**
 * 告警类型Service接口
 * 
 * @author yyearn
 * @date 2021-03-23
 */
public interface AlarmTypeService
{


    /**
     * 获取警告类型列表
     * @param state 状态
     * @return 类型列表
     */
    List<AlarmType> getAlarmTypes(String state);
}
