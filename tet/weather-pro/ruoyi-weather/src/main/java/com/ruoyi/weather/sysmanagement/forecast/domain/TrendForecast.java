package com.ruoyi.weather.sysmanagement.forecast.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 趋势预报管理对象 t_trend_forecast
 * 
 * @author Gaojide gaojide_109@163.com
 * @date 2021-03-19
 */
public class TrendForecast extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 上传人 */
    @Excel(name = "上传人")
    private String uploadUser;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 24小时预报 */
    private String forecast24;

    /** 48-96小时预报 */
    private String forecast96;

    /** 状态(待发布、已发布、已撤回) */
    @Excel(name = "状态(待发布、已发布、已撤回)")
    private String state;

    /** 发布时间 */
    private Date publishTime;
    
    /** 填写48-96小时预报 时默认的预报值 */
    private String forecast96DefaultValue;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setUploadUser(String uploadUser) 
    {
        this.uploadUser = uploadUser;
    }

    public String getUploadUser() 
    {
        return uploadUser;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    public void setForecast24(String forecast24) 
    {
        this.forecast24 = forecast24;
    }

    public String getForecast24() 
    {
        return forecast24;
    }
    public void setForecast96(String forecast96) 
    {
        this.forecast96 = forecast96;
    }

    public String getForecast96() 
    {
        return forecast96;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setPublishTime(Date publishTime) 
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime() 
    {
        return publishTime;
    }

    public String getForecast96DefaultValue() {
		return forecast96DefaultValue;
	}

	public void setForecast96DefaultValue(String forecast9DefaultValue) {
		this.forecast96DefaultValue = forecast9DefaultValue;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("uploadUser", getUploadUser())
            .append("uploadTime", getUploadTime())
            .append("forecast24", getForecast24())
            .append("forecast96", getForecast96())
            .append("state", getState())
            .append("publishTime", getPublishTime())
            .append("forecast9DefaultValue", getForecast96DefaultValue())
            .toString();
    }
}
