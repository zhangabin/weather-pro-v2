package com.ruoyi.weather.sysmanagement.forecast.controller;

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
import com.ruoyi.weather.sysmanagement.forecast.domain.TrendForecast;
import com.ruoyi.weather.sysmanagement.forecast.service.ITrendForecastService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 趋势预报管理Controller
 * 
 * @author Gaojide gaojide_109@163.com
 * @date 2021-03-19
 */
@RestController
@RequestMapping("/forecast/forecast")
public class TrendForecastController extends BaseController
{
    @Autowired
    private ITrendForecastService trendForecastService;

    /**
     * 查询趋势预报管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TrendForecast trendForecast)
    {
        startPage();
        List<TrendForecast> list = trendForecastService.selectTrendForecastList(trendForecast);
        return getDataTable(list);
    }

    /**
     * 导出趋势预报管理列表
     */
    @Log(title = "趋势预报管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TrendForecast trendForecast)
    {
        List<TrendForecast> list = trendForecastService.selectTrendForecastList(trendForecast);
        ExcelUtil<TrendForecast> util = new ExcelUtil<TrendForecast>(TrendForecast.class);
        return util.exportExcel(list, "forecast");
    }

    /**
     * 获取趋势预报管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(trendForecastService.selectTrendForecastById(id));
    }

    /**
     * 新增趋势预报管理
     */
    @Log(title = "趋势预报管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrendForecast trendForecast)
    {
        return toAjax(trendForecastService.insertTrendForecast(trendForecast));
    }

    /**
     * 修改趋势预报管理
     */
    @Log(title = "趋势预报管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrendForecast trendForecast)
    {
        return toAjax(trendForecastService.updateTrendForecast(trendForecast));
    }

    /**
     * 删除趋势预报管理
     */
    @Log(title = "趋势预报管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(trendForecastService.deleteTrendForecastByIds(ids));
    }
}
