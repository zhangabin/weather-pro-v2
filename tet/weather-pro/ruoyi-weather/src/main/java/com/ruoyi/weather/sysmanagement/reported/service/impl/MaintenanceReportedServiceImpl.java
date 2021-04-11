package com.ruoyi.weather.sysmanagement.reported.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.weather.sysmanagement.reported.mapper.MaintenanceReportedMapper;
import com.ruoyi.weather.sysmanagement.reported.domain.MaintenanceReported;
import com.ruoyi.weather.sysmanagement.reported.service.IMaintenanceReportedService;

/**
 * 维保上报Service业务层处理
 * 
 * @author yyearn
 * @date 2021-03-22
 */
@Service
public class MaintenanceReportedServiceImpl implements IMaintenanceReportedService
{
    @Autowired
    private MaintenanceReportedMapper maintenanceReportedMapper;

    /**
     * 查询维保上报
     * 
     * @param id 维保上报ID
     * @return 维保上报
     */
    @Override
    public MaintenanceReported selectMaintenanceReportedById(Long id)
    {
        return maintenanceReportedMapper.selectMaintenanceReportedById(id);
    }

    /**
     * 查询维保上报列表
     * 
     * @param maintenanceReported 维保上报
     * @return 维保上报
     */
    @Override
    public List<MaintenanceReported> selectMaintenanceReportedList(MaintenanceReported maintenanceReported)
    {
        return maintenanceReportedMapper.selectMaintenanceReportedList(maintenanceReported);
    }

    /**
     * 新增维保上报
     * 
     * @param maintenanceReported 维保上报
     * @return 结果
     */
    @Override
    public int insertMaintenanceReported(MaintenanceReported maintenanceReported)
    {
        return maintenanceReportedMapper.insertMaintenanceReported(maintenanceReported);
    }

    /**
     * 修改维保上报
     * 
     * @param maintenanceReported 维保上报
     * @return 结果
     */
    @Override
    public int updateMaintenanceReported(MaintenanceReported maintenanceReported)
    {
        return maintenanceReportedMapper.updateMaintenanceReported(maintenanceReported);
    }

    /**
     * 批量删除维保上报
     * 
     * @param ids 需要删除的维保上报ID
     * @return 结果
     */
    @Override
    public int deleteMaintenanceReportedByIds(Long[] ids)
    {
        return maintenanceReportedMapper.deleteMaintenanceReportedByIds(ids);
    }

    /**
     * 删除维保上报信息
     * 
     * @param id 维保上报ID
     * @return 结果
     */
    @Override
    public int deleteMaintenanceReportedById(Long id)
    {
        return maintenanceReportedMapper.deleteMaintenanceReportedById(id);
    }
}
