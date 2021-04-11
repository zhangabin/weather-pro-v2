package com.ruoyi.weather.sysmanagement.workareaforecast.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.weather.sysmanagement.workareaforecast.domain.WorkAreaForecast;

/**
 * 工区预报数据Service接口
 * 
 * @author Gaojide gaojide_109@163.com
 * @date 2021-03-29
 */
public interface IWorkAreaForecastService 
{
    /**
     * 查询工区预报数据
     * 
     * @param workAreaId 工区预报数据ID
     * @return 工区预报数据
     */
    public Map<String,List<WorkAreaForecast>> selectWorkAreaForecastById(Long workAreaId);

    /**
     * 查询工区预报数据列表
     * 
     * @param workAreaForecast 工区预报数据
     * @return 工区预报数据集合
     */
    public List<WorkAreaForecast> selectWorkAreaForecastList(WorkAreaForecast workAreaForecast);

    /**
     * 新增工区预报数据
     * 
     * @param workAreaForecast 工区预报数据
     * @return 结果
     */
    public int insertWorkAreaForecast(WorkAreaForecast workAreaForecast);
    
    /**
    * @title 批量插入
    * @author Gaojide gaojide_109@163.com
    * @date 2021-03-30
     */
    public int insertWorkAreaForecastList(List<WorkAreaForecast> workAreaForecastList);

    /**
     * 修改工区预报数据
     * 
     * @param workAreaForecast 工区预报数据
     * @return 结果
     */
    public int updateWorkAreaForecast(WorkAreaForecast workAreaForecast);

    /**
     * 批量删除工区预报数据
     * 
     * @param workAreaIds 需要删除的工区预报数据ID
     * @return 结果
     */
    public int deleteWorkAreaForecastByIds(Long[] workAreaIds);

    /**
     * 删除工区预报数据信息
     * 
     * @param workAreaId 工区预报数据ID
     * @return 结果
     */
    public int deleteWorkAreaForecastById(Long workAreaId);
    
    /**
     * @title 根据到达时间删除数据
     * @author Gaojide gaojide_109@163.com
     * @date 2021-03-31
     */
    public int deleteWorkAreaForecastByStartTimes(String startDateTime);
    
    /**
     * @title 根据线路和选择的预报日期查询数据,服务于车站预报的H5端
     * @author Gaojide gaojide_109@163.com
     * @date 2021-04-08
     */
     public Map<String,Object> getForecastByLineAndForecastDate(WorkAreaForecast workAreaForecast);
}
