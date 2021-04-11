package com.ruoyi.weather.sysmanagement.reported.mapper;

import java.util.List;
import com.ruoyi.weather.sysmanagement.reported.domain.MaintenanceReported;
import org.springframework.stereotype.Component;

/**
 * 维保上报Mapper接口
 * 
 * @author yyearn
 * @date 2021-03-22
 */
@Component
public interface MaintenanceReportedMapper 
{
    /**
     * 查询维保上报
     * 
     * @param id 维保上报ID
     * @return 维保上报
     */
    public MaintenanceReported selectMaintenanceReportedById(Long id);

    /**
     * 查询维保上报列表
     * 
     * @param maintenanceReported 维保上报
     * @return 维保上报集合
     */
    public List<MaintenanceReported> selectMaintenanceReportedList(MaintenanceReported maintenanceReported);

    /**
     * 新增维保上报
     * 
     * @param maintenanceReported 维保上报
     * @return 结果
     */
    public int insertMaintenanceReported(MaintenanceReported maintenanceReported);

    /**
     * 修改维保上报
     * 
     * @param maintenanceReported 维保上报
     * @return 结果
     */
    public int updateMaintenanceReported(MaintenanceReported maintenanceReported);

    /**
     * 删除维保上报
     * 
     * @param id 维保上报ID
     * @return 结果
     */
    public int deleteMaintenanceReportedById(Long id);

    /**
     * 批量删除维保上报
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaintenanceReportedByIds(Long[] ids);
}
