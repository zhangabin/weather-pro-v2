package com.ruoyi.weather.sysmanagement.station.service;

import java.util.List;

import com.ruoyi.weather.sysmanagement.station.body.RainfallStationAndWorkAreaBody;
import com.ruoyi.weather.sysmanagement.station.domain.TRainfallStation;

/**
 * 雨量站信息Service接口
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
public interface ITRainfallStationService 
{
    /**
     * 查询雨量站信息
     * 
     * @param id 雨量站信息ID
     * @return 雨量站信息
     */
    public TRainfallStation selectTRainfallStationById(Long id);

    /**
     * 查询雨量站信息列表
     * 
     * @param tRainfallStation 雨量站信息
     * @return 雨量站信息集合
     */
    public List<TRainfallStation> selectTRainfallStationList(TRainfallStation tRainfallStation);

    /**
     * 查询雨量站列表（携带工区信息）
     * @return 集合
     */
    public List<RainfallStationAndWorkAreaBody> selectTRainfallStationAndWorkAreaList();

    /**
     * 新增雨量站信息
     * 
     * @param tRainfallStation 雨量站信息
     * @return 结果
     */
    public int insertTRainfallStation(TRainfallStation tRainfallStation);

    /**
     * 修改雨量站信息
     * 
     * @param tRainfallStation 雨量站信息
     * @return 结果
     */
    public int updateTRainfallStation(TRainfallStation tRainfallStation);

    /**
     * 批量删除雨量站信息
     * 
     * @param ids 需要删除的雨量站信息ID
     * @return 结果
     */
    public int deleteTRainfallStationByIds(Long[] ids);

    /**
     * 删除雨量站信息信息
     * 
     * @param id 雨量站信息ID
     * @return 结果
     */
    public int deleteTRainfallStationById(Long id);
}
