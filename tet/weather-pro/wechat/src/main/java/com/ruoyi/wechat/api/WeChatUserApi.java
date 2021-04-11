package com.ruoyi.wechat.api;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.GetRequest;
import com.ruoyi.wechat.api.domain.AuthUserInfo;
import com.ruoyi.wechat.common.interceptor.WeChatApiInterceptor;

/**
 * 企业微信用户API
 * @ClassName WeChatApi
 * @Author 1129017402@qq.com
 * @Date 2021/3/2 23:52
 */
@BaseRequest(baseURL = "https://qyapi.weixin.qq.com",interceptor = WeChatApiInterceptor.class)
public interface WeChatUserApi {
    @GetRequest(url = "/cgi-bin/user/getuserinfo?access_token=${accessToken}&code=${code}",
                dataType = "json")
    AuthUserInfo authentication(@DataVariable("accessToken")String accessToken, @DataVariable("code")String code);
}
