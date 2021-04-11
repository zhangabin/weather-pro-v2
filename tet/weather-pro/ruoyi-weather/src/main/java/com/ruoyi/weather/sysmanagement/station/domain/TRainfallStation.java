package com.ruoyi.weather.sysmanagement.station.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 雨量站信息对象 t_rainfall_station
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
public class TRainfallStation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 工区id */
    @Excel(name = "工区id")
    private Long workAreaId;

    /** 安装地点 */
    @Excel(name = "安装地点")
    private String installationSite;

    /** 责任区段 */
    @Excel(name = "责任区段")
    private String responsibilityInterval;

    /** 责任工区 */
    @Excel(name = "责任工区")
    private String responsibilityWorkArea;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWorkAreaId(Long workAreaId) 
    {
        this.workAreaId = workAreaId;
    }

    public Long getWorkAreaId() 
    {
        return workAreaId;
    }
    public void setInstallationSite(String installationSite) 
    {
        this.installationSite = installationSite;
    }

    public String getInstallationSite() 
    {
        return installationSite;
    }
    public void setResponsibilityInterval(String responsibilityInterval) 
    {
        this.responsibilityInterval = responsibilityInterval;
    }

    public String getResponsibilityInterval() 
    {
        return responsibilityInterval;
    }
    public void setResponsibilityWorkArea(String responsibilityWorkArea) 
    {
        this.responsibilityWorkArea = responsibilityWorkArea;
    }

    public String getResponsibilityWorkArea() 
    {
        return responsibilityWorkArea;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workAreaId", getWorkAreaId())
            .append("installationSite", getInstallationSite())
            .append("responsibilityInterval", getResponsibilityInterval())
            .append("responsibilityWorkArea", getResponsibilityWorkArea())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .toString();
    }
}
