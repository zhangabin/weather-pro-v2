package com.ruoyi.weather.sysmanagement.reported.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.weather.sysmanagement.reported.domain.MaintenanceReported;
import com.ruoyi.weather.sysmanagement.reported.service.IMaintenanceReportedService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 维保上报Controller
 * 
 * @author yyearn
 * @date 2021-03-22
 */
@RestController
@RequestMapping("/reported/reported")
public class MaintenanceReportedController extends BaseController
{
    @Autowired
    private IMaintenanceReportedService maintenanceReportedService;

    /**
     * 查询维保上报列表
     */
    @PreAuthorize("@ss.hasPermi('reported:reported:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaintenanceReported maintenanceReported)
    {
        startPage();
        List<MaintenanceReported> list = maintenanceReportedService.selectMaintenanceReportedList(maintenanceReported);
        return getDataTable(list);
    }

    /**
     * 导出维保上报列表
     */
    @PreAuthorize("@ss.hasPermi('reported:reported:export')")
    @Log(title = "维保上报", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MaintenanceReported maintenanceReported)
    {
        List<MaintenanceReported> list = maintenanceReportedService.selectMaintenanceReportedList(maintenanceReported);
        ExcelUtil<MaintenanceReported> util = new ExcelUtil<MaintenanceReported>(MaintenanceReported.class);
        return util.exportExcel(list, "reported");
    }

    /**
     * 获取维保上报详细信息
     */
    @PreAuthorize("@ss.hasPermi('reported:reported:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(maintenanceReportedService.selectMaintenanceReportedById(id));
    }

    /**
     * 新增维保上报
     */
    @PreAuthorize("@ss.hasPermi('reported:reported:add')")
    @Log(title = "维保上报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaintenanceReported maintenanceReported)
    {
        return toAjax(maintenanceReportedService.insertMaintenanceReported(maintenanceReported));
    }

    /**
     * 修改维保上报
     */
    @PreAuthorize("@ss.hasPermi('reported:reported:edit')")
    @Log(title = "维保上报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaintenanceReported maintenanceReported)
    {
        return toAjax(maintenanceReportedService.updateMaintenanceReported(maintenanceReported));
    }

    /**
     * 删除维保上报
     */
    @PreAuthorize("@ss.hasPermi('reported:reported:remove')")
    @Log(title = "维保上报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(maintenanceReportedService.deleteMaintenanceReportedByIds(ids));
    }
}
