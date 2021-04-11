package com.ruoyi.wechat.web.service;

/**
 * 负责获取、刷新AccessToken
 * @ClassName AccessTokenService
 * @Author 1129017402@qq.com
 * @Date 2021/3/6 9:34
 */
public interface AccessTokenService {
    //AccessToken缓存名
    String ACCESS_TOKEN = "WE_CHAT_ACCESS_TOKEN";

    /**
     * 刷新AccessToken
     * @retuen {{@link String}} AccessToken
     * @author 1129017402@qq.com
     * @date 2021/3/5 17:20
     */
    String refreshAccessToken();

    /**
     * 获取AccessToken
     * @retuen {{@link String}} AccessToken
     * @author 1129017402@qq.com
     * @date 2021/3/5 17:20
     */
    String getAccessToken();
}
