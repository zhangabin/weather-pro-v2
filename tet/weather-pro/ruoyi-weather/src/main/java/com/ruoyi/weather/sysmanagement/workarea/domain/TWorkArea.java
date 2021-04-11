package com.ruoyi.weather.sysmanagement.workarea.domain;

import com.ruoyi.weather.sysmanagement.station.domain.TRainfallStation;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 工区信息对象 t_work_area
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
public class TWorkArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 工区名 */
    @Excel(name = "工区名")
    private String workAreaName;

    /** 起点里程 */
    @Excel(name = "起点里程")
    private String startingPoint;

    /** 终点里程 */
    @Excel(name = "终点里程")
    private String terminus;

    /** 中心里程数 */
    @Excel(name = "中心里程数")
    private String centre;

    /** 所属路线 */
    @Excel(name = "所属路线")
    private String line;
    
    /** 中心里程经度 */
    private String centreLongitude;
    
    /** 中心里程纬度  */
    private String centreLatitude;

    private List<TRainfallStation> tRainfallStations;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWorkAreaName(String workAreaName) 
    {
        this.workAreaName = workAreaName;
    }

    public String getWorkAreaName() 
    {
        return workAreaName;
    }
    public void setStartingPoint(String startingPoint) 
    {
        this.startingPoint = startingPoint;
    }

    public String getStartingPoint() 
    {
        return startingPoint;
    }
    public void setTerminus(String terminus) 
    {
        this.terminus = terminus;
    }

    public String getTerminus() 
    {
        return terminus;
    }
    public void setCentre(String centre) 
    {
        this.centre = centre;
    }

    public String getCentre() 
    {
        return centre;
    }
    public void setLine(String line) 
    {
        this.line = line;
    }

    public String getLine() 
    {
        return line;
    }
    
    public String getCentreLongitude() {
		return centreLongitude;
	}

	public void setCentreLongitude(String centreLongitude) {
		this.centreLongitude = centreLongitude;
	}

	public String getCentreLatitude() {
		return centreLatitude;
	}

	public void setCentreLatitude(String centreLatitude) {
		this.centreLatitude = centreLatitude;
	}

	public List<TRainfallStation> gettRainfallStations() {
        return tRainfallStations;
    }

    public void settRainfallStations(List<TRainfallStation> tRainfallStations) {
        this.tRainfallStations = tRainfallStations;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workAreaName", getWorkAreaName())
            .append("startingPoint", getStartingPoint())
            .append("terminus", getTerminus())
            .append("centre", getCentre())
            .append("line", getLine())
            .toString();
    }
}
