package com.ruoyi.weather.sysmanagement.station.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.weather.sysmanagement.station.body.RainfallStationAndWorkAreaBody;
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
import com.ruoyi.weather.sysmanagement.station.domain.TRainfallStation;
import com.ruoyi.weather.sysmanagement.station.service.ITRainfallStationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 雨量站信息Controller
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@RestController
@RequestMapping("/station/rainfall_station")
public class TRainfallStationController extends BaseController
{
    @Autowired
    private ITRainfallStationService tRainfallStationService;

    /**
     * 查询雨量站信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TRainfallStation tRainfallStation)
    {
        startPage();
        List<TRainfallStation> list = tRainfallStationService.selectTRainfallStationList(tRainfallStation);
        return getDataTable(list);
    }

    /**
     * 查询雨量站信息列表(携带工区)
     */
    @GetMapping("/list/wa")
    public TableDataInfo listAndWorkArea()
    {
        startPage();
        List<RainfallStationAndWorkAreaBody> list = tRainfallStationService.selectTRainfallStationAndWorkAreaList();
        return getDataTable(list);
    }

    /**
     * 导出雨量站信息列表
     */
    @Log(title = "雨量站信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TRainfallStation tRainfallStation)
    {
        List<TRainfallStation> list = tRainfallStationService.selectTRainfallStationList(tRainfallStation);
        ExcelUtil<TRainfallStation> util = new ExcelUtil<TRainfallStation>(TRainfallStation.class);
        return util.exportExcel(list, "rainfall_station");
    }

    /**
     * 获取雨量站信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tRainfallStationService.selectTRainfallStationById(id));
    }

    /**
     * 新增雨量站信息
     */
    @Log(title = "雨量站信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TRainfallStation tRainfallStation)
    {
        return toAjax(tRainfallStationService.insertTRainfallStation(tRainfallStation));
    }

    /**
     * 修改雨量站信息
     */
    @Log(title = "雨量站信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TRainfallStation tRainfallStation)
    {
        return toAjax(tRainfallStationService.updateTRainfallStation(tRainfallStation));
    }

    /**
     * 删除雨量站信息
     */
    @Log(title = "雨量站信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tRainfallStationService.deleteTRainfallStationByIds(ids));
    }
}
