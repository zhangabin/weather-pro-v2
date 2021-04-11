package com.ruoyi.weather.sysmanagement.station.mapper;

import java.util.List;

import com.ruoyi.weather.sysmanagement.station.body.RainfallStationAndWorkAreaBody;
import com.ruoyi.weather.sysmanagement.station.domain.TRainfallStation;
import org.springframework.stereotype.Repository;

/**
 * 雨量站信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@Repository
public interface TRainfallStationMapper 
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
     * 删除雨量站信息
     * 
     * @param id 雨量站信息ID
     * @return 结果
     */
    public int deleteTRainfallStationById(Long id);

    /**
     * 批量删除雨量站信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTRainfallStationByIds(Long[] ids);
}
