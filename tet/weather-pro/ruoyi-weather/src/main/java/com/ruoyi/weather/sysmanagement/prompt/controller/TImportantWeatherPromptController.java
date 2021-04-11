package com.ruoyi.weather.sysmanagement.prompt.controller;

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
import com.ruoyi.weather.sysmanagement.prompt.domain.TImportantWeatherPrompt;
import com.ruoyi.weather.sysmanagement.prompt.service.ITImportantWeatherPromptService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 重要天气提示Controller
 * 
 * @author yyearn
 * @date 2021-03-18
 */
@RestController
@RequestMapping("/prompt/tImportantWeatherPrompt")
public class TImportantWeatherPromptController extends BaseController
{
    @Autowired
    private ITImportantWeatherPromptService tImportantWeatherPromptService;

    /**
     * 查询重要天气提示列表
     */
    @PreAuthorize("@ss.hasPermi('prompt:tImportantWeatherPrompt:list')")
    @GetMapping("/list")
    public TableDataInfo list(TImportantWeatherPrompt tImportantWeatherPrompt)
    {
        startPage();
        List<TImportantWeatherPrompt> list = tImportantWeatherPromptService.selectTImportantWeatherPromptList(tImportantWeatherPrompt);
        return getDataTable(list);
    }

    /**
     * 导出重要天气提示列表
     */
    @PreAuthorize("@ss.hasPermi('prompt:tImportantWeatherPrompt:export')")
    @Log(title = "重要天气提示", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TImportantWeatherPrompt tImportantWeatherPrompt)
    {
        List<TImportantWeatherPrompt> list = tImportantWeatherPromptService.selectTImportantWeatherPromptList(tImportantWeatherPrompt);
        ExcelUtil<TImportantWeatherPrompt> util = new ExcelUtil<TImportantWeatherPrompt>(TImportantWeatherPrompt.class);
        return util.exportExcel(list, "tImportantWeatherPrompt");
    }

    /**
     * 获取重要天气提示详细信息
     */
    @PreAuthorize("@ss.hasPermi('prompt:tImportantWeatherPrompt:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tImportantWeatherPromptService.selectTImportantWeatherPromptById(id));
    }

    /**
     * 新增重要天气提示
     */
    @PreAuthorize("@ss.hasPermi('prompt:tImportantWeatherPrompt:add')")
    @Log(title = "重要天气提示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TImportantWeatherPrompt tImportantWeatherPrompt)
    {
        return toAjax(tImportantWeatherPromptService.insertTImportantWeatherPrompt(tImportantWeatherPrompt));
    }

    /**
     * 修改重要天气提示
     */
    @PreAuthorize("@ss.hasPermi('prompt:tImportantWeatherPrompt:edit')")
    @Log(title = "重要天气提示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TImportantWeatherPrompt tImportantWeatherPrompt)
    {
        return toAjax(tImportantWeatherPromptService.updateTImportantWeatherPrompt(tImportantWeatherPrompt));
    }

    /**
     * 删除重要天气提示
     */
    @PreAuthorize("@ss.hasPermi('prompt:tImportantWeatherPrompt:remove')")
    @Log(title = "重要天气提示", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tImportantWeatherPromptService.deleteTImportantWeatherPromptByIds(ids));
    }
}
