package com.ruoyi.wechat.schedule;

import com.ruoyi.wechat.api.WeChatContactsApi;
import com.ruoyi.wechat.api.domain.WeChatDepartment;
import com.ruoyi.wechat.api.domain.WeChatDepartmentSyncResult;
import com.ruoyi.wechat.common.config.WeChatConfig;
import com.ruoyi.wechat.web.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 企业微信通讯录同步定时任务
 * @ClassName ContactsAsyncSchedule
 * @Author 1129017402@qq.com
 * @Date 2021/3/13 14:33
 */
@Component
@EnableScheduling
public class ContactsAsyncSchedule {
    
    @Autowired
    private WeChatContactsApi contactsApi;
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private WeChatConfig weChatConfig;

    /**
     * 企业微信部门同步
     * @author 1129017402@qq.com
     * @date 2021/3/13 15:12
     */
    //@Scheduled(fixedRate = 3600000)
    private void departmentAsync(){
        WeChatDepartmentSyncResult result = contactsApi.syncDepartment(accessTokenService.getAccessToken(),
                weChatConfig.getRootDeptId());

        List<WeChatDepartment> weChatDept = result.getDepartment();

    }


    /**
     * 企业微信用户同步
     * @author 1129017402@qq.com
     * @date 2021/3/13 15:12
     */
    //@Scheduled(fixedRate = 3600000)
    private void userAsync(){

    }
}
