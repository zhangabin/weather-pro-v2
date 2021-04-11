package com.ruoyi.weather.sysmanagement.prompt.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.weather.sysmanagement.prompt.mapper.TImportantWeatherPromptMapper;
import com.ruoyi.weather.sysmanagement.prompt.domain.TImportantWeatherPrompt;
import com.ruoyi.weather.sysmanagement.prompt.service.ITImportantWeatherPromptService;

/**
 * 重要天气提示Service业务层处理
 * 
 * @author yyearn
 * @date 2021-03-18
 */
@Service
public class TImportantWeatherPromptServiceImpl implements ITImportantWeatherPromptService 
{
    @Autowired
    private TImportantWeatherPromptMapper tImportantWeatherPromptMapper;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询重要天气提示
     * 
     * @param id 重要天气提示ID
     * @return 重要天气提示
     */
    @Override
    public TImportantWeatherPrompt selectTImportantWeatherPromptById(Long id)
    {
        return tImportantWeatherPromptMapper.selectTImportantWeatherPromptById(id);
    }

    /**
     * 查询重要天气提示列表
     * 
     * @param tImportantWeatherPrompt 重要天气提示
     * @return 重要天气提示
     */
    @Override
    public List<TImportantWeatherPrompt> selectTImportantWeatherPromptList(TImportantWeatherPrompt tImportantWeatherPrompt)
    {
        return tImportantWeatherPromptMapper.selectTImportantWeatherPromptList(tImportantWeatherPrompt);
    }

    /**
     * 新增重要天气提示
     * 
     * @param tImportantWeatherPrompt 重要天气提示
     * @return 结果
     */
    @Override
    public int insertTImportantWeatherPrompt(TImportantWeatherPrompt tImportantWeatherPrompt)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        tImportantWeatherPrompt.setUploadUser(user.getNickName());
        tImportantWeatherPrompt.setUploadTime(DateUtils.getNowDate());
        tImportantWeatherPrompt.setState("1");
        return tImportantWeatherPromptMapper.insertTImportantWeatherPrompt(tImportantWeatherPrompt);
    }

    /**
     * 修改重要天气提示
     * 
     * @param tImportantWeatherPrompt 重要天气提示
     * @return 结果
     */
    @Override
    public int updateTImportantWeatherPrompt(TImportantWeatherPrompt tImportantWeatherPrompt)
    {
        return tImportantWeatherPromptMapper.updateTImportantWeatherPrompt(tImportantWeatherPrompt);
    }

    /**
     * 批量删除重要天气提示
     * 
     * @param ids 需要删除的重要天气提示ID
     * @return 结果
     */
    @Override
    public int deleteTImportantWeatherPromptByIds(Long[] ids)
    {
        return tImportantWeatherPromptMapper.deleteTImportantWeatherPromptByIds(ids);
    }

    /**
     * 删除重要天气提示信息
     * 
     * @param id 重要天气提示ID
     * @return 结果
     */
    @Override
    public int deleteTImportantWeatherPromptById(Long id)
    {
        return tImportantWeatherPromptMapper.deleteTImportantWeatherPromptById(id);
    }
}
