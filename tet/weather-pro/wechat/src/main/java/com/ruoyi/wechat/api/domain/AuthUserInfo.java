package com.ruoyi.wechat.api.domain;

/**
 * 登录授权时根据code获取到的用户信息
 * @ClassName AuthUserInfo
 * @Author 1129017402@qq.com
 * @Date 2021/3/4 15:14
 */
public class AuthUserInfo extends WeChatBaseResult {
    //用户ID
    private String userId;
    //手机设备号
    private String deviceId;
    //外部用户唯一id
    private String openId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
