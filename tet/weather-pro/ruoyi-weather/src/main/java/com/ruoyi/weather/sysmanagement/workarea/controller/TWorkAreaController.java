package com.ruoyi.weather.sysmanagement.workarea.controller;

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
import com.ruoyi.weather.sysmanagement.workarea.domain.TWorkArea;
import com.ruoyi.weather.sysmanagement.workarea.service.ITWorkAreaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工区信息Controller
 * 
 * @author ruoyi
 * @date 2021-03-18
 */
@RestController
@RequestMapping("/weather/work_area")
public class TWorkAreaController extends BaseController
{
    @Autowired
    private ITWorkAreaService tWorkAreaService;

    /**
     * 查询工区信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TWorkArea tWorkArea)
    {
        startPage();
        List<TWorkArea> list = tWorkAreaService.selectTWorkAreaList(tWorkArea);
        return getDataTable(list);
    }

    /**
     * 导出工区信息列表
     */
    @Log(title = "工区信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TWorkArea tWorkArea)
    {
        List<TWorkArea> list = tWorkAreaService.selectTWorkAreaList(tWorkArea);
        ExcelUtil<TWorkArea> util = new ExcelUtil<TWorkArea>(TWorkArea.class);
        return util.exportExcel(list, "work_area");
    }

    /**
     * 获取工区信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tWorkAreaService.selectTWorkAreaById(id));
    }

    /**
     * 新增工区信息
     */
    @Log(title = "工区信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TWorkArea tWorkArea)
    {
        return toAjax(tWorkAreaService.insertTWorkArea(tWorkArea));
    }

    /**
     * 修改工区信息
     */
    @Log(title = "工区信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TWorkArea tWorkArea)
    {
        return toAjax(tWorkAreaService.updateTWorkArea(tWorkArea));
    }

    /**
     * 删除工区信息
     */
    @Log(title = "工区信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tWorkAreaService.deleteTWorkAreaByIds(ids));
    }
}
