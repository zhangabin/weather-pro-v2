package com.ruoyi.weather.sysmanagement.alarmtype.mapper;

import com.ruoyi.weather.sysmanagement.alarmtype.domain.AlarmType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 重要天气提示Mapper接口
 * 
 * @author yyearn
 * @date 2021-03-18
 */
@Component
public interface AlarmTypeMapper
{

    public List<AlarmType> selectList(@Param("state") String state);


}
