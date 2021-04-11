package com.ruoyi.weather.sysmanagement.prompt.mapper;

import java.util.List;
import com.ruoyi.weather.sysmanagement.prompt.domain.TImportantWeatherPrompt;
import org.springframework.stereotype.Component;

/**
 * 重要天气提示Mapper接口
 * 
 * @author yyearn
 * @date 2021-03-18
 */
@Component
public interface TImportantWeatherPromptMapper 
{
    /**
     * 查询重要天气提示
     * 
     * @param id 重要天气提示ID
     * @return 重要天气提示
     */
    public TImportantWeatherPrompt selectTImportantWeatherPromptById(Long id);

    /**
     * 查询重要天气提示列表
     * 
     * @param tImportantWeatherPrompt 重要天气提示
     * @return 重要天气提示集合
     */
    public List<TImportantWeatherPrompt> selectTImportantWeatherPromptList(TImportantWeatherPrompt tImportantWeatherPrompt);

    /**
     * 新增重要天气提示
     * 
     * @param tImportantWeatherPrompt 重要天气提示
     * @return 结果
     */
    public int insertTImportantWeatherPrompt(TImportantWeatherPrompt tImportantWeatherPrompt);

    /**
     * 修改重要天气提示
     * 
     * @param tImportantWeatherPrompt 重要天气提示
     * @return 结果
     */
    public int updateTImportantWeatherPrompt(TImportantWeatherPrompt tImportantWeatherPrompt);

    /**
     * 删除重要天气提示
     * 
     * @param id 重要天气提示ID
     * @return 结果
     */
    public int deleteTImportantWeatherPromptById(Long id);

    /**
     * 批量删除重要天气提示
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTImportantWeatherPromptByIds(Long[] ids);
}
