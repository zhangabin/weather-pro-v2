package com.ruoyi.weather.sysmanagement.tour.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 防洪值班记录对象 t_tour_situation
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
public class TourSituation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** 值班人员 */
    @Excel(name = "值班人员")
    private String dutyOfficer;

    /** 降雨情况 */
    @Excel(name = "降雨情况")
    private String rainfall;

    /** 报警等级 */
    @Excel(name = "报警等级")
    private String alarmLevel;

    /** 巡查开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "巡查开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectionTimeStart;

    /** 巡查结束时间 */
    @Excel(name = "巡查结束时间")
    private Date inspectionTimeEnd;

    /** 巡查地段 */
    @Excel(name = "巡查地段")
    private String inspectionLot;

    /** 巡查方式 */
    @Excel(name = "巡查方式")
    private String inspectionType;

    /** 巡查人数、车辆 */
    @Excel(name = "巡查人数、车辆")
    private String inspectorsVehicles;

    /** 巡查隐患情况 */
    @Excel(name = "巡查隐患情况")
    private String inspectionHiddenDangers;

    /** 处置措施 */
    @Excel(name = "处置措施")
    private String disposalMeasures;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getDutyOfficer() {
        return dutyOfficer;
    }

    public void setDutyOfficer(String dutyOfficer) {
        this.dutyOfficer = dutyOfficer;
    }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Date getInspectionTimeStart() {
        return inspectionTimeStart;
    }

    public void setInspectionTimeStart(Date inspectionTimeStart) {
        this.inspectionTimeStart = inspectionTimeStart;
    }

    public Date getInspectionTimeEnd() {
        return inspectionTimeEnd;
    }

    public void setInspectionTimeEnd(Date inspectionTimeEnd) {
        this.inspectionTimeEnd = inspectionTimeEnd;
    }

    public String getInspectionLot() {
        return inspectionLot;
    }

    public void setInspectionLot(String inspectionLot) {
        this.inspectionLot = inspectionLot;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getInspectorsVehicles() {
        return inspectorsVehicles;
    }

    public void setInspectorsVehicles(String inspectorsVehicles) {
        this.inspectorsVehicles = inspectorsVehicles;
    }

    public String getInspectionHiddenDangers() {
        return inspectionHiddenDangers;
    }

    public void setInspectionHiddenDangers(String inspectionHiddenDangers) {
        this.inspectionHiddenDangers = inspectionHiddenDangers;
    }

    public String getDisposalMeasures() {
        return disposalMeasures;
    }

    public void setDisposalMeasures(String disposalMeasures) {
        this.disposalMeasures = disposalMeasures;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordTime", getRecordTime())
            .append("dutyOfficer", getDutyOfficer())
            .append("rainfall", getRainfall())
            .append("alarmLevel", getAlarmLevel())
            .append("inspectionTimeStart", getInspectionTimeStart())
            .append("inspectionTimeEnd", getInspectionTimeEnd())
            .append("inspectionLot", getInspectionLot())
            .append("inspectionType", getInspectionType())
            .append("inspectorsVehicles", getInspectorsVehicles())
            .append("inspectionHiddenDangers", getInspectionHiddenDangers())
            .append("disposalMeasures", getDisposalMeasures())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
