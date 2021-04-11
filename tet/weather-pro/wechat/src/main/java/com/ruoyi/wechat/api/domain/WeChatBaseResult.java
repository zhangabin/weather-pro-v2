package com.ruoyi.wechat.api.domain;

/**
 * @ClassName WeChatBaseResponse
 * @Author 1129017402@qq.com
 * @Date 2021/3/5 23:29
 */
public class WeChatBaseResult {
    //错误代码
    private Integer errcode;
    //错误信息
    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
