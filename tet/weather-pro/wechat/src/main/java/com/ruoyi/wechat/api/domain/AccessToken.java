package com.ruoyi.wechat.api.domain;

/**
 * AccessToken
 * @ClassName AccessToken
 * @Author 1129017402@qq.com
 * @Date 2021/3/5 22:03
 */
public class AccessToken extends WeChatBaseResult {

    private String access_token;
    private String expires_in;


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString(){
        return access_token;
    }
}
