package com.ruoyi.weather.sysmanagement.workarea.service;

import java.util.List;
import com.ruoyi.weather.sysmanagement.workarea.domain.TWorkArea;

/**
 * 工区信息Service接口
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
public interface ITWorkAreaService 
{
    /**
     * 查询工区信息
     * 
     * @param id 工区信息ID
     * @return 工区信息
     */
    public TWorkArea selectTWorkAreaById(Long id);

    /**
     * 查询工区信息列表
     * 
     * @param tWorkArea 工区信息
     * @return 工区信息集合
     */
    public List<TWorkArea> selectTWorkAreaList(TWorkArea tWorkArea);

    /**
     * 新增工区信息
     * 
     * @param tWorkArea 工区信息
     * @return 结果
     */
    public int insertTWorkArea(TWorkArea tWorkArea);

    /**
     * 修改工区信息
     * 
     * @param tWorkArea 工区信息
     * @return 结果
     */
    public int updateTWorkArea(TWorkArea tWorkArea);

    /**
     * 批量删除工区信息
     * 
     * @param ids 需要删除的工区信息ID
     * @return 结果
     */
    public int deleteTWorkAreaByIds(Long[] ids);

    /**
     * 删除工区信息信息
     * 
     * @param id 工区信息ID
     * @return 结果
     */
    public int deleteTWorkAreaById(Long id);
}
