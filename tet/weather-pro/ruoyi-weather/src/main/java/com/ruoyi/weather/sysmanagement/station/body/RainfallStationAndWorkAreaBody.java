package com.ruoyi.weather.sysmanagement.station.body;

import com.ruoyi.common.annotation.Excel;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 雨量计站点携带工区信息实体
 * @author 良子
 * @title: RainfallStationAndWorkAreaBody
 * @projectName weather
 * @description: TODO
 * @date 2021/3/26 14:54
 */
public class RainfallStationAndWorkAreaBody {

    /** id */
    private Long id;

    /** 工区id */
    private Long workAreaId;

    /** 安装地点 */
    private String installationSite;

    /** 责任区段 */
    private String responsibilityInterval;

    /** 责任工区 */
    private String responsibilityWorkArea;

    /** 经度 */
    private String longitude;

    /** 纬度 */
    private String latitude;

    /*工区名*/
    private String workAreaName;

    /** 所属铁路线 */
    private String line;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkAreaId() {
        return workAreaId;
    }

    public void setWorkAreaId(Long workAreaId) {
        this.workAreaId = workAreaId;
    }

    public String getInstallationSite() {
        return installationSite;
    }

    public void setInstallationSite(String installationSite) {
        this.installationSite = installationSite;
    }

    public String getResponsibilityInterval() {
        return responsibilityInterval;
    }

    public void setResponsibilityInterval(String responsibilityInterval) {
        this.responsibilityInterval = responsibilityInterval;
    }

    public String getResponsibilityWorkArea() {
        return responsibilityWorkArea;
    }

    public void setResponsibilityWorkArea(String responsibilityWorkArea) {
        this.responsibilityWorkArea = responsibilityWorkArea;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getWorkAreaName() {
        return workAreaName;
    }

    public void setWorkAreaName(String workAreaName) {
        this.workAreaName = workAreaName;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
