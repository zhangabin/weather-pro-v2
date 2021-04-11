package com.ruoyi.wechat.api.domain;

import java.util.List;

/**
 * @ClassName WeChatDepartmentSyncResult
 * @Author 1129017402@qq.com
 * @Date 2021/3/7 19:27
 */
public class WeChatDepartmentSyncResult extends WeChatBaseResult{
    private List<WeChatDepartment> department;

    public List<WeChatDepartment> getDepartment() {
        return department;
    }

    public void setDepartment(List<WeChatDepartment> department) {
        this.department = department;
    }
}
