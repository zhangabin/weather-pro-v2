package com.ruoyi.weather.sysmanagement.station.service.impl;

import java.util.List;

import com.ruoyi.weather.sysmanagement.station.body.RainfallStationAndWorkAreaBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.weather.sysmanagement.station.mapper.TRainfallStationMapper;
import com.ruoyi.weather.sysmanagement.station.domain.TRainfallStation;
import com.ruoyi.weather.sysmanagement.station.service.ITRainfallStationService;

/**
 * 雨量站信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@Service
public class TRainfallStationServiceImpl implements ITRainfallStationService 
{
    @Autowired
    private TRainfallStationMapper tRainfallStationMapper;

    /**
     * 查询雨量站信息
     * 
     * @param id 雨量站信息ID
     * @return 雨量站信息
     */
    @Override
    public TRainfallStation selectTRainfallStationById(Long id)
    {
        return tRainfallStationMapper.selectTRainfallStationById(id);
    }
    /**
     * 查询雨量站信息列表
     * 
     * @param tRainfallStation 雨量站信息
     * @return 雨量站信息
     */
    @Override
    public List<TRainfallStation> selectTRainfallStationList(TRainfallStation tRainfallStation)
    {
        return tRainfallStationMapper.selectTRainfallStationList(tRainfallStation);
    }

    @Override
    public List<RainfallStationAndWorkAreaBody> selectTRainfallStationAndWorkAreaList() {
        return tRainfallStationMapper.selectTRainfallStationAndWorkAreaList();
    }

    /**
     * 新增雨量站信息
     * 
     * @param tRainfallStation 雨量站信息
     * @return 结果
     */
    @Override
    public int insertTRainfallStation(TRainfallStation tRainfallStation)
    {
        return tRainfallStationMapper.insertTRainfallStation(tRainfallStation);
    }

    /**
     * 修改雨量站信息
     * 
     * @param tRainfallStation 雨量站信息
     * @return 结果
     */
    @Override
    public int updateTRainfallStation(TRainfallStation tRainfallStation)
    {
        return tRainfallStationMapper.updateTRainfallStation(tRainfallStation);
    }

    /**
     * 批量删除雨量站信息
     * 
     * @param ids 需要删除的雨量站信息ID
     * @return 结果
     */
    @Override
    public int deleteTRainfallStationByIds(Long[] ids)
    {
        return tRainfallStationMapper.deleteTRainfallStationByIds(ids);
    }

    /**
     * 删除雨量站信息信息
     * 
     * @param id 雨量站信息ID
     * @return 结果
     */
    @Override
    public int deleteTRainfallStationById(Long id)
    {
        return tRainfallStationMapper.deleteTRainfallStationById(id);
    }
}
