package com.ruoyi.wechat.web.service.impl;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.wechat.api.WeChatAccessTokenApi;
import com.ruoyi.wechat.common.config.WeChatConfig;
import com.ruoyi.wechat.web.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 微信AccessToken
 * @ClassName AccessToken
 * @Author 1129017402@qq.com
 * @Date 2021/3/4 15:53
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private WeChatAccessTokenApi accessTokenApi;


    /**
     * 刷新AccessToken
     * @retuen {{@link String}} AccessToken
     * @author 1129017402@qq.com
     * @date 2021/3/5 17:20
     */
    @Override
    public String refreshAccessToken(){
        String accessToken = accessTokenApi.get(weChatConfig.getCorpId(), weChatConfig.getCorpSecret()).toString();
        redisCache.setCacheObject(ACCESS_TOKEN, accessToken, 7200, TimeUnit.SECONDS);
        return accessToken;
    }

    /**
     * 获取AccessToken
     * @retuen {{@link String}} AccessToken
     * @author 1129017402@qq.com
     * @date 2021/3/5 17:20
     */
    @Override
    public String getAccessToken(){
        String accessToken = redisCache.getCacheObject(ACCESS_TOKEN);
        if(StringUtils.isEmpty(accessToken)){
            accessToken = refreshAccessToken();
        }
        return accessToken;
    }
}
