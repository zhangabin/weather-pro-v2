package com.ruoyi.wechat.api;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.GetRequest;
import com.ruoyi.wechat.api.domain.AccessToken;
import com.ruoyi.wechat.common.interceptor.WeChatApiInterceptor;

/**
 * @ClassName WeChatAccessTokenApi
 * @Author 1129017402@qq.com
 * @Date 2021/3/5 17:12
 */
@BaseRequest(baseURL = "https://qyapi.weixin.qq.com",interceptor = WeChatApiInterceptor.class)
public interface WeChatAccessTokenApi {
    /**
     * 获取AccessToken
     * @param corpId 企业id
     * @param corpSecret 应用id
     * @retuen {{@link String}} AccessToken
     * @author 1129017402@qq.com
     * @date 2021/3/5 21:39
     */
    @GetRequest(url = "/cgi-bin/gettoken?corpid=${corpId}&corpsecret=${corpSecret}",
                dataType = "json")
    AccessToken get(@DataVariable("corpId")String corpId, @DataVariable("corpSecret")String corpSecret);
}
