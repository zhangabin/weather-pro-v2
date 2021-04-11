package com.ruoyi.weather.sysmanagement.alarmtype.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.weather.sysmanagement.alarmtype.domain.AlarmType;
import com.ruoyi.weather.sysmanagement.alarmtype.service.AlarmTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 告警类型Controller
 * 
 * @author yyearn
 * @date 2021-03-23
 */
@RestController
@RequestMapping("/alarmType")
public class AlarmTypeController extends BaseController
{
    @Autowired
    private AlarmTypeService alarmTypeService;

    /**
     * 查询告警类型
     */
    @GetMapping("/{state}")
    public AjaxResult getAlarmTypes(String state)
    {
        List<AlarmType> alarmTypes = alarmTypeService.getAlarmTypes(state);
        return AjaxResult.success(alarmTypes);
    }

}
