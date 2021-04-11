package com.ruoyi.weather.sysmanagement.alarmtype.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 告警类型 t_alarm_type
 * 
 * @author yyearn
 * @date 2021-03-23
 */
public class AlarmType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;
    /** 告警类型 */
    private String  alarmType;
    /** 状态*/
    private String state;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("alarmType",getAlarmType())
            .append("state", getState())
            .toString();
    }
}
