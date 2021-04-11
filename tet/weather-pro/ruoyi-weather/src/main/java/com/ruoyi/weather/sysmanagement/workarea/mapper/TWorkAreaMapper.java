package com.ruoyi.weather.sysmanagement.workarea.mapper;

import java.util.List;
import com.ruoyi.weather.sysmanagement.workarea.domain.TWorkArea;
import org.springframework.stereotype.Repository;

/**
 * 工区信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@Repository
public interface TWorkAreaMapper 
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
     * 删除工区信息
     * 
     * @param id 工区信息ID
     * @return 结果
     */
    public int deleteTWorkAreaById(Long id);

    /**
     * 批量删除工区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTWorkAreaByIds(Long[] ids);
}
