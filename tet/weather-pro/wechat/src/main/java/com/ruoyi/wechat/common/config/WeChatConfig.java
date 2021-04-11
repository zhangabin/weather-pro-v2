package com.ruoyi.wechat.common.config;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 企业微信配置类
 * @ClassName WeChatConfiger
 * @Author 1129017402@qq.com
 * @Date 2021/3/2 22:49
 */
@Component
@ForestScan(basePackages = {"com.ruoyi.wechat.api"})
@ConfigurationProperties(prefix = "wechat")
public class WeChatConfig {
    //企业Id
    private String corpId;
    //应用id
    private String corpSecret;
    //根部门id
    private Integer rootDeptId;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

    public Integer getRootDeptId() {
        return rootDeptId;
    }

    public void setRootDeptId(Integer rootDeptId) {
        this.rootDeptId = rootDeptId;
    }
}
