package com.ruoyi.weather.sysmanagement.tour.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.EasyPoiUtil;
import com.ruoyi.weather.sysmanagement.tour.domain.TourSituation;
import com.ruoyi.weather.sysmanagement.tour.service.TourSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 防洪值班记录Controller
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
@RestController
@RequestMapping("/situation/situation")
public class TourSituationController extends BaseController
{
    @Autowired
    private TourSituationService tourSituationService;

    /**
     * 查询防洪值班记录列表
     */
    @PreAuthorize("@ss.hasPermi('situation:situation:list')")
    @GetMapping("/list")
    public TableDataInfo list(TourSituation tourSituation)
    {
        startPage();
        List<TourSituation> list = tourSituationService.selectTourSituationList(tourSituation);
        return getDataTable(list);
    }

    /**
     * 导出防洪值班记录列表
     */
    @PreAuthorize("@ss.hasPermi('situation:situation:export')")
    @Log(title = "防洪值班记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TourSituation tourSituation)
    {
        List<TourSituation> list = tourSituationService.selectTourSituationList(tourSituation);


        String templateUrl = "WEB-INF/fileTemplate/template.xls";
        String fileName = "导出文件";
        HashMap<String, Object> dataMap = new HashMap<>();
        ArrayList<HashMap<String, Object>> tourList = new ArrayList<>();
        int num = 1;
        for (TourSituation situation : list) {
            HashMap<String, Object> obj = new HashMap<>();
            obj.put("num",num );
            String recordTimeStr = DateUtil.format(situation.getRecordTime(), "yyyy-MM-dd HH:mm");
            obj.put("recordTime",recordTimeStr );
            obj.put("dutyOfficer",situation.getDutyOfficer() );
            obj.put("rainfall",situation.getRainfall() );
            String alarmLevel = situation.getAlarmLevel();
            String alarmLevelStr;
            switch (alarmLevel){
                case "1":
                    alarmLevelStr = "出巡";
                    break;
                case "2":
                    alarmLevelStr = "限速";
                    break;
                case "3":
                    alarmLevelStr = "封锁";
                    break;
                default:
                    alarmLevelStr = "空";
                    break;
            }
            obj.put("alarmLevel",alarmLevelStr);
            String inspectionTimeStartStr = DateUtil.format(situation.getInspectionTimeStart(), "yyyy-MM-dd HH:mm");
            String inspectionTimeEndStr = DateUtil.format(situation.getInspectionTimeEnd(), "yyyy-MM-dd HH:mm");
            obj.put("inspectionTime",inspectionTimeStartStr+"-"+inspectionTimeEndStr);
            obj.put("inspectionLot",situation.getInspectionLot() );
            String inspectionType = situation.getInspectionType();
            ArrayList<String> inspectionTypeStrList = new ArrayList<>();
            for (String s : inspectionType.split(",")) {
                if("a".equals(s)){
                    inspectionTypeStrList.add("人工");
                }
                if("b".equals(s)){
                    inspectionTypeStrList.add("巡查车");
                }

            }
            obj.put("inspectionType", CollUtil.join(inspectionTypeStrList, ","));
            obj.put("inspectorsVehicles",situation.getInspectorsVehicles() );
            obj.put("inspectionHiddenDangers",situation.getInspectionHiddenDangers() );
            obj.put("disposalMeasures",situation.getDisposalMeasures() );

            tourList.add(obj);
            num++;
        }
        dataMap.put("tourList",tourList);
        return EasyPoiUtil.export(templateUrl,fileName,dataMap);
    }

    /**
     * 获取防洪值班记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('situation:situation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tourSituationService.selectTourSituationById(id));
    }

    /**
     * 新增防洪值班记录
     */
    @PreAuthorize("@ss.hasPermi('situation:situation:add')")
    @Log(title = "防洪值班记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TourSituation tourSituation)
    {
        return toAjax(tourSituationService.insertTourSituation(tourSituation));
    }

    /**
     * 修改防洪值班记录
     */
    @PreAuthorize("@ss.hasPermi('situation:situation:edit')")
    @Log(title = "防洪值班记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TourSituation tourSituation)
    {
        return toAjax(tourSituationService.updateTourSituation(tourSituation));
    }

    /**
     * 删除防洪值班记录
     */
    @PreAuthorize("@ss.hasPermi('situation:situation:remove')")
    @Log(title = "防洪值班记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tourSituationService.deleteTourSituationByIds(ids));
    }
}
