package com.ruoyi.weather.sysmanagement.tour.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.weather.sysmanagement.tour.domain.TourSituation;
import com.ruoyi.weather.sysmanagement.tour.mapper.TourSituationMapper;
import com.ruoyi.weather.sysmanagement.tour.service.TourSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 防洪值班记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-26
 */

@Service
public class TourSituationServiceImpl implements TourSituationService
{
    @Autowired
    private TourSituationMapper tourSituationMapper;

    /**
     * 查询防洪值班记录
     * 
     * @param id 防洪值班记录ID
     * @return 防洪值班记录
     */
    @Override
    public TourSituation selectTourSituationById(Long id)
    {
        return tourSituationMapper.selectTourSituationById(id);
    }

    /**
     * 查询防洪值班记录列表
     * 
     * @param tourSituation 防洪值班记录
     * @return 防洪值班记录
     */
    @Override
    public List<TourSituation> selectTourSituationList(TourSituation tourSituation)
    {
        return tourSituationMapper.selectTourSituationList(tourSituation);
    }

    /**
     * 新增防洪值班记录
     * 
     * @param tourSituation 防洪值班记录
     * @return 结果
     */
    @Override
    public int insertTourSituation(TourSituation tourSituation)
    {
        tourSituation.setCreateTime(DateUtils.getNowDate());
        tourSituation.setRecordTime(DateUtils.getNowDate());
        tourSituation.setCreateUser("test");
        return tourSituationMapper.insertTourSituation(tourSituation);
    }

    /**
     * 修改防洪值班记录
     * 
     * @param tourSituation 防洪值班记录
     * @return 结果
     */
    @Override
    public int updateTourSituation(TourSituation tourSituation)
    {
        tourSituation.setUpdateTime(DateUtils.getNowDate());
        return tourSituationMapper.updateTourSituation(tourSituation);
    }

    /**
     * 批量删除防洪值班记录
     * 
     * @param ids 需要删除的防洪值班记录ID
     * @return 结果
     */
    @Override
    public int deleteTourSituationByIds(Long[] ids)
    {
        return tourSituationMapper.deleteTourSituationByIds(ids);
    }

    /**
     * 删除防洪值班记录信息
     * 
     * @param id 防洪值班记录ID
     * @return 结果
     */
    @Override
    public int deleteTourSituationById(Long id)
    {
        return tourSituationMapper.deleteTourSituationById(id);
    }
}
