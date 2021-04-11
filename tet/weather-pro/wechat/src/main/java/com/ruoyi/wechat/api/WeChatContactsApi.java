package com.ruoyi.wechat.api;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Get;
import com.ruoyi.wechat.api.domain.WeChatDepartmentSyncResult;
import com.ruoyi.wechat.api.domain.WeChatUserSyncResult;
import com.ruoyi.wechat.common.interceptor.WeChatApiInterceptor;

/**
 * 企业微信通讯录API
 * @ClassName WeChatContactsAPI
 * @Author 1129017402@qq.com
 * @Date 2021/3/2 23:56
 */
@BaseRequest(baseURL = "https://qyapi.weixin.qq.com",interceptor = WeChatApiInterceptor.class)
public interface WeChatContactsApi {

    /**
     * 同步企业微信用户
     * @param accessToken Token
     * @param depId 部门ID
     * @retuen {{@link WeChatUserSyncResult}}
     * @author 1129017402@qq.com
     * @date 2021/3/7 17:05
     */
    @Get(url = "/cgi-bin/user/list?access_token=${accessToken}&department_id=${depId}&fetch_child=1",
         dataType = "json")
    WeChatUserSyncResult syncUsers(@DataVariable("accessToken")String accessToken, @DataVariable("depId")Integer depId);

    /**
     * 企业微信部门同步
     * @param accessToken 微信AccessToken
     * @param depId 根部门ID
     * @retuen {{@link WeChatDepartmentSyncResult}}
     * @author 1129017402@qq.com
     * @date 2021/3/13 14:26
     */
    @Get(url = "/cgi-bin/department/list?access_token=${accessToken}&id=${depId}",
         dataType = "json")
    WeChatDepartmentSyncResult syncDepartment(@DataVariable("accessToken")String accessToken, @DataVariable("depId")Integer depId);
}
