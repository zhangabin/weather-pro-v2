package com.ruoyi.weather.sysmanagement.workareaforecast.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.weather.sysmanagement.workarea.domain.TWorkArea;
import com.ruoyi.weather.sysmanagement.workarea.service.ITWorkAreaService;
import com.ruoyi.weather.sysmanagement.workareaforecast.domain.WorkAreaForecast;
import com.ruoyi.weather.sysmanagement.workareaforecast.mapper.WorkAreaForecastMapper;
import com.ruoyi.weather.sysmanagement.workareaforecast.service.IWorkAreaForecastService;

/**
 * 工区预报数据Service业务层处理
 * 
 * @author Gaojide gaojide_109@163.com
 * @date 2021-03-29
 */
@Service
public class WorkAreaForecastServiceImpl implements IWorkAreaForecastService{
    @Autowired
    private WorkAreaForecastMapper workAreaForecastMapper;
    
    @Autowired
	private ITWorkAreaService tWorkAreaService;
    
    /** */
    private Map<Long,String> workAreaIdAndNameMap = null;
    
    /**
     * @title 查询工区预报数据
     * @param workAreaId 工区预报数据ID
     * @return 工区预报数据
     */
    @Override
    public Map<String,List<WorkAreaForecast>> selectWorkAreaForecastById(Long workAreaId){
    	if(null == workAreaIdAndNameMap) {
    		workAreaIdAndNameMap = workAreaIdAndNameMap();
    	} 
    	WorkAreaForecast workAreaForecast = new WorkAreaForecast();
    	workAreaForecast.setId(workAreaId);
    	workAreaForecast.setStartTime(DateUtils.parseDateToStr("yyyy-MM-dd 00:00:00", DateUtils.addDays(new Date(), 1)));
    	workAreaForecast.setEndTime(DateUtils.parseDateToStr("yyyy-MM-dd 23:00:00", DateUtils.addDays(new Date(), 3)));
    	List<WorkAreaForecast> areaForecasts = workAreaForecastMapper.selectWorkAreaForecastById(workAreaForecast);
    	//调整数据为服务要求的数据
    	areaForecasts.forEach(areaForecastsOne ->{
    		areaForecastsOne.setForecastHour(DateUtils.parseDateToStr("H时", areaForecastsOne.getDate_time()));
    		areaForecastsOne.setWorkName(workAreaIdAndNameMap.get(areaForecastsOne.getId()));
    		areaForecastsOne.setForecastDate(DateUtils.parseDateToStr("yyyy时M月d日", areaForecastsOne.getDate_time()));
    	});
    	
    	//开始进行按日预报日分组
    	//存储未来三天预报数据
    	Map<String,List<WorkAreaForecast>> forecastMap = new HashMap<String,List<WorkAreaForecast>>();
    	//存储未来第一天预报数据
    	List<WorkAreaForecast> forecastListFirstDAY = new ArrayList<WorkAreaForecast>();
    	//存储未来第二天预报数据
    	List<WorkAreaForecast> forecastListSecondDAY = new ArrayList<WorkAreaForecast>();
    	//存储未来第三天预报数据
    	List<WorkAreaForecast> forecastListThirdlyDAY = new ArrayList<WorkAreaForecast>();
    	
    	areaForecasts.forEach(areaForecastsOne ->{
    		if(DateUtils.parseDateToStr("yyyy-MM-dd", DateUtils.addDays(new Date(), 1)).equals(DateUtils.parseDateToStr("yyyy-MM-dd", areaForecastsOne.getDate_time()))) {
    			forecastListFirstDAY.add(areaForecastsOne);
    		}else if(DateUtils.parseDateToStr("yyyy-MM-dd", DateUtils.addDays(new Date(), 2)).equals(DateUtils.parseDateToStr("yyyy-MM-dd", areaForecastsOne.getDate_time()))) {
    			forecastListSecondDAY.add(areaForecastsOne);
    		}else if(DateUtils.parseDateToStr("yyyy-MM-dd", DateUtils.addDays(new Date(), 3)).equals(DateUtils.parseDateToStr("yyyy-MM-dd", areaForecastsOne.getDate_time()))) {
    			forecastListThirdlyDAY.add(areaForecastsOne);
    		}
    	});
    	forecastMap.put("firstDAY", forecastListFirstDAY);
    	forecastMap.put("secondDAY",forecastListSecondDAY);
    	forecastMap.put("thirdlyDAY",forecastListThirdlyDAY);
    	return forecastMap;
    }

    /**
     * @title 查询工区预报数据列表
     * @param workAreaForecast 工区预报数据
     * @return 工区预报数据
     */
    @Override
    public List<WorkAreaForecast> selectWorkAreaForecastList(WorkAreaForecast workAreaForecast){
        return workAreaForecastMapper.selectWorkAreaForecastList(workAreaForecast);
    }

    /**
     * @title  新增工区预报数据
     * @param workAreaForecast 工区预报数据
     * @return 结果
     */
    @Override
    public int insertWorkAreaForecast(WorkAreaForecast workAreaForecast){
        return workAreaForecastMapper.insertWorkAreaForecast(workAreaForecast);
    }
    
    /**
     * @title 批量插入
     * @author Gaojide gaojide_109@163.com
     * @date 2021-03-30
      */
     public int insertWorkAreaForecastList(List<WorkAreaForecast> workAreaForecastList) {
    	 return workAreaForecastMapper.insertWorkAreaForecastList(workAreaForecastList);
     }

    /**
     * @title 修改工区预报数据
     * @param workAreaForecast 工区预报数据
     * @return 结果
     */
    @Override
    public int updateWorkAreaForecast(WorkAreaForecast workAreaForecast){
        return workAreaForecastMapper.updateWorkAreaForecast(workAreaForecast);
    }

    /**
     * @title 批量删除工区预报数据
     * @param workAreaIds 需要删除的工区预报数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkAreaForecastByIds(Long[] workAreaIds){
        return workAreaForecastMapper.deleteWorkAreaForecastByIds(workAreaIds);
    }

    /**
     * @title 删除工区预报数据信息
     * @param workAreaId 工区预报数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkAreaForecastById(Long workAreaId){
        return workAreaForecastMapper.deleteWorkAreaForecastById(workAreaId);
    }
    
    /**
     * @title 根据到达时间删除数据
     * @author Gaojide gaojide_109@163.com
     * @date 2021-03-31
     */
    public int deleteWorkAreaForecastByStartTimes(String startDateTime) {
    	return workAreaForecastMapper.deleteWorkAreaForecastByStartTimes(startDateTime);
    }
    
    private  Map<Long,String> workAreaIdAndNameMap(){
    	List<TWorkArea> workAreaList =  tWorkAreaService.selectTWorkAreaList(null);
    	Map<Long,String> workAreaIdAndNameMap = new HashMap<Long, String>();
    	workAreaList.forEach(workAreaListOne ->{
    		workAreaIdAndNameMap.put(workAreaListOne.getId(), workAreaListOne.getWorkAreaName());
    	});
    	return workAreaIdAndNameMap;
    }
    
    /**
     * @title 根据线路和选择的预报日期查询数据,服务于车站预报的H5端
     * @author Gaojide gaojide_109@163.com
     * @date 2021-04-08
     */
     public Map<String,Object> getForecastByLineAndForecastDate(WorkAreaForecast workAreaForecast){
    	 if(workAreaForecast.getForecastDate() != null) {
    		 workAreaForecast.setStartTime(DateUtils.parseDateToStr("yyyy-MM-dd 00:00:00", DateUtils.parseDate(workAreaForecast.getForecastDate())));
        	 workAreaForecast.setEndTime(DateUtils.parseDateToStr("yyyy-MM-dd 23:00:00", DateUtils.parseDate(workAreaForecast.getForecastDate())));
    	 }else {
    		 workAreaForecast.setStartTime(DateUtils.parseDateToStr("yyyy-MM-dd 00:00:00", DateUtils.addDays(new Date(), 1)));
        	 workAreaForecast.setEndTime(DateUtils.parseDateToStr("yyyy-MM-dd 23:00:00",   DateUtils.addDays(new Date(), 1)));
    	 }
    	 List<WorkAreaForecast> areaForecasts = workAreaForecastMapper.getForecastByLineAndForecastDate(workAreaForecast);
     	 //调整数据为服务要求的数据
	     areaForecasts.forEach(areaForecastsOne ->{
	     	areaForecastsOne.setForecastHour(DateUtils.parseDateToStr("H时", areaForecastsOne.getDate_time()));
	     });
    	 Map<String,List<WorkAreaForecast>> areaForecastMap = areaForecasts.stream().collect(Collectors.groupingBy(b -> b.getWorkName()));
    	 //把未来三天的预报时间放在这里
    	 List<String> forecastDateStr = new ArrayList<String>();
    	 forecastDateStr.add(DateUtils.parseDateToStr("yyyy-MM-dd", DateUtils.addDays(new Date(), 1)));
    	 forecastDateStr.add(DateUtils.parseDateToStr("yyyy-MM-dd", DateUtils.addDays(new Date(), 2)));
    	 forecastDateStr.add(DateUtils.parseDateToStr("yyyy-MM-dd", DateUtils.addDays(new Date(), 3)));
    	 Map<String,Object> dataMap = new HashMap<String, Object>();
    	 dataMap.put("areaForecastMap", areaForecastMap);
    	 dataMap.put("forecastDateStr", forecastDateStr);
    	 return dataMap;
     }
}
