package com.ruoyi.weather.sysmanagement.forecast.mapper;

import java.util.List;
import com.ruoyi.weather.sysmanagement.forecast.domain.TrendForecast;

/**
 * 趋势预报管理Mapper接口
 * 
 * @author Gaojide gaojide_109@163.com
 * @date 2021-03-19
 */
public interface TrendForecastMapper 
{
    /**
     * 查询趋势预报管理
     * 
     * @param id 趋势预报管理ID
     * @return 趋势预报管理
     */
    public TrendForecast selectTrendForecastById(Long id);

    /**
     * 查询趋势预报管理列表
     * 
     * @param trendForecast 趋势预报管理
     * @return 趋势预报管理集合
     */
    public List<TrendForecast> selectTrendForecastList(TrendForecast trendForecast);

    /**
     * 新增趋势预报管理
     * 
     * @param trendForecast 趋势预报管理
     * @return 结果
     */
    public int insertTrendForecast(TrendForecast trendForecast);

    /**
     * 修改趋势预报管理
     * 
     * @param trendForecast 趋势预报管理
     * @return 结果
     */
    public int updateTrendForecast(TrendForecast trendForecast);

    /**
     * 删除趋势预报管理
     * 
     * @param id 趋势预报管理ID
     * @return 结果
     */
    public int deleteTrendForecastById(Long id);

    /**
     * 批量删除趋势预报管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTrendForecastByIds(Long[] ids);
}
