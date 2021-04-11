package com.ruoyi.wechat.web.service;

/**
 * 企业微信登录Service接口
 * @ClassName WeChatLoginService
 * @Author 1129017402@qq.com
 * @Date 2021/3/3 0:07
 */
public interface WeChatLoginService {
    /**
     * 微信登录接口
     * @param userName 用户id
     * @retuen {{@link String}} token
     * @author 1129017402@qq.com
     * @date 2021/3/4 15:57
     */
    String login(String userName);
}
