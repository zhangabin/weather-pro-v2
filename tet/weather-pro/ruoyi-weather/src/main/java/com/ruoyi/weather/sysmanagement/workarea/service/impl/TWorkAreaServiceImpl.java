package com.ruoyi.weather.sysmanagement.workarea.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.weather.sysmanagement.workarea.mapper.TWorkAreaMapper;
import com.ruoyi.weather.sysmanagement.workarea.domain.TWorkArea;
import com.ruoyi.weather.sysmanagement.workarea.service.ITWorkAreaService;

/**
 * 工区信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@Service
public class TWorkAreaServiceImpl implements ITWorkAreaService 
{
    @Autowired
    private TWorkAreaMapper tWorkAreaMapper;

    /**
     * 查询工区信息
     * 
     * @param id 工区信息ID
     * @return 工区信息
     */
    @Override
    public TWorkArea selectTWorkAreaById(Long id)
    {
        return tWorkAreaMapper.selectTWorkAreaById(id);
    }

    /**
     * 查询工区信息列表
     * 
     * @param tWorkArea 工区信息
     * @return 工区信息
     */
    @Override
    public List<TWorkArea> selectTWorkAreaList(TWorkArea tWorkArea)
    {
        return tWorkAreaMapper.selectTWorkAreaList(tWorkArea);
    }

    /**
     * 新增工区信息
     * 
     * @param tWorkArea 工区信息
     * @return 结果
     */
    @Override
    public int insertTWorkArea(TWorkArea tWorkArea)
    {
        return tWorkAreaMapper.insertTWorkArea(tWorkArea);
    }

    /**
     * 修改工区信息
     * 
     * @param tWorkArea 工区信息
     * @return 结果
     */
    @Override
    public int updateTWorkArea(TWorkArea tWorkArea)
    {
        return tWorkAreaMapper.updateTWorkArea(tWorkArea);
    }

    /**
     * 批量删除工区信息
     * 
     * @param ids 需要删除的工区信息ID
     * @return 结果
     */
    @Override
    public int deleteTWorkAreaByIds(Long[] ids)
    {
        return tWorkAreaMapper.deleteTWorkAreaByIds(ids);
    }

    /**
     * 删除工区信息信息
     * 
     * @param id 工区信息ID
     * @return 结果
     */
    @Override
    public int deleteTWorkAreaById(Long id)
    {
        return tWorkAreaMapper.deleteTWorkAreaById(id);
    }
}
