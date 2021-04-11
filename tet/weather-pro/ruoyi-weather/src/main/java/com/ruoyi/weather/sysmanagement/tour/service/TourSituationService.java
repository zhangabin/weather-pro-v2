package com.ruoyi.weather.sysmanagement.tour.service;

import java.util.List;
import com.ruoyi.weather.sysmanagement.tour.domain.TourSituation;

/**
 * 防洪值班记录Service接口
 * 
 * @author ruoyi
 * @date 2021-03-26
 */
public interface TourSituationService
{
    /**
     * 查询防洪值班记录
     * 
     * @param id 防洪值班记录ID
     * @return 防洪值班记录
     */
    public TourSituation selectTourSituationById(Long id);

    /**
     * 查询防洪值班记录列表
     * 
     * @param tourSituation 防洪值班记录
     * @return 防洪值班记录集合
     */
    public List<TourSituation> selectTourSituationList(TourSituation tourSituation);

    /**
     * 新增防洪值班记录
     * 
     * @param tourSituation 防洪值班记录
     * @return 结果
     */
    public int insertTourSituation(TourSituation tourSituation);

    /**
     * 修改防洪值班记录
     * 
     * @param tourSituation 防洪值班记录
     * @return 结果
     */
    public int updateTourSituation(TourSituation tourSituation);

    /**
     * 批量删除防洪值班记录
     * 
     * @param ids 需要删除的防洪值班记录ID
     * @return 结果
     */
    public int deleteTourSituationByIds(Long[] ids);

    /**
     * 删除防洪值班记录信息
     * 
     * @param id 防洪值班记录ID
     * @return 结果
     */
    public int deleteTourSituationById(Long id);
}
