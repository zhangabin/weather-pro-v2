package com.ruoyi.weather.sysmanagement.workareaforecast.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @title 工区预报数据对象 t_work_area_forecast
 * 
 * @author Gaojide gaojide_109@163.com
 * @date 2021-03-29
 */
public class WorkAreaForecast extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /************************这个实体类需要和解析格点数据的接口对接，根据返回的数据，有些属性不能用驼峰原则*****************************************************/
    
    /** 工区id */
    @Excel(name = "工区id")
    private Long id;

    /** 收到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收到时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date start_date_time;

    /** 资料时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "资料时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date_time;

    /** 温度 */
    @Excel(name = "温度")
    private Double temperature;

    /** 降水量 */
    @Excel(name = "降水量")
    private Double sum_precipitation;

    /** 风速 */
    @Excel(name = "风速")
    private Double wind_v;

    /** 风向 */
    @Excel(name = "风向")
    private Double wind_d;

    /** 相对湿度 */
    @Excel(name = "相对湿度")
    private Double relative_humidity;

    /** 压强 */
    @Excel(name = "压强")
    private Long pressure;

    /** 小时降水 */
    @Excel(name = "小时降水")
    private Double hour_precipitation;
    
    /** 开始时间 */
    private String startTime;
    
    /** 结束时间 */
    private String endTime;
    
    /** 预报小时 */
    private String forecastHour;
    
    /** 工区名称  */
    private String workName;
    
    /** 预报日期 */
    private String forecastDate;
    
    /** 铁路线路 */
    private String line;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public Date getStart_date_time() {
		return start_date_time;
	}

	public void setStart_date_time(Date start_date_time) {
		this.start_date_time = start_date_time;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getSum_precipitation() {
		return sum_precipitation;
	}

	public void setSum_precipitation(Double sum_precipitation) {
		this.sum_precipitation = sum_precipitation;
	}

	public Double getWind_v() {
		return wind_v;
	}

	public void setWind_v(Double wind_v) {
		this.wind_v = wind_v;
	}

	public Double getWind_d() {
		return wind_d;
	}

	public void setWind_d(Double wind_d) {
		this.wind_d = wind_d;
	}

	public Double getRelative_humidity() {
		return relative_humidity;
	}

	public void setRelative_humidity(Double relative_humidity) {
		this.relative_humidity = relative_humidity;
	}

	public Long getPressure() {
		return pressure;
	}

	public void setPressure(Long pressure) {
		this.pressure = pressure;
	}

	public Double getHour_precipitation() {
		return hour_precipitation;
	}

	public void setHour_precipitation(Double hour_precipitation) {
		this.hour_precipitation = hour_precipitation;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getForecastHour() {
		return forecastHour;
	}

	public void setForecastHour(String forecastHour) {
		this.forecastHour = forecastHour;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getForecastDate() {
		return forecastDate;
	}

	public void setForecastDate(String forecastDate) {
		this.forecastDate = forecastDate;
	}
	
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("startDateTime", getStart_date_time())
            .append("dateTime", getDate_time())
            .append("temperature", getTemperature())
            .append("sumPrecipitation", getSum_precipitation())
            .append("windV", getWind_v())
            .append("windD", getWind_d())
            .append("relativeHumidity", getRelative_humidity())
            .append("pressure", getPressure())
            .append("hourPrecipitation", getHour_precipitation())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("forecastHour", getForecastHour())
            .append("workName", getWorkName())
            .append("forecastDate", getForecastDate())
            .append("line", getLine())
            .toString();
    }
}
