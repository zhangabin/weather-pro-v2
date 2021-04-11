package com.ruoyi.weather.sysmanagement.forecast.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.weather.sysmanagement.forecast.domain.TrendForecast;
import com.ruoyi.weather.sysmanagement.forecast.mapper.TrendForecastMapper;
import com.ruoyi.weather.sysmanagement.forecast.service.ITrendForecastService;

/**
 * @title 趋势预报管理Service业务层处理
 * @author Gaojide gaojide_109@163.com
 * @date 2021-03-19
 */
@Service
public class TrendForecastServiceImpl implements ITrendForecastService {
    @Autowired
    private TrendForecastMapper trendForecastMapper;
    
    @Autowired
    private TokenService tokenService;

    /**
     * @title 查询趋势预报管理
     * @param id 趋势预报管理ID
     * @return 趋势预报管理
     */
    @Override
    public TrendForecast selectTrendForecastById(Long id) {
        return trendForecastMapper.selectTrendForecastById(id);
    }

    /**
     * @title 查询趋势预报管理列表
     * @param trendForecast 趋势预报管理
     * @return 趋势预报管理
     */
    @Override
    public List<TrendForecast> selectTrendForecastList(TrendForecast trendForecast){   
    	//调整前端的上传时间范围
    	Map<String,Object> uploadTimeParamMap = trendForecast.getParams();
    	if(uploadTimeParamMap != null  && uploadTimeParamMap.size() > 0) {
    		String beginUploadTime = DateUtils.parseDateToStr("yyyy-MM-dd 00:00:00",DateUtils.parseDate(uploadTimeParamMap.get("beginUploadTime")));
    		String endUploadTime = DateUtils.parseDateToStr("yyyy-MM-dd 23:59:59",DateUtils.parseDate(uploadTimeParamMap.get("endUploadTime")));
    		uploadTimeParamMap.put("beginUploadTime", beginUploadTime);
    		uploadTimeParamMap.put("endUploadTime", endUploadTime);
    		trendForecast.setParams(uploadTimeParamMap);
    	}
    	//设置填写48-96小时预报 时默认的预报值
    	List<TrendForecast> forecasts = trendForecastMapper.selectTrendForecastList(trendForecast);
    	forecasts.get(0).setForecast96DefaultValue("<p><strong>"+DateUtils.parseDateToStr("M月d日", DateUtils.addHours(new Date(), 48))+"-"+DateUtils.parseDateToStr("M月d日", DateUtils.addHours(new Date(), 96))+"天气预报 </strong></p>");
        return forecasts;
    }

    /**
     * @title 新增趋势预报管理
     * @param trendForecast 趋势预报管理
     * @return 结果
     */
    @Override
    public int insertTrendForecast(TrendForecast trendForecast){
    	LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
	    SysUser user = loginUser.getUser();
	    trendForecast.setUploadUser(user.getNickName());
        return trendForecastMapper.insertTrendForecast(trendForecast);
    }

    /**
     * @title 修改趋势预报管理
     * @param trendForecast 趋势预报管理
     * @return 结果
     */
    @Override
    public int updateTrendForecast(TrendForecast trendForecast){
        return trendForecastMapper.updateTrendForecast(trendForecast);
    }

    /**
     * @title 批量删除趋势预报管理
     * @param ids 需要删除的趋势预报管理ID
     * @return 结果
     */
    @Override
    public int deleteTrendForecastByIds(Long[] ids){
        return trendForecastMapper.deleteTrendForecastByIds(ids);
    }

    /**
     * @title 删除趋势预报管理信息
     * @param id 趋势预报管理ID
     * @return 结果
     */
    @Override
    public int deleteTrendForecastById(Long id){
        return trendForecastMapper.deleteTrendForecastById(id);
    }
}
