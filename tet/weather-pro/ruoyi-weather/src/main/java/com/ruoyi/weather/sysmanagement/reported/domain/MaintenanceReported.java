package com.ruoyi.weather.sysmanagement.reported.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 维保上报对象 t_maintenance_reported
 * 
 * @author yyearn
 * @date 2021-03-22
 */
public class MaintenanceReported extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 告警类型 */
    @Excel(name = "告警类型")
    private Long alarmType;

    /** 上报时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上报时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportedTime;

    /** 上报人 */
    @Excel(name = "上报人")
    private String reportedUser;

    /** 告警内容 */
    @Excel(name = "告警内容")
    private String alarmContent;

    /** 错误描述 */
    @Excel(name = "错误描述")
    private String errorDescription;

    /** 状态(待通知、已通知、已恢复) */
    @Excel(name = "状态(待通知、已通知、已恢复)")
    private String state;

    /** 操作记录 */
    @Excel(name = "操作记录")
    private String operatingRecord;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAlarmType(Long alarmType) 
    {
        this.alarmType = alarmType;
    }

    public Long getAlarmType() 
    {
        return alarmType;
    }
    public void setReportedTime(Date reportedTime) 
    {
        this.reportedTime = reportedTime;
    }

    public Date getReportedTime() 
    {
        return reportedTime;
    }
    public void setReportedUser(String reportedUser) 
    {
        this.reportedUser = reportedUser;
    }

    public String getReportedUser() 
    {
        return reportedUser;
    }
    public void setAlarmContent(String alarmContent) 
    {
        this.alarmContent = alarmContent;
    }

    public String getAlarmContent() 
    {
        return alarmContent;
    }
    public void setErrorDescription(String errorDescription) 
    {
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() 
    {
        return errorDescription;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setOperatingRecord(String operatingRecord) 
    {
        this.operatingRecord = operatingRecord;
    }

    public String getOperatingRecord() 
    {
        return operatingRecord;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("alarmType", getAlarmType())
            .append("reportedTime", getReportedTime())
            .append("reportedUser", getReportedUser())
            .append("alarmContent", getAlarmContent())
            .append("errorDescription", getErrorDescription())
            .append("remark", getRemark())
            .append("state", getState())
            .append("operatingRecord", getOperatingRecord())
            .toString();
    }
}
