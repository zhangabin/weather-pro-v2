package com.ruoyi.wechat.web.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.wechat.api.WeChatUserApi;
import com.ruoyi.wechat.api.domain.AuthUserInfo;
import com.ruoyi.wechat.web.service.AccessTokenService;
import com.ruoyi.wechat.web.service.WeChatLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 企业微信登录控制器
 * @ClassName WechatLoginController
 * @Author 1129017402@qq.com
 * @Date 2021/3/3 0:05
 */
@RestController
@RequestMapping("/weChatLogin")
public class WechatLoginController {
    @Resource
    private WeChatUserApi weChatUserApi;

    @Resource
    private WeChatLoginService weChatLoginService;

    @Autowired
    private AccessTokenService accessTokenService;

    @PostMapping
    public AjaxResult login(@RequestBody String code){
        AjaxResult ajaxResult = AjaxResult.success();
        AuthUserInfo userInfo = weChatUserApi.authentication(accessTokenService.getAccessToken(), code);
        String token = weChatLoginService.login(userInfo.getUserId());
        ajaxResult.put(Constants.TOKEN, token);
        return ajaxResult;
    }

}
