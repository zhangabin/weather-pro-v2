package com.ruoyi.weather.sysmanagement.workareaforecast.controller;

import java.util.List;
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
import com.ruoyi.weather.sysmanagement.workareaforecast.domain.WorkAreaForecast;
import com.ruoyi.weather.sysmanagement.workareaforecast.service.IWorkAreaForecastService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工区预报数据Controller
 * 
 * @author Gaojide gaojide_109@163.com
 * @date 2021-03-29
 */
@RestController
@RequestMapping("/workareaforecast/workAreaForecast")
public class WorkAreaForecastController extends BaseController
{
    @Autowired
    private IWorkAreaForecastService workAreaForecastService;

    /**
     * 查询工区预报数据列表
     */
    @GetMapping("/list")
    public TableDataInfo list(WorkAreaForecast workAreaForecast)
    {
        startPage();
        List<WorkAreaForecast> list = workAreaForecastService.selectWorkAreaForecastList(workAreaForecast);
        return getDataTable(list);
    }

    /**
     * 导出工区预报数据列表
     */
    @Log(title = "工区预报数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WorkAreaForecast workAreaForecast)
    {
        List<WorkAreaForecast> list = workAreaForecastService.selectWorkAreaForecastList(workAreaForecast);
        ExcelUtil<WorkAreaForecast> util = new ExcelUtil<WorkAreaForecast>(WorkAreaForecast.class);
        return util.exportExcel(list, "workAreaForecast");
    }

    /**
     * 获取工区预报数据详细信息
     */
    @GetMapping(value = "/{workAreaId}")
    public AjaxResult getInfo(@PathVariable("workAreaId") Long workAreaId)
    {
        return AjaxResult.success(workAreaForecastService.selectWorkAreaForecastById(workAreaId));
    }

    /**
     * 新增工区预报数据
     */
    @Log(title = "工区预报数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkAreaForecast workAreaForecast)
    {
        return toAjax(workAreaForecastService.insertWorkAreaForecast(workAreaForecast));
    }

    /**
     * 修改工区预报数据
     */
    @Log(title = "工区预报数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkAreaForecast workAreaForecast)
    {
        return toAjax(workAreaForecastService.updateWorkAreaForecast(workAreaForecast));
    }

    /**
     * 删除工区预报数据
     */
    @Log(title = "工区预报数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workAreaIds}")
    public AjaxResult remove(@PathVariable Long[] workAreaIds)
    {
        return toAjax(workAreaForecastService.deleteWorkAreaForecastByIds(workAreaIds));
    }
    
    /**
    * @title 根据线路和选择的预报日期查询数据,服务于车站预报的H5端
    * @author Gaojide gaojide_109@163.com
    * @date 2021-04-08
     */
    @GetMapping("/getForecastByLineAndForecastDate")
    public AjaxResult getForecastByLineAndForecastDate(WorkAreaForecast workAreaForecast){
    	return AjaxResult.success(workAreaForecastService.getForecastByLineAndForecastDate(workAreaForecast));
    }
}
