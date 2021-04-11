package com.ruoyi.wechat.api.domain;

import java.util.List;

/**
 * 微信用户同步结果类
 * @ClassName WeChatUserSyncResult
 * @Author 1129017402@qq.com
 * @Date 2021/3/7 17:28
 */
public class WeChatUserSyncResult extends WeChatBaseResult{
    private List<WeChatUser> userList;

    public List<WeChatUser> getUserList() {
        return userList;
    }

    public void setUserList(List<WeChatUser> userList) {
        this.userList = userList;
    }
}
