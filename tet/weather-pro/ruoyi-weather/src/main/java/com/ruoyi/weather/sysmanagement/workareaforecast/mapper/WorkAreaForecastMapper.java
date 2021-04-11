package com.ruoyi.weather.sysmanagement.workareaforecast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.weather.sysmanagement.workareaforecast.domain.WorkAreaForecast;

/**
 * 工区预报数据Mapper接口
 * 
 * @author Gaojide gaojide_109@163.com
 * @date 2021-03-29
 */
public interface WorkAreaForecastMapper 
{
    /**
     * 查询工区预报数据
     * 
     * @param workAreaId 工区预报数据ID
     * @return 工区预报数据
     */
    public List<WorkAreaForecast> selectWorkAreaForecastById(WorkAreaForecast workAreaForecast);

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
    public int insertWorkAreaForecastList(@Param("workAreaForecastList") List<WorkAreaForecast> workAreaForecastList);

    /**
     * 修改工区预报数据
     * 
     * @param workAreaForecast 工区预报数据
     * @return 结果
     */
    public int updateWorkAreaForecast(WorkAreaForecast workAreaForecast);

    /**
     * 删除工区预报数据
     * 
     * @param workAreaId 工区预报数据ID
     * @return 结果
     */
    public int deleteWorkAreaForecastById(Long workAreaId);

    /**
     * 批量删除工区预报数据
     * 
     * @param workAreaIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkAreaForecastByIds(Long[] workAreaIds);
    
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
    public List<WorkAreaForecast> getForecastByLineAndForecastDate(WorkAreaForecast workAreaForecast);
}


