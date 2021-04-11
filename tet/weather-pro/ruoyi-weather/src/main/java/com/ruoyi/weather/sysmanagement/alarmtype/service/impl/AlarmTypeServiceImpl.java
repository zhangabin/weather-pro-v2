package com.ruoyi.weather.sysmanagement.alarmtype.service.impl;

import com.ruoyi.weather.sysmanagement.alarmtype.domain.AlarmType;
import com.ruoyi.weather.sysmanagement.alarmtype.mapper.AlarmTypeMapper;
import com.ruoyi.weather.sysmanagement.alarmtype.service.AlarmTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 告警类型Service业务层处理
 * 
 * @author yyearn
 * @date 2021-03-23
 */
@Service
public class AlarmTypeServiceImpl implements AlarmTypeService
{
    @Autowired
    private AlarmTypeMapper alarmTypeMapper;

    /**
     * 获取警告类型列表
     *
     * @param state 状态
     * @return 类型列表
     */
    @Override
    public List<AlarmType> getAlarmTypes(String state) {
        return alarmTypeMapper.selectList(state);
    }
}
